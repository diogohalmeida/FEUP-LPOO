import java.util.ArrayList;
import java.util.Objects;

public class Comboio {
    protected String nome;
    protected ArrayList<Carruagem> carruagens;
    protected int numLugares;
    protected int numPassageiros;
    protected ServicoABordo servicoABordo;


    public Comboio(String nome) {
        this.nome = nome;
        this.carruagens = new ArrayList<>();
        this.numLugares = 0;
        this.numPassageiros = 0;
        this.servicoABordo = new ServicoRegular();
    }

    public String getNome() {
        return nome;
    }

    public int getNumLugares() {
        return numLugares;
    }

    public int getNumCarruagens() {
        return carruagens.size();
    }

    public void addCarruagem(Carruagem carruagem) {
        carruagens.add(carruagem);
        this.numLugares += carruagem.getNumLugares();
    }

    public Carruagem getCarruagemByOrder(int i) {
        return carruagens.get(i);
    }

    public void removeAllCarruagens(int i) {
        for (Carruagem carruagem: carruagens){
            if (carruagem.getNumLugares() == i) {
                this.numLugares -= i;
            }
        }
        carruagens.removeIf(carruagem -> carruagem.getNumLugares() == i);


    }

    public int getNumPassageiros() {
        return numPassageiros;
    }

    public int getLugaresLivres() {
        return numLugares - numPassageiros;
    }

    public void addPassageiros(int i) throws PassengerCapacityExceeded {
        if (numPassageiros + i > numLugares){
            throw new PassengerCapacityExceeded();
        }
        numPassageiros += i;
    }

    public void removePassageiros(int i) {
        numPassageiros -= i;
    }

    public void removePassageiros() {
        numPassageiros = 0;
    }


    @Override
    public String toString() {
        String result = "Comboio " + nome + ", ";

        if (getNumCarruagens() > 1 || getNumCarruagens() == 0){
            result += getNumCarruagens() + " carruagens, ";
        }
        else{
            result += getNumCarruagens() + " carruagem, ";
        }

        if (numLugares > 1 || numLugares == 0){
            result += numLugares + " lugares, ";
        }
        else{
            result += numLugares + " lugar, ";
        }

        if (numPassageiros > 1 || numPassageiros == 0){
            result += numPassageiros + " passageiros";
        }
        else{
            result += numPassageiros + " passageiro";
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comboio)) return false;
        Comboio comboio = (Comboio) o;
        return numLugares == comboio.numLugares &&
                numPassageiros == comboio.numPassageiros &&
                carruagens.equals(comboio.carruagens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carruagens, numLugares, numPassageiros);
    }

    public ServicoABordo getServicoABordo() {
        return servicoABordo;
    }

    public void setServicoABordo(ServicoABordo servicoABordo) {
        this.servicoABordo = servicoABordo;
    }

    public String getDescricaoServico() {
        return servicoABordo.getDescricaoServico();
    }
}
