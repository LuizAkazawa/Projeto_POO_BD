package Modelos;

import DAO.ContaDAO;
import excecoes.SaldoInsuficiente;

import java.util.Date;

public abstract class Conta {
    private static int cod_conta;
    private String username;
    private String senha;
    private String tipo_conta;
    private double saldo;
    private Date data_criacao;
    private String cpfUsuario;

    ContaDAO contaDAO = new ContaDAO();

    public Conta(String username, String senha, String tipo_conta, double saldo, String data_criacao, String cpfUsuario) {
        this.username = username;
        this.senha = senha;
        this.tipo_conta = tipo_conta;
        this.saldo = saldo;
        this.data_criacao = java.sql.Date.valueOf(data_criacao);
        this.cpfUsuario = cpfUsuario;
    }

    public void sacar(double qtd) throws SaldoInsuficiente {
        if(saldo >= qtd){
            this.saldo -= qtd;
        }else{
            throw new SaldoInsuficiente();
        }
    }
    public void depositar(double qtd){
        this.saldo += qtd;
    }

    public void mostrar_saldo(){
        System.out.println("Saldo = " + String.format("%.2f", this.saldo));
    }

    public void vender_acao(String acao){

    }

    public void mostrar_info(){

    }

    public void pedir_analise(){

    }

    public void pedir_recomendacao(){

    }

    public void conversar_conselheiro(){

    }

    public void chat_investidores(){

    }

    public int getCod_conta() {
        return cod_conta;
    }

    public void setCod_conta(int cod_conta) {
        this.cod_conta = cod_conta;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo_conta() {
        return tipo_conta;
    }

    public void setTipo_conta(String tipo_conta) {
        this.tipo_conta = tipo_conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public java.sql.Date getData_criacao() {
        return (java.sql.Date) data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }
}
