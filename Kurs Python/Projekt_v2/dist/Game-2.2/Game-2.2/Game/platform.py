from enum import Enum

from pyglet import image


class PlatformEffect(Enum):
    """
    Class representing possible effects caused by a platform.
    """
    Static = 0
    NoJump = 1
    Win = 2
    Kill = 3
    Jump = 4


class Platform:
    """
    Base type for a platform types.
    """
    def __init__(self, x: int, y: int, img_path: str, action: PlatformEffect) -> None:
        self.x = x
        self.y = y
        self.img = image.load(img_path)
        self.action = action

    def draw(self, x: int, y: int) -> None:
        """
        Draws a platform at (x, y) coordinates
        :param x: x coordinate
        :param y: y coordinate
        :return: None
        """
        self.img.blit(x, y)

    def get_effect(self) -> PlatformEffect:
        """
        Returns an effect caused by a platform.
        :return: PlatformEffect applicable to the layer.
        """
        return self.action

    def can_stand(self) -> bool:
        """
        Returns True if an entity can stand on the platform.
        :return:
        """
        raise NotImplementedError()
