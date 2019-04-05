package com.webapp.model;

import java.io.Serializable;
import java.util.Date;

public class Parcelas implements Serializable {

	private static final long serialVersionUID = 1L;

	private String parcela;
	private String valorParcela;
	private Date vencimentoParcela;

	public String getParcela() {
		return parcela;
	}

	public void setParcela(String parcela) {
		this.parcela = parcela;
	}

	public String getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(String valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Date getVencimentoParcela() {
		return vencimentoParcela;
	}

	public void setVencimentoParcela(Date vencimentoParcela) {
		this.vencimentoParcela = vencimentoParcela;
	}

}