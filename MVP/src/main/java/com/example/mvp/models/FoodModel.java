package com.example.mvp.models;

import com.example.mvp.util.DataBase;

import java.util.ArrayList;

public class FoodModel {
    // это не трогаем
    ArrayList<Food> foodList = new ArrayList<>();
    private int counter = 1; // добавили счетчик
    public interface DataChangedListener {
        void dataChanged(ArrayList<Food> foods);
    }

    // Меняем на private и делаем список
    private ArrayList<DataChangedListener> dataChangedListeners = new ArrayList<>();
    // добавляем метод который позволяет привязать слушателя
    public void addDataChangedListener(DataChangedListener listener) {
        // ну просто его в список добавляем
        this.dataChangedListeners.add(listener);
    }

    public void load() {
        // это не трогаем
        foodList.clear();
        DataBase.getDataFromDB(this);
        // заменили foodList на this
        // добавили второй параметр как false
        //this.add(new Fruit(1,100, "Яблоко", true), false);
        //this.add(new Chocolate(2,200, "шоколад Аленка", Chocolate.Type.milk), false);
        //this.add(new Cookie(3,300, "сладкая булочка с Маком", true, true, false), false);
        // тут поменяли
        //this.emitDataChanged();
    }

    public void deleteAll() {
        foodList = new ArrayList<>();
    }

    // добавили параметр emit в метод,
// если там true то вызывается оповещение слушателей
    public void add(Food food, boolean emit) {
        food.id = counter;
        counter += 1;

        this.foodList.add(food);

        if (emit) {
            this.emitDataChanged();
        }
    }

    // а это получается перегруженный метод, но с одним параметром
    // который вызывает add с двумя параметрами,
    // передавая в качестве второго параметра true
    // то есть вызывая add с одним параметром будет происходит оповещение
    public void add(Food food) {
        add(food, true);
    }

    // для удаления достаточно идентификатора
    public void delete(int id)
    {
        for (int i = 0; i< this.foodList.size(); ++i) {
            // ищем в цикле еду с данным айдишником
            if (this.foodList.get(i).id == id) {
                // если нашли то удаляем
                this.foodList.remove(i);
                break;
            }
        }

        // оповещаем об изменениях
        this.emitDataChanged();
    }

    // а это добавили
    public void emitDataChanged() { // Оповещение слушателей об изменении данных
        for (DataChangedListener listener: dataChangedListeners) {
            listener.dataChanged(foodList);
        }
    }

    public void edit(Food food) {
        // ищем объект в списке
        for (int i = 0; i< this.foodList.size(); ++i) {
            // чтобы id совпадал с id переданным форме
            if (this.foodList.get(i).id == food.id) {
                // если нашли, то подменяем объект
                this.foodList.set(i, food);
                break;
            }
        }

        this.emitDataChanged();
    }
}
