package siga;

public class EstadoEmprestado implements EstadoEmprestimo{
    @Override
    public String getSituacao(){
        return "EMPRESTADO";
    }

    @Override
    public void emprestar(Emprestimo emprestimo){
        throw new IllegalStateException("Não é possível emprestar aquilo que já está EMPRESTADO.");
    }

    @Override
    public void restituir(Emprestimo emprestimo){
        emprestimo.setEstado(new EstadoEncerrado());
    }

    @Override
    public void anular(Emprestimo emprestimo){
        throw new IllegalStateException("Não é possível anular aquilo que já está EMPRESTADO.");
    }
}
