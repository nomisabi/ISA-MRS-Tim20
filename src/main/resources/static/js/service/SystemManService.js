'use strict';

angular.module('myApp').factory('SystemManagerFactory',['$http', '$q',  function($http, $q) {


    var CREATEMAN_URI = 'http://localhost:8080/api/sysman/createManager';


    var factory = {
    		
    }; 
    
    factory.proba= function(){
    	document.write('ey mukodik');
    	return 1;
    	
    }
    
    factory.createManager=function(manager) {
    	alert('fdfs');
        var deferred = $q.defer();
        $http.post(CREATEMAN_URI, manager)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while User log in');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    return factory;
}]);