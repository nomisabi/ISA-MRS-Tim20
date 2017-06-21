
angular.module('myApp').controller('SupplierController',['$scope','$http','$window', '$route','$ocLazyLoad',function($scope,$http,$window, $route,$ocLazyLoad) {
	$ocLazyLoad.load('assets/js/common-scripts.js');
	$scope.page="non-active";
	
	function init() {
		$ocLazyLoad.load('assets/js/common-scripts.js');
		$http.get("http://localhost:8080/api/users/login").success(
				function(data){	
					$http.post("http://localhost:8080/api/suppliers/login",data).success(
							function(data){
								$scope.supplier=data;
								//alert(JSON.stringify(data));
								if ($scope.supplier.active)
									$scope.page="profile";
							}).error(
									function(data){
										$window.location.href="/";
									});
				});
		
		

	}
	
    init();
 
    $scope.logout=function(){
    	$http.get("http://localhost:8080/api/users/logout").success(function(data) {
    		//$window.href.location="/#/";
    	});
    	
    }
    
    $scope.update=function(){
    	$http.post("http://localhost:8080/api/suppliers/update", $scope.supplier).success(function(data) {
    		$route.reload();
    	}).error(function(data) {
    		alert("This email address is in our system");	
    		$route.reload();
    	});
    }
    
    $scope.changePass=function(){
    	if ($scope.pw1==$scope.pw2){
    		$scope.login= $scope.supplier;
    		$scope.login.password=$scope.pw1;
    		//alert("iit vok");
    		//alert(JSON.stringify($scope.login));
    		$http.post("http://localhost:8080/api/suppliers/changePass",$scope.login).success(
					function(data){
						//alert("success");
						$route.reload();
					});
    	} else {
    		alert("Passwords don't equals");
    		}
    }


    $scope.changeToProfile= function(){
    	if ($scope.supplier.active)
    		$scope.page="profile";   	
    }
    
    $scope.changeToRestaurants= function(){
    	if ($scope.supplier.active)
    		$scope.page="restaurant";   	
    }
    
  	$scope.changeToChangePass= function(){
  		if ($scope.supplier.active)
    		$scope.page="non-active"; 
  	}
}]);
