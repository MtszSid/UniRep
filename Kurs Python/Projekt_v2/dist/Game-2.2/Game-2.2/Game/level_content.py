import os
import sys
import time
from math import floor
from typing import Optional

import pyglet.text
from pyglet import image

from Game.coin import Coin
from Game.collectable import Collectable
from Game.entity import Entity
from Game.evil_entities import BasicEvilEntity, EvilEntity
from Game.platform import Platform, PlatformEffect
from Game.player import Player
from Game.star import Star
from Game.static_platform import BasicPlatform, KillPlatform, JumpPlatform, NoJumpPlatform, WinPlatform


def accelerate(accel: int, dt: float, init_speed: int, entity: Entity) -> int:
    """
    Given acceleration, time, and initial speed computes new speed value.
    :param accel: acceleration
    :param dt: time
    :param init_speed: initial velocity
    :param entity: an entity
    :return: new velocity value
    """
    speed = 0
    if accel == 0 and init_speed > 0:
        speed = max(0, int(init_speed - entity.max_acceleration * dt))
    elif accel == 0 and init_speed < 0:
        speed = min(0, int(init_speed + entity.max_acceleration * dt))
    elif accel > 0:
        speed = min(entity.max_velocity, int(init_speed + accel * dt))
    elif accel < 0:
        speed = max(-1 * entity.max_velocity, floor(init_speed + accel * dt))
    return speed


class Level:
    """
    class representing level content.
    """
    height: int
    width: int
    layout: Optional[list[list[Optional[Platform]]]]
    collectables: Optional[dict[tuple[int, int], Collectable]]
    entities: Optional[set[EvilEntity]]

    tile_height = 32

    def __init__(self, width: int, height: int, background: str):
        self.width = width
        self.height = height
        self.background_image = image.load(background)
        self.background = self.background_image.get_region(0, 0, width, height)
        self.player = Player(0, 0)
        self.current_beg_horizontal = 0
        self.current_beg_vertical = 0
        self.height_in_platforms = 0
        self.width_in_platforms = 0
        self.collectables = None
        self.layout = None
        self.start = time.time()
        self.time = self.start
        self.entities = None
        self.score_label = pyglet.text.Label("Points: 0", x=int(8 / 10 * width), y=int(9 / 10 * height))
        self.time_label = pyglet.text.Label("Time: 0 s", x=int(8 / 10 * width), y=int(9 / 10 * height - 32))
        self.locked = False
        self.win = False
        self.end = False
        self.initialized = False

    def conversion(self, char: str, cell_x: int, cell_y: int) -> Optional[Platform]:
        """
        Converts given argument to relevant platform, entity or collectable type.
        :param char: argument
        :param cell_x: cell horizontal number
        :param cell_y: cell vertical number
        :return: Platform or None
        """
        if char == ".":
            return None
        elif char == "1":
            self.collectables[(cell_x, cell_y)] = Coin(1, "Game/Assets/Coin_1.png")
        elif char == "5":
            self.collectables[(cell_x, cell_y)] = Coin(5, "Game/Assets/Coin_5.png")
        elif char == "#":
            self.collectables[(cell_x, cell_y)] = Coin(10, "Game/Assets/Coin_10.png")
        elif char == "$":
            self.collectables[(cell_x, cell_y)] = Coin(20, "Game/Assets/Coin_20.png")
        elif char == "%":
            self.collectables[(cell_x, cell_y)] = Coin(50, "Game/Assets/Coin_50.png")
        elif char == "*":
            self.collectables[(cell_x, cell_y)] = Star("Game/Assets/Star.png")
        elif char == "a":
            self.entities.add(BasicEvilEntity(cell_x * Level.tile_height, cell_y * Level.tile_height + 10))
        elif char == "g":
            return BasicPlatform(cell_x * Level.tile_height, cell_y * Level.tile_height, "Game/Assets/grass_2.png")
        elif char == "b":
            return BasicPlatform(cell_x * Level.tile_height, cell_y * Level.tile_height, "Game/Assets/Brick.png")
        elif char == "x":
            return KillPlatform(cell_x * Level.tile_height, cell_y * Level.tile_height, "Game/Assets/kill_platform.png")
        elif char == "j":
            return JumpPlatform(cell_x * Level.tile_height, cell_y * Level.tile_height, "Game/Assets/jump_platform.png")
        elif char == "w":
            return WinPlatform(cell_x * Level.tile_height, cell_y * Level.tile_height, "Game/Assets/win_platform.png")
        elif char == "n":
            return NoJumpPlatform(cell_x * Level.tile_height, cell_y * Level.tile_height,
                                  "Game/Assets/no_jump_platform.png")
        elif char == "p":
            self.player = Player(cell_x * Level.tile_height + 10, cell_y * Level.tile_height + 10)
        return None

    def initialize(self, level_layout: str) -> None:
        """
        Initializes created level.
        :param level_layout: level layout to convert.
        :return:
        """
        lvl = level_layout.split("\n")
        height = len(lvl)

        self.current_beg_horizontal = 0
        self.current_beg_vertical = 0
        self.height_in_platforms = 0
        self.width_in_platforms = 0
        self.locked = False
        self.win = False

        self.height_in_platforms = height
        self.width_in_platforms = len(lvl[0])
        self.collectables = {}
        self.entities = set()
        self.layout = [[self.conversion(lvl[i][j], j, height - i - 1)
                        for j in range(len(lvl[i]))] for i in range(height)]
        self.initialized = True

    def draw(self) -> None:
        """
        Draws elements of object on Canvas.
        :return:
        """
        self.background.blit(0, 0)

        horizontal_tiles = min(self.width_in_platforms, self.width // Level.tile_height + 1)
        vertical_tiles = min(self.height_in_platforms, self.height // Level.tile_height + 1)
        width = self.current_beg_horizontal // Level.tile_height
        height = self.current_beg_vertical // Level.tile_height

        for i in range(horizontal_tiles + 1):
            for j in range(vertical_tiles + 1):
                tile_y = self.height_in_platforms - 1 - (height + j)
                tile_x = i + width - 1
                if tile_y >= self.height_in_platforms \
                        or tile_x >= self.width_in_platforms:
                    continue

                tile = self.layout[tile_y][tile_x]

                collect = self.collectables.get((i + width - 1, height + j))
                if tile is not None:
                    tile.draw(
                        tile.x - self.current_beg_horizontal,
                        tile.y - self.current_beg_vertical
                    )

                if collect is not None:
                    collect.draw(
                        (i + width - 1) * Level.tile_height - self.current_beg_horizontal,
                        (height + j) * Level.tile_height - self.current_beg_vertical
                    )

        for i in self.entities:
            i.draw(self.current_beg_horizontal, self.current_beg_vertical)

        self.player.draw(self.current_beg_horizontal, self.current_beg_vertical)
        self.score_label.draw()
        self.time_label.draw()

        x_hearths = int(1 / 10 * self.width)
        x_stars = int(4.5 / 10 * self.width)
        y = int((9 / 10) * self.height)
        hearths = self.player.get_hearths()
        stars = self.player.get_stars()

        for i in range(3):
            hearths[i].blit(x_hearths + Level.tile_height * i, y)
        for i in range(3):
            stars[i].blit(x_stars + Level.tile_height * i, y)

    def update(self, dt: float) -> None:
        """
        Updates current state of level after dt amount of time.
        :param dt: Time interval
        :return:
        """
        self.time = int(time.time() - self.start)
        self.time_label.text = "Time: " + str(self.time) + " s"

        if self.player.lives <= 0:
            self.locked = True

        for i in self.entities:
            self.update_entity_position(dt, i)

        if self.locked:
            return

        pre_update_x = self.player.x
        pre_update_y = self.player.y
        self.update_player_position(dt, self.player)
        self.interact_with_platform()
        self.interact_with_enemies(pre_update_x, pre_update_y)
        self.entities.difference_update({i for i in self.entities if i.alive is False})
        self.collect_points()
        self.move_point_of_view()

    def move_point_of_view(self) -> None:
        """
        Ensures proper rendering of the level in such manner, that player is always  in the field of view.
        Ensures background movement.
        :return:
        """
        back_beg_x = min(self.player.x // 20, 2000 - self.width)
        back_beg_y = min(self.player.y // 20, 2000 - self.height)

        if self.player.x - self.current_beg_horizontal > (2 * self.width // 3) \
                and self.player.x - 2 * self.width // 3 + self.width \
                <= self.width_in_platforms * Level.tile_height:
            self.current_beg_horizontal = self.player.x - 2 * self.width // 3
        elif self.player.x - self.current_beg_horizontal < (self.width // 4) \
                and self.player.x - self.width // 4 > 1:
            self.current_beg_horizontal = self.player.x - self.width // 4

        if self.player.y - self.current_beg_vertical > (2 * self.height // 3) \
                and self.player.y - 2 * self.height // 3 + self.height \
                <= (self.height_in_platforms + 3) * Level.tile_height:
            self.current_beg_vertical = self.player.y - 2 * self.height // 3
        elif self.player.y - self.current_beg_vertical < (self.height // 4) \
                and self.player.y - self.height // 4 > 1:
            self.current_beg_vertical = self.player.y - self.height // 4

        self.background = self.background_image.get_region(back_beg_x, back_beg_y,
                                                           back_beg_x + self.width,
                                                           back_beg_y + self.height)

    def collect_points(self) -> None:
        """
        Tries to collect collectables at current player position and updates player score accordingly.
        :return:
        """
        for i in (self.player.x // Level.tile_height, (self.player.x + self.player.width) // Level.tile_height):
            for j in (self.player.y // Level.tile_height, (self.player.y + self.player.height) // Level.tile_height):
                if self.collectables.get((i, j)) is not None:
                    collect = self.collectables.pop((i, j))
                    if isinstance(collect, Coin):
                        self.player.add_points(collect.value)
                    elif isinstance(collect, Star):
                        self.player.stars += 1
                        self.player.add_points(200)
        self.score_label.text = "Points:" + str(self.player.points)

    def update_player_position(self, dt, entity: Entity) -> None:
        """
        Updates player position.
        :param dt: time interval
        :param entity: player
        :return:
        """
        dist_horizontal, dist_vertical = self.new_position(dt, entity)

        if dist_horizontal < 0:
            self.player.x = Level.tile_height + 10
        elif dist_horizontal >= (self.width_in_platforms - 1) * Level.tile_height:
            self.player.x = (self.width_in_platforms - 1) * Level.tile_height
        else:
            self.player.x = dist_horizontal
        if dist_vertical < 0:
            self.player.restart()
            self.current_beg_horizontal = 0
            self.current_beg_vertical = 0
        elif dist_vertical >= (self.height_in_platforms + 3) * Level.tile_height:
            self.player.y = (self.height_in_platforms + 3) * Level.tile_height
        else:
            self.player.y = dist_vertical

    def update_entity_position(self, dt, entity: EvilEntity) -> None:
        """
        Updates Entity position.
        :param dt: time interval
        :param entity: entity
        :return:
        """
        dist_horizontal, dist_vertical = self.new_position(dt, entity)

        if entity.velocity_horizontal == 0:
            entity.reverse_movement()

        if dist_horizontal < 0:
            entity.kill()
            return
        elif dist_horizontal >= (self.width_in_platforms - 1) * Level.tile_height:
            entity.kill()
            return
        else:
            entity.x = dist_horizontal

        if dist_vertical < 0:
            entity.kill()
            return
        elif dist_vertical >= (self.height_in_platforms + 3) * Level.tile_height:
            entity.kill()
            return
        else:
            entity.y = dist_vertical

    def new_position(self, dt: float, entity: Entity) -> tuple[int, int]:
        """
        Calculates entity's new position
        :param dt: Time interval
        :param entity: entity
        :return: New coordinates of an entity
        """
        x = entity.x
        y = entity.y
        init_speed = entity.velocity_horizontal
        accel = entity.acceleration
        speed = accelerate(accel, dt, init_speed, entity)
        init_speed_vertical = entity.velocity_vertical
        speed_vertical = accelerate(-10, dt, init_speed_vertical, entity)
        entity.velocity_vertical = speed_vertical
        entity.velocity_horizontal = speed
        dist_horizontal = int(speed * dt * 20) + x
        dist_vertical = int(speed_vertical * dt * 20) + y
        for i in (0, entity.width):
            for j in (0, entity.height):
                current_player_box_x = (entity.x + i) // Level.tile_height
                current_player_box_y = self.height_in_platforms - 1 - (entity.y + j) // Level.tile_height
                updated_player_box_x = (dist_horizontal + i) // Level.tile_height
                updated_player_box_y = self.height_in_platforms - 1 - (dist_vertical + j) // Level.tile_height

                if 0 <= updated_player_box_y < self.height_in_platforms and \
                        0 <= updated_player_box_x < self.width_in_platforms:

                    if current_player_box_x != updated_player_box_x and \
                            current_player_box_y != updated_player_box_y and \
                            self.layout[updated_player_box_y][updated_player_box_x] is not None:

                        if y % Level.tile_height == 0:
                            dist_vertical = y - y % Level.tile_height
                            entity.velocity_vertical = 0
                            if 0 <= y < self.height_in_platforms:
                                if self.layout[y][updated_player_box_x] is not None:
                                    dist_horizontal = x
                                    entity.velocity_horizontal = 0
                        else:
                            dist_horizontal = x
                            entity.velocity_horizontal = 0
                            if 0 <= x < self.width_in_platforms:
                                if self.layout[updated_player_box_y][x] is not None:
                                    dist_vertical = y - y % Level.tile_height
                                    entity.velocity_vertical = 0

                    elif current_player_box_x != updated_player_box_x and \
                            self.layout[updated_player_box_y][updated_player_box_x] is not None:
                        dist_horizontal = x
                        entity.velocity_horizontal = 0

                    elif current_player_box_y != updated_player_box_y and \
                            self.layout[updated_player_box_y][updated_player_box_x] is not None:
                        dist_vertical = y - y % Level.tile_height
                        entity.velocity_vertical = 0

        return dist_horizontal, dist_vertical

    def accelerate_player(self, val: int) -> None:
        """
        Accelerates player in horizontal direction.
        :param val: Direction of movement (-1 <=> Left, 1 <=> Right).
        :return: None
        """
        self.player.acceleration += val * self.player.max_acceleration

    def jump(self) -> None:
        """
        Sets vertical velocity of the player.
        :return: None
        """
        val = self.player.x // Level.tile_height
        val_1 = (self.player.x + self.player.width) // Level.tile_height
        height = self.height_in_platforms - self.player.y // Level.tile_height
        realisation = False

        if height >= self.height_in_platforms:
            return
        if self.player.y % Level.tile_height != 0:
            return
        if 0 <= val < self.width_in_platforms and self.layout[height][val] is not None:
            if self.layout[height][val].get_effect() == PlatformEffect.NoJump:
                return
            realisation = True
        if 0 <= val_1 < self.width_in_platforms and self.layout[height][val_1] is not None:
            if self.layout[height][val_1].get_effect() == PlatformEffect.NoJump:
                return
            realisation = True
        if realisation:
            self.player.velocity_vertical = 20

    def interact_with_enemies(self, pre_update_x: int, pre_update_y: int) -> None:
        """
        Looks for valid entity to interact, and based on pre conditions and actions are taken accordingly.
        :param pre_update_x: pre update player's x coordinate
        :param pre_update_y: pre update player's y coordinate
        :return:
        """
        p = self.player
        for i in self.entities:
            if i.x <= p.x + p.width and i.x + i.width >= p.x \
                    and i.y <= p.y + p.height and i.y + i.height >= p.y:

                if pre_update_y >= i.y + i.height:
                    i.kill()
                    self.player.points += 20
                else:
                    self.player.restart()
                    self.current_beg_horizontal = 0
                    self.current_beg_vertical = 0
                    return

    def interact_with_platform(self) -> None:
        """
        Checks whether action is needed based on platform effect, and applies accordingly.
        :return:
        """
        val = self.player.x // Level.tile_height
        val_1 = (self.player.x + self.player.width) // Level.tile_height
        height = self.height_in_platforms - self.player.y // Level.tile_height
        effect: PlatformEffect = PlatformEffect.Static

        if height >= self.height_in_platforms:
            return
        if self.player.y % Level.tile_height != 0:
            return
        if 0 <= val < self.width_in_platforms and self.layout[height][val] is not None:
            effect = self.layout[height][val].get_effect()
        if 0 <= val_1 < self.width_in_platforms and self.layout[height][val_1] is not None:
            if effect == PlatformEffect.Static:
                effect = self.layout[height][val_1].get_effect()

        if effect == PlatformEffect.Static or effect == PlatformEffect.NoJump:
            return
        elif effect == PlatformEffect.Kill:
            self.player.restart()
            self.current_beg_horizontal = 0
            self.current_beg_vertical = 0
            return
        elif effect == PlatformEffect.Jump:
            self.jump()
            return
        elif effect == PlatformEffect.Win:
            self.win = True
            self.locked = True
            return
