(function(){

	var app = angular.module('bugTracker');
	app.controller('HistoricoBug', function(bugService, $window){
		var vm = this;
		var idBug = $window.location.search.substr(7);
		bugService.getBug(idBug)
					.success(function(data) {
						vm.bug = data;
					})
					.error(function() {
						vm.mensagemErro = 'Não foi possível listar o histórico de eventos desse bug';
					});
	});
})();