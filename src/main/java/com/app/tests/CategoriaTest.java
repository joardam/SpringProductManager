package com.app.tests;

import com.app.model.bean.Categoria;
import com.app.model.dao.*;


public class CategoriaTest {
	public static void main(String[] args) {
		
		CategoriaDAO dao = new CategoriaDAO();
		
		Categoria categoria = dao.remove(3);
		
	}
}