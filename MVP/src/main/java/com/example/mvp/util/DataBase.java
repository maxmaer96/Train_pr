package com.example.mvp.util;

import com.example.mvp.models.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;

public class DataBase {
    public static Connection connection;

    public static void initialize() {
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(Config.DBUrl, Config.DBUser, Config.DBPassword);
            statement = connection.createStatement();
            try {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS `food` (`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT, `title` varchar(300) NOT NULL, `kkal` int(11) NOT NULL, `description` varchar(300) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
            } catch (SQLException e) {
                AlertUtils.showAlert("Не удалось создать таблицу", Alert.AlertType.ERROR);
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            AlertUtils.showAlert("Не удалось подключиться к БД", Alert.AlertType.ERROR);
        }
    }

    public static void sendDataToDB(ObservableList<Food> observableArrayList) {
        int id = 0;
        try {
            Connection connection = DriverManager.getConnection(Config.DBUrl, Config.DBUser, Config.DBPassword);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM `food`;");
            for (Food food :
                    observableArrayList) {
                id++;
                statement.executeUpdate("INSERT IGNORE INTO `food`(`id`,`title`, `kkal`, `description`) VALUES\n" +
                        "('" + id + "','" + food.getTitle() + "', '" + food.getKkal() + "', '" + food.getDescription() + "');");


                System.out.printf("|%-25s|%-25s|%-25s|%-25s\n", food.id, food.getTitle(), food.getKkal(), food.getDescription());
            }
        } catch (SQLException e) {
            AlertUtils.showAlert(e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public static void getDataFromDB(FoodModel foodModel) {
        try {
            Connection connection = DriverManager.getConnection(Config.DBUrl, Config.DBUser, Config.DBPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `food`;");
            foodModel.deleteAll();
            while (resultSet.next()) {
                switch (resultSet.getString("description").split(" ")[0]) {
                    case "Фрукт":
                        Fruit fruit = new Fruit(resultSet.getInt("id"), resultSet.getInt("kkal"),resultSet.getString("title"),false);
                        fruit.setDescription(resultSet.getString("description"));
                        foodModel.add(fruit, false);
                        break;
                    case "Шоколад":
                        Chocolate chocolate = new Chocolate(resultSet.getInt("id"), resultSet.getInt("kkal"),resultSet.getString("title"), Chocolate.Type.black);
                        chocolate.setDescription(resultSet.getString("description"));
                        foodModel.add(chocolate, false);
                        break;
                    case "Булочка":
                        Cookie cookie = new Cookie(resultSet.getInt("id"), resultSet.getInt("kkal"),resultSet.getString("title"),false,false,false);
                        cookie.setDescription(resultSet.getString("description"));
                        foodModel.add(cookie, false);
                        break;
                    default:
                        Food food = new Food(resultSet.getInt("id"), resultSet.getInt("kkal"),resultSet.getString("title"));
                        food.setDescription(resultSet.getString("description"));
                        foodModel.add(food, false);
                        break;

                }
            }
            foodModel.emitDataChanged();
        } catch (SQLException e) {
            AlertUtils.showAlert(e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public static boolean checkUserNum(String num) {
        try (ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `sotrudniks` WHERE `Num`='"+num+"'")) {
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUser(String num, String password) {
        User user = null;
        try (ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM `sotrudniks` s, sotrudnikroles r WHERE `Num`='"+num+"' and `Password`='"+password+"' and s.role=r.id")) {
            if (rs.next()) {
                user = new User(
                        rs.getString("Num"),
                        rs.getString("Password"),
                        rs.getString("SecondName"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("name")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
