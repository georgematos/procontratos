// lógica do controller
var funcao = function($scope, $http) {

	// Cria array de contratos
    loadContList = function() {
    	$http.get('/procontratos/contrato/lista/json').success(function(retorno) {
    		$scope.contratos = retorno;
	    }).error(function(msg) {
	    	$scope.mensagem = "Não foi possível recuparar a lista, por favor tente mais tarde.";
	    	console.log(msg);
	    });
    };
    
    // Cria array de fornecedores
    
    $http.get('/procontratos/contrato/pegaFornecedores/json').success(function(retorno) {
    	$scope.fornecedores = retorno;
    }).error(function(msg) {
    	$scope.mensagem = "Não foi possível recuparar a lista, por favor tente mais tarde.";
    	console.log(msg);
    });
    
    $scope.iniciarFornecedor = function(id) {
    	$http.get('/procontratos/contrato/pegaFornecedor/json/'+id).success(function(retorno) {
    		$scope.fornecedor = retorno;
	    }).error(function(msg) {
	    	$scope.mensagem = "Não foi possível recuparar o fornecedor, por favor tente mais tarde.";
	    	console.log(msg);
	    });
    };
    
    loadContList();
    
    $scope.remove = function(contrato) {
    	
    	var conf = confirm("Tem certeza que deseja apagar o contrato?");
    	if(conf == true) {
    		$http.post('/procontratos/contrato/remove/'+contrato.numero).success(function(retorno) {
    			loadContList();
    		}).error(function(msg) {
    			$scope.mensagem = "Não foi possível excluir o contrato, tente novamente.";
    		});    		
    	}
    	
    };

};

// a variável app é usada pra associar o módulo com um controller
app.controller('ProcontratoController', funcao);
