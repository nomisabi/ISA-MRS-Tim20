
angular.module('myApp').controller('EmployeeController',['$scope','$http','$window',function($scope, $http,$window) {
	

	function init() {
		
		$http.get("http://localhost:8080/api/employees/getlogedin").success(
				function(data){
					$scope.employees=data;
				});

	}
	
    $scope.employees;
    
    init();

    $scope.login= function(){
    	$http.post("http://localhost:8080/api/employees/login",{"email":$scope.mail,"password":$scope.pass, "firstName":"", "lastName":"" })
    	.success(function(data) {
    		$scope.sm=JSON.stringify(data);
    		window.location.href = '#/employee/info';
		}).error(function(data){
			alert("error");
			
		}
				);
    }   	

}]);