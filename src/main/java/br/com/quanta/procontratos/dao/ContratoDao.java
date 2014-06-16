package br.com.quanta.procontratos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.quanta.procontratos.modelo.Contrato;
import br.com.quanta.procontratos.repositorio.ContratoDaoRepository;

@Component
public class ContratoDao implements ContratoDaoRepository {

	private final Session session;
	
	public ContratoDao(Session session) {
		this.session = session;
	}
	
	@Override
	public void salvar(Contrato contrato) {
		
		session.merge(contrato);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contrato> pegarTodos() {
		ArrayList<Contrato> contratos = new ArrayList<>();
		Query query = session.createQuery("from Contrato");
		contratos = (ArrayList<Contrato>) query.list();
		return contratos;
	}

	@Override
	public Contrato pegaPorNumero(String num) {
		Contrato contrato = (Contrato) session.get(Contrato.class, num);
		return contrato;
	}

	@Override
	public void deletar(Contrato contrato) {
		Contrato contratoTransient = pegaPorNumero(contrato.getNumero());
		session.delete(contratoTransient);
	}

}
