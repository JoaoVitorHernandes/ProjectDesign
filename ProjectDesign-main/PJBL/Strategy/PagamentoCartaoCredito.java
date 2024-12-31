package PJBL.Strategy;

public class PagamentoCartaoCredito implements PagamentoStrategy {
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$" + valor + " realizado com Cartão de Crédito.");
    }
}