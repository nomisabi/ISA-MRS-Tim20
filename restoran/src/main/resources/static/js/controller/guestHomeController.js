'use strict';

angular.module('myApp').controller('GuestHomeController',['$scope','$http','$window','GuestHomeFactory',function($scope, $http,$window,$ocLazyLoad, GuestHomeFactory) {
	$ocLazyLoad.load('assets/js/common-scripts.js');
	$scope.guest = {id:null,email:'',password:'',firstName:'',lastName:'',address:''};
	$scope.page="profile";
	$scope.friends = [];
	$scope.users = [];
	$scope.restaurants = [];
	$scope.notifications = [];
	$scope.reservations = [];
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
	$scope.reservation = {id:null,"restaurant":null, "dateAndTime":'', "duration": ''};
	$scope.table = {id:null, number:null, numberOfChairs: null, restaurant: $scope.restaurant};
	$scope.tableNum = [];
	$scope.list = [];
	$scope.myOrder = '';
	$scope.reverse = false;
	$scope.savedReservation = {id:null,"restaurant":null, "startTime":'', "endTime": ''};
	$scope.menuList =[];
	$scope.drinkList = [];
	$scope.prepared = false;
	
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
    	$scope.restaurants = [];
    	$scope.reservations = [];
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
    
    $scope.changeToHome= function(){
    	$scope.users = [];
    	$scope.restaurants = [];
    	$scope.reservations = [];
    	$http.post("http://localhost:8080/api/visitedRestaurants", $scope.guest).success(
				function(data){
					$scope.reservations=data;	
				});	
    		
    	$scope.page="home";
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
    
    $scope.searchFriends= function(search){   	
    	$scope.friends = [];
    	$http.post('http://localhost:8080/api/friendship/searchFriends',{"firstName":search})
    	.success(function(data) {
    		for (var j = 0; j < $scope.list.length; j++) {
				$scope.friends.push($scope.list[j]);
			}
    		
    		for (var i = 0; i < data.length; i++) {
    			if (!$scope.exists(data[i])){
    				$scope.friends.push(data[i]);
    			}
				
			}
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
    
    $scope.deleteRequest= function(id){    	
    	$http.post('http://localhost:8080/api/friendship/deleteFriend',{"idGuest":$scope.guest.id,"idFriend":id})
    	.success(function(data) {
    		//$scope.changeToFriends();
    		$window.location.reload();
		}).error(function(data){
			//alert("error");
			}
		);    
    }
    
    $scope.deleteFriend= function(id){    	
    	$http.post('http://localhost:8080/api/friendship/deleteFriend',{"idGuest":$scope.guest.id,"idFriend":id})
    	.success(function(data) {
    		$scope.changeToFriends();
    		//$window.location.reload();
		}).error(function(data){
			//alert("error");
			}
		);    
    }
   
    
    $scope.confirmRequest= function(guest){   
    	$http.post('http://localhost:8080/api/friendship/addFriend',{"idGuest":$scope.guest.id,"idFriend":guest.id})
    	.success(function(data) {
    		alert("Request confirm");
    		$window.location.reload();
    	//	$scope.changeToFriends();
		}).error(function(data){
			//alert("error");
			}
		);        	
    }
    
    $scope.reserve= function(restaurant){   	   	
    	//alert(restaurant.id);    
    	$scope.tableNum = null;
    	$scope.table = {id:null, number:null, numberOfChairs: null, restaurant: $scope.restaurant};
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
    		$scope.dateStr = $scope.reservation.dateAndTime.toDateString();
    		$scope.timeStr = $scope.reservation.dateAndTime.toLocaleTimeString();
    		$scope.list = [];
    		$scope.tableNum = [];
    		$scope.page ="reserve2";
		}).error(function(data){
			//alert("error");
			}
		); 
    }
    
    $scope.reserveNext2 = function(){   	   	
    	//alert($scope.tableNum);
    	if ($scope.tableNum.length != 0){
    		$http.post('http://localhost:8080/api/restaurant/reservation',{"restaurant":$scope.reservation.restaurant, "dateAndTime":$scope.reservation.dateAndTime, "duration": $scope.reservation.duration, "tables": $scope.tableNum, "guest":$scope.guest})
        	.success(function(data) {
        		$scope.savedReservation = data;
        		$scope.friends=[];	
        		$scope.list = [];
        		$scope.page ="reserve3";
        				
        	//	alert("Reservation succesful");
        		
        		
    		}).error(function(data){
    			
    			alert("Table is reserved. Please choose other table.");
    			}
    		);    		
    		
    		
    	}else{
    		alert("Please, choose a table")
    	}
    	
    }
    
    $scope.reserveNext3 = function(){   	    	
    	$http.post('http://localhost:8080/api/restaurant/friends',{"friends":$scope.list,"reservation":$scope.savedReservation})
    	.success(function(data) {
    		//alert("Friends invited");
    		$scope.restaurant = data;
    		$scope.prepared = false;
    		$scope.menuList = [];
    		$scope.drinkList = [];
    		$scope.page = "reserve4";
		}).error(function(data){
			//alert("error");
			}
		); 
    	
    }
    
    $scope.reserveNext4 = function(){   	
    	$http.post('http://localhost:8080/api/restaurant/order',{"menuItems":$scope.menuList,"drinkMenuItems":$scope.drinkList, "reservation": $scope.savedReservation, "guest": $scope.guest, "prepared": $scope.prepared})
    	.success(function(data) {
    		//alert("Order");
    		$scope.reservation = data;
    		$scope.changeToHome();
    		
		}).error(function(data){
			//alert("error");
			}
		); 
    
    }
    
    $scope.choosePrepared = function() {
    	//alert(item.id);
    	$scope.prepared = !$scope.prepared;
    	//alert($scope.prepared);
    	
  
	}
    
    
    $scope.klik = function(item) {
    	//alert(item.id);
    	var idx = $scope.tableNum.indexOf(item);
        if (idx > -1) {
          $scope.tableNum.splice(idx, 1);
        }
        else {
          $scope.tableNum.push(item);
        }
        //alert($scope.tableNum);
    	//if (vrednost.reserved){
    	//	alert("Table is reserved");
    	//	$scope.tableNum = null;
    	//}
    	//else{
    //		$scope.tableNum = vrednost.tableOfRestaurant.id;
    //		$scope.table = vrednost.tableOfRestaurant;
    //		alert(vrednost.tableOfRestaurant.number);
    //	}
	}
    
    $scope.addToList = function(item) {
    	//alert(item.id);
    	var idx = $scope.list.indexOf(item);
        if (idx > -1) {
          $scope.list.splice(idx, 1);
        }
        else {
          $scope.list.push(item);
        }
        //alert($scope.list);
	}
    
    $scope.addToMenuList = function(item) {
    	//alert(item.id);
    	var idx = $scope.menuList.indexOf(item);
        if (idx > -1) {
          $scope.menuList.splice(idx, 1);
        }
        else {
          $scope.menuList.push(item);
        }
        //alert($scope.list);
	}
    
    $scope.addToDrinkList = function(item) {
    	//alert(item.id);
    	var idx = $scope.drinkList.indexOf(item);
        if (idx > -1) {
          $scope.drinkList.splice(idx, 1);
        }
        else {
          $scope.drinkList.push(item);
        }
        //alert($scope.list);
	}
    
    $scope.logout= function(){
    	$http.get("http://localhost:8080/api/users/logout");
    }
    
    $scope.exists = function(item) {
		for (var i = 0; i < $scope.list.length; i++) {
			if ($scope.list[i].id == item.id){
				return true;
			}
		}
		return false;
	}
    
    $scope.orderByMe = function(x) {
    	//alert(x);
    	$scope.reverse = ($scope.myOrder === x) ? !$scope.reverse : false;
        $scope.myOrder = x;
    }
    

}]);
