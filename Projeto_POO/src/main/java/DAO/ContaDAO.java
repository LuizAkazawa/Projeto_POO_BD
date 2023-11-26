package DAO;

import Modelos.*;
import excecoes.NaoPossuiAcao;
import excecoes.SaldoInsuficiente;

import java.sql.SQLException;
import java.util.ArrayList;

public class ContaDAO extends ConnectionDAO{


    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertConta(Conta c) {

        connectToDB();

        String sql = "INSERT INTO conta (username,senha,tipo_conta,saldo,data_criacao,Usuario_cpfUsuario) values(?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, c.getUsername());
            pst.setString(2, c.getSenha());
            pst.setString(3, c.getTipo_conta());
            pst.setDouble(4, c.getSaldo());
            pst.setDate(5, c.getData_criacao());
            pst.setString(6, c.getCpfUsuario());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro1_conta: " + exc.getMessage());
            System.out.println("aaaaaaaaaaaaaaa");
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro2_conta: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //COLETA TODAS AS INFORMAÇÕES DA AÇÃO QUE POSSUI A SIGLA DIGITADA
    public Acao infoAcoes(String sigla){
        connectToDB();
        sigla = "'" + sigla + "'";
        String sql = "SELECT * FROM Acoes WHERE sigla = " + sigla;
        try{
            st = con.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            Acao a = null;
            a = new Acao(rs.getString("sigla"), rs.getDouble("cotacao"), rs.getString("empresa_proprietaria"));
            return a;
        } catch (SQLException e) {
            System.out.println("ErroCheck_conta: " + e);
            return null;
        }
    }

    //RETORNA A QUANTIDADE DE AÇÕES DA SIGLA DIGITADA
    public Acao qtdAcoes(Conta c, String sigla){

        connectToDB();
        String sql = "SELECT quantidade FROM Conta_has_Acoes WHERE Acoes_siglaAcoes = " + sigla;
        try{
            st = con.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            Acao a = null;
            a = new Acao(rs.getString("sigla"), rs.getDouble("cotacao"), rs.getString("empresa_proprietaria"));
            return a;
        } catch (SQLException e) {
            System.out.println("ErroCheck2_conta: " + e);
            return null;
        }
    }

    //CONFERE SE A QUANTIDADE DE AÇÕES DA SIGLA DIGITADA QUE A CONTA POSSUI
    public int  hasAcoes(Conta c, String sigla){
        connectToDB();
        sigla = "'" + sigla + "'";
        String sql = "SELECT quantidade FROM Conta_has_Acoes WHERE Acoes_siglaAcoes = " + sigla + " and Conta_codConta = " + c.getCod_conta();
        try{
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ErroCheck3_conta: " + e);
            return 0;
        }
        return 0;
    }

    //MÉTODO PARA AUXILIAR NA COMPRA DAS AÇÕES
    public boolean updateConta_has_Acoes(Conta c, String sigla, int qtd) {
        connectToDB();
        String sql = "UPDATE Conta_has_Acoes SET quantidade=? where Acoes_siglaAcoes=? and Conta_codConta=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, qtd);
            pst.setString(2,sigla);
            pst.setInt(3,c.getCod_conta());
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro4_conta = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro5_conta: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //MÉTODO PARA AUXILIAR NA COMPRA
    public boolean insertConta_has_Acoes(Conta c, String sigla, int qtd) {

        connectToDB();
        Acao a = infoAcoes(sigla);

        String sql = "INSERT INTO Conta_has_Acoes (Conta_codConta, Acoes_siglaAcoes, quantidade) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            if(hasAcoes(c, sigla) != 0){
                updateConta_has_Acoes(c, sigla, qtd + hasAcoes(c, sigla));
            }else{
                pst.setInt(1, c.getCod_conta());
                pst.setString(2, a.getSigla());
                pst.setInt(3, qtd);
                pst.execute();
            }
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro6_conta: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro7_conta: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //MÉTODO PARA AUXILIAR NA VENDA DAS AÇÕES
    public boolean vendeConta_has_Acoes(Conta c, String sigla, int qtdAlterada) {

        connectToDB();
        Acao a = infoAcoes(sigla);

        String sql = "UPDATE Conta_has_Acoes SET quantidade =? WHERE Conta_codConta =? and Acoes_siglaAcoes=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, qtdAlterada);
            pst.setInt(2, c.getCod_conta());
            pst.setString(3, sigla);
            updateConta_has_Acoes(c, sigla, qtdAlterada);
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro6_conta: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro7_conta: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean updateConta(String cpf, String mudaColuna, String mudanca) {
        connectToDB();
        String sql = "UPDATE conta SET " + mudaColuna + "=? where Usuario_cpfUsuario=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, mudanca);
            pst.setString(2,cpf);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro8_conta = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro9_conta: " + exc.getMessage());
            }
        }
        return sucesso;
    }


    //DELETE
    public boolean deleteConta(String cpfUsuario) {
        connectToDB();
        String sql = "DELETE FROM conta where Usuario_cpfUsuario=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cpfUsuario);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //CHECA QUANTAS AÇÕES COMPRADAS A CONTA TEM DA AÇÃO "sigla"
    public int checkQtdAcoes (Conta c, String sigla){
        connectToDB();
        sigla = "'" + sigla + "'";
        String sql = "SELECT quantidade FROM Conta_has_Acoes WHERE Conta_codConta = " + c.getCod_conta() + " and Acoes_siglaAcoes = " + sigla;
        try{
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                return rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            System.out.println("ErroCheckQtdAcoes: " + e);
        }
        return 0;
    }

    //MOSTRA AS AÇÕES QUE A CONTA POSSUI
    public void mostraAcoesPossui(Conta c){
        connectToDB();
        String sql = "SELECT * FROM Conta_has_Acoes WHERE Conta_codConta = " + c.getCod_conta();
        try{
            st = con.createStatement();
            rs = st.executeQuery(sql);
            String siglaAcao;
            int qtd;
            double teste;
            System.out.println("===================================");
            while(rs.next()){
                siglaAcao = rs.getString("Acoes_siglaAcoes");
                //teste = getPriceAcao(siglaAcao);
                qtd = rs.getInt("quantidade");
                System.out.println(siglaAcao + " : " + qtd + " ações");
                //System.out.println(teste);

            }
        } catch (SQLException e) {
            System.out.println("ErroMostraAcao: " + e);
        }
        System.out.println("===================================");
    }

    //SELECT
    public ArrayList<Conta> selectConta() {
        ArrayList<Conta> contas = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Conta";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de contas: ");

            while (rs.next()) {
                Conta cAux;
                if(rs.getString("tipo_conta").equals("Black")){
                    cAux = new Conta_Black(rs.getString("username"),rs.getString("senha")
                            ,rs.getString("tipo_conta"),rs.getDouble("saldo"),rs.getString("data_criacao"),rs.getString("Usuario_cpfUsuario"));
                }
                else if (rs.getString("tipo_conta").equals("Gold")) {
                    cAux = new Conta_Gold(rs.getString("username"),rs.getString("senha")
                            ,rs.getString("tipo_conta"),rs.getDouble("saldo"),rs.getString("data_criacao"),rs.getString("Usuario_cpfUsuario"));
                }
                else{
                    cAux = new Conta_Gratuita(rs.getString("username"),rs.getString("senha")
                            ,rs.getString("tipo_conta"),rs.getDouble("saldo"),rs.getString("data_criacao"),rs.getString("Usuario_cpfUsuario"));
                }

                System.out.println("Codigo = " + rs.getInt("cod_conta"));
                System.out.println("Username = " + cAux.getUsername());
                System.out.println("Senha = " + cAux.getSenha());
                System.out.println("Tipo da conta = " + cAux.getTipo_conta());
                System.out.println("Saldo = " + cAux.getSaldo());
                System.out.println("Data de criação = " + cAux.getData_criacao());
                System.out.println("Cpf do usuario = " + cAux.getCpfUsuario());

                System.out.println("--------------------------------");

                contas.add(cAux);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return contas;
    }

    //VERIFICA OS DADOS NO LOGIN
    public Conta contaLoga(String usuario, String senha) {
        Conta contaAux = null;
        connectToDB();
        usuario = "'"+usuario+"'"; //Adicionando aspas para o MYSQL reconhecer que é uma string
        String sql = "SELECT * FROM conta WHERE username= "+usuario+" and senha = " + senha;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            //System.out.println("Lista de usuarios: ");

            while (rs.next()) {

                if(rs.getString("tipo_conta").equals("Gratuita")){
                    contaAux = new Conta_Gratuita(rs.getString("username"),rs.getString("senha"),rs.getString("tipo_conta"), rs.getDouble("saldo"), rs.getString("data_criacao"), rs.getString("Usuario_cpfUsuario"));
                }else if(rs.getString("tipo_conta").equals("Gold")){
                    contaAux = new Conta_Gold(rs.getString("username"),rs.getString("senha"),rs.getString("tipo_conta"), rs.getDouble("saldo"), rs.getString("data_criacao"), rs.getString("Usuario_cpfUsuario"));
                }else if(rs.getString("tipo_conta").equals("Black")){
                    contaAux = new Conta_Black(rs.getString("username"),rs.getString("senha"),rs.getString("tipo_conta"), rs.getDouble("saldo"), rs.getString("data_criacao"), rs.getString("Usuario_cpfUsuario"));
                }
                contaAux.setCod_conta(rs.getInt("cod_conta"));
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return contaAux;
    }
}
