from pyglet import image

from Game.entity import Entity


class Player(Entity):
    hearth_full = image.load("Game/Assets/hearth_full.png")
    hearth_empty = image.load("Game/Assets/hearth_empty.png")
    star_full = image.load("Game/Assets/Star.png")
    star_empty = image.load("Game/Assets/star_empty.png")

    def __init__(self, position_x: int, position_y: int) -> None:
        super().__init__(31, 31, 15, 200, position_x, position_y, "Game/Assets/Player.png")
        self.start_pos_x = position_x
        self.start_pos_y = position_y
        self.points = 0
        self.stars = 0
        self.lives = 3

    def restart(self) -> None:
        self.lives -= 1
        self.x = self.start_pos_x
        self.y = self.start_pos_y

    def add_points(self, points: int) -> None:
        self.points += points

    def get_hearths(self):
        if self.lives == 1:
            return Player.hearth_full, Player.hearth_empty, Player.hearth_empty
        elif self.lives == 2:
            return Player.hearth_full, Player.hearth_full, Player.hearth_empty
        elif self.lives == 3:
            return Player.hearth_full, Player.hearth_full, Player.hearth_full
        else:
            return Player.hearth_empty, Player.hearth_empty, Player.hearth_empty

    def get_stars(self):
        if self.stars == 1:
            return Player.star_full, Player.star_empty, Player.star_empty
        elif self.stars == 2:
            return Player.star_full, Player.star_full, Player.star_empty
        elif self.stars == 3:
            return Player.star_full, Player.star_full, Player.star_full
        else:
            return Player.star_empty, Player.star_empty, Player.star_empty
