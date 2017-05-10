'use strict';

angular.module('myApp').controller('GuestHomeController',['$scope','$http','$window','GuestHomeFactory',function($scope, $http,$window,$ocLazyLoad, GuestHomeFactory) {
	$scope.guest = {id:null,email:'',password:'',firstName:'',lastName:'',address:''};
	$scope.page="profile";
	$scope.friends = [];
	$scope.users = [];
	$scope.restaurants = [];
	$scope.notifications = [];
	$scope.tables = [];
	$scope.search = ""; 
	$scope.restaurant ={id:null, name:'', location:''};
	$scope.min = new Date();
	$scope.max = new Date('2017-06-01');
	$scope.time = new Date('2017-06-01 9:00');
	$scope.date = new Date();
	$scope.duration = 1;
	$scope.dateStr = "";
	$scope.timeStr = "";
	$scope.reservation = {"restaurant":null, "dateAndTime":'', "duration": ''};
	$scope.table = {id:null, number:null, numberOfChairs: null, restaurant: $scope.restaurant};
	$scope.tableNum = null;
	
	init();
	
	
	function init() {
		$http.get("http://localhost:8080/api/users/login")
		.success(
				function(data){
					$http.post("http://localhost:8080/api/guest/login",data)
					.success(
							function(data){
								$scope.guest=data;
								$http.post("http://localhost:8080/api/friendship/getRequest",data).success(
										function(data){
											$scope.notifications = data;
											//alert($scope.notifications.length);
										});
							}
					).error(
							function(data){
								$window.location.href="/#/";
							}
					);
					
				}
		).error(
				function(data){
					$window.location.href="/";
				}
		);	    
	}
	
	
	$scope.changeToFriends= function(){
		$scope.users = [];
		$http.post("http://localhost:8080/api/friends",$scope.guest).success(
				function(data){
					$scope.friends=data;	
				});	
    	$scope.page="friends";
    }
	
	$scope.changeToFriend= function(){
		$scope.users = [];	
    	$scope.page="friend";
    }
    
    $scope.changeToProfile= function(){
    	$scope.users =[];
    	$scope.page="profile";
    }
    
    $scope.changeToRestaurants= function(){
    	$scope.users = [];
    	$http.get("http://localhost:8080/api/restaurants").success(
				function(data){
					$scope.restaurants=data;	
				});	
    	$scope.page="restaurants";
    }
    
    $scope.changeToReserve= function(){
    	$scope.users = [];
    		
    	$scope.page="reserve";
    }
    
    
    
    $scope.save= function(){
    	$http.put('http://localhost:8080/api/guests/'+$scope.guest.id,$scope.guest)
    	.success(function(data) {
    		alert("Profile changes successfully saved.");
    		if (data.email != $scope.guest.email){
    			$window.location.href="/#/";
    		}else{
    			$scope.guest = data;
    			$window.location.reload();
    		}
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
    
    $scope.confirmRequest= function(guest){   
    	
    	//alert(guest.id);
    	//alert($scope.guest.id);
    	$http.post('http://localhost:8080/api/friendship/addFriend',{"idGuest":$scope.guest.id,"idFriend":guest.id})
    	.success(function(data) {
    		alert("Request confirm");
    		$window.location.reload();
    		$scope.page = "friends";
		}).error(function(data){
			//alert("error");
			}
		);        	
    }
    
    $scope.reserve= function(restaurant){   	   	
    	//alert(restaurant.id);    
    	$scope.tableNum = null;
    	$scope.restaurant = restaurant;
    	$scope.page="reserve";
    }
    
    $scope.reserveNext = function(duration, date){   	   	
    	//alert($scope.time.toLocaleTimeString());    
    	//alert(date.toDateString());
    	//alert($scope.restaurant.name);
    	//alert(duration);
    	
    	var dateTime = new Date();
    	dateTime.setDate(date.getDate());
    	dateTime.setMonth(date.getMonth());
    	dateTime.setFullYear(date.getFullYear());
    	dateTime.setHours($scope.time.getHours());
    	dateTime.setMinutes($scope.time.getMinutes());
    	dateTime.setSeconds($scope.time.getSeconds());
    	dateTime.setMilliseconds($scope.time.getMilliseconds());
    	
    	$scope.reservation = {"restaurant":$scope.restaurant, "dateAndTime":dateTime, "duration": duration};
    	
    	
    	$http.post('http://localhost:8080/api/restaurant/tables',$scope.reservation)
    	.success(function(data) {
    		$scope.tables = data;
    		//alert(data);
    		$scope.dateStr = $scope.reservation.dateAndTime.toLocaleDateString();
    		$scope.timeStr = $scope.reservation.dateAndTime.toLocaleTimeString();
    		$scope.page ="reserve2";
		}).error(function(data){
			//alert("error");
			}
		); 
    }
    
    $scope.reserveNext2 = function(){   	   	
    	//alert($scope.tableNum);
    	if ($scope.tableNum != null){
    		$http.post('http://localhost:8080/api/restaurant/reservation',{"restaurant":$scope.reservation.restaurant, "dateAndTime":$scope.reservation.dateAndTime, "duration": $scope.reservation.duration, "table": $scope.table, "guest":$scope.guest})
        	.success(function(data) {
        		$scope.tableNum = null;
        	//	alert("Reservation succesful");
        		$scope.page ="reserve3";
        		
    		}).error(function(data){
    			alert("Table is reserved. Please choose other table.");
    			}
    		); 
    		
    		
    	 		
    		
    		
    		
    		
    	}else{
    		alert("Please, choose a table")
    	}
    	
    }
    
    $scope.reserveNext3 = function(){   	
    	
    	
    	
    }
    
    $scope.klik = function(vrednost) {
    	if (vrednost.reserved){
    		alert("Table is reserved");
    		$scope.tableNum = null;
    	}
    	else{
    		$scope.tableNum = vrednost.tableOfRestaurant.id;
    		$scope.table = vrednost.tableOfRestaurant;
    		alert(vrednost.tableOfRestaurant.number);
    	}
	}
    
    $scope.logout= function(){
    	$http.get("http://localhost:8080/api/users/logout");
    }
    

}]);
