package Main;

import DAO.ContaDAO;
import DAO.HomebrokerDAO;
import DAO.UserDAO;
import Modelos.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /*
        IMPLEMENTANDO CADASTRAR_CONTA NO HOMEBROKER
        FAZER AÇÕES
        FAZER FIIS
        FAZER RANDOMIZAÇÃO DAS COTAÇÕES

        CADASTRO DE USUARIOS E DE CONTAS VAI SER ESTATICO
        FOCAR MAIS NAS AÇÕES
        FAZER UM SISTEMA DE LOGIN COM USERNAME E SENHA

         */

        UserDAO userDAO= new UserDAO();
        HomebrokerDAO homebrokerDAO = new HomebrokerDAO();
        ContaDAO contaDAO = new ContaDAO();

        Homebroker h1 = new Homebroker("www.naosei.com", "1");

        Usuario u1 = new Usuario("a", "u2@gmail.com","123", "2023-11-20", "1"); //ANO-MES-DIA
        Usuario u2 = new Usuario("c", "u2@gmail.com", "3123","2023-11-20", "1");

        Conta c1 = new Conta_Black("Luiz", "12345", "Black", 12345.20, "2023-10-12", "123");
        Conta c2 = new Conta_Gold("Marie", "54321", "Gold", 145.20, "2023-11-30", "3123");

        homebrokerDAO.insertHB(h1);
        userDAO.insertUser(u1);
        userDAO.insertUser(u2);
        contaDAO.insertConta(c1);
        contaDAO.insertConta(c2);


        userDAO.selectUser();
        contaDAO.selectUser();

        Usuario teste = userDAO.coletaInfo("123");
        System.out.println(teste.getEmail());



/*
        int modoVisualizacao;
        int op;
        modoVisualizacao = sc.nextInt();

        while(modoVisualizacao != 3){
            switch(modoVisualizacao){
                //ADMIN
                case 1:
                    op = sc.nextInt();
                    while(op != 8){
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
                                contaDAO.selectUser();
                                break;
                            case 5:
                                //REMOVER CONTA
                                h1.deletarConta();
                                break;
                            case 6:
                                //UPDATE INFO DA CONTA

                                break;
                            case 7:
                                //UPDATE USUARIO

                                break;
                            case 8:
                                //DELETAR USUARIO
                                h1.deletarUsuario();
                                break;
                        }
                        op = sc.nextInt();
                    }
                    break;
                //USUARIO
                case 2:
                    op = sc.nextInt();
                    while(op != 8){
                        switch(op){
                            case 1:

                            case 2:
                        }

                        op = sc.nextInt();
                    }
                    break;


            }
        }



        */
    }
}