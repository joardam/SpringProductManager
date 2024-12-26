package com.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.JoinColumn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.app.dao.CategoriaDAO;
import com.app.enums.EditMode;
import com.app.model.Categoria;


@Component
@ManagedBean(name = "categoriaController")
@SessionScope
public class CategoriaController {
	
	
	@Autowired
	private CategoriaDAO categoriaDAO;
	
	
	private Categoria categoria = new Categoria();
	
	
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
	
	
	private EditMode editMode = EditMode.VIEW;
	
	
	
	@PostConstruct
	public void init() {
		try {
			listCategoria = this.categoriaDAO.findAll();
		} catch(Exception e) {
			System.err.println(e);			
		}
	}
	
	public void setMode(EditMode editMode) {
		this.editMode = editMode;
	}
	
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
				break;
			}	
			cancel();
		}
		
		
		 catch(Exception e) {
			System.out.println(e);
		 	}
		}
		
		
		
		
	public void remove(Categoria categoria) {
		try {
			categoriaDAO.remove(categoria);
			listCategoria.remove(categoria);
			
		} catch(Exception e) {
			System.err.println(e);
		}
		
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
		cancel();
		return "Menu.xhtml?faces-redirect=true";
		
		} catch(Exception e) {
			System.err.println(e);
		}
		return null;
	}

	
	
}
