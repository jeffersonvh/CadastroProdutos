/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.utils.Conexao;

/**
 *
 * @author jeffe
 */
public class Categoria {
    private int idcategoria;
    private String descricao;

    public Categoria() {
    }

    public Categoria(int idcategoria, String descricao) {
        this.idcategoria = idcategoria;
        this.descricao = descricao;
    }

    public Categoria(String descricao) {
        this.descricao = descricao;
    }
    
    public List<Categoria> consultar(String filtro){
        ResultSet rs = null;
        List<Categoria> lista = new ArrayList<>();
        try {
            String sql = "select * from categoria"
                      + " where descricao like '%"+filtro+"%'";
            Connection con = Conexao.conectar();
            PreparedStatement stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next()){
              lista.add(new Categoria(
                      rs.getInt("idcategoria"),
                      rs.getString("descricao")));
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return lista;
    }
    
    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria consultarById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdproduto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
