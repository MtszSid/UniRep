from pyglet import image
from pyglet.image import ImageData


class Entity:
    """
    Class representing entities.
    """
    height: int
    width: int
    max_velocity: int
    max_acceleration: int
    position_x: int
    position_y: int
    image: ImageData

    def __init__(self, height: int, width: int, max_velocity: int,
                 max_acceleration: int, position_x: int, position_y: int,
                 image_path: str):
        """
        :param height: Height in pixels
        :param width: Width in pixels
        :param max_velocity: Maximal velocity of an entity
        :param max_acceleration: Maximal acceleration of an entity
        :param position_x: Current horizontal start of the level content.
        :param position_y: Current vertical start of the level content.
        :param image_path: Entity image path.
        """
        self.height = height
        self.width = width
        self.max_velocity = max_velocity
        self.max_acceleration = max_acceleration
        self.x = position_x
        self.y = position_y
        self.acceleration = 0
        self.velocity_horizontal = 0
        self.velocity_vertical = 0
        self.image = image.load(image_path)

    def set_position(self, x: int, y: int) -> None:
        """
        Sets position of an Entity.
        :param x: Horizontal position
        :param y: Vertical position
        :return:
        """
        self.x = x
        self.y = y

    def draw(self, beg_horizontal: int, beg_vertical: int) -> None:
        """
        Draws an entity, relative to the current camera position.
        :param beg_horizontal: Horizontal start of the level content
        :param beg_vertical: Vertical start of the level content
        :return:
        """
        self.image.blit(self.x - beg_horizontal, self.y - beg_vertical)

    def reverse_movement(self) -> None:
        """
        Reverses horizontal velocity.
        :return:
        """
        self.acceleration *= -1
