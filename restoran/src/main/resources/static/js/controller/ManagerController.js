
angular.module('myApp').controller('ManagerController',['$scope','$http','$window','$route', function($scope, $http,window, $route) {
	
	$scope.page="non-active";
	function init() {
		$http.get("http://localhost:8080/api/users/login").success(
				function(data){	
					$http.post("http://localhost:8080/api/manager/login",data).success(
							function(data){
								$scope.manager=data;
								if ($scope.manager.active)
									$scope.page="profile";							
							});
				});

	}
	
    init();

    $scope.changePass=function(){
    	if ($scope.pw1==$scope.pw2){
    		$scope.login= $scope.manager;
    		$scope.login.password=$scope.pw1;
    		$http.post("http://localhost:8080/api/manager/changePass",$scope.login).success(
					function(data){
						$route.reload();
					});
    	} else {
    		alert("Passwords don't equals");
    		}
    }


    $scope.changeToProfile= function(){
    	if ($scope.manager.active)
    		$scope.page="profile";
    	
    }
    $scope.changeToRestaurants= function(){
    	if ($scope.manager.active)
    		$scope.page="restaurant";   	
    }
    	
    $scope.changeToMenu= function(){ 
    	if ($scope.manager.active)
    		$scope.page="menu";   	  
    }
  	$scope.changeToDrinkMenu= function(){
  		if ($scope.manager.active)
    		$scope.page="drinkmenu";   	
  	}
  	$scope.changeToSupplier= function(){  
  		if ($scope.manager.active)
    		$scope.page="supplier";   	
    
  	}
  	$scope.changeToEmployee= function(){
  		if ($scope.manager.active)
    		$scope.page="employee"; 
  	}
  	$scope.changeToSchedule= function(){
  		if ($scope.manager.active)
    		$scope.page="schedule"; 
  	}
  	$scope.changeToRoens= function(){
  		if ($scope.manager.active)
    		$scope.page="reon"; 
  	}
  	$scope.changeToScores= function(){
  		if ($scope.manager.active)
    		$scope.page="scores"; 
  	}

}]);
