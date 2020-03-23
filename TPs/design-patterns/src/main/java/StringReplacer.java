public class StringReplacer implements StringTransformer {
    char replacer;
    char to_replace;

    public StringReplacer(char to_replace, char replacer){
        this.replacer = replacer;
        this.to_replace = to_replace;
    }


    @Override
    public void execute(StringDrink drink) {
        char c;
        String result = "";
        for (int i = 0; i < drink.getText().length(); i++) {
            c = drink.getText().charAt(i);

            if (c == to_replace){
                c = replacer;
            }

            result += c;
        }

        drink.setText(result);
    }
}
