public class Circulo extends Figura {
    private Ponto centro;
    private int raio;

    public Circulo(Ponto centro, int raio) {
        this.centro = centro;
        this.raio = raio;

        if (raio < 0){
            throw new IllegalArgumentException();
        }

        this.area = Math.PI * raio * raio;
    }

    public int getRaio() {
        return raio;
    }

    public Ponto getCentro() {
        return centro;
    }
}
