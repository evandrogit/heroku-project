package com.webapp.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.webapp.model.Cliente;
import com.webapp.model.Emprestimo;
import com.webapp.model.Simulacao;
import com.webapp.repository.Emprestimos;
import com.webapp.repository.filter.EmprestimoFilter;
import com.webapp.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEmprestimosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Emprestimos emprestimos;

	private List<Emprestimo> listaEmprestimos = new ArrayList<Emprestimo>();

	private EmprestimoFilter filtro = new EmprestimoFilter();

	@Inject
	private Emprestimo emprestimo;

	private Emprestimo emprestimoSelecionado;
	
	
	private static final Locale BRAZIL = new Locale("pt", "BR");

	private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);

	private NumberFormat nf = new DecimalFormat("###,##0.00", REAL);

	private String valorEmprestado;
	

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			listaEmprestimos = emprestimos.todos();
		}
	}
	
	public void prepararInfo() {
		valorEmprestado = nf.format(Double.parseDouble(emprestimoSelecionado.getValorEmprestimo()));
	}

	public void pesquisar() {
		listaEmprestimos = emprestimos.filtrados(filtro);
		emprestimoSelecionado = null;
	}

	public void excluir() {

		emprestimos.remove(emprestimoSelecionado);

		emprestimoSelecionado = null;

		pesquisar();

		PrimeFaces.current().executeScript(
				"swal({ type: 'success', title: 'Concluído!', text: 'Empréstimo excluído com sucesso!' });");

	}

	public List<Emprestimo> getListaEmprestimos() {
		return listaEmprestimos;
	}

	public EmprestimoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(EmprestimoFilter filtro) {
		this.filtro = filtro;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public Emprestimo getEmprestimoSelecionado() {
		return emprestimoSelecionado;
	}

	public void setEmprestimoSelecionado(Emprestimo emprestimoSelecionado) {
		this.emprestimoSelecionado = emprestimoSelecionado;
	}

	public String getValorEmprestado() {
		return valorEmprestado;
	}
}
