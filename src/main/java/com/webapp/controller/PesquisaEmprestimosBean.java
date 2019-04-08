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

import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.PrimeFaces;

import com.webapp.model.Emprestimo;
import com.webapp.model.Parcela;
import com.webapp.repository.Emprestimos;
import com.webapp.repository.Parcelas;
import com.webapp.repository.filter.EmprestimoFilter;
import com.webapp.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEmprestimosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Emprestimos emprestimos;

	@Inject
	private Parcelas parcelas;

	private List<Emprestimo> listaEmprestimos = new ArrayList<Emprestimo>();

	private List<Parcela> listaParcelas = new ArrayList<Parcela>();

	private EmprestimoFilter filtro = new EmprestimoFilter();

	@Inject
	private Emprestimo emprestimo;

	private Emprestimo emprestimoSelecionado;

	private static final Locale BRAZIL = new Locale("pt", "BR");

	private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);

	private NumberFormat nf = new DecimalFormat("###,##0.00", REAL);

	private String valorEmprestado;

	private String valorTotalPago;

	private String saldoDevedorInicial;

	private String valorJurosInicial;

	private String valorJurosFinal;

	private String debito;

	private String juros;

	private String total;

	@NotBlank
	private String valorPago;

	@NotBlank
	private String debitoRestante;

	@NotBlank
	private String jurosDaParcela;

	@NotBlank
	private String desconto;

	@NotBlank
	private String valorRestante;

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			listaEmprestimos = emprestimos.todos();
		}
	}

	public void prepararInfo() {
		valorEmprestado = nf.format(Double.parseDouble(emprestimoSelecionado.getValorEmprestimo()));
		valorTotalPago = nf.format(emprestimoSelecionado.getTotalPago().doubleValue());
		saldoDevedorInicial = nf.format(emprestimoSelecionado.getSaldoDevedorInicial().doubleValue());
		valorJurosInicial = nf.format(emprestimoSelecionado.getJurosInicial().doubleValue());
		valorJurosFinal = nf.format(emprestimoSelecionado.getJurosFinal().doubleValue());

		debito = nf.format(emprestimoSelecionado.getDebito().doubleValue());
		juros = nf.format(emprestimoSelecionado.getJuros().doubleValue());
		total = nf.format(emprestimoSelecionado.getTotal().doubleValue());

		listaParcelas = parcelas.todasParcelas(emprestimoSelecionado.getId());
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

	public List<Parcela> getListaParcelas() {
		return listaParcelas;
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

	public String getValorTotalPago() {
		return valorTotalPago;
	}

	public String getSaldoDevedorInicial() {
		return saldoDevedorInicial;
	}

	public String getValorJurosInicial() {
		return valorJurosInicial;
	}

	public String getValorJurosFinal() {
		return valorJurosFinal;
	}

	public String getDebito() {
		return debito;
	}

	public String getJuros() {
		return juros;
	}

	public String getTotal() {
		return total;
	}

	public String getValorPago() {
		return valorPago;
	}

	public void setValorPago(String valorPago) {
		this.valorPago = valorPago;
	}

	public String getDebitoRestante() {
		return debitoRestante;
	}

	public void setDebitoRestante(String debitoRestante) {
		this.debitoRestante = debitoRestante;
	}

	public String getJurosDaParcela() {
		return jurosDaParcela;
	}

	public void setJurosDaParcela(String jurosDaParcela) {
		this.jurosDaParcela = jurosDaParcela;
	}

	public String getDesconto() {
		return desconto;
	}

	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}

	public String getValorRestante() {
		return valorRestante;
	}

	public void setValorRestante(String valorRestante) {
		this.valorRestante = valorRestante;
	}
}
