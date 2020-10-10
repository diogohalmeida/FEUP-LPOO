public class GateOr extends LogicGate {
    public GateOr(LogicVariable internal, LogicVariable input1, LogicVariable input2) throws ColisionException, CycleException {
        super(internal);
        output = internal;

        inputs = new LogicVariable[]{input1, input2};

        if (input1.getValue() == null || input2.getValue() == null){
            throw new CycleException();
        }

        output.setValue(input1.getValue() || input2.getValue());

        output.setOperation(this);

        formula = getSymbol() + "(" + input1.getFormula() + "," + input2.getFormula() + ")";

        output.setFormula(formula);
    }

    @Override
    public String getSymbol() {
        return "OR";
    }
}
