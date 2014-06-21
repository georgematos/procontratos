package br.com.quanta.procontratos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.quanta.procontratos.modelo.Fornecedor;

@Component
public class FornecedorDao extends Dao<Fornecedor> {

	protected FornecedorDao(Session session) {
		super(session);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fornecedor> pegarTodos() {
		Query query = session.createQuery("from Fornecedor");
		return  (ArrayList<Fornecedor>) query.list();
	}

	@Override
	public Fornecedor pegaPorId(Object id) {
		return (Fornecedor) session.get(Fornecedor.class, (Long) id);
	}

	@Override
	public Object getId(Fornecedor fornecedor) {
		return fornecedor.getId();
	}

}
