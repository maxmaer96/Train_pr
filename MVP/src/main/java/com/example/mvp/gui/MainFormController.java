package com.example.mvp.gui;

import com.example.mvp.models.*;
import com.example.mvp.util.Config;
import com.example.mvp.util.DataBase;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    public MainFormController() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(new File("src/main/java/com/example/mvp/gui/MainForm.fxml").toURL());
            fxmlLoader.setController(this);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle(Config.appName);
        stage.getIcons().add(new Image(new File("file:src/main/resources/com/example/mvp/"+Config.appIconPath).getPath()));
        stage.setScene(scene);
        stage.show();
    }

    public TableView mainTable;
    // можно удалить этот список, наш контроллер больше данных не хранит
    //ObservableList<Food> foodList = FXCollections.observableArrayList();

    // создали экземпляр класса модели
    FoodModel foodModel = new FoodModel();

    public MenuBar topMenu;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        topMenu.setDisable(AuthController.user.getRoleName().equals("Консультант")); // Консультантам запрещена работа с данными
        // теперь вызываем метод, вместо прямого присваивания
        // прям как со всякими кнопочками
        foodModel.addDataChangedListener(foods -> {
            mainTable.setItems(FXCollections.observableArrayList(foods));
            DataBase.sendDataToDB(FXCollections.observableArrayList(foods));
        });

        foodModel.load(); // добавляем вызов метода загрузить данные

        // создаем столбец, указываем что столбец преобразует Food в String,
        // указываем заголовок колонки как "Название"
        TableColumn<Food, String> titleColumn = new TableColumn<>("Название");
        // указываем какое поле брать у модели Food
        // в данном случае title, кстати именно в этих целях необходимо было создать getter и setter для поля title
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        // тоже самое для калорийности
        TableColumn<Food, String> kkalColumn = new TableColumn<>("Калорийность");
        kkalColumn.setCellValueFactory(new PropertyValueFactory<>("kkal"));


        // добавляем столбец с описанием
        TableColumn<Food, String> descriptionColumn = new TableColumn<>("Описание");
        // если хотим что-то более хитрое выводить, то используем лямбда выражение
        descriptionColumn.setCellValueFactory(cellData -> {
            // плюс надо обернуть возвращаемое значение в обертку свойство
            return new SimpleStringProperty(cellData.getValue().getDescription());
        });

        // подцепляем столбцы к таблице
        mainTable.getColumns().addAll(titleColumn, kkalColumn, descriptionColumn);
    }

    // добавляем инфу что наш код может выбросить ошибку IOException
    public void onAddClick(ActionEvent actionEvent) throws IOException {
        // эти три строчки создюат форму из fxml файлика
        // в принципе можно было бы обойтись
        // Parent root = FXMLLoader.load(getClass().getResource("FoodForm.fxml"));
        // но дальше вот это разбиение на три строки упростит нам жизнь
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("src/main/java/com/example/mvp/gui/FoodForm.fxml").toURL());
        Parent root = loader.load();

        // ну а тут создаем новое окно
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        // указываем что оно модальное
        stage.initModality(Modality.WINDOW_MODAL);
        // указываем что оно должно блокировать главное окно
        // ну если точнее, то окно, на котором мы нажали на кнопку
        stage.initOwner(this.mainTable.getScene().getWindow());

        // сначала берем контроллер
        FoodFormController controller = loader.getController();
        // передаем модель
        controller.foodModel = foodModel;

        // показываем форму
        stage.showAndWait();

    /*
    это не нужно больше
    if (controller.getModalResult()) {
        Food newFood = controller.getFood();
        this.foodList.add(newFood);
    }
    */
    }

    public void onEditClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("src/main/java/com/example/mvp/gui/FoodForm.fxml").toURL());
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.mainTable.getScene().getWindow());

        // передаем выбранную еду
        FoodFormController controller = loader.getController();
        controller.setFood((Food) this.mainTable.getSelectionModel().getSelectedItem());
        controller.foodModel = foodModel; // передаем модель в контроллер

        stage.showAndWait();

        // если нажали кнопку сохранить
        /*
    это не нужно больше
    if (controller.getModalResult()) {
        int index = this.mainTable.getSelectionModel().getSelectedIndex();
        this.mainTable.getItems().set(index, controller.getFood());
    }
    */
    }

    public void onDeleteClick(ActionEvent actionEvent) {
        // берем выбранную на форме еду
        Food food = (Food) this.mainTable.getSelectionModel().getSelectedItem();

        // выдаем подтверждающее сообщение
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText(String.format("Точно удалить %s?", food.getTitle()));

        // если пользователь нажал OK
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            foodModel.delete(food.id); // тут вызываем метод модели, и передаем идентификатор
        }
    }

    public void onUpdateClick(ActionEvent actionEvent) {
        DataBase.getDataFromDB(foodModel); // Загрузка данных из БД
    }

}
