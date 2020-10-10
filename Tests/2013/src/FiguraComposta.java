public class FiguraComposta extends Figura implements Countable {
    private Figura[] figuras;

    public FiguraComposta(Figura[] figuras) {
        this.area = 0;
        this.figuras = figuras;
        for (Figura figura: figuras){
            this.area += figura.area;
        }
    }

    @Override
    public int count() {
        return figuras.length;
    }
}
