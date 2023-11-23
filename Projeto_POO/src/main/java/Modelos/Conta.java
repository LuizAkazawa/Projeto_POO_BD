package Modelos;

import DAO.ContaDAO;
import excecoes.NaoPossuiAcao;
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
        System.out.println("===================================");
        System.out.println("Saldo = " + String.format("%.2f", this.saldo));
        System.out.println("===================================");
    }

    public void comprarAcao(Conta c) {
        int numAcao;
        String sigla = "";
        int qtd;
        double custo;
        Acao a = null;

        System.out.println("===================================");
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
        System.out.println("===================================");
        a = contaDAO.infoAcoes(sigla);
        custo = a.getCotacao() * qtd;
        System.out.println("Custo da transação = " + String.format("%.2f", custo));
        try{
            c.sacar(custo);
            contaDAO.insertConta_has_Acoes(c, sigla, qtd);
            System.out.println("Transação realizada !");
        }catch(SaldoInsuficiente e){
            System.out.println("Saldo insuficiente para cumprir a transação. O saldo não foi alterado");
            System.out.println("Saldo atual: " + this.saldo);
        }
        System.out.println("===================================");
    }

    public void venderAcao(Conta c){
        int numAcao;
        String sigla = "";
        int qtdVendida;
        double lucro;
        Acao a = null;
        System.out.println("===================================");
        System.out.println("Selecione uma ação a ser vendida: ");
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
        qtdVendida = sc.nextInt();

        a = contaDAO.infoAcoes(sigla);
        lucro = a.getCotacao() * qtdVendida;
        try{
            int qtdAcoes = contaDAO.checkQtdAcoes(c, sigla);
            if(qtdAcoes >= qtdVendida){
                c.depositar(lucro);
                contaDAO.vendeConta_has_Acoes(c, sigla, qtdAcoes - qtdVendida);
                System.out.println("Transação realizada !");
                System.out.println("DINHEIRO OBTIDO = " + String.format("%.2f", lucro));
            }else{
                throw new NaoPossuiAcao();
            }
        }catch(NaoPossuiAcao e){
            System.out.println("Você não possui ações suficientes !");
        }
        System.out.println("===================================");
    }

    public void mostra_acoes(Conta c){
        contaDAO.mostraAcoesPossui(c);
    }

    public void mostrar_info(){
        System.out.println("===================================");
        System.out.println("Username: " + username);
        System.out.println("Senha: " + senha);
        System.out.println("Tipo da conta: " + tipo_conta);
        System.out.println("Saldo: " + String.format("%.2f", this.saldo));
        System.out.println("Data de criação: " + data_criacao);
        System.out.println("CPF: " + cpfUsuario);
        System.out.println("===================================");
    }
    public void pedir_analise(){
        System.out.println("===================================");
        System.out.println("É possível que o índice IBOVESPA suba graças à melhora nas exportações das empresas brasileiras após a pandemia");
        System.out.println("===================================");
    }

    public void pedir_recomendacao(){
        System.out.println("===================================");
        System.out.println("A ação da empresa Fleury(FLRY3) pode ser um grande ativo a ser mantido na carteira de investimentos nos próximos anos");
        System.out.println("===================================");
    }

    public void conversar_conselheiro(){
        System.out.println("===================================");
        System.out.println("Entrando em contato com o conselheiro ...");
        System.out.println("===================================");
    }

    public void chat_investidores(){
        System.out.println("===================================");
        System.out.println("Entrando no chat com Investidores ...");
        System.out.println("===================================");
    }

    public int getCod_conta() {
        return cod_conta;
    }

    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo_conta() {
        return tipo_conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public java.sql.Date getData_criacao() {
        return (java.sql.Date) data_criacao;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public static void setCod_conta(int cod_conta) {
        Conta.cod_conta = cod_conta;
    }
}
