package siga;

import java.util.ArrayList;
import java.util.List;

public class CatalogoExemplares {

    private final Acervo acervo;

    public CatalogoExemplares(Acervo acervo) {
        this.acervo = acervo;
    }

    public List<Exemplar> listarDisponiveis() {
        List<Exemplar> disponiveis = new ArrayList<>();
        for (Exemplar item : acervo.consultarExemplares()) {
            if (item.isDisponivel()) {
                disponiveis.add(item);
            }
        }
        return disponiveis;
    }
}
