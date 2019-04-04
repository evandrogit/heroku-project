package com.webapp.model;

import java.io.Serializable;
import java.util.Date;

public class Parcelas implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer parcela;
	private String valorParcela;
	private Date vencimentoParcela;

	public Integer getParcela() {
		return parcela;
	}

	public void setParcela(Integer parcela) {
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