'use strict';

angular.module('myApp').factory('GuestHomeFactory',['$http', '$q',  function($http, $q) {
	
	var USER_URI = 'http://localhost:8080/api/guests/';


	    var factory = {
	    		
	    		
	    }; 
	    
	    factory.updateUser=function updateUser(user, id) {
	    	alert("da");
	        var deferred = $q.defer();
	        $http.put(USER_URI+id, user)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while updating User');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	    
	    
	  
	    
	    return factory;
	    
	    

   
}]);