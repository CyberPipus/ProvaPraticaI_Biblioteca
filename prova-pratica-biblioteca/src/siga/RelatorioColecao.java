package siga;

import java.util.List;

public class RelatorioColecao {
    private final Acervo acervo;

    public RelatorioColecao(Acervo acervo) {
        this.acervo = acervo;
    }

    public List<Exemplar> listarTodos() {
        return acervo.consultarExemplares();
    }

    public void imprimir() {
        System.out.println("   Situacao segundo o relatorio:");
        for (Exemplar item : listarTodos()) {
            System.out.println("     " + item + " - "
                    + (item.isDisponivel() ? "disponivel" : "baixado"));
        }
    }
}
