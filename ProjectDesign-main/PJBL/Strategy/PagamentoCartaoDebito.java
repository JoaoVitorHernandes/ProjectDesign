package PJBL.Strategy;

public class PagamentoCartaoDebito implements PagamentoStrategy {
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$" + valor + " realizado com Cartão de Débito.");
    }
}