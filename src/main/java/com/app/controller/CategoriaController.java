package com.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.JoinColumn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.app.dao.CategoriaDAO;
import com.app.enums.EditMode;
import com.app.model.Categoria;
import com.app.model.Produto;


@Component
@ManagedBean(name = "categoriaController")
@SessionScope
public class CategoriaController {
	
	
	@Autowired
	private CategoriaDAO categoriaDAO;
	private Categoria categoria = new Categoria();
	
	
	private String filterSearchDescricao = "";
	
	public CategoriaDAO getCategoriaDAO() {
		return categoriaDAO;
	}

	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public EditMode getEditMode() {
		return editMode;
	}

	public void setEditMode(EditMode editMode) {
		this.editMode = editMode;
	}

	private List<Categoria> listCategoria;
	private List<Categoria> filteredListCategoria;
	
	
	
	
	private EditMode editMode = EditMode.VIEW;
	
	
	
	@PostConstruct
	public void init() {
		try {
			if (!FacesContext.getCurrentInstance().isPostback() && 
					!FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
			listCategoria = this.categoriaDAO.findAll();
//			this.hardResetFilteredList();
//			this.resetLoad()
			this.cancel();
			}
		} catch(Exception e) {
			System.err.println(e);			
		}
	}
	
	public void setMode(EditMode editMode) {
		this.editMode = editMode;
	}
	
	
//	public void hardResetFilteredList() {
//		try {
//			filteredListCategoria =  new ArrayList<>();
//			for (Categoria categoria : listCategoria) {
//				filteredListCategoria.add(categoria);
//			}
//			
//		} catch(Exception e){
//			System.err.println(e);
//		}
//	}
	
	
//	public void controlSearch() {
//		
//		try {
//			
//		this.hardResetFilteredList();
//		
//		String filterSearchDescricaoClone = filterSearchDescricao.trim().toLowerCase();
//		
//		this.filteredListCategoria = filteredListCategoria.stream()
//			.filter(categoria -> filterSearchDescricao.isEmpty() ||
//					safeContains(categoria.getDescricao() , filterSearchDescricaoClone))
//			.collect(Collectors.toList());
//	
//		} catch (Exception e) {
//	        System.err.println(e);
//	    }
//	}
//	
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
//	
	
	public List<Categoria> getListCategoria(){
		return this.listCategoria;
	}
	
	
	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}
	
	
	public void save() {
		try {
			
			categoriaDAO.save(this.categoria);
	
		switch(editMode) {
		
			case EDIT:
				break;
			
			case VIEW:
				listCategoria.add(this.categoria);
				filteredListCategoria.add(this.categoria);
				break;
			}	
			cancel();
//			controlSearch();
		}
		
		
		 catch(Exception e) {
			System.out.println(e);
		 	}
		}
		
		
		
		
	public void remove(Categoria categoria) {
		try {
			categoriaDAO.remove(categoria);
			listCategoria.remove(categoria);
			filteredListCategoria.remove(categoria);
//			controlSearch();
			
		} catch(Exception e) {
			System.err.println(e);
		}
		
	}
	
	public void resetLoad() {
		cancel();
		filterSearchDescricao = "";
//		controlSearch();
		
		
		
	}
	
	
	public void edit(Categoria categoria) {
		this.editMode = EditMode.EDIT;
		this.categoria = categoria;
		
	}
	
	public void cancel() {
		this.editMode = EditMode.VIEW;
		this.categoria = new Categoria();
		
	}	
	
	
	public String goToMenu() {
		try{
//		resetLoad();
		//new
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

	public List<Categoria> getFilteredListCategoria() {
		return filteredListCategoria;
	}

	public void setFilteredListCategoria(List<Categoria> filteredListCategoria) {
		this.filteredListCategoria = filteredListCategoria;
	}

	
	
}
