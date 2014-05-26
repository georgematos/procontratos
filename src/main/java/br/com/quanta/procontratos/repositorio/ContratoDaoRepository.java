package br.com.quanta.procontratos.repositorio;

import java.util.List;

import br.com.quanta.procontratos.modelo.Contrato;

public interface ContratoDaoRepository {
	
	void salvar(Contrato contrato);
	List<Contrato> pegarTodos();
	Contrato pegaPorNumero(String numero);
	void deletar(Contrato contrato);

}
