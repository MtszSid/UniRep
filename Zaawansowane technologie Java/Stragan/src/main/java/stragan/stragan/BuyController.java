package stragan.stragan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class BuyController {
    public TableView<ItemTransactionModel> ProductsTable;
    public Label TotalPriceLabel;
    public TextField AmountTextField;
    public ComboBox<String> ProductsComboBox;
    public TextField PricePerPiece;
    public TableColumn<ItemTransactionModel, String> NameColumn;
    public TableColumn<ItemTransactionModel, String> ShortNameColumn;
    public TableColumn<ItemTransactionModel, BigDecimal> AmountColumn;
    public TableColumn<ItemTransactionModel, BigDecimal> SumColumn;

    BigDecimal totalPrice;
    private ObservableList<ItemTransactionModel> ProductsToBuy;

    Scene mainScene;

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public void OnAdd(ActionEvent actionEvent) {
        String query = "SELECT * FROM ITEMS WHERE ShortName = ?;;";
        try (PreparedStatement stmt = HelloApplication.prepareStatement(query)){
            if (!Objects.equals(PricePerPiece.getText(), "") &&
                    !Objects.equals(AmountTextField.getText(), "") &&
                    !Objects.equals(ProductsComboBox.getValue(), "")) {
                stmt.setString(1, ProductsComboBox.getValue());

                ResultSet rs = stmt.executeQuery();
                rs.next();
                String name = rs.getString("FullName");
                BigDecimal amount = new BigDecimal(AmountTextField.getText()).setScale(2, RoundingMode.CEILING);
                BigDecimal price = new BigDecimal(PricePerPiece.getText()).setScale(2, RoundingMode.CEILING);
                BigDecimal totalPrice = amount.multiply(price).setScale(2, RoundingMode.CEILING);

                int type = rs.getInt("Type");

                if(type == 1){
                    amount = amount.setScale(0, RoundingMode.FLOOR);
                }

                ProductsToBuy.add(new ItemTransactionModel(
                        name,
                        ProductsComboBox.getValue(),
                        amount,
                        totalPrice
                ));
                this.totalPrice = this.totalPrice.add(totalPrice);
                TotalPriceLabel.setText(this.totalPrice.toString() + " zł");

            }
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Niewłaściwy format ceny.");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void OnCancel(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(mainScene);
    }

    public void OnCommit(ActionEvent actionEvent) {
        String query0 = "SELECT * FROM ITEMS;;";
        String query1 = "SELECT * FROM STOCK;;";
        String query2 = "INSERT INTO STOCK VALUES (?, ?)";
        String query3 = "UPDATE STOCK SET Amount = ? WHERE ID = ?";
        String query4 = "INSERT INTO REGISTER VALUES (?, ?, ?, ?)";

        HashMap<Integer, BigDecimal> stock = new HashMap<>();
        HashMap<String, Integer> nameToIdMap = new HashMap<>();

        try(PreparedStatement stmt0 = HelloApplication.prepareStatement(query0);
            PreparedStatement stmt1 = HelloApplication.prepareStatement(query1);
            PreparedStatement stmt2 = HelloApplication.prepareStatement(query2);
            PreparedStatement stmt3 = HelloApplication.prepareStatement(query3);
            PreparedStatement stmt4 = HelloApplication.prepareStatement(query4)) {

            ResultSet rs0 = stmt0.executeQuery();
            ResultSet rs1 = stmt1.executeQuery();

            while(rs0.next()){
                nameToIdMap.put(rs0.getString("ShortName"), rs0.getInt("ID"));
            }

            while(rs1.next()){
                stock.put(rs1.getInt("ID"), rs1.getBigDecimal("Amount"));
            }

            for (ItemTransactionModel tm: ProductsToBuy) {
                if(stock.containsKey(nameToIdMap.get(tm.getShortName()))){
                    stock.put(nameToIdMap.get(tm.getShortName()),
                            stock.get(nameToIdMap.get(tm.getShortName())).add(tm.getAmount())
                                    .setScale(2, RoundingMode.CEILING));
                }
                else{
                    stock.put(nameToIdMap.get(tm.getShortName()), tm.getAmount());
                    stmt2.setInt(1, nameToIdMap.get(tm.getShortName()));
                    stmt2.setBigDecimal(2, BigDecimal.ZERO);
                    stmt2.executeUpdate();
                }

                stmt4.setInt(1, nameToIdMap.get(tm.getShortName()));
                stmt4.setBigDecimal(2, tm.getAmount());
                stmt4.setBigDecimal(3, tm.getTotalPrice().multiply(BigDecimal.valueOf(-1)));
                stmt4.setDate(4, Date.valueOf(LocalDate.now()));

                stmt4.executeUpdate();
            }
            HelloApplication.commit();

            for (Integer s: stock.keySet()) {
                stmt3.setBigDecimal(1, stock.get(s));
                stmt3.setInt(2, s);
                stmt3.executeUpdate();

            }

            HelloApplication.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        OnCancel(actionEvent);
    }

    public void OnView(){
        ProductsToBuy = FXCollections.observableArrayList();
        ProductsTable.setItems(ProductsToBuy);
        ObservableList<String> products = FXCollections.observableArrayList();
        ProductsComboBox.setItems(products);

        TotalPriceLabel.setText("0.0 zł");

        NameColumn.setCellValueFactory(
                new PropertyValueFactory<ItemTransactionModel, String>("name")
        );

        ShortNameColumn.setCellValueFactory(
                new PropertyValueFactory<ItemTransactionModel, String>("shortName")
        );

        AmountColumn.setCellValueFactory(
                new PropertyValueFactory<ItemTransactionModel, BigDecimal>("amount")
        );

        SumColumn.setCellValueFactory(
                new PropertyValueFactory<ItemTransactionModel, BigDecimal>("totalPrice")
        );

        String query = "SELECT * FROM ITEMS;;";

        totalPrice = BigDecimal.ZERO;

        try (PreparedStatement stmt = HelloApplication.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String shortName = rs.getString("ShortName");
                products.add(shortName);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
