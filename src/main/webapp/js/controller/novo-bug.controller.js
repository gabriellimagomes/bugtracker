(function(){

	var app = angular.module('bugTracker');
	app.controller('NovoBug', function($scope, bugService){
		var vm = this;
		$scope.salvaBug = function(bug) {
			bugService.salvaBug(bug)
						.success(function() {
							vm.mensagem = 'Novo bug criado com sucesso';
						})
						.error(function() {
							vm.mensagemErro = 'Não foi possível criar um novo bug';
						});
		}
	});
})();