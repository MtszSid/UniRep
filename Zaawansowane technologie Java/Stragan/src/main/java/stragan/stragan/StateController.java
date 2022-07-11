package stragan.stragan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.jar.Attributes;

public class StateController {
    public DatePicker BeginningDate;
    public DatePicker EndDate;
    public Label TotalPriceLabel;
    public TableView<ItemTransactionModel> ProductsTable;
    public Button CancelButton;
    public Button CommitButton;
    public ComboBox<String> ShortNames;
    public TextField NewPriceTextField;

    private ObservableList<ItemTransactionModel> ProductsInStock;
    private Scene mainScene;

    public void GenerateTotalPrice(ActionEvent actionEvent) {
        String query = "SELECT * FROM REGISTER WHERE Date <= ? AND Date >= ? ;;";

        BigDecimal price = BigDecimal.ZERO;

        try (PreparedStatement stmt = HelloApplication.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(EndDate.getValue()));
            stmt.setDate(2, Date.valueOf(BeginningDate.getValue()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                price = price.add(rs.getBigDecimal("Price"));
            }
            TotalPriceLabel.setText(price.toString() + " zł");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void OnCancel(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(mainScene);
        clear();
    }

    public void clear(){

    }

    public void OnCommit(ActionEvent actionEvent) {
        String query = "UPDATE ITEMS SET Price = ? WHERE ShortName = ?";

        try (PreparedStatement stmt = HelloApplication.prepareStatement(query)) {
            stmt.setString(2, ShortNames.getValue());
            stmt.setBigDecimal(1, new BigDecimal(NewPriceTextField.getText())
                    .setScale(2, RoundingMode.CEILING));
            stmt.executeUpdate();
            HelloApplication.commit();

            RepopulateTable();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public void OnView(){
        ObservableList<String> Names = FXCollections.observableArrayList();

        ShortNames.setItems(Names);


        String query = "SELECT * FROM ITEMS;;";

        try (PreparedStatement stmt = HelloApplication.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String shortName = rs.getString("ShortName");
                Names.add(shortName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RepopulateTable();
    }

    private void RepopulateTable() {
        ProductsInStock = FXCollections.observableArrayList();
        ProductsTable.setItems(ProductsInStock);

        String query = "SELECT * FROM ITEMS AS it JOIN STOCK AS st ON it.ID = st.ID;;";

        try (PreparedStatement stmt = HelloApplication.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String shortName = rs.getString("ShortName");
                String name = rs.getString("FullName");
                BigDecimal price = rs.getBigDecimal("Price");
                BigDecimal amount = rs.getBigDecimal("Amount");

                ProductsInStock.add(new ItemTransactionModel(
                        name,
                        shortName,
                        amount,
                        price
                ));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
