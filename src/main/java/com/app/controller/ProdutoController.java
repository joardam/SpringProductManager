package com.app.controller;

import java.util.ArrayList;
import java.util.List;

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
	
	private String produtoSearch;
	
	
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
			resetFilteredList();
			
			
			
			}
		} catch(Exception e){
			System.err.println(e);
		}
	}
	
	
	public void resetFilteredList() {
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
			
			this.resetFilteredList();
			String produtoSearchClone = produtoSearch.trim();
			
			
			if(produtoSearch.isEmpty() || produtoSearch == null) {
				
			} 
			else {
				
				List<Produto> tempListProduto = new ArrayList<>();

				for(Produto produto : listProduto) {
					tempListProduto.add(produto);
				}	
				
				for(Produto produto : tempListProduto) {
					if(produto.getDescricao().contains(produtoSearchClone)) {
						continue;
					} else {
						filteredListProduto.remove(produto);
					}
					
					
				}
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





	public String getProdutoSearch() {
		return produtoSearch;
	}





	public void setProdutoSearch(String produtoSearch) {
		this.produtoSearch = produtoSearch;
	}
	
	
	
	
}