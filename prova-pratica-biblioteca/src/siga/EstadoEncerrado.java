package siga;

public class EstadoEncerrado implements EstadoEmprestimo{
    @Override
    public String getSituacao(){
        return "ENCERRADO";
    }

    @Override
    public void emprestar(Emprestimo emprestimo){
        throw new IllegalStateException("Não é possível emprestar aquilo que já está ENCERRADO.");
    }

    @Override
    public void restituir(Emprestimo emprestimo){
        throw new IllegalStateException("Não é possível restituir aquilo que já está ENCERRADO.");
    }

    @Override
    public void anular(Emprestimo emprestimo){
        throw new IllegalStateException("Não é possível anular aquilo que já está ENCERRADO.");
    }
}
