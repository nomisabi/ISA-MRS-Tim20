'use strict';

angular.module('myApp').controller('GuestHomeController',['$scope','$http','$window', '$ocLazyLoad','GuestHomeFactory','$routeParams',function($scope, $http,$window,$ocLazyLoad, GuestHomeFactory,$route$routeParams) {
	$ocLazyLoad.load('assets/js/common-scripts.js');
	$scope.message = $route$routeParams.id;
	$scope.date = new Date();
    $scope.time = new Date();
    $scope.dateTime = new Date();
    $scope.minDate = moment().subtract(1, 'month');
    $scope.maxDate = moment().add(1, 'month');
    $scope.dateTimeStr = '';
    
	$scope.guest = {id:null,email:'',password:'',firstName:'',lastName:'',address:''};
	$scope.page="";
	$scope.friends = [];
	$scope.users = [];
	$scope.restaurants = [];
	$scope.notifications = [];
	$scope.reservations = [];
	$scope.tables = [];
	$scope.search = ""; 
	$scope.restaurant ={id:null, name:'', location:''};
	$scope.duration = 1;
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
	$scope.id = null;
	$scope.flag = false;
	$scope.drinkReserve = [];
	$scope.regions = [];
	$scope.model = [];
	init();
	
	
	
	
	function popover(tekst) {
		var unique_id = $.gritter.add({
            // (string | mandatory) the heading of the notification
            title: 'Notification',
            // (string | mandatory) the text inside the notification
            text: tekst,
            // (string | optional) the image to display on the left
            image: 'assets/img/ui-sam.jpg',
            // (bool | optional) if you want it to fade out on its own or just sit there
            sticky: false,
            // (int | optional) the time you want it to be alive for before fading out
            time: 5000,
            // (string | optional) the class name you want to apply to that specific message
            class_name: 'my-sticky-class'
        });
		$("#date-popover").popover({html: true, trigger: "manual"});
        $("#date-popover").hide();
        $("#date-popover").click(function (e) {
            $(this).hide();
        });
		
	}
	
	$scope.getNotifications = function() {
		$http.post("http://localhost:8080/api/friendship/getRequest",
				    $scope.guest)
			 .success(
					 function(data){
						 $scope.notifications = data;
						 });
	}
	
	$scope.getGuestLogIn = function(data) {
		$http.post("http://localhost:8080/api/guest/login",
				    data)
			 .success(
					 function(data){
						 $scope.page = "profile";
						 $scope.guest=data;
						 $scope.getNotifications();
			})
			.error(
					function(data){
						$window.location.href="/#/";
					}
			);
	}
	
	function init() {
		$ocLazyLoad.load('assets/js/common-scripts.js');
		
		if ($scope.message === undefined){
			$http.get("http://localhost:8080/api/users/login")
			.success(
				function(data){
					$scope.getGuestLogIn(data);
					
				}
			).error(
				function(data){
					$window.location.href="/";
				}
			);	    
		}else{
			$scope.page = "invite";
			$http.post("http://localhost:8080/api/reservation",{"token":$scope.message})
			.success(
					function(data){
						$scope.reservation = data.reservation;
						$scope.friends = data.friends;
						$scope.guest = data.guest;
						$scope.id = data.id;
						
					}
			).error(
					function(data){
						$window.location.href="/#/";
					}
			);
		}
	}
	
	
	$scope.changeToFriends= function(){
		$scope.users = [];
		$http.post("http://localhost:8080/api/friends",
				   $scope.guest)
			 .success(
					 function(data){
						 $scope.friends=data;	
						 $scope.page="friends";
			 });		
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
    	$http.get("http://localhost:8080/api/restaurants")
    	     .success(
    	    		 function(data){
    	    			 $scope.restaurants=data;
    	    			 $scope.page="restaurants";
    	     });	
    }
    
    $scope.changeToReserve= function(){
    	$scope.users = [];
    	$scope.page="reserve";
    }
    
    $scope.changeToHome= function(){
    	$scope.users = [];
    	$scope.restaurants = [];
    	$scope.reservations = [];
    	$http.post("http://localhost:8080/api/visitedRestaurants", 
    			   $scope.guest)
    	     .success(
    	    		 function(data){
    	    			 $scope.reservations=data;	
    	    			 $scope.page="home";
    	    	     }
    	     );	
    }
    
    $scope.changeToViewReservation = function (id){
    	$scope.tables = [];
    	$scope.menuList = [];
    	$scope.drinkList = [];
    	$scope.friends = [];
    	
    	$http.post('http://localhost:8080/api/reservation/' + id,
    			    $scope.guest)
    		 .success(
    				 function(data) {
    					 $scope.reservation = data.reservation;
    					 $scope.friends = data.guests;
    					 $scope.tables = data.tables;
    					 $scope.menuList = data.menuItems;
    					 $scope.drinkList = data.drinkMenuItems;
    					 $scope.flag = data.flag;
    					 $scope.id = data.guestReservationId;
    					 $scope.page = "view";
    				 }
    	     ).error(
    	    		 function(data){
    	    			 //alert("error");
    	    		 }
    	     ); 	
    }
    
    $scope.save= function(){
    	$http.put('http://localhost:8080/api/guests/'+$scope.guest.id,
    			   $scope.guest)
    		 .success(
    				 function(data) {
    					 if (data.email != $scope.guest.email){
    						 $window.location.href="/#/";
    						 popover("Profile changes successfully saved.Please, log in with a new email address.");
    					 }else{
    						 $scope.guest = data;
    						 $window.location.reload();
    				     }
    		}).error(
    				function(data){
    					alert("Error profile change.");
    				}
    		);
    }
    
    $scope.searchUsers= function(search){   	
    	$http.post('http://localhost:8080/api/friendship/search',
    			   {"firstName":search})
    		 .success(
    				 function(data) {
    					 $scope.users = data;
    				 }
    		 ).error(
    				 function(data){
    					 //alert("error");
    				 }
    		 );         	
    }
    
    $scope.searchFriends= function(search){   	
    	$scope.friends = [];
    	$http.post('http://localhost:8080/api/friendship/searchFriends',
    			   {"firstName":search})
    		 .success(
    				 function(data) {
    					 for (var j = 0; j < $scope.list.length; j++) {
    						 $scope.friends.push($scope.list[j]);
    					 }
    					 
    					 for (var i = 0; i < data.length; i++) {
    						 if (!$scope.exists(data[i])){
    							 $scope.friends.push(data[i]);
    						 }
    				     }
    				 }
    		 ).error(
    				 function(data){
    					 //alert("error");
    				 }
    		 );         	
    }
    
    $scope.addRequest= function(id){   	   	
    	$scope.users = [];
    	$http.post('http://localhost:8080/api/friendship/sendRequest',
    			   {"idGuest":$scope.guest.id,"idFriend":id})
    		.success(
    				function(data) {
    					popover("Request for friendship successfully sent.");
    				}
    		).error(
    				function(data){
    					//alert("error");
    				}
    		); 	
    }
    
    $scope.deleteRequest= function(id){    	
    	$http.post('http://localhost:8080/api/friendship/deleteFriend',
    			   {"idGuest":$scope.guest.id,"idFriend":id})
    		 .success(
    				 function(data) {
    					 //alert("delete");
    					 $scope.getNotifications();
    					 popover("Friend request has been canceled.");
    				 }
    	     ).error(
    	    		 function(data){
    	    			 //alert("error");
    	    		 }
    	     );    
    }
    
    $scope.deleteFriend= function(id){    	
    	$http.post('http://localhost:8080/api/friendship/deleteFriend',
    			    {"idGuest":$scope.guest.id,"idFriend":id})
    		 .success(
    				 function(data) {
    					 $scope.changeToFriends();
    					 popover("Friend successfully deleted from the list of friends.");
    				 }
    		 ).error(
    				 function(data){
    					 //alert("error");
    				 }
    		 );    
    }
  
    $scope.confirmRequest= function(guest){   
    	$http.post('http://localhost:8080/api/friendship/addFriend',
    			    {"idGuest":$scope.guest.id,"idFriend":guest.id})
    	     .success(
    	    		 function(data) {
    	    			 $scope.getNotifications();
    	    			 $scope.changeToFriends();
    	    			 popover("Friend request is accepted.");
    	    		 }
    	     ).error(
    	    		 function(data){
    	    			 //alert("error");
    	    		 }
    	     );        	
    }
    
    $scope.reserve= function(restaurant){   	   	   
    	$scope.tableNum = null;
    	$scope.table = {id:null, number:null, numberOfChairs: null, restaurant: $scope.restaurant};
    	$scope.restaurant = restaurant;
    	$scope.page="reserve";
    }
    
    $scope.reserveNext = function(duration, dateTime){   	   	
    	$scope.duration = duration;
    	$scope.dateTime = dateTime;
    	
    	$scope.reservation = {"restaurant":$scope.restaurant, "dateAndTime":dateTime, "duration": duration};
    	
    	$http.post('http://localhost:8080/api/restaurant/tables',
    			   $scope.reservation)
    		 .success(
    				 function(data) {
    					 $scope.regions = data;
    					 $scope.model = [];
    					 
    					 for (var i=0; i<$scope.regions.length;i++){
    						 var container = {'name':$scope.regions[i].name, 'items': []};
    						 for (var j=0; j<$scope.regions[i].tables.length;j++){
    							 var item= {
    									 "label": $scope.regions[i].tables[j].tableOfRestaurant.number,
    									 "id":$scope.regions[i].tables[j].tableOfRestaurant.id,
    									 "numberOfChairs":$scope.regions[i].tables[j].tableOfRestaurant.numberOfChairs, 
    									 "reserved": $scope.regions[i].tables[j].reserved,
    									 "selected": false
    							 };
    							 container.items.push(item);
    						 }
    						 $scope.model.push([container]);
    					 }
    					 
    					 $scope.dateTimeStr = dateTime.toDateString() + " " + dateTime.toTimeString().slice(0,5);
    					 $scope.list = [];
    					 $scope.tableNum = [];
    					 $scope.page ="reserve2";
    				 }
    	     ).error(
    	    		 function(data){
    	    			 //alert("error");
    	    		 }
    	     ); 
    }
    
    
    $scope.reserveNext2 = function(){   	   	
    	if ($scope.tableNum.length != 0){
    		$http.post('http://localhost:8080/api/restaurant/reservation',
    				   { "restaurant":$scope.reservation.restaurant, 
    			         "dateAndTime":$scope.reservation.dateAndTime, 
    			         "duration": $scope.reservation.duration, 
    			         "tables": $scope.tableNum, 
    			         "guest":$scope.guest})
    			 .success(
    					 function(data) {
    						 $scope.savedReservation = data;
    						 $scope.friends=[];	
    						 $scope.list = [];
    						 $scope.page ="reserve3";
    						 popover("You have successfully reserved a table at a restaurant " + 
    								 $scope.reservation.restaurant.name+".");
    					 }
    		     ).error(
    		    		 function(data){
    		    			 $scope.reserveNext($scope.duration, $scope.dateTime);
    		    			 popover("Table is reserved. Please choose other table.");
    		    		 }
    		     );    		
    	}else{
    		popover("Please, choose a table");	
    	}
    }
    
    $scope.reserveNext3 = function(){   	
    	
    	$http.post('http://localhost:8080/api/restaurant/friends',
    			   {"friends":$scope.list,"reservation":$scope.savedReservation, "guest":$scope.guest})
    		 .success(
    				 function(data) {
    					 $scope.restaurant = data;
    					 $scope.prepared = false;
    					 $scope.menuList = [];
    					 $scope.drinkList = [];
    					 
    					 for (var i = 0; i < data.drinkMenu.items.length; i++) {
    						 $scope.drinkList.push({"item": data.drinkMenu.items[i],
    							                    "price": data.drinkMenu.items[i].price,
    							                     "quality": 0});
    					 }
    					 
    					 for (var i = 0; i < data.menu.items.length; i++) {
    						 $scope.menuList.push({"item": data.menu.items[i],
    							                   "price": data.menu.items[i].price,
    							                   "quality": 0});
    					 }
    					 $scope.page = "reserve4";
    				 }
    	    ).error(
    	    		function(data){
    	    			//alert("error");
    	    		}
    	    ); 
    }
    
    $scope.reserveNext4 = function(){   
    	var listReserveDrink = [];
    	var listReserveFood = [];
  
    	for (var i = 0; i < $scope.drinkList.length; i++) {	
    		if ($scope.drinkList[i].quality > 0){
    			var drink = {"drinkMenuItem": $scope.drinkList[i].item, 
    					    "guest": $scope.guest,
    					    "reservation": $scope.savedReservation, 
    					    "prepared": $scope.prepared, 
    					    "quality": $scope.drinkList[i].quality};
    		
    			listReserveDrink.push(drink);
    		}
		}
    	
    	for (var i = 0; i < $scope.menuList.length; i++) {
			if ($scope.menuList[i].quality > 0){	
				var menu = {"menuItem": $scope.menuList[i].item, 
						   "guest": $scope.guest ,
						   "reservation": $scope.savedReservation, 
						   "prepared": $scope.prepared, 
						   "quality": $scope.menuList[i].quality};
				listReserveFood.push(menu);
			}
		}
    	
    	
    	$http.post('http://localhost:8080/api/restaurant/order',
    			   {"drinkMenuItems":listReserveDrink, "menuItems": listReserveFood})
    	     .success(function(data) {
    	    	 $scope.reservation = data;
    	    	 $scope.changeToHome();
    	     })
    	     .error(
    	    		 function(data){
    	    			 //alert("error");
    	    		 }
    	    );
    }
    
    $scope.choosePrepared = function() {
    	$scope.prepared = !$scope.prepared;
	}
    
    
    $scope.klik = function(item) {
    	if (!item.reserved){
    		item.selected = !item.selected;
    		var idx = $scope.tableNum.indexOf(item);
    		if (idx > -1) {
    			$scope.tableNum.splice(idx, 1);
    		}
    		else {
    			$scope.tableNum.push(item);
    		}
    	}
	}
    
    $scope.addToList = function(item) {
    	var idx = $scope.list.indexOf(item);
        if (idx > -1) {
          $scope.list.splice(idx, 1);
        }
        else {
          $scope.list.push(item);
        }
	}
    
    $scope.addDrink = function(id) {
    	for (var i = 0; i < $scope.drinkList.length; i++) {
			if ( $scope.drinkList[i].item.id == id ){
				$scope.drinkList[i].quality ++;
				break;
			}
		}
	}
    
    $scope.addMenu = function(id) {
    	for (var i = 0; i < $scope.menuList.length; i++) {
			if ( $scope.menuList[i].item.id == id ){
				$scope.menuList[i].quality ++;
				break;
			}
		}
	}
    
    $scope.deleteDrink = function(id) {
    	for (var i = 0; i < $scope.drinkList.length; i++) {
			if ( $scope.drinkList[i].drink.id == id ){
				$scope.drinkList[i].quality --;
				break;
			}
		}
	}
    
    $scope.deleteMenu = function(id) {
    	for (var i = 0; i < $scope.menuList.length; i++) {
			if ( $scope.menuList[i].drink.id == id ){
				$scope.menuList[i].quality --;
				break;
			}
		}
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
    	$scope.reverse = ($scope.myOrder === x) ? !$scope.reverse : false;
        $scope.myOrder = x;
    }
    
    $scope.confirmInvite = function() {
    	$http.post('http://localhost:8080/api/reservation/confirm',
    			   {"id":$scope.id})
    		 .success(
    				 function(data) {
    					 $scope.friends = [];
    					 $scope.savedReservation = $scope.reservation;
    					 $scope.reserveNext3();
    					 popover("Invitation was accepted. Now you can order food or drinks.");
    					 }
    		).error(
    				function(data){
    					//alert("error");
    				}
    		);     
    }
    
    $scope.deleteInvite = function() {
    	
    	$http.post('http://localhost:8080/api/reservation/delete',
    			   {"id":$scope.id})
    		 .success(
    				 function(data) {
    					 $scope.friends = [];
    					 $scope.reservation = [];
    					 $scope.changeToHome();
    					 popover("Invitation canceled.");
    				 }
    	     ).error(
    	    		 function(data){
    	    			 //alert("error");
    	    		 }
    	     );     
    }
    
    
    $scope.deleteReservation = function() {
    	$http.post('/api/reservation/guest/delete',
    			 { "reservation":$scope.reservation, 
    		       "guestReservationId": $scope.id, 
    		       "tables": $scope.tables, 
    		       "guests": $scope.friends, 
    		       "menuItems": $scope.menuList, 
    		       "drinkMenuItems": $scope.drinkList })
    		 .success(
    				 function(data) {
    					 $scope.changeToHome();
    					 popover("Reservation is canceled.");
    				 }
    		 ).error(
    				 function(data){
    					 $scope.reservation = data.reservation;
    					 $scope.friends = data.guests;
    					 $scope.tables = data.tables;
    					 $scope.menuList = data.menuItems;
    					 $scope.drinkList = data.drinkMenuItems;
    					 $scope.flag = data.flag;
    					 $scope.id = data.guestReservationId;
    					 popover("There is less than 30 minutes to the reservation. You can't cancel this reservation.");
    				 }
    		 );     
    }
}])
