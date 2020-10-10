public class Pendular extends Comboio {
    public Pendular(String nome) {
        super(nome);
        this.servicoABordo = new ServicoSemEnjoos();
    }

    @Override
    public String toString() {
        String result = "Pendular " + nome + ", ";

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
}
