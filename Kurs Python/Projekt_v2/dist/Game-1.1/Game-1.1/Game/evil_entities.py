from Game.entity import Entity


class EvilEntity(Entity):
    """
    Basic class representing evil entities.
    """
    alive: bool

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
        self.alive = True
        super().__init__(height, width, max_velocity, max_acceleration, position_x, position_y, image_path)

    def kill(self) -> bool:
        """
        Logically kills an enemy
        :return:
        """
        ans = self.alive
        self.alive = False
        return ans


class BasicEvilEntity(EvilEntity):
    """
    Basic class representing a basic evil entity.
    """
    def __init__(self, position_x, position_y):
        """
        :param position_x: Starting horizontal position.
        :param position_y: Starting vertical position.
        """
        super().__init__(31, 31, 8, 200, position_x, position_y, "Game/Assets/evil_1.png")
        self.velocity_horizontal = self.max_velocity
        self.acceleration = self.max_acceleration
