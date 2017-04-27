
angular.module('myApp').controller('SupplierController',['$scope','$http','$window',function($scope, $http,window) {
	

	function init() {

		$http.get("http://localhost:8080/api/suppliers/getlogedin").success(
				function(data){
					$scope.supplier=data;
				});

	}
	
    
    init();

    $scope.login= function(){
    	$scope.supp={"name":"", "email":$scope.mail, "password":$scope.pass};

    	$http.post("http://localhost:8080/api/suppliers/login",JSON.stringify($scope.supp))
    	.success(function(data) {
    		$scope.supplier=JSON.stringify(data);
    		window.location.href = '#/supplier/info';
		}).error(function(data){
			alert("error");
			
		}
				);
    }
    
    $scope.changePass=function(){
    	if ($scope.newPass1!=$scope.newPass2){
    		alert("New passwords are not equals");
    	} else if ($scope.supplier.password!=$scope.oldPass){
    		alert("Old password is not good.");
    	}else{
    		$scope.supplier.password=$scope.newPass1;
    		$http.post("http://localhost:8080/api/suppliers/changePass",JSON.stringify($scope.supplier));
    	}
    }
    
    	

}]);
