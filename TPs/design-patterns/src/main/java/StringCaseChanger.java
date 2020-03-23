public class StringCaseChanger implements StringTransformer{

    @Override
    public void execute(StringDrink drink) {
        char c;
        String result = "";
        for (int i = 0; i < drink.getText().length(); i++){
            c = drink.getText().charAt(i);
            if (Character.isLowerCase(c)){
                c = Character.toUpperCase(c);
            }
            else if (Character.isUpperCase(c)){
                c = Character.toLowerCase(c);
            }
            result += c;
        }
        drink.setText(result);
    }
}
