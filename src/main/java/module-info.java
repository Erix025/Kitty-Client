module com.example.kittyfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.alibaba.fastjson2;

    opens com.example.kittyfx to javafx.fxml;
    exports com.example.kittyfx;
    opens com.example.kittyfx.controllers to javafx.fxml;
    exports com.example.kittyfx.controllers;
    exports com.example.kittyfx.models;
    opens com.example.kittyfx.models to javafx.fxml;
}