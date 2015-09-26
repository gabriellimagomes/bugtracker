(function(){

	var app = angular.module('bugTracker');
	app.controller('ListagemBugs', function($scope, bugService, $window){
		var vm = this;
		bugService.getBugs()
					.success(function(data) {
						vm.bugs = data;
					})
					.error(function() {
						vm.mensagemErro = 'Não foi possível listar os bugs existentes';
					});
		this.direcionaParaMudancaStatus = function(idBug) {
			$window.location.href = '/bugtracker/mudanca-status-bug.html?idBug=' +idBug;
		};
		this.direcionaParaHistorico = function(idBug) {
			$window.location.href = '/bugtracker/historico-bug.html?idBug=' +idBug;
		};
	});
})();