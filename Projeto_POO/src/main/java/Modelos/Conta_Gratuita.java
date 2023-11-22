package Modelos;

import java.util.Date;

public class Conta_Gratuita extends Conta{

    public Conta_Gratuita(String username, String senha, String tipo_conta, double saldo, String data_criacao, String cpfUsuario) {
        super(username, senha, tipo_conta, saldo, data_criacao, cpfUsuario);
    }

    @Override
    public void pedir_analise(){
        System.out.println("Você não pode pedir análise! Faça o upgrade para a conta GOLD ou BLACK " +
                "para habilitar essa função");
    }

    @Override
    public void pedir_recomendacao(){
        System.out.println("Você não pode pedir recomendação! Faça o upgrade para a conta GOLD ou BLACK " +
                "para habilitar essa função");
    }

    @Override
    public void conversar_conselheiro(){
        System.out.println("Você não pode conversar com um conselheiro! Faça o upgrade para a conta BLACK " +
                "para habilitar essa função");
    }

    @Override
    public void chat_investidores(){
        System.out.println("Você não pode entrar no chat com outros investidores! Faça o upgrade para a conta BLACK " +
                "para habilitar essa função");
    }

}
