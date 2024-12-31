package PJBL.Produtos;

public class Aquatico extends Veiculo {
    private String tipoPropulsao;

    public Aquatico(int codVeiculo, String tipo, String marca, String modelo, int ano, int quilometragem, String cor,
                    int capacidadeDePassageiro, double preco, int quantidade, String tipoPropulsao) {
        super(codVeiculo, tipo, marca, modelo, ano, quilometragem, cor, capacidadeDePassageiro, preco, quantidade);
        this.tipoPropulsao = tipoPropulsao;
    }

    public String getTipoPropulsao() {
        return tipoPropulsao;
    }

    public void setTipoPropulsao(String tipoPropulsao) {
        this.tipoPropulsao = tipoPropulsao;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Tipo de Propulsão: " + tipoPropulsao);
    }
}