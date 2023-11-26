package Modelos;

import DAO.UserDAO;

import java.sql.Date;
import java.util.Scanner;

public class Usuario {
    private String nome;
    private String cpf;
    private Date data_nasc;
    private String ipHomebroker;
    private String email;

    public Usuario(String nome, String email, String cpf, String data_nasc, String ipHomebroker) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.data_nasc = Date.valueOf(data_nasc);
        this.ipHomebroker = ipHomebroker;
    }

    public String getNome() {
        return nome;
    }



    public String getCpf() {
        return cpf;
    }



    public Date getData_nasc() {
        return data_nasc;
    }



    public String getIpHomebroker() {
        return ipHomebroker;
    }



    public String getEmail() {
        return email;
    }


}
