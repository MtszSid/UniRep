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
import java.time.LocalDate;
import java.util.HashMap;

public class SaleController {
    public TableView<ItemTransactionModel> ProductsTable;
    public Label TotalPriceLabel;
    public TextField AmountTextField;
    public ComboBox<String> ProductsComboBox;
    private HashMap<String, BigDecimal> Products;
    private ObservableList<ItemTransactionModel> ProductsToSell;

    Scene mainScene;

    private BigDecimal totalPrice;

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public void OnAdd(ActionEvent actionEvent) {
        String query = "SELECT * FROM STOCK AS st JOIN ITEMS AS it ON st.ID = it.ID WHERE ShortName = ? ;;";

        String productShortName = ProductsComboBox.getValue();

        if(!Products.containsKey(productShortName)){
            Products.put(productShortName, BigDecimal.ZERO);
        }

        try (PreparedStatement stmt = HelloApplication.prepareStatement(query)) {
            stmt.setString(1, productShortName);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                BigDecimal amount = rs.getBigDecimal("Amount").subtract(Products.get(productShortName));
                BigDecimal wantedAmount = new BigDecimal(AmountTextField.getText())
                                                            .setScale(2, RoundingMode.CEILING);
                BigDecimal price = rs.getBigDecimal("Price");
                String name = rs.getString("FullName");

                int type = rs.getInt("Type");

                if(type == 1){
                    amount = amount.setScale(0, RoundingMode.FLOOR);
                }

                if(wantedAmount.compareTo(amount) > 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Brak wystarczającej ilości produktu na wyposażeniu.");
                    alert.showAndWait();
                }
                else{
                    Products.put(productShortName, Products.get(productShortName).add(wantedAmount));
                    BigDecimal pricePerAmount = price.multiply(wantedAmount).setScale(2, RoundingMode.CEILING);
                    ProductsToSell.add(
                            new ItemTransactionModel(
                                    name,
                                    productShortName,
                                    wantedAmount,
                                    pricePerAmount));
                    totalPrice = totalPrice.add(pricePerAmount);
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Brak produktu na wyposażeniu.");
                alert.showAndWait();
            }
        }
        catch (SQLException e) {
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
        String query3 = "UPDATE STOCK SET Amount = ? WHERE ID = ? ;;";
        String query4 = "INSERT INTO REGISTER VALUES (?, ?, ?, ?) ;;";

        HashMap<Integer, BigDecimal> stock = new HashMap<>();
        HashMap<String, Integer> nameToIdMap = new HashMap<>();

        try(PreparedStatement stmt0 = HelloApplication.prepareStatement(query0);
            PreparedStatement stmt1 = HelloApplication.prepareStatement(query1);
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

            for (ItemTransactionModel tm: ProductsToSell) {

                stock.put(nameToIdMap.get(tm.getShortName()),
                        stock.get(nameToIdMap.get(tm.getShortName())).subtract(tm.getAmount())
                                .setScale(2, RoundingMode.CEILING));


                stmt4.setInt(1, nameToIdMap.get(tm.getShortName()));
                stmt4.setBigDecimal(2, tm.getAmount());
                stmt4.setBigDecimal(3, tm.getTotalPrice());
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
        ProductsToSell = FXCollections.observableArrayList();
        ProductsTable.setItems(ProductsToSell);
        ObservableList<String> products = FXCollections.observableArrayList();
        ProductsComboBox.setItems(products);
        Products = new HashMap<>();

        String query = "SELECT * FROM ITEMS;;";

        totalPrice = BigDecimal.ZERO;
        TotalPriceLabel.setText("0.0 zł");

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
