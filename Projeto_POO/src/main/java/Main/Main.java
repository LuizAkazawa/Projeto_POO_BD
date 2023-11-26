package Main;

import DAO.AcaoDAO;
import DAO.ContaDAO;
import DAO.HomebrokerDAO;
import DAO.UserDAO;
import Modelos.*;
import excecoes.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        UserDAO userDAO= new UserDAO();
        HomebrokerDAO homebrokerDAO = new HomebrokerDAO();
        ContaDAO contaDAO = new ContaDAO();
        AcaoDAO acaoDAO = new AcaoDAO();

        //Registrando um homebroker para ser possível a administração do banco de dados
        Homebroker h1 = null;
        h1 = homebrokerDAO.loginHomebroker();


        int modoVisualizacao;
        int op;
        System.out.println("Digite o modo de visualização: ");
        System.out.println("1 - Administrador: ");
        System.out.println("2 - Usuario: ");
        System.out.println("3 - Finalizar programa: ");
        modoVisualizacao = sc.nextInt();

        while(modoVisualizacao != 3){
            switch(modoVisualizacao){
                //ADMIN
                case 1:
                    System.out.println("Digite o que deseja fazer: ");
                    System.out.println("1 - Registrar usuario ");
                    System.out.println("2 - Registrar Conta ");
                    System.out.println("3 - Mostrar todos os usuarios ");
                    System.out.println("4 - Mostrar todas as contas ");
                    System.out.println("5 - Remover conta ");
                    System.out.println("6 - Remover usuario ");
                    System.out.println("7 - Atualizar informações de alguma conta ");
                    System.out.println("8 - Atualizar informações de algum usuário ");
                    System.out.println("9 - Sair ");
                    op = sc.nextInt();
                    while(op != 9){
                        switch(op){
                            case 1:
                                //REGISTRA USUARIO
                                h1.registraUsuario();
                                break;
                            case 2:
                                //REGISTRA CONTA
                                h1.registrarConta();
                                break;
                            case 3:
                                //MOSTRAR TODOS OS USUARIOS
                                userDAO.selectUser();
                                break;
                            case 4:
                                //MOSTRAR TODAS AS CONTAS
                                contaDAO.selectConta();
                                break;
                            case 5:
                                //REMOVER CONTA
                                h1.deletarConta();
                                break;
                            case 6:
                                //DELETAR USUARIO
                                h1.deletarUsuario();
                                break;
                            case 7:
                                //UPDATE INFO DA CONTA
                                sc.nextLine();

                                String cpfSearch_conta;
                                int op_updateConta;
                                String columnMuda_conta = "";
                                String novosDados_conta;

                                System.out.println("Digite o cpf para atualizar as informações da conta: ");
                                cpfSearch_conta = sc.nextLine();
                                System.out.println("Qual informação deseja alterar? :");
                                System.out.println("1 - Username");
                                System.out.println("2 - Senha");
                                System.out.println("3 - Tipo da Conta");
                                op_updateConta = sc.nextInt();
                                while(op_updateConta != 1 && op_updateConta != 2 && op_updateConta != 3 ){
                                    System.out.println("Digite um valor válido: ");
                                    op_updateConta = sc.nextInt();
                                }
                                switch(op_updateConta){
                                    case 1:
                                        columnMuda_conta = "username";
                                        break;
                                    case 2:
                                        columnMuda_conta = "senha";
                                        break;
                                    case 3:
                                        columnMuda_conta = "tipo_conta";
                                        break;
                                }
                                sc.nextLine();
                                System.out.println("Digite os novos dados: ");
                                novosDados_conta = sc.nextLine();
                                contaDAO.updateConta(cpfSearch_conta, columnMuda_conta, novosDados_conta);
                                break;
                            case 8:
                                //UPDATE USUARIO
                                sc.nextLine();
                                String cpfSearch_usuario;
                                int op_updateUsuario;
                                String columnMuda_usuario = "";
                                String novosDados_usuario;

                                System.out.println("Digite o cpf para atualizar as informações da conta: ");
                                cpfSearch_usuario = sc.nextLine();
                                System.out.println("Qual informação deseja alterar? :");
                                System.out.println("1 - Nome");
                                System.out.println("2 - E-mail");
                                op_updateUsuario = sc.nextInt();

                                while(op_updateUsuario != 1 && op_updateUsuario != 2){
                                    System.out.println("Digite um valor válido: ");
                                    op_updateUsuario = sc.nextInt();
                                }
                                switch(op_updateUsuario){
                                    case 1:
                                        columnMuda_usuario = "nome";
                                        break;
                                    case 2:
                                        columnMuda_usuario = "email";
                                        break;
                                }
                                sc.nextLine();
                                System.out.println("Digite os novos dados: ");
                                novosDados_usuario = sc.nextLine();
                                userDAO.updateUser(cpfSearch_usuario, columnMuda_usuario, novosDados_usuario);
                                break;
                        }
                        System.out.println("Digite o que deseja fazer: ");
                        System.out.println("1 - Registrar usuario ");
                        System.out.println("2 - Registrar Conta ");
                        System.out.println("3 - Mostrar todos os usuarios ");
                        System.out.println("4 - Mostrar todas as contas ");
                        System.out.println("5 - Remover conta ");
                        System.out.println("6 - Remover usuario ");
                        System.out.println("7 - Atualizar informações de alguma conta ");
                        System.out.println("8 - Atualizar informações de algum usuário ");
                        System.out.println("9 - Sair ");
                        op = sc.nextInt();
                    }
                    break;
                //USUARIO
                case 2:
                    sc.nextLine();
                    String username_login;
                    String senha_login;
                    Conta conta_logado = null;

                    //PARTE DE LOGIN
                    while(conta_logado == null){
                        System.out.println("Digite o username: ");
                        username_login = sc.nextLine();
                        System.out.println("Digite a senha: ");
                        senha_login = sc.nextLine();
                        conta_logado = contaDAO.contaLoga(username_login, senha_login);
                        try{
                            System.out.println("================= " + conta_logado.getUsername() + " está logado! =================");
                        }catch(NullPointerException e){
                            System.out.println("Usuario ou senha inválida");
                        }
                    }

                    System.out.println("Selecione a ação que deseja executar: ");
                    System.out.println("0 - Sair: ");
                    System.out.println("1 - Depositar: ");
                    System.out.println("2 - Sacar: ");
                    System.out.println("3 - Mostrar saldo: ");
                    System.out.println("4 - Mostrar ações: ");
                    System.out.println("5 - Comprar ação: ");
                    System.out.println("6 - Vender ação: ");
                    System.out.println("7 - Mostrar ações que possuo: ");
                    System.out.println("8 - Pedir análise: ");
                    System.out.println("9 - Pedir recomendação: ");
                    System.out.println("10 - Conversar com conselheiro: ");
                    System.out.println("11 - Chat com investidores: ");
                    System.out.println("12 - Mostrar informações da conta: ");
                    op = sc.nextInt();
                    while(op != 0){
                        sc.nextLine();
                        switch(op){
                            case 1:
                                //Depositar
                                double deposito;
                                System.out.println("Deposito em R$: ");
                                deposito = sc.nextDouble();
                                conta_logado.depositar(deposito);
                                conta_logado.mostrar_saldo();
                                break;
                            case 2:
                                //sacar
                                double sacar;
                                System.out.println("Sacar em R$: ");
                                sacar = sc.nextDouble();
                                try{
                                    conta_logado.sacar(sacar);
                                }catch(SaldoInsuficiente e){
                                    System.out.println("Saldo Insuficiente !!!");
                                }
                                conta_logado.mostrar_saldo();
                                break;

                            case 3:
                                //MOSTRAR O SALDO ATUAL
                                conta_logado.mostrar_saldo();
                                break;

                            case 4:
                                //MOSTRA AÇÕES
                                acaoDAO.selectAcao();
                                break;

                            case 5:
                                //Compra ação
                                conta_logado.comprarAcao(conta_logado);
                                break;
                            case 6:
                                //vender açoes
                                conta_logado.venderAcao(conta_logado);
                                break;

                            case 7:
                                //mostrar ações que possuo
                                conta_logado.mostra_acoes(conta_logado);
                                break;

                            case 8:
                                //pedir análise
                                conta_logado.pedir_analise();
                                break;

                            case 9:
                                //pedir recomendação
                                conta_logado.pedir_recomendacao();
                                break;

                            case 10:
                                // conversar com conselheiro
                                conta_logado.conversar_conselheiro();
                                break;

                            case 11:
                                //chat_investidores
                                conta_logado.chat_investidores();
                                break;

                            case 12:
                                //mostra info
                                conta_logado.mostrar_info();
                                break;
                        }

                        System.out.println("Selecione a ação que deseja executar: ");
                        System.out.println("0 - Sair: ");
                        System.out.println("1 - Depositar: ");
                        System.out.println("2 - Sacar: ");
                        System.out.println("3 - Mostrar saldo: ");
                        System.out.println("4 - Mostrar ações: ");
                        System.out.println("5 - Comprar ação: ");
                        System.out.println("6 - Vender ação: ");
                        System.out.println("7 - Mostrar ações que possuo: ");
                        System.out.println("8 - Pedir análise: ");
                        System.out.println("9 - Pedir recomendação: ");
                        System.out.println("10 - Conversar com conselheiro: ");
                        System.out.println("11 - Chat com investidores: ");
                        System.out.println("12 - Mostrar informações da conta: ");

                        op = sc.nextInt();
                    }
                    break; //break do modo visualizacao

            }
            System.out.println("Digite o modo de visualização: ");
            System.out.println("1 - Admin: ");
            System.out.println("2 - Usuario: ");
            System.out.println("3 - Finalizar programa: ");
            modoVisualizacao = sc.nextInt();
        }




    }
}