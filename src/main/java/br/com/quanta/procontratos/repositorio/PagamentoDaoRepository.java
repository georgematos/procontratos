package br.com.quanta.procontratos.repositorio;

import java.util.List;

import br.com.quanta.procontratos.modelo.Pagamento;

public interface PagamentoDaoRepository {
	
	void salvar(Pagamento pag);
	List<Pagamento> pegarTodos();
	Pagamento pegaPorId(Long id);
	void deletar(Pagamento pag);

}
