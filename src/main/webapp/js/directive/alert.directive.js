(function(){

	var app = angular.module('bugTracker');
	app.directive('alert', function() {
		return {
			templateUrl: 'shared/alert-body.html',
			restrict: 'AE',
			scope: {
				mensagemSucesso: '=',
				mensagemErro: '='
			}
		};
	});
})();