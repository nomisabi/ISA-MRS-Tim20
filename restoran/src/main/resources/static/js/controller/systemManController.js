'use strict';

angular.module('myApp').controller('SystemManagerController',['$scope','$http','$window','$route','$ocLazyLoad','SystemManagerFactory', 'geocoder','NgMap', function($scope, $http,$window,$route, $ocLazyLoad,SystemManagerFactory, geocoder, NgMap) {

	$scope.page="profile";
	$scope.man_without_rest=[];
	$scope.out=null;
	$scope.my_place_id = "ChIJVTTpPWEQW0cRKP4kN2h9bws";
	$scope.string="45.2671352,19.83354959999997";
	
	$scope.$watch("out", function(newValue, oldValue) {
	        if ($scope.out.formatted_address.length > 3) {
	            
	            var s=JSON.stringify($scope.out.geometry.location);
	            var str= s.split(',');
	            var lat=str[0].split(':');
	            var lng1=str[1].split(':');
	            var lng=lng1[1].split('}');
	        	$scope.string=lat[1]+","+lng[0];
	        }
	      });
	
	//$scope.string2=""+$scope.out.location.lan+","+$scope.out.location.lng;
	 NgMap.getMap().then(function(map) {
		    console.log(map.getCenter());
		    console.log('markers', map.markers);
		    console.log('shapes', map.shapes);
		  });
	
	function init() {
		$ocLazyLoad.load('assets/js/common-scripts.js');
		$http.get("http://localhost:8080/api/users/login").success(

				function(data){
					$http.post("http://localhost:8080/api/sysman/login",data).success(
							function(data){
								$scope.sm=data;
								if (data==null)
									$window.location.href="/";
							}).error(
									function(data){
										$window.location.href="/";
									})
				});
	    $http.get("http://localhost:8080/api/manager").then(
	    		function(data){
	    			$scope.managers=data.data;
	    			//if ($scope.managers.count()!=0){
	    			$http.post("http://localhost:8080/api/manager/getRestColl",$scope.managers).error(
								function(data){	
							}).success(
								function(data){
									$scope.managers=data;
									for (var i = 0; i < $scope.managers.length; i++){
								    	if ($scope.managers[i].restaurant==null)
								    		$scope.man_without_rest.push($scope.managers[i]);
								    }
							});
	    				//}
	    			});
	    $http.get("http://localhost:8080/api/users").then(
	    		function(data){
	    			$scope.users=data.data;
	    		});
	    $http.get("http://localhost:8080/api/sysman/restaurants").then(
	    		function(data){
	    			$scope.restaurants=data.data;
	    		});
	    
	    
	}
	
	//$ocLazyLoad.load('common-scripts.js');
	/*function myNavFunction(id) {
	    $("#date-popover").hide();
	    var nav = $("#" + id).data("navigation");
	    var to = $("#" + id).data("to");
	    console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
	}
	
	var unique_id = $.gritter.add({
	    // (string | mandatory) the heading of the notification
	    title: 'Welcome to Dashgum!',
	    // (string | mandatory) the text inside the notification
	    text: 'Hover me to enable the Close Button. You can hide the left sidebar clicking on the button next to the logo. Free version for <a href="http://blacktie.co" target="_blank" style="color:#ffd777">BlackTie.co</a>.',
	    // (string | optional) the image to display on the left
	    image: 'assets/img/ui-sam.jpg',
	    // (bool | optional) if you want it to fade out on its own or just sit there
	    sticky: true,
	    // (int | optional) the time you want it to be alive for before fading out
	    time: '',
	    // (string | optional) the class name you want to apply to that specific message
	    class_name: 'my-sticky-class'
	});
*/

    
    $scope.restaurants=[];
    $scope.proba="proba"
    init();
    
    $scope.createManager= function (){   	
    	$scope.manager.restaurant=null;
    	$http.post("http://localhost:8080/api/sysman/createManager",JSON.stringify($scope.manager))
        .then(function (response) { if (response.data!="") $scope.managers.push(response.data); else alert("this email address in in our system"); });    	
    }
    
    
    $scope.new_manager= function(){
    	$scope.new_man.password="pass";
    	$scope.new_man.active=false;
    	$http.post("http://localhost:8080/api/manager/addManager",JSON.stringify($scope.new_man))
    		.error(function(data){
					alert('This email address is exist.');
					return;
				}).then(function(data){
			    	$window.location.reload();
					$scope.page="manager";   
				});		
    }	
    
    $scope.new_sysmanager= function(){   
    	//alert(JSON.stringify($scope.new_sysman));
    	$http.post("http://localhost:8080/api/sysman/createSysman",JSON.stringify($scope.new_sysman))
    		.error(function(data){
					alert('This email address is exist.');
					//$window.location.reload();
				}).then(function(data){
			    	$window.location.reload();
				});		
    }
    
    $scope.new_restaurant= function(){
    	$scope.new_rest.location= $scope.out.formatted_address;
    	$http.post("http://localhost:8080/api/sysman/addRest",{"r":$scope.new_rest, "m":$scope.new_rest_manager})
    		.error(function(data){
					alert('This restaurant name is exist.');
					return;
			}).success(function(data){
					$window.location.reload();
					$scope.page="restaurant";  
				});
    	
    	 	
    }
    
    
    $scope.logout= function(){
    	$http.get("http://localhost:8080/api/users/logout");
    }
   
    
    
    $scope.update=function(){
    	$http.post("http://localhost:8080/api/sysman/update", $scope.sm).success(function(data) {
    		$window.location.reload();
    	}).error(function(data) {
    		alert("This email address is in our system");	
    		$window.location.reload();
    	});
    }
    
    $scope.changePass=function(){
    	if ($scope.pw1==$scope.pw2){
    		$scope.login= $scope.sm;
    		$scope.login.password=$scope.pw1;
    		//alert("iit vok");
    		//alert(JSON.stringify($scope.login));
    		$http.post("http://localhost:8080/api/sysman/updatePass",$scope.login).success(
					function(data){
						//alert("mar megint szorakozik");
						$window.location.reload();
					});
    	} else {
    		alert("Passwords don't equals");
    		}
    }


    
    $scope.changeToManager= function(){
    	$scope.page="manager";
    	
    }
    
    $scope.changeToProfile= function(){
    	$scope.page="profile";
    	
    }
    $scope.changeToRestaurants= function(){
    	/*for (var i = 0; i < $scope.managers.length; i++){
	    	if ($scope.managers[i].restaurant==null)
	    		$scope.man_without_rest.push($scope.managers[i]);
	    }*/
    	$scope.page="restaurant";
    	
    }
    $scope.changeToCreateManager= function(){
    	$scope.page="new_manager";    	
    }
    $scope.changeToCreateRestaurant= function(){
    	$scope.page="new_restaurant";    	
    }
    $scope.changeToUsers= function(){
    	$scope.page="users";    	
    }
    $scope.changeToCreateSysman= function(){
    	$scope.page="new_sysman";    	
    }
    $scope.changeToChangePassword= function(){
    	$scope.page="new_pass";    	
    }
   
  

}]);
