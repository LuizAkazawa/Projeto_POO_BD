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

    //UPDATE
    public boolean updateConta(String colunaMudanca, Conta c) {
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
}
