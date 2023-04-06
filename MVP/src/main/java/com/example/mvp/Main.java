package com.example.mvp;

import com.example.mvp.gui.AuthController;
import com.example.mvp.util.Config;
import com.example.mvp.util.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new AuthController();
        DataBase.initialize();
    }

    public static void main(String[] args) {
        launch();
    }
}