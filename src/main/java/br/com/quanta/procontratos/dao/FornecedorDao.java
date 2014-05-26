package br.com.quanta.procontratos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.quanta.procontratos.modelo.Fornecedor;
import br.com.quanta.procontratos.repositorio.FornecedorDaoRepository;

@Component
public class FornecedorDao implements FornecedorDaoRepository {

	private final Session session;
	
	public FornecedorDao(Session session) {
		this.session = session;
	}
	
	@Override
	public void salvar(Fornecedor fornecedor) {
		session.save(fornecedor);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fornecedor> pegarTodos() {
		ArrayList<Fornecedor> fornecedores = new ArrayList<>();
		Query query = session.createQuery("from Fornecedor");
		fornecedores = (ArrayList<Fornecedor>) query.list();
		return fornecedores;
	}

	@Override
	public Fornecedor pegaPorId(Long id) {
		Fornecedor fornecedor = (Fornecedor) session.get(Fornecedor.class, id);
		return fornecedor;
	}

	@Override
	public void deletar(Fornecedor fornecedor) {
		Fornecedor fornecedorTransient = pegaPorId(fornecedor.getId());
		session.delete(fornecedorTransient);
	}

}
