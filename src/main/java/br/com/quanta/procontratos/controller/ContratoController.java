package br.com.quanta.procontratos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;
import br.com.quanta.procontratos.modelo.Contrato;
import br.com.quanta.procontratos.modelo.Fornecedor;
import br.com.quanta.procontratos.repositorio.ContratoDaoRepository;
import br.com.quanta.procontratos.repositorio.FornecedorDaoRepository;

@Resource
public class ContratoController {

	private final ContratoDaoRepository conDao;
	private final FornecedorDaoRepository forDao;
	private Result result;
	private Validator validator;

	public ContratoController(ContratoDaoRepository conDao, FornecedorDaoRepository forDao, Result result,
			Validator validator) {
		this.conDao = conDao;
		this.forDao = forDao;
		this.result = result;
		this.validator = validator;
	}
		
	@Path("/")
	public void index() {
		result.redirectTo(ContratoController.class).lista();
	}

	public void formNovoContrato() {
		
	}
	
	public void formEditarContrato(Contrato contrato, HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		Contrato contratoEdit = conDao.pegaPorNumero(request.getParameter("numero"));
		result.include("contrato", contratoEdit);
	}
	
	public void persiste(Contrato contrato, HttpServletRequest request) {

		Long fornecedorId = Long.parseLong(request.getParameter("selecionado.id"));

		Fornecedor fornecedor = new Fornecedor();
		fornecedor = forDao.pegaPorId(fornecedorId+1);
		
		contrato.setFornecedor(fornecedor);
		
		conDao.salvar(contrato);
		
		result.redirectTo(ContratoController.class).lista();
		
	}

	public void contratoSalvar(final Contrato contrato, final HttpServletRequest request) {
		
		System.err.println(contrato.getDataIni());
		System.err.println(contrato.getDataFim());

		validator.checking(new Validations() {
			{
				that(!(conDao.pegaPorNumero(contrato.getNumero()) != null), "erro", "contrato.numero.invalido"); // Verifica se o contrato já existe
				that(!(contrato.getDataFim().before(contrato.getDataIni())), "erro", "contrato.data.invalida"); // Verifica se a data de inicio é maior que a data de fim
				that(!(request.getParameter("selecionado.id").equals("")), "erro", "contrato.fornecedor.invalido"); // Verfica se o campo fornecedor está vazio
			}
		});

		validator.onErrorUsePageOf(ContratoController.class).formNovoContrato();
		
		persiste(contrato, request);

	}
	
	@Path("/contrato/contratoAlterar/{id}")
	public void contratoAlterar(final Contrato contrato, HttpServletRequest request, Long id) throws JsonGenerationException, JsonMappingException, IOException {
		
		validator.checking(new Validations() {
			{
				that(!(contrato.getDataFim().before(contrato.getDataIni())), "erro", "contrato.data.invalida");
			}
		});
		
		contrato.setFornecedor(forDao.pegaPorId(id));
		
		validator.onErrorUsePageOf(ContratoController.class).formEditarContrato(contrato, request);
		
		persiste(contrato, request);
		
	}
	
	public List<Contrato> lista() {
		List<Contrato> contratos = new ArrayList<>();
		contratos = conDao.pegarTodos();
		return contratos;
	}

	@Path("/contrato/lista/json")
	public void contratoListaJson() throws JsonGenerationException,	JsonMappingException, IOException {
		List<Contrato> contratos = new ArrayList<>();
		contratos = conDao.pegarTodos();
		ObjectMapper mapper = new ObjectMapper();
		String objetoJson = mapper.writeValueAsString(contratos);
		result.use(Results.http()).body(objetoJson);
	}

	@Path("/contrato/remove/{numero}")
	public void contratoExcluir(final String numero) {
		Contrato contratoTrs = conDao.pegaPorNumero(numero);
		conDao.deletar(contratoTrs);
		result.nothing();
	}
	
	@Path("/contrato/pegaFornecedores/json")
	public void contratoPegaFornecedores() throws JsonGenerationException, JsonMappingException, IOException {
		List<Fornecedor> fornecedores = new ArrayList<>(); 
		fornecedores = forDao.pegarTodos();
		ObjectMapper mapper = new ObjectMapper();
		String objetoJason = mapper.writeValueAsString(fornecedores);
		result.use(Results.http()).body(objetoJason);
	}
	
	@Path("/contrato/pegaFornecedor/json/{id}")
	public void contratoPegaFornecedor(Long id) throws JsonGenerationException, JsonMappingException, IOException {
		Fornecedor fornecedor = forDao.pegaPorId(id);
		ObjectMapper mapper = new ObjectMapper();
		String objetoJason = mapper.writeValueAsString(fornecedor);
		result.use(Results.http()).body(objetoJason);
	}

}
