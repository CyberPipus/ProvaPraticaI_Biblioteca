package siga;

public class Emprestimo{

    private final Leitor solicitante;
    private final Exemplar item;
    private EstadoEmprestimo estado;

    public Emprestimo(Leitor solicitante, Exemplar item){
        this.solicitante = solicitante;
        this.item = item;
        this.estado = new EstadoReservado();
    }

    public String getSituacao(){
        return estado.getSituacao();
    }

    public void emprestar(){
        estado.emprestar(this);
    }

    public void restituir(){
        estado.restituir(this);
    }

    public void anular(){
        estado.anular(this);
    }

    void setEstado(EstadoEmprestimo estado){
        this.estado = estado;
    }

    public Leitor getSolicitante(){
        return solicitante;
    }

    public Exemplar getExemplar(){
        return item;
    }
}
