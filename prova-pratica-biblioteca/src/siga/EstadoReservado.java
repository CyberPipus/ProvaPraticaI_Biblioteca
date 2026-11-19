package siga;

public class EstadoReservado implements EstadoEmprestimo {
    @Override
    public String getSituacao() {
        return "RESERVADO";
    }

    @Override
    public void emprestar(Emprestimo emprestimo) {
        emprestimo.setEstado(new EstadoEmprestado());
    }

    @Override
    public void restituir(Emprestimo emprestimo) {
        throw new IllegalStateException("Não é possível restituir aquilo que está RESERVADO.");
    }

    @Override
    public void anular(Emprestimo emprestimo) {
        emprestimo.setEstado(new EstadoEncerrado());
    }
}
