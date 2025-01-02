package com.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.app.dao.ProdutoDAO;
import com.app.enums.EditMode;
import com.app.model.Produto;

@Component
@ManagedBean(name = "produtoController")
@SessionScope
public class ProdutoController {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	private Produto produto = new Produto();
	
	private String filterSearchDescricao = "";
	private String filterSearchValor = "" ;
	private String filterSearchQtd = "";
	private String filterSearchCategoria = "";
	
	
	private List<Produto> listProduto;
	
	private List<Produto> filteredListProduto;
	
	public List<Produto> getFilteredListProduto() {
		return filteredListProduto;
	}
	
	
	
	

	public void setFilteredListProduto(List<Produto> filteredListProduto) {
		this.filteredListProduto = filteredListProduto;
	}

	private EditMode editMode = EditMode.VIEW;
	
	public void setMode(EditMode editMode) {
		this.editMode = editMode;
	}

	@PostConstruct
	public void init() {
		try {
			if (!FacesContext.getCurrentInstance().isPostback() && !FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
			listProduto = this.produtoDAO.findAll();
			hardResetFilteredList();
			
			
			
			}
		} catch(Exception e){
			System.err.println(e);
		}
	}
	
	
	public void hardResetFilteredList() {
		try {
			filteredListProduto =  new ArrayList<>();
			for (Produto produto : listProduto) {
				filteredListProduto.add(produto);
			}
			
		} catch(Exception e){
			System.err.println(e);
		}
	}
	
	
	

	
	
	public void controlSearchDescricao() {
		try {
			this.hardResetFilteredList();
			String filterSearchDescricaoClone = filterSearchDescricao.trim();
			String filterSearchValorClone = filterSearchValor.trim();
			String filterSearchQtdClone = filterSearchQtd.trim();
			String filterSearchCategoriaClone = filterSearchCategoria.trim();
			
			
			if(filterSearchDescricao.isEmpty()) {
				
			} 
			else {
				
				this.filteredListProduto = filteredListProduto.stream().
						filter(produto -> (
								produto.getDescricao().contains(filterSearchDescricaoClone))
								)
                        .collect(Collectors.toList());
				}
			
			
			
			if(filterSearchValor.isEmpty()) {
				
			}
			
			else {
				this.filteredListProduto = filteredListProduto.stream().
						filter(produto -> (
								Double.toString(produto.getValor()).contains(filterSearchValorClone))
								)
                        .collect(Collectors.toList());
			}
			

		}catch(Exception e) {
			System.err.println(e);
		}
		
	}
	
	
	public List<Produto> getListProduto() {
		return listProduto;
	}

	public void setListProduto(List<Produto> listProduto) {
		this.listProduto = listProduto;
	}
	
	
	public void save() {
		try {
			produtoDAO.save(this.produto);
			
			switch(editMode) {
			
			case EDIT:
				break;
			
			case VIEW:
				listProduto.add(this.produto);
				controlSearchDescricao();
				break;	
			}
			cancel();
			
			
			
		} catch(Exception e){
			System.err.println(e);
		}
		
	}
	
	
	public Produto getProduto() {
		return produto;
	}

	public EditMode getEditMode() {
		return editMode;
	}

	public void setEditMode(EditMode editMode) {
		this.editMode = editMode;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void remove(Produto produto) {
		try {
			produtoDAO.remove(produto);
			listProduto.remove(produto);
			controlSearchDescricao();
			
		} catch(Exception e){
			System.err.println(e);
		}
	}
	
	
	
	public void edit(Produto produto) {
		this.editMode = EditMode.EDIT;
		this.produto = produto ;
		
	}
	
	
	
	public void cancel(){
        this.editMode = EditMode.VIEW;
        this.produto = new Produto() ;
    }
	
	public String goToMenu() {
		try{
		cancel();
		return "Menu.xhtml?faces-redirect=true";
		
		} catch(Exception e) {
			System.err.println(e);
		}
		return null;
	}





	public String getFilterSearchDescricao() {
		return filterSearchDescricao;
	}





	public void setFilterSearchDescricao(String filterSearchDescricao) {
		this.filterSearchDescricao = filterSearchDescricao;
	}





	public String getFilterSearchValor() {
		return filterSearchValor;
	}





	public void setFilterSearchValor(String filterSearchValor) {
		this.filterSearchValor = filterSearchValor;
	}





	public String getFilterSearchQtd() {
		return filterSearchQtd;
	}





	public void setFilterSearchQtd(String filterSearchQtd) {
		this.filterSearchQtd = filterSearchQtd;
	}





	public String getFilterSearchCategoria() {
		return filterSearchCategoria;
	}





	public void setFilterSearchCategoria(String filterSearchCategoria) {
		this.filterSearchCategoria = filterSearchCategoria;
	}





	
	
}