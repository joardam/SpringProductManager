package com.app.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dao.ProdutoDAO;

import com.app.enums.EditMode;

import com.app.model.Produto;

@Component
public class ProdutoController{
	
	@Autowired
	private ProdutoDAO produtoDAO;
	private Produto produto = new Produto();
	

	public void save() {
		try {
		produtoDAO.save(this.produto);
		} catch(Exception e) {
			System.err.println(e);
		}
	}
	
	
	public void remove() {
		try {
			produtoDAO.remove(this.produto);
			} catch(Exception e) {
				System.err.println(e);
			}
		}
	
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}



	public ProdutoDAO getProdutoDAO() {
		return produtoDAO;
	}

	
	
	
}
