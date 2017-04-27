
angular.module('myApp').controller('ManagerController',['$scope','$http','$window',function($scope, $http,window) {
	

	function init() {

		$http.get("http://localhost:8080/api/manager/getlogedin").success(
				function(data){
					$scope.manager=data;
				});

	}
	
    $scope.manager;
    
    init();

    $scope.login= function(){
    	$http.post("http://localhost:8080/api/manager/login",{"email":$scope.mail,"password":$scope.pass, "firstName":"", "lastName":"" })
    	.success(function(data) {
    		$scope.sm=JSON.stringify(data);
    		window.location.href = '#/man/info';
		}).error(function(data){
			alert("error");
			
		}
				);
    }
    
    	

}]);
