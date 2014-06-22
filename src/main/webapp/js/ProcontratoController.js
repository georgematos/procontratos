// lógica do controller
var funcao = function($scope, $http, $resource) {

	// Recupera um Array de contratos via requisição ajax ao servidor de aplicação.
    $scope.carregarListaDeContratos = function() {
    	$http.get('/procontratos/lista').success(function(retorno) {
    		$scope.contratos = retorno;
	    }).error(function(msg) {
	    	$scope.mensagem = "Nao foi possivel recuparar a lista, por favor tente mais tarde.";
	    	console.log(msg);
	    });
    };
    
    // Recupera um Array de fornecedores via requisição ajax ao servidor de aplicação.
    $scope.carregarListaDeFornecedores = function() {
    	$http.get('/procontratos/pegaFornecedores').success(function(retorno) {
    		$scope.fornecedores = retorno;
    	}).error(function(msg) {
    		$scope.mensagem = "Não foi possível recuparar a lista, por favor tente mais tarde.";
    		console.log(msg);
    	});
    };
    
    $scope.iniciarFornecedor = function(id) {
    	$http.get('/procontratos/pegaFornecedor/'+id).success(function(retorno) {
    		$scope.fornecedor = retorno;
    	}).error(function(msg) {
    		$scope.mensagem = "Não foi possével recuparar o fornecedor, por favor tente mais tarde.";
    		console.log(msg);
    	});
    };
    
    $scope.remove = function(contrato) {
    	var conf = confirm("Tem certeza que deseja apagar o contrato?");
    	if(conf == true) {
    		$http.post('/procontratos/remove/'+contrato.numero).success(function(retorno) {
    			$scope.carregarListaDeContratos();
    		}).error(function(msg) {
    			$scope.mensagem = "Não foi possível excluir o contrato, tente novamente.";
    		});    		
    	}    	
    };

    $scope.carregarListaDeContratos();
    $scope.carregarListaDeFornecedores();
    
};


// a variável app é usada pra associar o módulo com um controller
app.controller('ProcontratoController', funcao);
