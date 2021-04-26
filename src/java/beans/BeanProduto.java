/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import models.Produto;

@ManagedBean
@ViewScoped
public class BeanProduto {

    private int idproduto;
    private String descricao = "";
    private String observacao;
    private float preco;
    private int estoque;
    private int idcategoria;
    private String filtro;
    private List<Produto> lista = new ArrayList<>();
    
    public void consultarById(int id){
        Produto ct = new Produto();
        ct = ct.consultarById(id);
        idproduto = ct.getIdproduto();
        descricao = ct.getDescricao();
        observacao = ct.getObservacao();
        preco = ct.getPreco();
        estoque = ct.getEstoque();
        idcategoria = ct.getIdcategoria();
    }
    
    public String redirectEditar(int id){
       return "editaproduto.?faces-redirect=true&idproduto="+id;      
    }
    
    public void consultar(){      
      lista = new Produto().consultar(descricao, idcategoria);  
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

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<Produto> getLista() {
        return lista;
    }

    public void setLista(List<Produto> lista) {
        this.lista = lista;
    }
    
 
    public void salvar() {
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (descricao.equals("")) {
            msg = new FacesMessage("Informe descrição");
            view.addMessage(null, msg);
        }

        if (observacao.equals("")) {
            msg = new FacesMessage("Informe observação");
            view.addMessage(null, msg);
        } 

        if (preco == 0) {
            msg = new FacesMessage("Informe preço");
            view.addMessage(null, msg);
        }
           
        if (estoque == 0) {
            msg = new FacesMessage("Informe estoque");
            view.addMessage(null, msg);
        }
        
        if (msg == null) {
            Produto ct = new Produto(this.descricao, this.observacao, this.preco, this.estoque, this.idcategoria);
            if (ct.salvar()) {
                msg = new FacesMessage("Produto salvo com sucesso");
                view.addMessage(null, msg);
            }
        }
    }  
    
     public String update() {
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;

        if (descricao.equals("")) {
            msg = new FacesMessage("Informe descrição");
            view.addMessage(null, msg);
        }

        if (observacao.equals("")) {
            msg = new FacesMessage("Informe observação");
            view.addMessage(null, msg);
        } 

        if (preco == 0) {
            msg = new FacesMessage("Informe preço");
            view.addMessage(null, msg);
        }
           
        if (estoque == 0) {
            msg = new FacesMessage("Informe estoque");
            view.addMessage(null, msg);
        }
        
        if (msg == null) {
            Produto ct = new Produto(this.idproduto, this.descricao, this.observacao, this.preco, this.estoque, this.idcategoria);
            if (ct.update()) {
                msg = new FacesMessage("Produto atualizado com sucesso");
                view.addMessage(null, msg);
            }
        }
        
        return "consultaproduto.?faces-redirect=true";
       
    }
     
    public String delete(int id) {
        FacesContext view = FacesContext.getCurrentInstance();
        FacesMessage msg = null;
        
        Produto ct = new Produto(id);
        if (ct.delete()) {
            msg = new FacesMessage("Produto excluído com sucesso");
            view.addMessage(null, msg);
        }
        
       return "consultaproduto.?faces-redirect=true";
    }
}
