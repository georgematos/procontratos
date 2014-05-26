package br.com.quanta.procontratos.repositorio;

import java.util.List;

import br.com.quanta.procontratos.modelo.Fornecedor;

public interface FornecedorDaoRepository {
	
	void salvar(Fornecedor fornecedor);
	List<Fornecedor> pegarTodos();
	Fornecedor pegaPorId(Long id);
	void deletar(Fornecedor fornecedor);

}
