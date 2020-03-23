import java.util.List;

public class StringTransformerGroup implements StringTransformer {
    List<StringTransformer> transformers;

    public StringTransformerGroup(List<StringTransformer> transformers){
        this.transformers = transformers;
    }


    @Override
    public void execute(StringDrink drink) {
        StringTransformer transformer;
        for (int i = 0; i < transformers.size(); i++){
            transformer = transformers.get(i);
            transformer.execute(drink);
        }
    }
}
