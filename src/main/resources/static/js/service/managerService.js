angular.module('myApp').factory('managerFactory', ['$http','$q', function($http, $q){
	var factory = {};
	
	factory.proba(){
		document.write('g/');
	};
	
	return factory;
}]);