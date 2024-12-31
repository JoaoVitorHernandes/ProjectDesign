package PJBL.Strategy;

public class PagamentoDinheiro implements PagamentoStrategy {
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$" + valor + " realizado em Dinheiro.");
    }
}