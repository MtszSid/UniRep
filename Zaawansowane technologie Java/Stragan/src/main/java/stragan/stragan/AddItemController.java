package stragan.stragan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController implements Initializable {
    Scene mainScene;

    @FXML
    public TextField Name;
    @FXML
    public TextField ShortName;
    @FXML
    public TextField Price;
    @FXML
    public ComboBox<String> Type;

    @FXML
    public void onClickAddButton(ActionEvent actionEvent) {
        if(Name.getText().equals("") ||
            ShortName.getText().equals("") ||
            Price.getText().equals("") ||
            Type.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Niewłaściwe dane");
            alert.showAndWait();
            return;
        }
        try{
            Double.parseDouble(Price.getText());
        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Niewłaściwy format ceny.");
            alert.showAndWait();
            return;
        }

        String query = "SELECT * FROM ITEMS WHERE ShortName = ? ;";

        try(PreparedStatement statement = HelloApplication.prepareStatement(query)) {
            statement.setString(1, ShortName.getText());
            ResultSet set = statement.executeQuery();
            if(set.next()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Produkt o danym skrócie istnieje.");
                alert.showAndWait();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query1 = "SELECT * FROM TYPE WHERE Name = ? ;";
        String query2 = "INSERT INTO ITEMS VALUES (?, ?, ?, ?);";
        try(PreparedStatement statement1 = HelloApplication.prepareStatement(query1);
                PreparedStatement statement2 = HelloApplication.prepareStatement(query2)) {

            statement1.setString(1, Type.getValue());

            ResultSet rs = statement1.executeQuery();
            rs.next();

            String typeID = rs.getString("ID");

            statement2.setString(1, Name.getText());
            statement2.setString(2, ShortName.getText());
            statement2.setBigDecimal(3, new BigDecimal(Price.getText()).setScale(1, RoundingMode.UP));
            statement2.setInt(4, Integer.parseInt(typeID));

            statement2.executeUpdate();

            HelloApplication.commit();

            clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClickCancelButton(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(mainScene);
        clear();
    }

    private void clear() {
        Name.clear();
        ShortName.clear();
        Price.clear();
        Type.getEditor().clear();
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> items = FXCollections.observableArrayList();

        Type.setItems(items);

        String query = "select * from TYPE";

        try (PreparedStatement stmt = HelloApplication.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String typeName = rs.getString("Name");
                items.add(typeName);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
