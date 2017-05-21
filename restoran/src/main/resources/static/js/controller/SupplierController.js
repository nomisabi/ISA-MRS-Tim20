
angular.module('myApp').controller('SupplierController',['$scope','$http','$window', '$route','$ocLazyLoad',function($scope,$http,$window, $route,$ocLazyLoad) {
	$ocLazyLoad.load('assets/js/common-scripts.js');
	$scope.page="non-active";
	$scope.rating={
		title : 'Rating 3',
		description : 'I\'m editable...',
		rating : 1,
		basedOn : 5,
		starsCount : 5,
		iconClass : 'fa fa-star',
		editableRating : true,
		showGrade : true
	};
	$scope.price=0;
	
	function init() {
		$ocLazyLoad.load('assets/js/common-scripts.js');
		$http.get("http://localhost:8080/api/users/login").success(
				function(data){	
					$http.post("http://localhost:8080/api/suppliers/login",data).success(
							function(data){
								$scope.supplier=data;
								//alert(JSON.stringify(data));
								$http.post("http://localhost:8080/api/suppliers/restaurant",data).then(
										function(data){
											$scope.restaurants=data.data;
											
										});
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
    
    $scope.changeToPage= function(s){
    	$scope.supply=s;
    	$scope.mine=null;
    	$scope.have=false;
    	for(var i=0; i< $scope.supply.offer.length;i++){
    		if ($scope.supply.offer[i].supplier.id==$scope.supplier.id){
    			$scope.mine=$scope.supply.offer[i];
    			$scope.have=true;
    		}
    	}
    	if ($scope.supplier.active)
    		$scope.page="show_supply";   	
    }
    
    $scope.changeToRestaurants= function(){
    	if ($scope.supplier.active)
    		$scope.page="restaurant";   	
    }
    $scope.changeToOrders= function(){
    	$scope.rest=$scope.restaurants;
    	for (var i=0;i<$scope.rest.length;i++){
    		$http.post("http://localhost:8080/api/manager/supply",$scope.rest[i]).success(
					function(data){
						$scope.rest.supplies=data;
					});
    	}
    	if ($scope.supplier.active)
    		$scope.page="orders";   	
    }
    
  	$scope.changeToChangePass= function(){
  		if ($scope.supplier.active)
    		$scope.page="non-active"; 
  	}
  	
  	$scope.createOffer= function(){
  		offer={"supplier":$scope.supplier, "status":"WAITING" , "price":$scope.price, "quality": $scope.rating.rating};
  		$http.post("http://localhost:8080/api/manager/createOffer",{"o":offer, "s": $scope.supply}).success(
				function(data){
					$route.reload();
				}).error(
						function(data){
							alert("error");
						}).then(
								function(data){
									$route.reload();
								});
  	}
}]);
