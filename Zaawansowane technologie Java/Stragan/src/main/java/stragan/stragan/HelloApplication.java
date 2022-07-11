package stragan.stragan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloApplication extends Application {
    private static Connection connection;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainSceneLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene mainScene = new Scene(mainSceneLoader.load(), 600, 600);

        FXMLLoader addItemSceneLoader = new FXMLLoader(HelloApplication.class.getResource("add-item-view.fxml"));
        Scene addItemScene = new Scene(addItemSceneLoader.load(), 600, 600);

        FXMLLoader saleSceneLoader = new FXMLLoader(HelloApplication.class.getResource("sale-view.fxml"));
        Scene saleScene = new Scene(saleSceneLoader.load(), 600, 600);

        FXMLLoader buySceneLoader = new FXMLLoader(HelloApplication.class.getResource("buy-view.fxml"));
        Scene buyScene = new Scene(buySceneLoader.load(), 600, 600);

        FXMLLoader stateSceneLoader = new FXMLLoader(HelloApplication.class.getResource("state-view.fxml"));
        Scene stateScene = new Scene(stateSceneLoader.load(), 600, 600);

        MainController mainController = mainSceneLoader.getController();
        mainController.setAddItemScene(addItemScene);
        mainController.setSaleScene(saleScene);
        mainController.setBuyScene(buyScene);
        mainController.setStateScene(stateScene);

        AddItemController addItemController = addItemSceneLoader.getController();
        addItemController.setMainScene(mainScene);

        SaleController saleController = saleSceneLoader.getController();
        saleController.setMainScene(mainScene);

        BuyController buyController = buySceneLoader.getController();
        buyController.setMainScene(mainScene);

        StateController stateController = stateSceneLoader.getController();
        stateController.setMainScene(mainScene);

        mainController.setBuyController(buyController);
        mainController.setStateController(stateController);
        mainController.setAddItemController(addItemController);
        mainController.setSaleController(saleController);

        stage.setTitle("Zintegrowany Sytstem Zarządzania Straganem (ZSZS v 1.0)");
        stage.setScene(mainScene);
        stage.setResizable(false);
        stage.show();
    }

    public static PreparedStatement prepareStatement(String query) throws SQLException {
        if(connection == null || connection.isClosed()){
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost;instanceName=MSSQLSERVER2;database=Stragan;username=DataBaseUser;password=password;;encrypt=true;trustServerCertificate=true;");
        }

        return connection.prepareStatement(query);
    }

    public static void commit() throws SQLException {
        if(connection != null && !connection.isClosed()){
            connection.commit();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if(connection != null && !connection.isClosed()){
            connection.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}