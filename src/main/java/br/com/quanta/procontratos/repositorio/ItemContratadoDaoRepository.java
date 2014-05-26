package br.com.quanta.procontratos.repositorio;

import java.util.List;

import br.com.quanta.procontratos.modelo.ItemContratado;

public interface ItemContratadoDaoRepository {
	
	void salvar(ItemContratado item);
	List<ItemContratado> pegarTodos();
	ItemContratado pegaPorNumero(Long numero);
	void deletar(ItemContratado item);

}
