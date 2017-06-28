
angular.module('myApp').controller('EmployeeController',['$scope','$http','$window',function($scope, $http,$window) {
	

	function init() {
		
		$http.get("https://rest-cupcake.herokuapp.com/api/employees/getlogedin").success(
				function(data){
					$scope.employees=data;
				});

	}
	
    $scope.employees;
    
    init();

    $scope.login= function(){
    	$http.post("https://rest-cupcake.herokuapp.com/api/employees/login",{"email":$scope.mail,"password":$scope.pass, "firstName":"", "lastName":"" })
    	.success(function(data) {
    		$scope.sm=JSON.stringify(data);
    		window.location.href = '#/employee/info';
		}).error(function(data){
			alert("error");
			
		}
				);
    }   	

}]);