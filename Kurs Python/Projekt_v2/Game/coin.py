from pyglet import image

from Game.collectable import Collectable


class Coin(Collectable):
    """
    Represents collectable points.
    """
    def __init__(self, value: int, img: str):
        """
        :param value: value of a coin
        :param img: image of a coin
        """
        self.value = value
        self.image = image.load(img)

    def draw(self, x: int, y: int) -> None:
        """
        Draws coin
        :param x: Horizontal position on the screen
        :param y: Vertical position on the screen
        :return:
        """
        self.image.blit(x, y)
