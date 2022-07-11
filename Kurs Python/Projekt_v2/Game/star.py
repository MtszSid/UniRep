from pyglet import image

from Game.collectable import Collectable


class Star(Collectable):
    """
    Represents collectable stars.
    """

    def __init__(self, img: str):
        """
        :param img: star image
        """
        self.image = image.load(img)

    def draw(self, x: int, y: int):
        """
        Draws a star.
        :param x: Horizontal position on the screen
        :param y: Vertical position on the screen
        :return:
        """

        self.image.blit(x, y)
