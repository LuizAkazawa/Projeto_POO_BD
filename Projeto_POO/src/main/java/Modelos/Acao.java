package Modelos;

public class Acao {
    private String sigla;
    private double cotacao;
    private String empresa_proprietaria;

    public Acao(String sigla, double cotacao, String empresa_proprietaria) {
        this.sigla = sigla;
        this.cotacao = cotacao;
        this.empresa_proprietaria = empresa_proprietaria;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public double getCotacao() {
        return cotacao;
    }

    public void setCotacao(double cotacao) {
        this.cotacao = cotacao;
    }

    public String getEmpresa_proprietaria() {
        return empresa_proprietaria;
    }

    public void setEmpresa_proprietaria(String empresa_proprietaria) {
        this.empresa_proprietaria = empresa_proprietaria;
    }
}
