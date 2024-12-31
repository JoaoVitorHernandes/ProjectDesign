package PJBL.Produtos;

public class Aviao extends Aereo {
    private int numeroDeMotores;
    private String tipoDeAviao;
    private double alcanceDeVoo;

    public Aviao(int codVeiculo, String tipo, String marca, String modelo, int ano, int quilometragem, String cor,
                 int capacidadeDePassageiro, double preco, int quantidade, String tipoPropulsao, int numeroDeMotores,
                 String tipoDeAviao, double alcanceDeVoo) {
        super(codVeiculo, tipo, marca, modelo, ano, quilometragem, cor, capacidadeDePassageiro, preco, quantidade,
                tipoPropulsao);
        this.numeroDeMotores = numeroDeMotores;
        this.tipoDeAviao = tipoDeAviao;
        this.alcanceDeVoo = alcanceDeVoo;
    }

    public int getNumeroDeMotores() {
        return numeroDeMotores;
    }

    public void setNumeroDeMotores(int numeroDeMotores) {
        this.numeroDeMotores = numeroDeMotores;
    }

    public String getTipoDeAviao() {
        return tipoDeAviao;
    }

    public void setTipoDeAviao(String tipoDeAviao) {
        this.tipoDeAviao = tipoDeAviao;
    }

    public double getAlcanceDeVoo() {
        return alcanceDeVoo;
    }

    public void setAlcanceDeVoo(double alcanceDeVoo) {
        this.alcanceDeVoo = alcanceDeVoo;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Número de Motores: " + numeroDeMotores);
        System.out.println("Tipo de Avião: " + tipoDeAviao);
        System.out.println("Alcance de Voo: " + alcanceDeVoo + " km");
    }
}