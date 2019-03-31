package com.webapp.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.util.jsf.FacesUtil;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;
	
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private HttpServletResponse response;
	

	public void preRender() {
		if ("true".equals(request.getParameter("invalid"))) {
			FacesUtil.addErrorMessageLogin("Usuário ou senha inválido!");
		}
	}
	
	public void acessar() throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.xhtml");
		dispatcher.forward(request, response);
		
		facesContext.responseComplete();
	}
	
}