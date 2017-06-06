/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eh13memo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author linuxifrs
 */
public class Banco {

    private final static String driver = "org.postgresql.Driver";
    private final static String user = "postgres";
    private final static String senha = "postgres";
    private final static String url = "jdbc:postgresql://localhost:5432/bd";
    Connection con;

    public ArrayList<Candidato> selectCandidato() throws SQLException, ClassNotFoundException {
        ArrayList<Candidato> lista = new ArrayList<Candidato>();
        Statement st;

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(this.url, this.user, this.senha);
            st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT CD.*,CE.cod_ocupacao  FROM candidato_eleicao CE JOIN candidato CD ON(CE.seq_candidato = CD.sequencial) "
                    + "JOIN municipio MU ON(MU.codigo = CE.cod_municipio) WHERE CE.ano_eleicao = 2016 AND UF = 'RS'");
            
            while (rs.next()) {
                Candidato c = new Candidato();
                c.setNome(rs.getString("nome"));
                c.setSequencial(rs.getLong("sequencial"));
                c.setCpf(rs.getString("cpf"));
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTimeInMillis(rs.getDate("data_nasc").getTime());
                c.setNascimento(gc.toString());
                c.setSexo(rs.getString("sexo"));
                c.setOcupacao(rs.getInt("cod_ocupacao"));
                lista.add(c);
            }
            rs.close();
            st.close();
            con.close();
   
        } catch (Exception e) {
            System.out.println("Erro no get candidato!" + e.getMessage());
        }
        return lista;
    }

    public ArrayList<Ocupacao> selectOcupacao() throws SQLException, ClassNotFoundException {
        ArrayList<Ocupacao> lista = new ArrayList<Ocupacao>();
        Statement st;
        
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(this.url, this.user, this.senha);
            st = con.createStatement();
           ResultSet rs = st.executeQuery("SELECT DISTINCT * FROM ocupacao");
            while (rs.next()) {
                Ocupacao c = new Ocupacao();
                c.setCodigo(rs.getInt("codigo"));
                c.setDescricao(rs.getString("descricao"));
                lista.add(c);
            }
        con.close();
   
        } catch (Exception e) {
            System.out.println("Houve um erro no get ocupacao " + e.getMessage());
        }
        return lista;
    }

}
