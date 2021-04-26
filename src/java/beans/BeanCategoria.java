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
import models.Categoria;

@ManagedBean
@ViewScoped
public class BeanCategoria {

    private int idcategoria;
    private String descricao;
    private String filtro;
    private List<Categoria> lista = new ArrayList<>();
    
    public void consultarById(int id){
        Categoria ct = new Categoria();
        ct = ct.consultarById(id);
        idcategoria = ct.getIdproduto();
        descricao = ct.getDescricao();
        idcategoria = ct.getIdcategoria();
    }
    
    public void consultar(){      
      lista = new Categoria().consultar(descricao);  
    }

    public void load(){
      lista = new Categoria().consultar("");  
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public List<Categoria> getLista() {
        return lista;
    }

    public void setLista(List<Categoria> lista) {
        this.lista = lista;
    }

}
