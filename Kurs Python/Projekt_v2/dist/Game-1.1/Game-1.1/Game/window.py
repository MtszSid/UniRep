from typing import Optional, Tuple

from glooey import Gui
from pyglet.image import ImageData
from pyglet.window import key
from pyglet.gl import *

from Game.data_context import DataContext, Levels
from Game.gui_elements import Menu, EndDialog
from Game.level_canvas import LevelCanvas


class Window(pyglet.window.Window):

    def __init__(self):
        background: ImageData

        super(Window, self).__init__(width=1280, height=720, caption='Demo', fullscreen=True)

        self.level = None

        self.data_context = DataContext()

        self.initialized = False

        self.gui = Gui(self)
        self.Menu = Menu()

        self.gui.add(self.Menu)
        self.Menu.init()

        self.end_dialog = None

        glEnable(GL_BLEND)
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)

    def on_draw(self):
        self.clear()

        if self.initialized:
            self.level.draw()
        else:
            self.gui.on_draw()

    def initialize(self, level_id: int):
        level = self.data_context.get(level_id)
        if level is None:
            return

        self.level = LevelCanvas(self.display, self.width, self.height, level.background, level_id)
        self.level.initialize(level.level_layout)
        self.initialized = True

    def on_key_press(self, symbol, modifiers):
        if symbol == key.ESCAPE:
            self.close()
        if symbol == key.H:
            self.initialized = False
            self.level = None
        if self.level is None:
            return
        if symbol == key.LEFT:
            self.level.accelerate_player(-1)
        if symbol == key.SPACE:
            self.level.jump()
        if symbol == key.RIGHT:
            self.level.accelerate_player(1)
        if symbol == key.DOWN:
            pass

    def on_key_release(self, symbol, modifiers):
        if self.level is None:
            return
        if symbol == key.LEFT:
            self.level.accelerate_player(1)
        if symbol == key.RIGHT:
            self.level.accelerate_player(-1)

    def update(self, dt: float) -> None:
        if self.initialized:
            self.level.update(dt)
            if self.level.level.locked and self.end_dialog is None:
                self.end_dialog = EndDialog(self.level.level.win,
                                            self.level.level.player.points,
                                            self.level.level.time,
                                            self.level.level.player.stars)
                if self.level.level.win:
                    self.data_context.update(self.level.ID,
                                             self.level.level.player.stars,
                                             self.level.level.time,
                                             self.level.level.player.points)
                self.level = None
                self.initialized = False
                self.end_dialog.open(self.gui)
                self.end_dialog = None
                self.Menu.init()

    def close(self):
        self.data_context.close()
        super().close()

    def get_level_data(self, level_id: int) -> Optional[Tuple[int, int, int]]:
        level = self.data_context.get(level_id)
        if level is None:
            return None
        return level.best_score, level.best_time, level.best_stars

    def get_level_amount(self) -> int:
        return self.data_context.count_levels()
