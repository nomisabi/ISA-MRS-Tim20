'use strict';

angular.module('myApp').controller('GuestHomeController',['$scope','$http','$window','GuestHomeFactory',function($scope, $http,$window,$ocLazyLoad, GuestHomeFactory) {
	$scope.guest = {id:null,email:'',password:'',firstName:'',lastName:'',address:''};
	$scope.page="restaurants";
	$scope.friends = [];
	$scope.users = [];
	$scope.notifications = [];
	$scope.search = "";
	
	init();
	
	
	function init() {
		$http.get("http://localhost:8080/api/users/login").success(
				function(data){
					$http.post("http://localhost:8080/api/guest/login",data).success(
							function(data){
								$scope.guest=data;
							});
				});	    
	}
	
	/*
	function getData() {
		$scope.guest = {id:1,email:'email',password:'pass',firstName:'nena',lastName:'djeric',address:'address'};
		$scope.users = [
			{id:2,email:'email1',password:'pass1',firstName:'nena1',lastName:'djeric1',address:'address1'},
			{id:3,email:'email2',password:'pass2',firstName:'nena2',lastName:'djeric2',address:'address2'}
		];
		
		$http.get('http://localhost:8080/api/guestLog')
    	.success(function(data) {
    		$scope.guest = data;
    		$scope.guest.id = 1;
    		
		}).error(function(data){
			alert("Error profile change.");
			}
		);	
	}
	*/
	
	$scope.changeToAddFriend= function(){
		$scope.users = [];
    	$scope.page="friend";
    }
	
	$scope.changeToFriends= function(){
		$scope.users = [];
    	$scope.page="friends";
    }
    
    $scope.changeToProfile= function(){
    	$scope.users =[];
    	$scope.page="profile";
    }
    
    $scope.changeToRestaurants= function(){
    	$scope.users = [];
    	$scope.page="restaurants";
    }
    
    $scope.save= function(){
    	$http.put('http://localhost:8080/api/guests/'+$scope.guest.id,$scope.guest)
    	.success(function(data) {
    		alert("Profile changes successfully saved.");
		}).error(function(data){
			alert("Error profile change.");
			}
		);
		   	
    }
    
    $scope.searchUsers= function(search){   	
    	$http.post('http://localhost:8080/api/friendship/search',{"firstName":search})
    	.success(function(data) {
    		$scope.users = data;
		}).error(function(data){
			//alert("error");
			}
		);         	
    }
    
    $scope.addRequest= function(id){   	   	
    	alert(id);
    	$scope.users = [];
    	$http.post('http://localhost:8080/api/friendship/sendRequest',{"idGuest":$scope.guest.id,"idFriend":id})
    	.success(function(data) {
    		alert("Request successfuly sent.")
		}).error(function(data){
			//alert("error");
			}
		); 
    	
    	
    	
    	
    }
    $scope.deleteRequest= function(email){
    	
    	alert("Delete request.<"+email+">");
    	
    }
    $scope.deleteFriend= function(id){
    	alert("Delete friend.<"+id+">");
    	for(var i = 0; i < $scope.friends.length; i++){
            if($scope.friends[i].id === id) {
                $scope.users[$scope.friends.length] = $scope.friends[i];
                $scope.friends.splice(i, 1);
                break;
            }
        }	
    	
    	$http.post('http://localhost:8080/api/deleteFriend',{"idGuest":$scope.guest.id,"idFriend":id})
    	.success(function(data) {
    		//
		}).error(function(data){
			//alert("error");
			}
		);   
    }
    
    
    $scope.addFriend= function(id){   	   	
    	$http.post('http://localhost:8080/api/addFriend',{"idGuest":$scope.guest.id,"idFriend":id})
    	.success(function(data) {
    		//
		}).error(function(data){
			//alert("error");
			}
		);        	
    }

}]);
