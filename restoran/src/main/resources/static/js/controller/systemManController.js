'use strict';

angular.module('myApp').controller('SystemManagerController',['$scope','$http','$window','SystemManagerFactory',function($scope, $http,$window,$ocLazyLoad, SystemManagerFactory) {
	
	$scope.managers=[];
	$scope.page="profile";
	function init() {

		$http.get("http://localhost:8080/api/users/login").success(
				function(data){
					$http.post("http://localhost:8080/api/sysman/login",data).success(
							function(data){
								$scope.sm=data;
							});
				});
	    $scope.managers.push($http.get("http://localhost:8080/api/managers"));
	    
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
    
    $scope.changeToManager= function(){
    	$scope.page="manager";
    	
    }
    
    $scope.changeToProfile= function(){
    	$scope.page="profile";
    	
    }
    $scope.changeToRestaurants= function(){
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
    
    	

}]);
