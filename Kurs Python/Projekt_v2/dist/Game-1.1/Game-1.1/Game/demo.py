import pyglet
from Game.window import Window
from pyglet import clock


def start() -> None:
    """
    Function starting a program.
    To be called in main.
    :return:
    """
    window = Window()

    cl = clock.get_default()
    cl.schedule_interval(window.update, 1 / 40)

    pyglet.app.run()



