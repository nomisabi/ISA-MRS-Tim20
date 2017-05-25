
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
	$scope.price=1;
	$scope.updateOff=false; 
	
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
											$scope.rest=$scope.restaurants;
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
    
    $scope.updateOffer=function(){
		offer={"id":$scope.mine.id,"supplier":$scope.supplier, "status":"WAITING" , "price":$scope.updatePrice, "quality": $scope.updateRating.rating};
  		//alert(JSON.stringify(offer));
		$http.post("http://localhost:8080/api/suppliers/updateOffer",offer).success(
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
    
    $scope.changeToUpdateOffer= function(){
    	//alert("haho");
    	$scope.updatePrice= $scope.mine.price;
    	$scope.updateRating={
    			title : 'Rating 3',
    			description : 'I\'m editable...',
    			rating : $scope.mine.quality,
    			basedOn : 5,
    			starsCount : 5,
    			iconClass : 'fa fa-star',
    			editableRating : true,
    			showGrade : true
    		};
    	$scope.offer_status="update"; 	
    }
    
    $scope.changeToMyOffer= function(){
    	$http.post("http://localhost:8080/api/suppliers/getSuppliesWitMyOffer",$scope.supplier).success(
				function(data){
					$scope.myoffer=data;
				});
    	$scope.page="myOffer"; 	
    }
    
    
    function formatDate(date){
    	var day = date.getDate();
    	day= day > 10 ? day : "0"+day;
        var monthIndex = date.getMonth()+1;
        monthIndex= monthIndex > 10 ? monthIndex : "0"+monthIndex;
        var year = date.getFullYear();
        var hours = date.getHours()-2;
        hours= hours > 10 ? hours : "0"+hours;
        var minutes = date.getMinutes();
        minutes= minutes > 10 ? minutes : "0"+minutes;
        var sec= date.getSeconds(); 
        sec= sec > 10 ? sec : "0"+sec;
        
    	return year+"-"+monthIndex+"-"+day+"T"+hours+":"+minutes+":"+sec;
    }
    
    $scope.changeToPage= function(s){
    	$scope.supply=s;
    	$scope.mine=null;
    	$scope.have=false;
    	$scope.finished=false;
    	$scope.updatable=false;
    	
    	var date =formatDate(new Date());
		if (s.from_date>date){
			alert("This supply is not started yet.");
			return;
		}
		if (s.to_date<date ){
			$scope.finished=true;
		}
			
    	for(var i=0; i< $scope.supply.offer.length;i++){
    		if ($scope.supply.offer[i].supplier.id==$scope.supplier.id){
    			$scope.mine=$scope.supply.offer[i];
    			$scope.have=true;
    		}
    	}
    	
    	if ($scope.have==true){
    		$scope.offer_status="show_mine";
    		if (!s.chosed && !$scope.finished)
    			$scope.updatable=true;
    	}
    	else
    		{
    			if (!s.chosed && !$scope.finished)
    				$scope.offer_status="new";
    			else if (s.chosed)
    				$scope.offer_status="not_active";
    			else if (!s.chosed && $scope.finished)
    				$scope.offer_status="expired";
    		
    		}

    	if ($scope.supplier.active)
    		$scope.page="show_supply";   	
    }
    
    $scope.changeToRestaurants= function(){
    	if ($scope.supplier.active)
    		$scope.page="restaurant";   	
    }
    $scope.changeToOrders= function(){
    	for (var i=0;i<$scope.rest.length;i++){
    		$http.post("http://localhost:8080/api/manager/supply",$scope.rest[i]).success(
					function(data){
						$scope.rest.supplies=data;
					});
    	}
    	$http.post("http://localhost:8080/api/manager/supply_choosed",$scope.supplier).success(
				function(data){
					$scope.rest.wait=data;
				});
    	if ($scope.supplier.active)
    		$scope.page="orders";   	
    }
    
    $scope.sendOffer=function(supply){
    	mine=null;
    	for(var i=0; i< supply.offer.length;i++){
    		if (supply.offer[i].supplier.id==$scope.supplier.id){
    			mine=supply.offer[i];
    		}
    	}
    	$http.post("http://localhost:8080/api/suppliers/sendOffer",mine).success(
				function(data){
					$route.reload();
				});
    } 
    
    $scope.changeToOrdersHistory= function(){
    	$scope.rest=$scope.restaurants;
    	for (var i=0;i<$scope.rest.length;i++){
    		$http.post("http://localhost:8080/api/manager/supply_hist",$scope.rest[i]).success(
    				function(data){
    					$scope.rest.supplies_hist=data;
    				});
    	}
    	if ($scope.supplier.active)
    		$scope.page="history";   	
    }
    
  	$scope.changeToChangePass= function(){
  		if ($scope.supplier.active)
    		$scope.page="non-active"; 
  	}
  	
  	$scope.createOffer= function(){
  		//alert($scope.price);
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
