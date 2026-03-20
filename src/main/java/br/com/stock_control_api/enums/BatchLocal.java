package br.com.stock_control_api.enums;

public enum BatchLocal {
    SALES_AREA("AREA DE VENDA"),
    STOCK("ESTOQUE/CÂMARA FRIA");

    private String descricao;
    private BatchLocal(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
