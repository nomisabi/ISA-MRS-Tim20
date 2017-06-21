'use strict';

angular.module('myApp').factory('UserService', ['$http', '$q', function($http, $q){

    var USER_URI = 'http://localhost:8080/api/guests/';
    var LOG_IN_URI = 'http://localhost:8080/api/guests/logIn';
    var FACEBOOK_URI = 'http://localhost:8080/connect/facebook';
    var URI_GUEST = 'http://localhost:8080/api/guestLog';

    var factory = {
    		createUser: createUser,
    		logIn     : logIn,
    		facebookLogIn :facebookLogIn,
    		updateUser:updateUser,
    		fetchUser : fetchUser
        
    };

    return factory;
    
    function createUser(user) {
        var deferred = $q.defer();
        $http.post(USER_URI, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function logIn(user) {
        var deferred = $q.defer();
        $http.post(LOG_IN_URI, user)
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
    
    function facebookLogIn() {
    	
    	 var deferred = $q.defer();
         $http.get(FACEBOOK_URI)
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
    
    function fetchUser() {
        var deferred = $q.defer();
        $http.get(URI_GUEST)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function updateUser(user, id) {
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
   

}]);
