package com.example.mvp.gui;

import com.example.mvp.models.User;
import com.example.mvp.util.AlertUtils;
import com.example.mvp.util.Config;
import com.example.mvp.util.DataBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class AuthController {
    private Stage thisStage;

    public AuthController() {
        thisStage = new Stage();
        try {
            // Создание загрузчика окна авторизации
            FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/com/example/mvp/gui/auth-view.fxml").toURL());
            fxmlLoader.setController(this);
            Scene scene = new Scene(fxmlLoader.load());
            // Задание ограничений размеров, название и иконку она
            thisStage.setMaxHeight(350);
            thisStage.setMaxWidth(600);
            thisStage.setMinHeight(350);
            thisStage.setMinWidth(600);
            thisStage.getIcons().add(new Image(new File("file:src/main/resources/com/example/mvp/"+Config.appIconPath).getPath()));
            thisStage.setTitle(Config.appName);
            thisStage.setScene(scene);
            thisStage.show(); // Отобразить окно на экране

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private ImageView refreshBtn;
    @FXML
    private PasswordField passwordField; // Поле пароля

    @FXML
    private TextField
            textFieldNum, // Поле номера
            textFieldCod; // Поле кода

    @FXML
    private void checkNum(KeyEvent ke) { // Проверка номера сотрудника
        if (ke.getCode().equals(KeyCode.ENTER)) {
            if (DataBase.checkUserNum(textFieldNum.getText())) {
                passwordField.setDisable(false);
                passwordField.requestFocus();
            } else {
                passwordField.setText("");
                passwordField.setDisable(true);
                AlertUtils.showAlert("Неверный номер сотрудника", Alert.AlertType.ERROR);
            }
        }
    }
    public static User user = null;
    @FXML
    private void tryAuth(KeyEvent ke) { // Попытка авторизации с логином и паролем
        if (ke.getCode().equals(KeyCode.ENTER)) {
            user = DataBase.getUser(textFieldNum.getText(), passwordField.getText());
            if (user != null) {
                textFieldCod.setDisable(false);
                loginBtn.setDisable(false);
                refreshBtn.setDisable(false);
                textFieldCod.requestFocus();
                refreshCode();
            } else {
                textFieldCod.setText("");
                textFieldCod.setDisable(true);
                loginBtn.setDisable(true);
                refreshBtn.setDisable(true);
                AlertUtils.showAlert("Неверный пароль сотрудника");
            }
        }
    }

    @FXML
    private void checkCode(KeyEvent ke) {
        if (ke.getCode().equals(KeyCode.ENTER)) {
            logIn();
        }
    }

    @FXML
    private Button loginBtn;
    @FXML
    private void logIn() {
        if (!textFieldCod.getText().equals(CodeGenerator.userCodeString) || CodeGenerator.userCodeString.equals("")) { // Если код неверный или его нет, то метод не будет выполнен
            AlertUtils.showAlert("Неверный код", Alert.AlertType.ERROR);
            return;
        }
        new MainFormController();
        AlertUtils.showAlert("Авторизация прошла успешно, \nваша роль "+user.getRoleName()+".");
        thisStage.close();
    }

    @FXML
    public void initialize() {
        // При запуске приложения
        clear();
    }

    @FXML
    private void clear() { // Очистка полей
        textFieldCod.setText("");
        textFieldCod.setDisable(true);
        loginBtn.setDisable(true);
        refreshBtn.setDisable(true);
        passwordField.setText("");
        passwordField.setDisable(true);
        textFieldNum.setText("");
    }

    @FXML
    private void refreshCode() {
        if (textFieldCod.isDisabled()) { // Если поле с кодом не активно, то метод не будет выполнен
            AlertUtils.showAlert("Вы не можете получить код,\n" +
                    "так как вы не авторизованы.", Alert.AlertType.ERROR);
            return;
        }
        if (!CodeGenerator.userCodeString.equals("")) { // Если код есть, то метод не будет выполнен
            AlertUtils.showAlert("Вы не можете получить код,\n" +
                    "до истечения срока действия предыдущего кода", Alert.AlertType.ERROR);
            return;
        }

        new CodeGenerator(AuthController.this); // Получение нового кода
    }
}