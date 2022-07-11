package stragan.stragan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {
    public Button addItemButton;
    Scene addItemScene;
    Scene saleScene;
    Scene buyScene;
    Scene stateScene;

    StateController stateController;
    AddItemController addItemController;
    SaleController saleController;
    BuyController buyController;

    @FXML
    protected void onAddNewItem(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(addItemScene);
    }

    public void setAddItemScene(Scene addItemScene) {
        this.addItemScene = addItemScene;
    }

    public void setSaleScene(Scene saleScene) {
        this.saleScene = saleScene;
    }

    public void setBuyScene(Scene buyScene) {
        this.buyScene = buyScene;
    }

    public void setStateScene(Scene stateScene) {
        this.stateScene = stateScene;
    }

    public void onSale(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(saleScene);
        saleController.OnView();
    }

    public void onByu(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(buyScene);
        buyController.OnView();
    }

    public void OnState(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(stateScene);
        stateController.OnView();
    }

    public void setStateController(StateController stateController) {
        this.stateController = stateController;
    }

    public void setAddItemController(AddItemController addItemController) {
        this.addItemController = addItemController;
    }

    public void setSaleController(SaleController saleController) {
        this.saleController = saleController;
    }

    public void setBuyController(BuyController buyController) {
        this.buyController = buyController;
    }
}
