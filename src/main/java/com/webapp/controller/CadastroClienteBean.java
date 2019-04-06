package com.webapp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.webapp.model.Cliente;
import com.webapp.repository.Clientes;
import com.webapp.repository.filter.ClienteFilter;
import com.webapp.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;

	@Inject
	private Cliente cliente;

	private List<Cliente> todosClientes;

	private Cliente clienteSelecionado;

	private ClienteFilter filtro = new ClienteFilter();

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			listarTodos();
		}
	}

	public void prepararNovoCadastro() {
		cliente = new Cliente();
	}

	public void prepararEditarCadastro() {
		cliente = clienteSelecionado;
	}

	public void salvar() {

		clientes.save(cliente);

		clienteSelecionado = null;

		listarTodos();

		PrimeFaces.current().executeScript(
				"PF('downloadLoading').hide(); swal({ type: 'success', title: 'Concluído!', text: 'Cliente salvo com sucesso!' });");
	}

	public void excluir() {

		clientes.remove(clienteSelecionado);

		clienteSelecionado = null;

		listarTodos();

		PrimeFaces.current().executeScript(
				"swal({ type: 'success', title: 'Concluído!', text: 'Cliente excluído com sucesso!' });");

	}

	public void pesquisar() {
		System.out.println(filtro.getNome());
		todosClientes = clientes.filtrados(filtro);
		clienteSelecionado = null;
	}

	private void listarTodos() {
		todosClientes = clientes.todos();
	}

	public List<Cliente> getTodosClientes() {
		return todosClientes;
	}

	public ClienteFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ClienteFilter filtro) {
		this.filtro = filtro;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}