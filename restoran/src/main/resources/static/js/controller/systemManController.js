'use strict';

angular.module('myApp').controller('SystemManagerController',['$scope','$http','$window','SystemManagerFactory',function($scope, $http,window,SystemManagerFactory) {
	
    //$scope.sm={email:'myaddress',password:'mypassword'};
	$scope.managers=[];
	function init() {

		$http.get("http://localhost:8080/api/sysman/getlogedin").success(
				function(data){
					$scope.sm=data;
				});
		
	    $scope.managers.push($http.get("http://localhost:8080/api/managers"));
	}
	
    $scope.manager;
    
    $scope.restaurants=[];
    $scope.proba="proba"
    init();
    $scope.createManager= function (){   	
    	$scope.manager.restaurant=null;
    	$http.post("http://localhost:8080/api/sysman/createManager",JSON.stringify($scope.manager))
        .then(function (response) { if (response.data!="") $scope.managers.push(response.data); else alert("this email address in in our system"); });    	
    }
    
    $scope.login= function(){
    	$http.post("http://localhost:8080/api/sysman/login",{"email":$scope.mail,"password":$scope.pass })
    	.success(function(data) {
    		$scope.sm=JSON.stringify(data);
    		window.location.href = '#/sysman/info';
		}).error(function(data){
			alert("error");
			
		}
				);
    }
    
    $scope.reg= function() {
		if ($scope.newpass!=$scope.newpass2){
			if ($scope.newpass1=="")
				alert("Password is required.");	
			alert("Passwords are not equals.");}
		else if ($scope.newemail=="")
			alert("Email is required.");
		else
		{
			$http.post("http://localhost:8080/api/sysman/signUp",{"email":$scope.newemail,"password":$scope.newpass}).success(
					function(data) {
						window.location.href = '#/sysman/login';
					}).error(function(data){
						alert('This email address is exist.');
					});
		}
    	
	}
    
    	

}]);
