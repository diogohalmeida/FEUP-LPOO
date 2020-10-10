import java.util.ArrayList;

public class CombinatorialCircuit {
    private ArrayList<LogicVariable> variables = new ArrayList<>();



    public boolean addVariable(LogicVariable variable) {
        if (variables.contains(variable)){
            return false;
        }
        else{
            variables.add(variable);
            return true;
        }
    }

    public Object getVariableByName(String name) {
        for (LogicVariable variable: variables){
            if (variable.getName() == name){
                return variable;
            }
        }
        return null;
    }
}
