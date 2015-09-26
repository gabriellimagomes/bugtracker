(function(){

	var app = angular.module('bugTracker');
	app.factory('bugHttpInterceptor', function($localStorage){
		
		return {
			request: function(config) {
				config.headers['Authorization'] = $localStorage.$default().auth;
				config.headers['Content-Type'] = 'application/json; charset=utf-8';
				
				return config;
			}
		} ;
	});
	
	app.config(['$httpProvider', function($httpProvider) {  
	    $httpProvider.interceptors.push('bugHttpInterceptor');
	}]);
	
})();