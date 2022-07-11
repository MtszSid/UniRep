module stragan.stragan {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens stragan.stragan to javafx.fxml;
    exports stragan.stragan;
}