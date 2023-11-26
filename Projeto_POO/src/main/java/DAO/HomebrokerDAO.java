package DAO;

import Modelos.Acao;
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

    public Homebroker loginHomebroker(){
        connectToDB();
        Homebroker h1 = null;
        String sql = "SELECT * FROM Homebroker WHERE ip = 1" ;
        try{
            st = con.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            h1 = new Homebroker(rs.getString("url"), rs.getString("ip"));
            return h1;
        } catch (SQLException e) {
            System.out.println("ErroLogin_homebroker: " + e);
            return null;
        }
    }

}
