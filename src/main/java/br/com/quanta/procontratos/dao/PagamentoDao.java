package br.com.quanta.procontratos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.quanta.procontratos.modelo.Pagamento;
import br.com.quanta.procontratos.repositorio.PagamentoDaoRepository;

@Component
public class PagamentoDao implements PagamentoDaoRepository {

	private final Session session;
	
	public PagamentoDao(Session session) {
		this.session = session;
	}
	
	@Override
	public void salvar(Pagamento pag) {
		session.save(pag);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pagamento> pegarTodos() {
		ArrayList<Pagamento> pagamentos = new ArrayList<>();
		Query query = session.createQuery("from Pagamento");
		pagamentos = (ArrayList<Pagamento>) query.list();
		return pagamentos;
	}

	@Override
	public Pagamento pegaPorId(Long id) {
		Pagamento pag = (Pagamento) session.get(Pagamento.class, id);
		return pag;
	}

	@Override
	public void deletar(Pagamento pag) {
		Pagamento pagamentoTransient = pegaPorId(pag.getId());
		session.delete(pagamentoTransient);
	}

}
