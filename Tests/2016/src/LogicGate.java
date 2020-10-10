import java.util.ArrayList;

public abstract class LogicGate {
    protected LogicVariable output;
    protected String formula;
    protected LogicVariable[] inputs;

    LogicGate(LogicVariable input) throws ColisionException {
        if (input.getCalculatedBy() != null){
            throw new ColisionException();
        }
    }

    public LogicVariable getOutput() {
        return output;
    }

    public LogicVariable[] getInputs() {
        return inputs;
    }

    public abstract String getSymbol();

    public String getFormula() {
        return formula;
    }
}
