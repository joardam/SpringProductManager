package com.app.tests;

import com.app.model.dao.ProdutoDAO;

public class ProdutoTest {
	public static void main(String[] args) {
		ProdutoDAO daoP = new ProdutoDAO();
		
		
		daoP.remove(2);
		
		

	}
}
