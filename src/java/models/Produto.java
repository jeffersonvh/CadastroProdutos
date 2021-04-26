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
public class Produto {
    private int idproduto;
    private String descricao;
    private String observacao;
    private float preco;
    private int estoque;
    private int idcategoria;
    private String dsccategoria;

    public Produto() {
    }
    
    public Produto(int idproduto) {
        this.idproduto = idproduto;
    }
    
    public Produto(int idproduto, String descricao, String observacao, float preco, int estoque, int idcategoria) {
        this.idproduto = idproduto;
        this.descricao = descricao;
        this.observacao = observacao;
        this.preco = preco;
        this.estoque = estoque;
        this.idcategoria = idcategoria;
    }
    
    public Produto(int idproduto, String descricao, String observacao, float preco, int estoque, int idcategoria, String dsccategoria) {
        this.idproduto = idproduto;
        this.descricao = descricao;
        this.observacao = observacao;
        this.preco = preco;
        this.estoque = estoque;
        this.idcategoria = idcategoria;
        this.dsccategoria = dsccategoria;
    }
    
    public Produto(String descricao, String observacao, float preco, int estoque, int idcategoria) {
        this.descricao = descricao;
        this.observacao = observacao;
        this.preco = preco;
        this.estoque = estoque;
        this.idcategoria = idcategoria;
    }
    
    public Produto consultarById(int id){
       ResultSet rs = null;
       Produto ct = null;
       try {
            String sql = "select * from produto"
                      + " where idproduto = ? ";
            Connection con = Conexao.conectar();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if(rs.next()){
              ct = new Produto(
                      rs.getInt("idproduto"),
                      rs.getString("descricao"),
                      rs.getString("observacao"),
                      rs.getFloat("preco"),
                      rs.getInt("estoque"),
                      rs.getInt("idcategoria"));
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
       return ct;
    }
    
    public List<Produto> consultar(String filtro, int idcategoria){
        ResultSet rs = null;
        String search = null;
        List<Produto> lista = new ArrayList<>();
        try {
            String sql = "select"
                      + " produto.idproduto,"
                      + " produto.descricao,"
                      + " produto.observacao,"
                      + " produto.preco,"
                      + " produto.estoque,"
                      + " produto.idcategoria,"
                      + " categoria.descricao dsccategoria"
                      + " from produto, categoria"
                      + " where produto.idcategoria = categoria.idcategoria"
                      + " and produto.descricao like '%"+filtro+"%'";
            
            if(idcategoria > 0){
              sql += " and produto.idcategoria = "+idcategoria+"";
            }
            
            Connection con = Conexao.conectar();
            PreparedStatement stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next()){
              lista.add(new Produto(
                      rs.getInt("idproduto"),
                      rs.getString("descricao"),
                      rs.getString("observacao"),
                      rs.getFloat("preco"),
                      rs.getInt("estoque"),
                      rs.getInt("idcategoria"),
                      rs.getString("dsccategoria")));
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return lista;
    }
    
    public boolean salvar(){
        try {
            String sql = "insert into produto(descricao, observacao, preco, estoque, idcategoria)" +
                    "values(?,?,?,?,?)";
            Connection con = Conexao.conectar();
            PreparedStatement stm = con.prepareStatement(sql); 
            stm.setString(1, this.descricao);
            stm.setString(2, this.observacao);
            stm.setFloat(3, this.preco);
            stm.setInt(4, this.estoque);
            stm.setInt(5, this.idcategoria);
            stm.execute();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return true;
    }
    
    public boolean update(){
        try {
            String sql = "update produto set "
                     + " descricao = ?, "
                     + " observacao = ?, "
                     + " preco = ?, " 
                     + " estoque = ?, "
                     + " idcategoria = ? "
                     + " where idproduto = ?";
            Connection con = Conexao.conectar();
            PreparedStatement stm = con.prepareStatement(sql); 
            stm.setString(1, this.descricao);
            stm.setString(2, this.observacao);
            stm.setFloat(3, this.preco);
            stm.setInt(4, this.estoque);
            stm.setInt(5, this.idcategoria);
            stm.setInt(6, this.idproduto);
            stm.execute();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return true;
    }
    
    public boolean delete() {
       try {
            String sql = "delete from produto where idproduto = ?";
            Connection con = Conexao.conectar();
            PreparedStatement stm = con.prepareStatement(sql); 
            stm.setInt(1, this.idproduto);
            stm.execute();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return true;
    }
    
    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getDsccategoria() {
        return dsccategoria;
    }

    public void setDsccategoria(String dsccategoria) {
        this.dsccategoria = dsccategoria;
    }
    
}
