package util;

import java.util.ArrayList;

public class SolutionsHistory {
    private final ArrayList<Solution> solutions = new ArrayList<>();
    public void add(Solution solution){
        solutions.add(solution);
    }
    public Solution get(int index){
        if (index>=solutions.size()){
            return solutions.get(solutions.size()-1);
        }
        else if (index<0){
            return solutions.get(0);
        }
        else return solutions.get(index);
    }
    public int getHistorySize(){
        return solutions.size();
    }
}
