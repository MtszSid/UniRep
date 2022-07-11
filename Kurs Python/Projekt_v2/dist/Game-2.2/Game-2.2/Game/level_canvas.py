from pyglet.canvas import Canvas

from Game.level_content import Level


class LevelCanvas(Canvas):
    """
    Class representing Level Canvas.
    """
    level: Level
    ID: int
    width: int
    height: int

    def __init__(self, display, width: int, height: int, background: str, ID: int):
        super().__init__(display, 0, hdc=0)
        self.level = Level(width, height, background)
        self.ID = ID
        self.width = width
        self.height = height

    def initialize(self, level_layout: str) -> None:
        """
        Initializes the level content.
        :param level_layout: Layout of the level.
        :return:
        """
        self.level.initialize(level_layout)

    def draw(self) -> None:
        """
        Draws the level content.
        :return:
        """
        self.level.draw()

    def update(self, dt: float) -> None:
        """
        Updates the state of the level.
        :param dt: time between updates
        :returns: None
        """
        self.level.update(dt)

    def accelerate_player(self, val: int) -> None:
        """
        Accelerates player in horizontal direction.
        :param val: direction of acceleration
        :returns: None
        """
        if self.level is not None:
            self.level.accelerate_player(val)

    def jump(self) -> None:
        """
        Sets vertical velocity of the player.
        :returns: None
        """
        if self.level is not None:
            self.level.jump()
