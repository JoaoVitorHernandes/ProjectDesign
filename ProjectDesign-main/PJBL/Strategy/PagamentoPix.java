package PJBL.Strategy;

public class PagamentoPix implements PagamentoStrategy {
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento de R$" + valor + " realizado via Pix.");
    }
}
