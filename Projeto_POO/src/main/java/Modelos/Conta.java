package Modelos;

import DAO.ContaDAO;
import excecoes.SaldoInsuficiente;

import java.util.Date;
import java.util.Scanner;

public abstract class Conta {
    private static int cod_conta;
    private String username;
    private String senha;
    private String tipo_conta;
    private double saldo;
    private Date data_criacao;
    private String cpfUsuario;

    ContaDAO contaDAO = new ContaDAO();
    Scanner sc = new Scanner(System.in);

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

    public void comprarAcao(Conta c) {
        int numAcao;
        String sigla = "";
        int qtd;
        double custo;
        Acao a = null;

        System.out.println("Selecione uma ação: ");
        System.out.println("1 - Fleury");
        System.out.println("2 - Magalu");
        System.out.println("3 - Banco do Brasil");
        numAcao = sc.nextInt();

        while (numAcao < 1 || numAcao > 3) {
            System.out.println("Digite um numero valido: ");
            numAcao = sc.nextInt();
        }
        switch (numAcao) {
            case 1:
                sigla = "FLRY3";
                break;
            case 2:
                sigla = "MGLU3";
                break;
            case 3:
                sigla = "BBAS3";
                break;
        }
        System.out.println("Digite a quantidade: ");
        qtd = sc.nextInt();

        a = contaDAO.infoAcoes(sigla);
        custo = a.getCotacao() * qtd;
        System.out.println("CUSTO DA TRANSAÇÃO = " + custo);
        try{
            c.sacar(custo);
            contaDAO.insertConta_has_Acoes(c, sigla, qtd);
            System.out.println("Transação realizada !");
        }catch(SaldoInsuficiente e){
            System.out.println("Saldo insuficiente para cumprir a transação. O saldo não foi alterado");
            System.out.println("Saldo atual: " + this.saldo);
        }
    }

    public void venderAcao(){

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
