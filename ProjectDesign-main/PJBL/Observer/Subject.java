package PJBL.Observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observadores = new ArrayList<>();

    public void adicionarObservador(Observer observador) {
        observadores.add(observador);
    }

    public void removerObservador(Observer observador) {
        observadores.remove(observador);
    }

    public void notificarTodos(String mensagem) {
        for (Observer observador : observadores) {
            observador.notificar(mensagem);
        }
    }
}
