import java.util.Objects;

public class LogicVariable {
    private String name, formula;
    private Boolean value;
    private LogicGate operation;

    public LogicVariable(String name){
        this.name = name;
        this.formula = name;
    }

    public LogicVariable(String name, Boolean value){
        this.name = name;
        this.value = value;
        this.formula = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Boolean value) { this.value = value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogicVariable that = (LogicVariable) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Object getCalculatedBy() {
        return operation;
    }

    public void setOperation(LogicGate operation) {
        this.operation = operation;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Boolean getValue() {
        if (this.operation == null){
            return value;
        }

        if (this.operation instanceof GateNot){
            return !operation.inputs[0].getValue();
        }

        if (this.operation instanceof GateAnd){
            return operation.inputs[0].getValue() && operation.inputs[1].getValue();
        }

        if (this.operation instanceof GateOr){
            return operation.inputs[0].getValue() || operation.inputs[1].getValue();
        }

        return null;
    }
}
