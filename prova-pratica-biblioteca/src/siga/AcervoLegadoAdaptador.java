package siga;

import java.util.ArrayList;

import java.util.List;

public class AcervoLegadoAdaptador implements Acervo {

    private final AcervoLegadoWS legado;

    public AcervoLegadoAdaptador(AcervoLegadoWS legado) {
        this.legado = legado;
    }

    @Override
    public List<Exemplar> consultarExemplares() {
        List<Exemplar> exemplares = new ArrayList<>();
        String[][] matriz = legado.consultarExemplares();
        for (String[] linha : matriz) {
            Exemplar item = new Exemplar(linha[0], linha[1]);
            // Tratamento unico que aceita maiuscula e minuscula
            item.setDisponivel("D".equalsIgnoreCase(linha[2]));
            exemplares.add(item);
        }
        return exemplares;
    }
}
