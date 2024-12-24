package com.app.tests;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.app.config.AppConfig;
import com.app.controller.CategoriaController;
import com.app.controller.ProdutoController;
import com.app.converter.CategoriaConverter;
import com.app.dao.CategoriaDAO;
import com.app.model.Categoria;
import com.app.model.Produto;


public class ApplicationTest {

public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		
		
		CategoriaConverter categoriaConverter = (CategoriaConverter) context.getBean("categoriaConverter");
	
		
		
		
		
		System.out.println("end");
	
		((AbstractApplicationContext) context).close();
		
	}
}

