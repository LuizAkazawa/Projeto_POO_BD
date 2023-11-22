package DAO;

import Modelos.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou
    Scanner sc = new Scanner(System.in);

    //INSERT
    public boolean insertUser(Usuario user) {

        connectToDB();

        String sql = "INSERT INTO usuario (nome,email,cpf,data_nascimento,Homebroker_ipHomebroker) values(?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, user.getNome());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getCpf());
            pst.setDate(4, user.getData_nasc());
            pst.setString(5, user.getIpHomebroker());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
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

    //UPDATE
    public boolean updateUser(String cpf, String mudaColuna, String mudanca) {
        connectToDB();
        String sql = "UPDATE usuario SET " + mudaColuna + "=? where cpf=?";
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
    public boolean deleteUser(String cpf) {
        connectToDB();
        String sql = "DELETE FROM usuario where cpf=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cpf);
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

    //SELECT
    public ArrayList<Usuario> selectUser() {
        ArrayList<Usuario> users = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM usuario";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de usuarios: ");

            while (rs.next()) {

                Usuario userAux = new Usuario(rs.getString("nome"),rs.getString("email"),rs.getString("cpf"), rs.getString("data_nascimento"), rs.getString("Homebroker_ipHomebroker"));

                System.out.println("nome = " + userAux.getNome());
                System.out.println("cpf = " + userAux.getCpf());
                System.out.println("data_nascimento = " + userAux.getData_nasc());
                System.out.println("IP HOMEBROKER = " + userAux.getIpHomebroker());
                System.out.println("--------------------------------");

                users.add(userAux);
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
        return users;
    }

    //COLETA INFO

    public Usuario coletaInfo(String cpf) {
        Usuario userAux = null;
        connectToDB();
        String sql = "SELECT * FROM usuario WHERE cpf = " + cpf;

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de usuarios: ");

            while (rs.next()) {

                userAux = new Usuario(rs.getString("nome"),rs.getString("email"),rs.getString("cpf"), rs.getString("data_nascimento"), rs.getString("Homebroker_ipHomebroker"));

                System.out.println("nome = " + userAux.getNome());
                System.out.println("cpf = " + userAux.getCpf());
                System.out.println("data_nascimento = " + userAux.getData_nasc());
                System.out.println("IP HOMEBROKER = " + userAux.getIpHomebroker());
                System.out.println("--------------------------------");
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
        return userAux;
    }

}


