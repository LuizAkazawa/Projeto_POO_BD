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

    Scanner sc = new Scanner(System.in);

    UserDAO userDAO = new UserDAO();

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = Date.valueOf(data_nasc);
    }

    public String getIpHomebroker() {
        return ipHomebroker;
    }

    public void setIpHomebroker(String ipHomebroker) {
        this.ipHomebroker = ipHomebroker;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
