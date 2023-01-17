package com.example.kittyfx.Controllers;

public class MovableController extends Controller {

    int pre_x = 0, pre_y = 0;

    public void Initialize() {
        //拖动
        scene.setOnMousePressed(event -> {
            System.out.println("Pressed");
            pre_x = (int) event.getScreenX();
            pre_y = (int) event.getScreenY();
            System.out.println(pre_x + "," + pre_y);
        });
        scene.setOnMouseDragged(event -> {
            int x = (int) event.getScreenX() - pre_x + (int) stage.getX();
            int y = (int) event.getScreenY() - pre_y + (int) stage.getY();
            System.out.println((x + "," + y));
            pre_x = (int) event.getScreenX();
            pre_y = (int) event.getScreenY();
            stage.setX(x);
            stage.setY(y);
        });
    }

    public void Show() {
    }
}
