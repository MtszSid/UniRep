module com.example.collatz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.collatz to javafx.fxml;
    exports com.example.collatz;
}