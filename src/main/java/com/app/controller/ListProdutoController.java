package com.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.enums.EditMode;
import com.app.model.Produto;

@Component
public class ListProdutoController {
	
	@Autowired
	private ProdutoController produtoController;
	
	private List<Produto> listProduto;
	
	private EditMode editMode = EditMode.VIEW;
	
	public void setMode(EditMode editMode) {
		this.editMode = editMode;
	}

	@PostConstruct
	public void init() {
		try {
			listProduto = this.produtoController.getProdutoDAO().findAll();
		} catch(Exception e){
			System.err.println(e);
		}
	}
	
	
	public void save() {
		try {
			produtoController.save();
			
			switch(editMode) {
			
			case EDIT:
				break;
			
			case VIEW:
				listProduto.add(this.produtoController.getProduto());
				break;
		
				
			}
			
		} catch(Exception e){
			System.err.println(e);
		}
		
	}
	
	
	public void remove(Produto produto) {
		try {
			produtoController.getProdutoDAO().remove(produto);
			listProduto.remove(produto);
			
		} catch(Exception e){
			System.err.println(e);
		}
	}
	
	
	
	public void edit(Produto produto) {
		this.editMode = EditMode.EDIT;
		this.getProdutoController().setProduto(produto);
		
	}
	
	
	
	public void cancel(){
        this.editMode = EditMode.VIEW;
        this.produtoController.setProduto(new Produto());
    }
	
	
	public ProdutoController getProdutoController() {
		return produtoController;
	}
	
	
	
}
