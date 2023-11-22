package DAO;

import Modelos.Homebroker;

import java.sql.SQLException;
import java.util.ArrayList;

public class HomebrokerDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertHB(Homebroker hb) {

        connectToDB();

        String sql = "INSERT INTO homebroker (ip,url) values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, hb.getIp());
            pst.setString(2, hb.getUrl());
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
    public boolean updateUser(String ip, Homebroker hb) {
        connectToDB();
        String sql = "UPDATE homebroker SET ip=?, url=?, where id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, hb.getIp());
            pst.setString(2, hb.getUrl());
            pst.setString(3,ip);
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
    public boolean deleteUser(String ip) {
        connectToDB();
        String sql = "DELETE FROM homebroker where ip=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, ip);
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
    public ArrayList<Homebroker> selectUser() {
        ArrayList<Homebroker> hbs = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM homebroker";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de hb's: ");

            while (rs.next()) {

                Homebroker hbAux = new Homebroker(rs.getString("ip"),rs.getString("url"));

                System.out.println("ip = " + hbAux.getIp());
                System.out.println("url = " + hbAux.getUrl());
                System.out.println("--------------------------------");

                hbs.add(hbAux);
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
        return hbs;
    }

}
