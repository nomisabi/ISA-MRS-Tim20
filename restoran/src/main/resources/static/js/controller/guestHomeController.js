'use strict';

angular.module('myApp').controller('GuestHomeController',['$scope','$http','$window','GuestHomeFactory',function($scope, $http,$window,$ocLazyLoad, GuestHomeFactory) {
	$scope.guest = {id:null,email:'',password:'',firstName:'',lastName:'',address:''};
	$scope.page="restaurants";
	$scope.friends = [];
	$scope.users = [];
	$scope.notifications = [];
	
	getData();
	
	
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
	
	
	$scope.changeToAddFriend= function(){
    	$scope.page="friend";
    }
	
	$scope.changeToFriends= function(){
    	$scope.page="friends";
    }
    
    $scope.changeToProfile= function(){
    	$scope.page="profile";
    }
    
    $scope.changeToRestaurants= function(){
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
    
    $scope.addRequest= function(id){   	   	
    	for(var i = 0; i < $scope.users.length; i++){
            if($scope.users[i].id === id) {
                $scope.friends[$scope.friends.length] = $scope.users[i];
                $scope.users.splice(i, 1);
                alert($scope.users[i].email);
                break;
            }
        }
    	
    	$http.post('http://localhost:8080/api/sendRequest',{"idGuest":$scope.guest.id,"idFriend":id})
    	.success(function(data) {
    		//
		}).error(function(data){
			//alert("error");
			}
		);    
    	
    	
    	alert("Add request. <"+email+">");
    	
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
