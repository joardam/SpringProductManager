package com.app.converter;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.app.controller.CategoriaController;
import com.app.dao.CategoriaDAO;
import com.app.model.Categoria;


@ManagedBean(name = "categoriaConverter")
@Component
@RequestScoped
public class CategoriaConverter implements Converter {

    @Autowired
    private CategoriaController categoriaController; 
    
    
    
    
    public CategoriaConverter() {
    	
    }
    
    
    @Override
    public Categoria getAsObject(FacesContext context, UIComponent component, String value) {
    	
    	
    	if (value == null || value.isEmpty()) {
            return null;
        }
        try {
        	List<Categoria> listCategoria = categoriaController.getListCategoria();
        	Categoria categoria = null;
        	
        	
        	for(Categoria categoriaAnalyzed : listCategoria) {
        		if(categoriaAnalyzed.getId().equals(Integer.parseInt(value))) {
        			categoria = categoriaAnalyzed;
        		}
        	}
        	
        	
        	return categoria;
        	
        	
        	
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID da categoria inválido: " + value);
        }
		
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    	if (value == null) {
            return "";
        }
        if (value instanceof Categoria) {
            return String.valueOf(((Categoria) value).getId());
        } else {
            throw new IllegalArgumentException("Objeto inválido: " + value);
        }
    }
}


