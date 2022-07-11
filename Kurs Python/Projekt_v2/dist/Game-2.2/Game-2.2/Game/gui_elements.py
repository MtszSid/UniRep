import glooey
from glooey import Widget, HBox, VBox, Image, Button, Background, Bin, Grid, OkDialog
from glooey.text import Label
from pyglet import image


class gui_element(Widget):
    """
    Basic GUI element presenting level info.
    """
    custom_alignment = 'center'

    def __init__(self, level_id: int, score: int, time: int, stars: int):
        super().__init__()
        self.level_id = level_id
        self.score = score
        self.time = time
        self.stars = stars
        self.box = VBox()

        label = Label(str(self.level_id))
        label.set_font_size(30)
        label.set_alignment("center")
        self.box.add(label)

        vbox = HBox()
        for i in range(self.stars):
            vbox.add(Image(image.load("Game/Assets/Star.png")))
        for i in range(3 - self.stars):
            vbox.add(Image(image.load("Game/Assets/star_empty.png")))
        self.box.pack(vbox)

        time_label = Label(str(self.time) + " s")
        time_label.set_alignment("center")

        self.box.pack(time_label)

        score_label = Label(str(self.score) + " points")
        score_label.set_alignment("center")

        self.box.pack(score_label)

        self.set_width_hint(170)
        self.set_height_hint(170)
        self._attach_child(self.box)

    def do_draw(self):

        self.box.do_draw()


class customButton(Button):
    """
    Basic button holding level info. When clicked initializes level with ID = level_id.
    """
    class Foreground(gui_element):

        def __init__(self, level_id: int = 0, score: int = 0, time: int = 0, stars: int = 0):
            super().__init__(level_id, score, time, stars)

    class Base(Background):
        custom_color = "#222222"

    class Over(Background):
        custom_color = "#333333"

    class Down(Background):
        custom_color = "#111111"

    def __init__(self, level_id: int, score: int, time: int, stars: int):
        super().__init__(level_id=level_id, score=score, time=time, stars=stars)
        self.level_id = level_id

    def on_mouse_press(self, x, y, button, modifiers):
        self.get_window().initialize(self.level_id)


class Dialog(OkDialog):
    """
    Basic custom OkDialog.
    """
    class Decoration(Background):
        custom_color = "#100010"

    class Box(glooey.Grid):
        custom_right_padding = 14
        custom_top_padding = 14
        custom_left_padding = 17
        custom_bottom_padding = 17
        custom_cell_padding = 9

    class OkButton(Button):
        custom_text = 'Ok'


class EndDialog(OkDialog):
    """
    Dialog shown after completing a level.
    """
    custom_alignment = "center"

    class Decoration(Background):
        custom_color = "#100010"

    class Box(glooey.Grid):
        custom_right_padding = 40
        custom_top_padding = 20
        custom_left_padding = 40
        custom_bottom_padding = 20
        custom_cell_padding = 10

    class OkButton(Button):
        custom_text = 'Ok'

    def __init__(self, win: bool, score: int, time: int, stars: int, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.Content = VBox()
        self.Content.set_cell_padding(10)
        self.set_alignment("center")
        self.add(self.Content)
        self.stars = stars
        if win:
            self.WinLabel = Label("You Won!")
        else:
            self.WinLabel = Label("Game Over.")
        self.WinLabel.set_font_size(24)

        self.Score = Label("Score: " + str(score))
        self.time = Label("Time: " + str(time) + " s")

        vbox = HBox()
        for i in range(self.stars):
            vbox.add(Image(image.load("Game/Assets/Star.png")))
        for i in range(3 - self.stars):
            vbox.add(Image(image.load("Game/Assets/star_empty.png")))

        self.Content.add(self.WinLabel)
        self.Content.add(vbox)
        self.Content.add(self.Score)
        self.Content.add(self.time)


class Bar(Bin):
    """Basic navigation bar."""
    def __init__(self):
        super().__init__()
        self.content = HBox()
        self.add(self.content)
        self.content.set_alignment("right")

        self.info_button = Button()
        self.info_button.set_foreground(Image(image.load("Game/Assets/info.png")))
        self.info_button.push_handlers(on_click=lambda x: self.show_dialog())
        self.content.pack(self.info_button)

        self.exit_button = Button()
        self.exit_button.set_foreground(Image(image.load("Game/Assets/exit.png")))
        self.exit_button.push_handlers(on_click=lambda x: self.get_window().close())
        self.content.pack(self.exit_button)

    def show_dialog(self):
        dialog = Dialog()
        dialog.add(Label("Poruszasz się strzałkami (lewo, prawo).\nSkaczesz spacją.\nAby wrócić do menu naciśnij h\n"
                         "Aby wyjść naciśnij ESC.\n\n© Mateusz Sidło\nWrocław, 2022"))
        dialog.open(self.get_window().gui)


class Menu(glooey.Frame):
    """
    Basic menu Frame.
    """
    custom_alignment = "fill"

    class Decoration(Background):
        custom_alignment = "fill"
        custom_color = "#40414A"

    class Box(Bin):

        custom_right_padding = 14
        custom_top_padding = 14
        custom_left_padding = 17
        custom_bottom_padding = 17

    def __init__(self):
        super().__init__()

        self.page = 0

        self.Content = VBox()
        self.Content.custom_width_hint = 1200
        self.Content.set_alignment("center")
        self.add(self.Content)

        self.true_Content = HBox()
        self.true_Content.set_alignment("center")

        self.left_button = Button()
        self.left_button.set_foreground(Image(image.load("Game/Assets/left_arrow.png")))
        self.left_button.push_handlers(on_mouse_press=lambda x, y, z, w: self.page_turn(-1))
        self.true_Content.pack(self.left_button)

        self.grid = Grid()
        self.grid.padding = 10
        self.grid.set_default_row_height(170)
        self.grid.set_default_row_height(170)
        self.true_Content.pack(self.grid)

        self.right_button = Button()
        self.right_button.set_foreground(Image(image.load("Game/Assets/right_arrow.png")))
        self.right_button.push_handlers(on_mouse_press=lambda x, y, z, w: self.page_turn(1))
        self.true_Content.pack(self.right_button)

        self.Content.pack(Bar())
        self.Content.add(self.true_Content)

    def init(self):
        self.grid.clear()

        for i in range(3):
            for j in range(3):
                level_info = self.get_window().get_level_data(self.page * 9 + i * 3 + j + 1)
                if level_info is not None:
                    self.grid[i, j] = customButton(self.page * 9 + i * 3 + j + 1,
                                                   level_info[0],
                                                   level_info[1],
                                                   level_info[2])

    def page_turn(self, direction: int) -> None:
        pages = (self.get_window().get_level_amount() - 1) // 9
        if 0 <= self.page + direction <= pages:
            self.page += direction
            self.init()
