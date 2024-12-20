package com.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.app.dao.ProdutoDAO;
import com.app.enums.EditMode;
import com.app.model.Produto;

@Component
@ManagedBean(name = "produtoController")
@SessionScoped
public class ProdutoController {
	
	@Autowired
	private ProdutoDAO produtoDAO;
	
	private Produto produto = new Produto();
	
	private List<Produto> listProduto;
	
	private EditMode editMode = EditMode.VIEW;
	
	public void setMode(EditMode editMode) {
		this.editMode = editMode;
	}

	@PostConstruct
	public void init() {
		try {
			listProduto = this.produtoDAO.findAll();
		} catch(Exception e){
			System.err.println(e);
		}
	}
	
	
	public void onload() {
		this.listProduto = this.produtoDAO.findAll();
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
			
		} catch(Exception e){
			System.err.println(e);
		}
		
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
	
	
	
	
	
	
}
