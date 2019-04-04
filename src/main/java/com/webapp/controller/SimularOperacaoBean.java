package com.webapp.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.webapp.model.Simulacao;

@Named
@SessionScoped
public class SimularOperacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean result = false;

	private NumberFormat nf = new DecimalFormat("###,##0.00");

	private String valorEmprestado;

	@Inject
	private Simulacao simulacao;

	public void calcular() {
		try {
			result = true;
			valorEmprestado = nf.format(Double.parseDouble(simulacao.getValorEmprestimo()));
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/wep-application/simulacao/simularOperacao.xhtml");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void recalcular() {
		try {
			result = false;
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/wep-application/simulacao/simularOperacao.xhtml");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void novaSimulacao() {
		try {
			result = false;
			simulacao = new Simulacao();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/wep-application/simulacao/simularOperacao.xhtml");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getValorEmprestado() {
		return valorEmprestado;
	}

	public void setValorEmprestado(String valorEmprestado) {
		this.valorEmprestado = valorEmprestado;
	}

	public Simulacao getSimulacao() {
		return simulacao;
	}

	public void setSimulacao(Simulacao simulacao) {
		this.simulacao = simulacao;
	}
}
