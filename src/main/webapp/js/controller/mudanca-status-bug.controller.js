(function(){

	var app = angular.module('bugTracker');
	app.controller('MudancaStatusBug', function(bugService, $window){
		var vm = this;
		var idBug = $window.location.search.substr(7);
		bugService.getBug(idBug)
					.success(function(data) {
						vm.bug = data;
					})
					.error(function() {
						vm.mensagemErro = 'Não foi possível trazer os dados do bug';
					});
		this.passaProximaFase = function(descricao) {
			bugService.passaProximaFase(idBug, descricao)
						.success(function() {
							vm.mensagem = 'Este bug foi passado para o próximo status com sucesso';
						})
						.error(function() {
							vm.mensagemErro = 'Não foi possível passar o bug para o próximo status';
						});
		};
		this.passaFaseAnterior = function(descricao) {
			bugService.passaFaseAnterior(idBug, descricao)
						.success(function() {
							vm.mensagem = 'Este bug foi passado para o status anterior com sucesso';
						})
						.error(function() {
							vm.mensagemErro = 'Não foi possível passar o bug para o status anterior';
						});
		};
	});
})();