(function(){

	var app = angular.module('bugTracker');
	app.factory('bugService', function($http, appConfig) {
		
		var _private = {
			getBug: function(idBug){
				return $http.get(appConfig.path + '/bugs/' + idBug);
			},
			salvaBug: function(bug) {
				return $http.post(appConfig.path + '/bugs', bug);
			},
			getBugs: function() {
				return $http.get(appConfig.path + '/bugs');
			},
			
			passaProximaFase: function(idBug, descricao) {
				return $http.put(appConfig.path + '/bugs/status/proximo', {id: idBug, descricao: descricao});
			},
			
			passaFaseAnterior: function(idBug, descricao) {
				return $http.put(appConfig.path + '/bugs/status/anterior', {id: idBug, descricao: descricao});
			}
		};
		
		return {
			getBug: _private.getBug,
			salvaBug: _private.salvaBug,
			getBugs: _private.getBugs,
			passaProximaFase: _private.passaProximaFase,
			passaFaseAnterior: _private.passaFaseAnterior
		}
	});
})();