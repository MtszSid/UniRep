import os
import sys
from unittest import TestCase

os.chdir("../")
from Game.level_content import *


class TestLevel(TestCase):
    def test_initialize(self):
        value = """p15#$%a*
gxjnwb.."""
        level = Level(700, 700, "Game/Assets/Background_1.png")
        level.initialize(value)
        content = level.layout
        self.assertIsInstance(content[1][0], BasicPlatform)
        self.assertIsInstance(content[1][1], KillPlatform)
        self.assertIsInstance(content[1][2], JumpPlatform)
        self.assertIsInstance(content[1][3], NoJumpPlatform)
        self.assertIsInstance(content[1][4], WinPlatform)
        self.assertIsInstance(content[1][5], BasicPlatform)
        self.assertIsNone(content[1][6])
        self.assertIsNone(content[1][7])
        self.assertEqual(level.player.x, 10)
        self.assertEqual(level.player.y, Level.tile_height + 10)
        self.assertIsInstance(level.collectables[(1, 1)], Coin)
        self.assertIsInstance(level.collectables[(2, 1)], Coin)
        self.assertIsInstance(level.collectables[(3, 1)], Coin)
        self.assertIsInstance(level.collectables[(4, 1)], Coin)
        self.assertIsInstance(level.collectables[(5, 1)], Coin)
        self.assertIsInstance(level.collectables[(7, 1)], Star)
        self.assertEqual(len(level.entities), 1)

    def test_collect_points(self):
        value = """p15#$%*.
gggggggg"""
        level = Level(700, 700, "Game/Assets/Background_1.png")
        level.initialize(value)

        for i in range(6):
            level.collect_points()
            level.player.x += Level.tile_height

        self.assertEqual(level.player.points, 286)
        self.assertEqual(level.player.stars, 1)

    def test_update_player_position(self):
        value = """p.....g.
ggnggggg"""
        level = Level(700, 700, "Game/Assets/Background_1.png")
        level.initialize(value)

        level.player.y = Level.tile_height
        level.jump()
        level.accelerate_player(1)
        x = level.player.x
        y = level.player.y
        level.update_player_position(0.05, level.player)
        self.assertNotEqual(x, level.player.x)
        self.assertNotEqual(y, level.player.y)

    def test_update_entity_position(self):

        value = """p.g.a.g.
ggnggggg"""
        level = Level(700, 700, "Game/Assets/Background_1.png")
        level.initialize(value)

        entity = level.entities.pop()
        x = entity.x
        level.update_entity_position(0.01, entity)
        self.assertNotEqual(entity.x, x)

    def test_accelerate_player(self):
        value = """p15#$%a*
gggggggg"""
        level = Level(700, 700, "Game/Assets/Background_1.png")
        level.initialize(value)

        level.accelerate_player(1)
        self.assertEqual(level.player.acceleration, level.player.max_acceleration)
        level.accelerate_player(-1)
        self.assertEqual(level.player.acceleration, 0)
        level.accelerate_player(-1)
        self.assertEqual(level.player.acceleration, -1 * level.player.max_acceleration)

    def test_jump(self):
        value = """p.......
ggnggggg"""
        level = Level(700, 700, "Game/Assets/Background_1.png")
        level.initialize(value)

        level.player.y = Level.tile_height

        level.jump()
        self.assertGreater(level.player.velocity_vertical, 0)
        level.player.x = 2 * Level.tile_height
        level.player.velocity_vertical = 0
        level.jump()
        self.assertEqual(level.player.velocity_vertical, 0)

    def test_interact_with_enemies(self):
        value = """gp.g
ga.g
gggg"""
        level = Level(700, 700, "Game/Assets/Background_1.png")
        level.initialize(value)
        level.player.y -= 20
        level.interact_with_enemies(level.player.x, level.player.y + 20)
        for i in level.entities:
            self.assertEqual(i.alive, False)

        value = """gpag
gggg"""
        level = Level(700, 700, "Game/Assets/Background_1.png")
        level.initialize(value)
        level.update(0.1)
        level.update(0.1)
        level.update(0.1)
        level.update(0.1)
        level.update(0.1)
        self.assertNotEqual(level.player.lives, 3)

    def test_interact_with_platform(self):
        value = """p.......
jjjggggg"""
        level = Level(700, 700, "Game/Assets/Background_1.png")
        level.initialize(value)
        level.player.y = Level.tile_height

        level.interact_with_platform()
        self.assertGreater(level.player.velocity_vertical, 0)

        value = """p.......
xxxggggg"""
        level = Level(700, 700, "Game/Assets/Background_1.png")
        level.initialize(value)
        level.player.y = Level.tile_height

        level.interact_with_platform()
        self.assertNotEqual(level.player.lives, 3)

        value = """p.......
wwwggggg"""
        level = Level(700, 700, "Game/Assets/Background_1.png")
        level.initialize(value)
        level.player.y = Level.tile_height

        level.interact_with_platform()
        self.assertEqual(level.win, True)



