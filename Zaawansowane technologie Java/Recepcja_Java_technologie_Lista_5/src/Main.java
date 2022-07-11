import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application implements EventHandler<ActionEvent> {

    Label NameLabel, SurnameLabel, GenderLabel, DateOfBirthLabel, DietLabel, HobbyLabel, SportLabel, PetsLabel;
    TextField NameTextField, SurnameTextField;
    ToggleGroup GenderGroup;
    DatePicker DateOfBirthPicker;
    TextArea HobbyTextArea;
    ComboBox<String> DietComboBox;
    Button ClearButton, SaveButton;
    VBox SportVBox, PetsVBox;
    RadioButton DefGender, Male, Female;

    static String[] diets  = {"No restrictions", "Vegetarian", "Vegan", "Dairy free", "Gluten free"};
    static String[] sports = {"Football", "Basketball", "Table tennis", "Tennis", "Swimming"};
    static String[] pets   = {"Dog", "Cats", "Snakes", "Hamsters"};


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane layout = new GridPane();
        Scene scene = new Scene(layout, 500, 500);

        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(500);
        primaryStage.setResizable(false);

        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(5);
        layout.setHgap(5);

        NameLabel       = new Label("Name:");
        NameTextField   = new TextField();

        layout.add(NameLabel, 0, 0);
        layout.add(NameTextField, 1, 0);

        SurnameLabel     = new Label("Surname:");
        SurnameTextField = new TextField();

        layout.add(SurnameLabel, 0, 1);
        layout.add(SurnameTextField, 1, 1);

        VBox genderGroup = new VBox();

        GenderLabel = new Label("Gender:");
        GenderGroup = new ToggleGroup();

        RadioButton male         = new RadioButton("Male");
        RadioButton female       = new RadioButton("Female");
        RadioButton notSpecified = new RadioButton("Wishes not to disclose.");

        male            .setToggleGroup(GenderGroup);
        female          .setToggleGroup(GenderGroup);
        notSpecified    .setToggleGroup(GenderGroup);

        notSpecified.fire();

        DefGender   = notSpecified;
        Male        = male;
        Female      = female;

        genderGroup.getChildren().addAll(male, female, notSpecified);

        layout.add(GenderLabel, 0, 2);
        layout.add(genderGroup, 1, 2);

        DateOfBirthLabel    = new Label("Date of birth:");
        DateOfBirthPicker   = new DatePicker();

        layout.add(DateOfBirthLabel , 0, 3);
        layout.add(DateOfBirthPicker, 1, 3);

        DietLabel    = new Label("Diet:");
        DietComboBox = new ComboBox<>(FXCollections.observableArrayList(diets));

        layout.add(DietLabel,    0, 4);
        layout.add(DietComboBox, 1, 4);

        HobbyLabel = new Label("Hobby:");
        HobbyTextArea = new TextArea();

        layout.add(HobbyLabel, 2, 0);
        layout.add(HobbyTextArea, 2, 1, 2, 3);

        SportLabel = new Label("Sports:");
        SportVBox = new VBox();

        for (String s: sports) {
            SportVBox.getChildren().add(new CheckBox(s));
        }

        layout.add(SportLabel, 2, 5);
        layout.add(SportVBox, 3, 6);

        PetsLabel = new Label("Pets:");
        PetsVBox = new VBox();

        for (String s: pets) {
            PetsVBox.getChildren().add(new CheckBox(s));
        }

        layout.add(PetsLabel, 0, 5);
        layout.add(PetsVBox, 1, 6);

        SaveButton  = new Button("Save");
        ClearButton = new Button("Discard");

        ClearButton.setMinWidth(70);
        SaveButton .setMinWidth(70);

        SaveButton .setOnAction(this);
        ClearButton.setOnAction(this);

        layout.add(ClearButton, 0, 7);
        layout.add(SaveButton, 1, 7);

        primaryStage.setTitle("Form");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void handle(ActionEvent event) {
        if(event.getSource() == SaveButton) {

            FileWriter out = null;

            try {
                out = new FileWriter("index.txt", true);

                out.write("Name: ");
                out.write(NameTextField.getText());
                out.write("\n");

                out.write("Surname: ");
                out.write(SurnameTextField.getText());
                out.write("\n");

                out.write("Date of birth: ");
                out.write(DateOfBirthPicker.getEditor().getText());
                out.write("\n");

                out.write("Gender: ");
                if(Male.isSelected()){
                    out.write("Male");
                }
                else if(Female.isSelected()){
                    out.write("Female");
                }
                else{
                    out.write("Wishes not to disclose.");
                }
                out.write("\n");

                out.write("Diet: ");
                out.write(DietComboBox.getEditor().getText());
                out.write("\n");

                out.write("Hobby: ");
                out.write("\n");
                out.write(HobbyTextArea.getText());
                out.write("\n");

                out.write("Sport: ");
                out.write("\n");

                for (Node n: SportVBox.getChildren()) {
                    CheckBox cb = (CheckBox) n;
                    if(cb.isSelected()) {
                        out.write(" - ");
                        out.write(cb.getText());
                        out.write("\n");
                    }
                }
                out.write("\n");

                out.write("Pets: ");
                out.write("\n");

                for (Node n: PetsVBox.getChildren()) {
                    CheckBox cb = (CheckBox) n;
                    if(cb.isSelected()) {
                        out.write(" - ");
                        out.write(cb.getText());
                        out.write("\n");
                    }
                }
                out.write("\n");
                out.write("\n");
                out.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(event.getSource() == SaveButton || event.getSource() == ClearButton){
            NameTextField   .setText("");
            SurnameTextField.setText("");
            HobbyTextArea   .setText("");

            GenderGroup     .selectToggle(DefGender);

            DateOfBirthPicker.getEditor().clear();

            DietComboBox.valueProperty().set(null);

            for (Node n: SportVBox.getChildren()) {
                CheckBox cb = (CheckBox) n;
                cb.setSelected(false);
            }

            for (Node n: PetsVBox.getChildren()) {
                CheckBox cb = (CheckBox) n;
                cb.setSelected(false);
            }
        }

    }
}
