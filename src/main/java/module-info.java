module kalkulator_javafx{
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;

    exports pl.sda.iss to javafx.graphics;
    opens  pl.sda.iss to javafx.fxml;

}