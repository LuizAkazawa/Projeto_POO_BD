package DAO;

import Modelos.*;

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
            System.out.println("Erro1: " + exc.getMessage());
            System.out.println("aaaaaaaaaaaaaaa");
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro2: " + exc.getMessage());
            }
        }
        return sucesso;
    }

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
            System.out.println("ErroCheck: " + e);
            return null;
        }
    }

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
            System.out.println("ErroCheck: " + e);
            return null;
        }
    }

    public boolean insertConta_has_Acoes(Conta c, String sigla, int qtd) {

        connectToDB();
        Acao a = infoAcoes(sigla);

        String sql = "INSERT INTO Conta_has_Acoes (Conta_codConta, Acoes_siglaAcoes, quantidade) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, c.getCod_conta());
            pst.setString(2, a.getSigla());
            pst.setInt(3, qtd);

            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro1: " + exc.getMessage());
            System.out.println("aaaaaaaaaaaaaaa");
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro2: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    /*
    //UPDATE
    public boolean updateConta(String cpf, String colunaMudanca, String novoDado) {
        connectToDB();

        String sql = "UPDATE conta SET " + colunaMudanca + " =? where Usuario_cpfUsuario =?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, c.getCod_conta());
            pst.setString(2, c.getUsername());
            pst.setString(3, c.getSenha());
            pst.setString(4, c.getTipo_conta());
            pst.setDate(5, c.getData_criacao());
            pst.setDouble(6, c.getSaldo());
            pst.setString(7, c.getCpfUsuario());
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
    */

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


    //TYPE CHECK
    public String checkTipo(Conta c){
        connectToDB();
        String sql = "SELECT tipo_conta FROM conta WHERE Usuario_cpfUsuario = " + c.getCpfUsuario();
        try{
            st = con.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            return rs.getString("tipo_conta");
        } catch (SQLException e) {
            System.out.println("ErroCheck: " + e);
            return null;
        }
    }


    //SELECT
    public ArrayList<Conta> selectUser() {
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
                    contaAux = new Conta_Black(rs.getString("username"),rs.getString("senha"),rs.getString("tipo_conta"), rs.getDouble("saldo"), rs.getString("data_criacao"), rs.getString("Usuario_cpfUsuario"));
                }else if(rs.getString("tipo_conta").equals("Black")){
                    contaAux = new Conta_Black(rs.getString("username"),rs.getString("senha"),rs.getString("tipo_conta"), rs.getDouble("saldo"), rs.getString("data_criacao"), rs.getString("Usuario_cpfUsuario"));
                }
                contaAux.setCod_conta(rs.getInt("cod_conta"));
/*
                System.out.println("nome = " + contaAux.getNome());
                System.out.println("cpf = " + contaAux.getCpf());
                System.out.println("data_nascimento = " + contaAux.getData_nasc());
                System.out.println("IP HOMEBROKER = " + contaAux.getIpHomebroker());
                System.out.println("--------------------------------");
 */
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

    public void comprarAcao(String sigla, int qtd){

    }
    public void venderAcao(Conta c, String sigla, int qtd){

    }

}
