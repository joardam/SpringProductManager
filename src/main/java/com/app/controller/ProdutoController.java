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
	
//	private String filterSearchDescricao = "";
//	private String filterSearchValor = "" ;
//	private String filterSearchQtd = "";
//	private String filterSearchCategoria = "";
	
	
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
			filteredListProduto = new ArrayList<>(listProduto);
			
//			filteredListProduto = this.produtoDAO.findAll();
//			this.hardResetFilteredList();
			this.cancel();
			
			
			
			
			}
		} catch(Exception e){
			System.err.println(e);
		}
	}
	
	
//	public void hardResetFilteredList() {
//		try {
//			filteredListProduto =  new ArrayList<>();
//			for (Produto produto : listProduto) {
//				filteredListProduto.add(produto);
//			}
//			
//		} catch(Exception e){
//			System.err.println(e);
//		}
//	}
//	
	
	

	
	
//	public void controlSearch() {
//	    try {
//	        this.hardResetFilteredList();
//
//	        String filterSearchDescricaoClone = filterSearchDescricao.trim().toLowerCase();
//	        String filterSearchValorClone = filterSearchValor.trim().toLowerCase();
//	        String filterSearchQtdClone = filterSearchQtd.trim().toLowerCase();
//	        String filterSearchCategoriaClone = filterSearchCategoria.trim().toLowerCase();
//
//	        this.filteredListProduto = filteredListProduto.stream()
//	            .filter(produto -> filterSearchDescricaoClone.isEmpty() || 
//	                safeContains(produto.getDescricao(), filterSearchDescricaoClone))
//	            .filter(produto -> filterSearchValorClone.isEmpty() || 
//	                safeContains(produto.getValor(), filterSearchValorClone))
//	            .filter(produto -> filterSearchQtdClone.isEmpty() || 
//	                safeContains(produto.getQtd(), filterSearchQtdClone))
//	            .filter(produto -> filterSearchCategoriaClone.isEmpty() || 
//	                safeContains(produto.getCategoria() != null ? produto.getCategoria().getDescricao() : null, filterSearchCategoriaClone))
//	            .collect(Collectors.toList());
//	    } catch (Exception e) {
//	        System.err.println(e);
//	    }
//	}

	
//	/**
//	 * Verifica se o valor de origem contém o filtro, lidando com null.
//	 * 
//	 * @param source O valor de origem.
//	 * @param filter O valor a ser buscado.
//	 * @return true se o source contém o filter; false caso contrário.
//	 */
//	private boolean safeContains(String source, String filter) {
//	    return source != null && filter != null && source.toLowerCase().contains(filter);
//	}
//
//	/**
//	 * Sobrecarga para números, convertendo para String antes de verificar.
//	 * 
//	 * @param source O valor de origem.
//	 * @param filter O valor a ser buscado.
//	 * @return true se o source contém o filter; false caso contrário.
//	 */
//	private boolean safeContains(Number source, String filter) {
//	    return source != null && filter != null && source.toString().contains(filter);
//	}
//	
	
	
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
				filteredListProduto.add(this.produto);
				
				
				break;	
			}
//			controlSearch();
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
			filteredListProduto.remove(produto);
//			controlSearch();
			
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
        this.produto = new Produto();
    }
	
//	public void resetLoad() {
//		cancel();
////		filterSearchDescricao = "";
////		filterSearchValor = "" ;
////		filterSearchQtd = "";
////		filterSearchCategoria = "";
////		controlSearch();
//		
//		
//		
//	}
//	
	public String goToMenu() {
		try{
		this.cancel();
		
		return "Menu.xhtml?faces-redirect=true";
		
		} catch(Exception e) {
			System.err.println(e);
		}
		return null;
	}





//	public String getFilterSearchDescricao() {
//		return filterSearchDescricao;
//	}



//
//
//	public void setFilterSearchDescricao(String filterSearchDescricao) {
//		this.filterSearchDescricao = filterSearchDescricao;
//	}
//
//
//
//
//
//	public String getFilterSearchValor() {
//		return filterSearchValor;
//	}
//
//
//
//
//
//	public void setFilterSearchValor(String filterSearchValor) {
//		this.filterSearchValor = filterSearchValor;
//	}
//
//
//
//
//
//	public String getFilterSearchQtd() {
//		return filterSearchQtd;
//	}
//
//
//
//
//
//	public void setFilterSearchQtd(String filterSearchQtd) {
//		this.filterSearchQtd = filterSearchQtd;
//	}
//
//
//
//
//
//	public String getFilterSearchCategoria() {
//		return filterSearchCategoria;
//	}
//
//
//
//
//
//	public void setFilterSearchCategoria(String filterSearchCategoria) {
//		this.filterSearchCategoria = filterSearchCategoria;
//	}
//
//
//


	
	
}