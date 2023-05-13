package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс представляющий историю произведенных решений.
 * В паттерне проектирования "Memento" класс SolutionsHistory
 * играет роль Caretaker. Он хранит "снимки" решений, позволяет
 * добавлять решения и считывать прошлые состояния объекта.
 * @author Sleptsov D.A.
 * */
public class SolutionsHistory {
    /**
     * Поле solutions представляет List в котором будут храниться объекты Solution
     * Поле является финальным, чтобы исключить возможность присвоения переменной solutions
     * ссылки на другой экземпляр коллекции.
     * */
    private final List<Solution> solutions = new ArrayList<>();
    /**
     * Метод add добавляет решение в историю
     * @param solution - полученное решение
     * */
    public void add(Solution solution){
        solutions.add(solution);
    }
    /**
     * Метод get позволяет получить решение из истории по заданному индексу.
     * <ul><li> Если переданный индекс меньше 0, то метод вернём первый элемент из истории
     * <li>Если переданный индекс больше размера истории, то метод вернём последний элемент из истории </ul>
     * @param index - номер решения в истории.
     * @return одно из записанных в историю решений (объект типа Solution)
     * */
    public Solution get(int index){
        if (index>=solutions.size()){
            return solutions.get(solutions.size()-1);
        }
        else if (index<0){
            return solutions.get(0);
        }
        else return solutions.get(index);
    }
    /**
     * Метод getHistorySize позволяет получить количество решений в истории
     * @return количество элементов в коллекции
     * */
    public int getHistorySize(){
        return solutions.size();
    }
}
