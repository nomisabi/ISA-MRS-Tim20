'use strict';

angular.module('myApp').factory('EmployeeService', ['$http', '$q', function($http, $q){
	
	var EMPLOYEE_URI = 'http://localhost:8080/api/employees/';
    var LOG_IN_URI = 'http://localhost:8080/api/employees/login';
    var FACEBOOK_URI = 'http://localhost:8080/connect/facebook';
    var URI_EMPLOYEE = 'http://localhost:8080/api/employeeLog';
    
    var factory = {
    		login    : login
    };
    
    return factory;
    
    function login(employee) {
        var deferred = $q.defer();
        $http.post(LOG_IN_URI, employee)
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
    
}]);