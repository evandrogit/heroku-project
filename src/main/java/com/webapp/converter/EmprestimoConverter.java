package com.webapp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.webapp.model.Emprestimo;
import com.webapp.repository.Emprestimos;

@FacesConverter(forClass = Emprestimo.class)
public class EmprestimoConverter implements Converter {

	@Inject
	private Emprestimos emprestimos;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Emprestimo retorno = null;

		if (value != null && !"".equals(value)) {
			Long id = new Long(value);
			retorno = emprestimos.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Emprestimo eempretimo = (Emprestimo) value;
			return eempretimo.getId() == null ? null : eempretimo.getId().toString();
		}

		return "";
	}
}
