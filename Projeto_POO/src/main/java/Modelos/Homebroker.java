package Modelos;

import DAO.ContaDAO;
import DAO.UserDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Homebroker {
    Scanner sc = new Scanner(System.in);
    UserDAO userDAO = new UserDAO();
    ContaDAO contaDAO = new ContaDAO();
    private String url;
    private String ip;

    public Homebroker(String url, String ip) {
        this.url = url;
        this.ip = ip;
    }


    public void registraUsuario (){
        String nome;
        String email;
        String cpf;
        String data_nasc_string;
        String ipHomebroker;

        System.out.println("Registrando Usuário...");
        System.out.println("======================");
        System.out.println("Nome: ");
        nome = sc.nextLine();
        System.out.println("email: ");
        email = sc.nextLine();
        System.out.println("CPF: ");
        cpf = sc.nextLine();
        System.out.println("Data_nascimento (AA-MM-DD): ");
        data_nasc_string = sc.nextLine();
        System.out.println("IP do Homebroker que a conta está: ");
        ipHomebroker = sc.nextLine();

        //Date data_nasc = Date.valueOf(data_nasc_string);
        Usuario u = new Usuario(nome, email, cpf, data_nasc_string, ipHomebroker);
        userDAO.insertUser(u);
    }


    public void registrarConta() { //NECESSÁRIO PASSAR O USUÁRIO PARA LINKAR O CPF DO USUÁRIO COM A CONTA
        String username;
        String senha;
        String tipo_conta;
        double saldo;
        String data_criacao;
        String cpfUsuario;

        System.out.println("Cadastrando conta ... ");
        System.out.println("======================");
        System.out.println("Username: ");
        username = sc.nextLine();
        System.out.println("Senha: ");
        senha = sc.nextLine();
        System.out.println("Tipo de conta: ");
        tipo_conta = sc.nextLine();
        saldo = 0;
        System.out.println("Data de criação da conta: ");
        data_criacao = sc.nextLine();
        System.out.println("CPF do usuario: ");
        cpfUsuario = sc.nextLine();

        Conta c;
        if (tipo_conta.equals("Black")) {
            c = new Conta_Black(username, senha, tipo_conta, saldo, data_criacao, cpfUsuario);
            contaDAO.insertConta(c);
        }else if(tipo_conta.equals("Gold")){
            c = new Conta_Gold(username, senha, tipo_conta, saldo, data_criacao, cpfUsuario);
            contaDAO.insertConta(c);
        }else if(tipo_conta.equals("Gratuita")){
            c = new Conta_Gratuita(username, senha, tipo_conta, saldo, data_criacao, cpfUsuario);
            contaDAO.insertConta(c);
        }

    }

    public void deletarUsuario() {
        System.out.println("Digite o CPF do usuário a ser deletado: ");
        String cpfDeletar = sc.nextLine();
        userDAO.deleteUser(cpfDeletar);
    }

    public void deletarConta() {
        System.out.println("Digite o CPF do usuário proprietário da conta a ser deletada: ");
        String cpfDeletar = sc.nextLine();
        contaDAO.deleteConta(cpfDeletar);
    }




    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
