package br.com.quanta.procontratos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.quanta.procontratos.dao.ContratoDao;
import br.com.quanta.procontratos.dao.FornecedorDao;
import br.com.quanta.procontratos.modelo.Contrato;
import br.com.quanta.procontratos.modelo.Fornecedor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Resource
public class ContratoController {

	private final ContratoDao contratoDao;
	private final FornecedorDao fornecedorDao;
	private Result result;
	private Validator validator;
	private Gson gson;
	
	public ContratoController(ContratoDao contratoDao, FornecedorDao fornecedorDao, Result result,
			Validator validator) {
		this.contratoDao = contratoDao;
		this.fornecedorDao = fornecedorDao;
		this.result = result;
		this.validator = validator;
		this.gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); // Configura o formato da data
		
	}
		
	@Path("/")
	public void index() {
		result.redirectTo(ContratoController.class).lista();
	}
	
	public void formEditarContrato(HttpServletRequest request) {
		Contrato contrato = gson.fromJson(request.getParameter("contrato"), Contrato.class);
		result.include("contrato", contrato);
	}
	
	public void persiste(Contrato contrato, HttpServletRequest request) {

		Long fornecedorId = Long.parseLong(request.getParameter("selecionado.id"));
		
		Fornecedor fornecedor = fornecedorDao.pegaPorId(fornecedorId);
		
		contrato.setFornecedor(fornecedor);
		
		contratoDao.salvar(contrato);
		
		result.redirectTo(ContratoController.class).lista();
		
	}

	public void contratoSalvar(final Contrato contrato, final HttpServletRequest request) {
		
		validator.checking(new Validations() {
			{
				that(!((contratoDao).pegaPorId(contrato.getNumero()) != null), "erro", "contrato.numero.invalido"); // Verifica se o contrato já existe
				that(!(contrato.getDataFim().before(contrato.getDataInicio()) || !(contrato.getDataFim().after(contrato.getDataInicio()))), "erro", "contrato.data.invalida"); // Verifica se a data de inicio é maior que a data de fim
				that(!(request.getParameter("selecionado.id").equals("")), "erro", "contrato.fornecedor.invalido"); // Verfica se o campo fornecedor está vazio
			}
		});
		
		validator.onErrorUsePageOf(ContratoController.class).formEditarContrato(request);
		
		persiste(contrato, request);

	}
	
	public void contratoAlterar(final Contrato contrato, final HttpServletRequest request) {
		
		validator.checking(new Validations() {
			{
				that(!(contrato.getDataFim().before(contrato.getDataInicio()) || !(contrato.getDataFim().after(contrato.getDataInicio()))), "erro", "contrato.data.invalida"); // Verifica se a data de inicio é maior que a data de fim
				that(!(request.getParameter("selecionado.id").equals("?")), "erro", "contrato.fornecedor.invalido"); // Se o campo estiver vazio, o angular envia "?" na requisição.
			}
		});
				
		validator.onErrorUsePageOf(ContratoController.class).formEditarContrato(request);
				
		persiste(contrato, request);
		
	}
	
	public List<Contrato> lista() {
		List<Contrato> contratos = new ArrayList<>();
		contratos = contratoDao.pegarTodos();
		return contratos;
	}
	
}
