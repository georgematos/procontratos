package br.com.quanta.procontratos.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.quanta.procontratos.repositorio.Repository;

public abstract class Dao<T> implements Repository<T> {
	
	protected final Session session;
	
	protected Dao(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(T object) {
		session.merge(object);		
	}

	@Override
	public void deletar(T object) {
		Object objectTransient = pegaPorId(getId(object));
		session.delete(objectTransient);		
	}

	@Override
	public abstract List<T> pegarTodos();

	@Override
	public abstract T pegaPorId(Object object);

	@Override
	public abstract Object getId(T object);

}
