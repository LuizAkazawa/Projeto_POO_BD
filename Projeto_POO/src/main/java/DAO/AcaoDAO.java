package DAO;

import Modelos.Acao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AcaoDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou
    Scanner sc = new Scanner(System.in);

    //INSERT
    public boolean insertAcao(Acao acao) {

        connectToDB();

        String sql = "INSERT INTO acoes (sigla,cotacao,empresa_proprietaria ) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, acao.getSigla());
            pst.setDouble(2, acao.getCotacao());
            pst.setString(3, acao.getEmpresa_proprietaria());
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
    public boolean updateAcao(String sigla, double cotacao) {
        connectToDB();
        String sql = "UPDATE acoes SET cotacao=? where sigla=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setDouble(1, cotacao);
            pst.setString(2,sigla);
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
    public boolean deleteAcao(String cpf) {
        connectToDB();
        String sql = "DELETE FROM acoes where cpf=?";
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
    public ArrayList<Acao> selectAcao() {
        ArrayList<Acao> users = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM acoes";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de ações: ");

            while (rs.next()) {

                Acao userAux = new Acao(rs.getString("sigla"),rs.getDouble("cotacao"),rs.getString("empresa_proprietaria"));

                System.out.println("Sigla = " + userAux.getSigla());
                System.out.println("Cotação = " + userAux.getCotacao());
                System.out.println("Empresa proprietária = " + userAux.getEmpresa_proprietaria());
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
}
