package br.com.quanta.procontratos.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.quanta.procontratos.modelo.Contrato;
import br.com.quanta.procontratos.modelo.Fornecedor;
import br.com.quanta.procontratos.repositorio.ContratoDaoRepository;
import br.com.quanta.procontratos.repositorio.FornecedorDaoRepository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Resource
public class ContratoControllerJSON {

	private final ContratoDaoRepository contratoDao;
	private final FornecedorDaoRepository fornecedorDao;
	private Result result;
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

	public ContratoControllerJSON(ContratoDaoRepository conDao, FornecedorDaoRepository forDao, Result result) {
		this.contratoDao = conDao;
		this.fornecedorDao = forDao;
		this.result = result;
	}
		
	@Path("/contrato/lista/json")
	public void contratoListaJson() {
		List<Contrato> contratos = new ArrayList<>();
		contratos = contratoDao.pegarTodos();
		String objetoGson = gson.toJson(contratos);
		result.use(Results.http()).body(objetoGson);
	}

	@Path("/contrato/remove/{numero}")
	public void contratoExcluir(final String numero) {
		Contrato contratoTrs = contratoDao.pegaPorNumero(numero);
		contratoDao.deletar(contratoTrs);
		result.nothing();
	}
	
	@Path("/contrato/pegaFornecedores/json")
	public void contratoPegaFornecedores() {
		List<Fornecedor> fornecedores = new ArrayList<>(); 
		fornecedores = fornecedorDao.pegarTodos();
		String objetoGson = gson.toJson(fornecedores);
		result.use(Results.http()).body(objetoGson);
	}
	
	@Path("/contrato/pegaFornecedor/json/{id}")
	public void contratoPegaFornecedor(Long id) {
		Fornecedor fornecedor = fornecedorDao.pegaPorId(id);
		String objetoGson = gson.toJson(fornecedor);
		result.use(Results.http()).body(objetoGson);
	}

}
