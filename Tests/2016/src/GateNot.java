public class GateNot extends LogicGate {
    public GateNot(LogicVariable internal, LogicVariable input) throws ColisionException, CycleException {
        super(internal);
        output = internal;

        inputs = new LogicVariable[]{input};

        if (input.getValue() == null){
            throw new CycleException();
        }

        output.setValue(!input.getValue());

        output.setOperation(this);

        formula = getSymbol() + "(" + input.getFormula() + ")";

        output.setFormula(formula);
    }

    @Override
    public String getSymbol() {
        return "NOT";
    }
}
