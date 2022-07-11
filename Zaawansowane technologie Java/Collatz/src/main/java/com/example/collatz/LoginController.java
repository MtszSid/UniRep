package com.example.collatz;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Objects;

public class LoginController {
    public TextField UserName;
    public PasswordField Password;
    public Label ErrorMessage;

    Scene collatzScene;

    static String query = "SELECT * FROM USERS WHERE UserName = ?;";

    public void OnLogIn(ActionEvent actionEvent) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost;instanceName=MSSQLSERVER2;database=JavaUserData;username=DataBaseUser;password=password;;encrypt=true;trustServerCertificate=true;");){

            try (PreparedStatement stmt = connection.prepareStatement(query)){
                stmt.setString(1, UserName.getText());

                ResultSet rs = stmt.executeQuery();

                if(rs.next()){
                    String password = rs.getString("Password").trim();
                    String pswd = Password.getText();
                    if(Objects.equals(password, pswd)){
                        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                        primaryStage.setScene(collatzScene);
                    }
                    else{
                        ErrorMessage.setText("Invalid input data.");
                        Password.setText("");
                    }
                }
                else{
                    ErrorMessage.setText("Invalid input data.");
                    Password.setText("");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void OnExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void SetCollatzScene(Scene cs){
        collatzScene = cs;
    }
}
