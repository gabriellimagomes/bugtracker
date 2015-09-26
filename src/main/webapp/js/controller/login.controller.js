(function(){

	var app = angular.module('bugTracker');
	app.controller('Login', function($scope, $http, appConfig, $localStorage, $window){
		var vm = this;
		
		$scope.login = function(auth) {			
			
			$http.post(appConfig.path + '/login', auth)
					.success(function(data, status, getHeader, config) {
						var auth = getHeader('Authorization');
						$scope.$storage = $localStorage.$reset();
						$scope.$storage.auth = auth;
						$window.location.href = '/bugtracker/listagem-bugs.html';
					})
					.error(function() {
						$scope.$storage = $localStorage.$reset();
						vm.mensagemErro = 'Não foi possível fazer login com essas informações';
					});
		};
		
		$scope.logout = function() {
			$localStorage.$reset();
			$window.location.href = '/curso/login.html';
		};
	});
})();