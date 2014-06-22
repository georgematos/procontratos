package br.com.quanta.procontratos.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.quanta.procontratos.dao.ContratoDao;
import br.com.quanta.procontratos.dao.FornecedorDao;
import br.com.quanta.procontratos.modelo.Contrato;
import br.com.quanta.procontratos.modelo.Fornecedor;
import br.com.quanta.procontratos.repositorio.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Resource
public class ContratoControllerJSON {

	private final Repository<Contrato> contratoDao;
	private final Repository<Fornecedor> fornecedorDao;
	private Result result;
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

	public ContratoControllerJSON(ContratoDao contratoDao, FornecedorDao fornecedorDao, Result result) {
		this.contratoDao = contratoDao;
		this.fornecedorDao = fornecedorDao;
		this.result = result;
	}
		
	@Path("/lista")
	public void contratoListaJson() {
		String objetoGson = gson.toJson(contratoDao.pegarTodos());
		result.use(Results.http()).body(objetoGson);
	}

	@Path("/remove/{numero}")
	public void contratoExcluir(final String numero) {
		Contrato contratoTransient = contratoDao.pegaPorId(numero);
		contratoDao.deletar(contratoTransient);
		result.nothing();
	}
	
	@Path("/pegaFornecedores")
	public void contratoPegaFornecedores() {
		String objetoGson = gson.toJson(fornecedorDao.pegarTodos());
		result.use(Results.http()).body(objetoGson);
	}
	
	@Path("/pegaFornecedor/{id}")
	public void contratoPegaFornecedor(Long id) {
		Fornecedor fornecedor = fornecedorDao.pegaPorId(id);
		String objetoGson = gson.toJson(fornecedor);
		result.use(Results.http()).body(objetoGson);
	}

}
