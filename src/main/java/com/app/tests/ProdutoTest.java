package com.app.tests;

import org.springframework.stereotype.Component;

import com.app.dao.ProdutoDAO;


@Component
public class ProdutoTest {
	public static void main(String[] args) {
		ProdutoDAO daoP = new ProdutoDAO();
		
		
		daoP.remove(2);
		
		

	}
}
