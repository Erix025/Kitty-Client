module com.example.kittyfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.alibaba.fastjson2;

    opens com.example.kittyfx to javafx.fxml;
    exports com.example.kittyfx;
    opens com.example.kittyfx.Controllers to javafx.fxml;
    exports com.example.kittyfx.Controllers;
    exports com.example.kittyfx.Models;
    opens com.example.kittyfx.Models to javafx.fxml;
}