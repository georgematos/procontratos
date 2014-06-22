package br.com.quanta.procontratos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.quanta.procontratos.modelo.ItemContratado;
import br.com.quanta.procontratos.repositorio.ItemContratadoDaoRepository;

@Component
public class ItemContratadoDao implements ItemContratadoDaoRepository {

	private final Session session;
	
	public ItemContratadoDao(Session session) {
		this.session = session;
	}
	
	@Override
	public void salvar(ItemContratado item) {
		session.save(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemContratado> pegarTodos() {
		ArrayList<ItemContratado> itens = new ArrayList<>();
		Query query = session.createQuery("from ItemContratado");
		itens = (ArrayList<ItemContratado>) query.list();
		return itens;
	}

	@Override
	public ItemContratado pegaPorNumero(Long num) {
		ItemContratado contrato = (ItemContratado) session.get(ItemContratado.class, num);
		return contrato;
	}

	@Override
	public void deletar(ItemContratado contrato) {
		ItemContratado itemTransient = pegaPorNumero(contrato.getNumero());
		session.delete(itemTransient);
	}

}
