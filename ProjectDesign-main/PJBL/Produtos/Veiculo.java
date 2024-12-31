package PJBL.Produtos;

public abstract class Veiculo {
    private int codVeiculo;
    private String tipo;
    private String marca;
    private String modelo;
    private int ano;
    private int quilometragem;
    private String cor;
    private int capacidadeDePassageiro;
    private double preco;
    private int quantidade;

    public Veiculo(int codVeiculo, String tipo, String marca, String modelo, int ano, int quilometragem, String cor,
                   int capacidadeDePassageiro, double preco, int quantidade) {
        this.codVeiculo = codVeiculo;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.quilometragem = quilometragem;
        this.cor = cor;
        this.capacidadeDePassageiro = capacidadeDePassageiro;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Getters and Setters

    public int getCodVeiculo() {
        return codVeiculo;
    }

    public void setCodVeiculo(int codVeiculo) {
        this.codVeiculo = codVeiculo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getCapacidadeDePassageiro() {
        return capacidadeDePassageiro;
    }

    public void setCapacidadeDePassageiro(int capacidadeDePassageiro) {
        this.capacidadeDePassageiro = capacidadeDePassageiro;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void displayInfo() {
        System.out.println("--Veículo a seguir foi cadastrado--");
        System.out.println("Código do Veículo: " + codVeiculo);
        System.out.println("Tipo: " + tipo);
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano: " + ano);
        System.out.println("Quilometragem: " + quilometragem);
        System.out.println("Cor: " + cor);
        System.out.println("Capacidade de Passageiro: " + capacidadeDePassageiro);
        System.out.println("Preço: " + preco);
        System.out.println("Quantidade: " + quantidade);
    }
}
