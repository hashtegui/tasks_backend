package org.mestresolutions.tasks.domain.tasks;

public class GetAllTasksQueryDto {

    private boolean emAndamento = false;
    private boolean finalizada = false;
    private boolean excluido = false;

    public GetAllTasksQueryDto() {
    }

    public boolean isEmAndamento() {
        return emAndamento;
    }

    public void setEmAndamento(boolean emAndamento) {
        this.emAndamento = emAndamento;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluidos) {
        this.excluido = excluidos;
    }

}
