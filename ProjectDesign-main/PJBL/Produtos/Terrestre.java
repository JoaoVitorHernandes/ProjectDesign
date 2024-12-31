package PJBL.Produtos;

public class Terrestre extends Veiculo {
    private int numRodas;

    public Terrestre(int codVeiculo, String tipo, String marca, String modelo, int ano, int quilometragem, String cor,
                     int capacidadeDePassageiro, double preco, int quantidade, int numRodas) {
        super(codVeiculo, tipo, marca, modelo, ano, quilometragem, cor, capacidadeDePassageiro, preco, quantidade);
        this.numRodas = numRodas;
    }

    // Getters and Setters

    public int getNumRodas() {
        return numRodas;
    }

    public void setNumRodas(int numRodas) {
        this.numRodas = numRodas;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Número de Rodas: " + numRodas);
    }
}
