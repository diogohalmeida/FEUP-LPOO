public class StringInverter implements StringTransformer {

    @Override
    public void execute(StringDrink drink) {
        StringBuffer sbuffer = new StringBuffer(drink.getText());
        sbuffer.reverse();
        drink.setText(sbuffer.toString());
    }

}
