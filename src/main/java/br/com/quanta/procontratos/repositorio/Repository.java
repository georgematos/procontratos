package br.com.quanta.procontratos.repositorio;

import java.util.List;

public interface Repository<T> {
		
	void salvar(T object);
	List<T> pegarTodos();
	T pegaPorId(Object object); // Pode ser uma String em Contrato ou um Long em Fornecedor
	void deletar(T object);
	Object getId(T object);
	
}
