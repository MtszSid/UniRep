from Game.platform import Platform, PlatformEffect


class BasicPlatform(Platform):

    def can_stand(self) -> bool:
        return True

    def __init__(self, x: int, y: int, img_str: str) -> None:
        super().__init__(x, y, img_str, PlatformEffect.Static)


class NoJumpPlatform(Platform):

    def can_stand(self) -> bool:
        return True

    def __init__(self, x: int, y: int, img_str: str) -> None:
        super().__init__(x, y, img_str, PlatformEffect.NoJump)


class KillPlatform(Platform):

    def can_stand(self) -> bool:
        return True

    def __init__(self, x: int, y: int, img_str: str) -> None:
        super().__init__(x, y, img_str, PlatformEffect.Kill)


class JumpPlatform(Platform):

    def can_stand(self) -> bool:
        return True

    def __init__(self, x: int, y: int, img_str: str) -> None:
        super().__init__(x, y, img_str, PlatformEffect.Jump)


class WinPlatform(Platform):

    def can_stand(self) -> bool:
        return True

    def __init__(self, x: int, y: int, img_str: str) -> None:
        super().__init__(x, y, img_str, PlatformEffect.Win)
