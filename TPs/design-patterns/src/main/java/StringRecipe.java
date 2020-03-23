import java.util.List;

public class StringRecipe{
    List<StringTransformer> transformers;


    public StringRecipe(List<StringTransformer> transformers){
        this.transformers = transformers;
    }

    public void mix(StringDrink drink) {
        StringTransformer transformer;
        for (int i = 0; i < transformers.size(); i++){
            transformer = transformers.get(i);
            transformer.execute(drink);
        }
    }
}
