
class Collectable:
    """
    Class represents Collectable items
    """
    def draw(self, x, y):
        """
        Abstract method.
        :param x: Horizontal position on the screen
        :param y: Vertical position on the screen
        :return:
        """
        raise NotImplementedError()
