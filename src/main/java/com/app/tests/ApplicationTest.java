package com.app.tests;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.app.config.AppConfig;
import com.app.controller.ProdutoController;

import com.app.model.Categoria;
import com.app.model.Produto;


public class ApplicationTest {

public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		
		
		ProdutoController produtoController = (ProdutoController) context.getBean("produtoController");
		
		
		
		System.out.println(produtoController.getEditMode());
		;
		
	
		
		
		System.out.println("print");
	
		((AbstractApplicationContext) context).close();
		
	}
}

