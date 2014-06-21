package br.com.quanta.procontratos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.quanta.procontratos.modelo.Contrato;

@Component
public class ContratoDao extends Dao<Contrato> {

	protected ContratoDao(Session session) {
		super(session);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contrato> pegarTodos() {
		Query query = session.createQuery("from Contrato");
		return  (ArrayList<Contrato>) query.list();
	}
	
	@Override
	public Contrato pegaPorId(Object numero) {
		return (Contrato) session.get(Contrato.class, (String) numero);
	}

	@Override
	public Object getId(Contrato contrato) {
		return contrato.getNumero();
	}

}
