
angular.module('myApp').controller('ManagerController',['$scope','$http','$window','$route','$ocLazyLoad','$mdDialog','$timeout', 'calendarConfig', function($scope, $http,$window, $route, $ocLazyLoad, $mdDialog, $timeout,calendarConfig) {
	$ocLazyLoad.load('assets/js/common-scripts.js');
	//popover
	
    $scope.status = '  ';
    $scope.customFullscreen = false;
    $scope.cont=['tables'];
    
    $scope.page="non-active";
	$scope.updateMenu=false;
	$scope.typeOfEmployee = [{
		    value: 'CHEF',
		    label: 'Chef'
		  }, {
		    value: 'WAITER',
		    label: 'Waiter'
		  }, {
			value: 'BARTENDER',
			label: 'Bartender'
		  }]; 
	$scope.selected = $scope.typeOfEmployee[0];
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
	
	$scope.user1 = null;
	  $scope.users1 = null;

	  $scope.loadUsers = function() {
		  $scope.users1 =  $scope.users1  || [
	  		                          	        { id: 1, name: 'Scooby Doo' },
	  		                          	        { id: 2, name: 'Shaggy Rodgers' },
	  		                          	        { id: 3, name: 'Fred Jones' },
	  		                          	        { id: 4, name: 'Daphne Blake' },
	  		                          	        { id: 5, name: 'Velma Dinkley' }
	  		                          	      ]; 
	    // Use timeout to simulate a 650ms request.

	    return $timeout(function() {
	    	$scope.users1 = $scope.existing_suppliers || null;
	    }, 650);
	  }; 

	function init() {
		
		$http.get("http://localhost:8080/api/users/login").success(
				function(data){	
					$http.post("http://localhost:8080/api/manager/login",data).success(
							function(data){
								$scope.manager=data;
								$http.post("http://localhost:8080/api/manager/getRest",$scope.manager).success(
										function(data){
											if (data==null)
												$window.location.href="/";
											//popover(JSON.stringify(data));
											$scope.manager.restaurant=data;
											
											
									});
								
								if ($scope.manager.active)
									$scope.page="profile";
							}).error(function(data){
								$window.location.href="/";
							});
				});
	}
	
    init();
 
    $scope.showPrompt = function(ev) {
        // Appending dialog to document.body to cover sidenav in docs app
        var confirm = $mdDialog.prompt()
          .title('What would you name your region?')
          .textContent('')
          .placeholder('Name of region')
          .ariaLabel('Dog name')
          .initialValue('')
          .targetEvent(ev)
          .ok('Create!')
          .cancel('Cancel');

        $mdDialog.show(confirm).then(function(result) {
          $scope.status = 'You decided to name your dog ' + result + '.';
          $http.post("http://localhost:8080/api/manager/regions", {"id":$scope.manager.restaurant.id}).then(function(data){
  			for (var i=0; i<$scope.regions.length;i++){

	  			if ($scope.regions[i].name == result){
	  				popover("Region with this name is exist.");
	  				return;
	  				}
	  			}
 
  			$scope.regions=data.data;
  			region={"name":result,"restaurant":$scope.manager.restaurant};
    		  $http.post("http://localhost:8080/api/manager/newRegion",region).then(function(data){
    			$http.post("http://localhost:8080/api/manager/regions", {"id":$scope.manager.restaurant.id}).then(function(data){
    	  			$scope.regions=data.data;
    			});
    			  var container = {name:result, id:data.data.id, items: [], effectAllowed: 'all', copying:true};
    			  $scope.model.push([container]);
    			  //popover(JSON.stringify($scope.model));
    		  });
          });
		
  		  
        }, function() {
        //  $scope.status = 'You didn\'t name your dog.';
        });
      };
      
      $scope.updatePrompt = function(ev, container) {
          // Appending dialog to document.body to cover sidenav in docs app
          var confirm = $mdDialog.prompt()
            .title('Update the name of region')
            .textContent('')
            .placeholder('New name of region')
            .ariaLabel('Dog name')
            .initialValue('')
            .targetEvent(ev)
            .ok('Change!')
            .cancel('Cancel');

          $mdDialog.show(confirm).then(function(result) {
        	  for (var i=0; i<$scope.regions.length;i++){
  	  			if ($scope.regions[i].name == result){
  	  				popover("Region with this name is exist.");
  	  				return;
  	  				}
  	  			}
        	  container.name=result;
        	  $http.post("http://localhost:8080/api/manager/updateRegion", container).then(function(data){
    	  			//popover("ok");
    			});
            }, function() {
              //$scope.status = 'You didn\'t name your dog.';
            });
        };
      
      
    
    $scope.logout=function(){
    	$http.get("http://localhost:8080/api/users/logout").success(function(data) {
    		$window.href.location="/#/";
    	});
    	
    }
    
    $scope.update=function(){
    	$http.post("http://localhost:8080/api/manager/update", $scope.manager).success(function(data) {
    		$ocLazyLoad.load('assets/js/common-scripts.js')
    		init();
    		//$route.reload();		
    	}).error(function(data) {
    		popover("This email address is in our system");	
    		$route.reload();
    	});
    }
    
    $scope.changePass=function(){
    	if ($scope.pw1==$scope.pw2){
    		$scope.login= $scope.manager;
    		$scope.login.password=$scope.pw1;
    		$http.post("http://localhost:8080/api/manager/changePass",$scope.login).success(
					function(data){
						//$window.href.location="/#/";
						//$window.href.location="/#/man/index/";
						$route.reload();
					});
    	} else {
    		popover("Passwords don't equals");
    		}
    }

    $scope.addSupToRest=function(user1){
    	$http.post("http://localhost:8080/api/manager/addSuppToRest",{"r":$scope.manager.restaurant, "s":user1}).success(
	    		function(data){
	    			$route.reload();
	    		});
    	
    }
    
    $scope.changeToProfile= function(){
    	if ($scope.manager.active)
    		$scope.page="profile";
    	
    }
    
    $scope.changeToAddExistin= function(){
    	if ($scope.supp==true)
    		$scope.supp=false;
    	else
    		$scope.supp=true;
    	
    }
    
    $scope.changeToOffers= function(i){
    	$scope.supply=i;
    	
    	if ($scope.manager.active)
    		$scope.page="offers";
    	
    }
    
    $scope.changeToRestaurants= function(){
    	if ($scope.manager.active)
    		$scope.page="restaurant";   	
    }
    	
    $scope.changeToMenu= function(){ 
    	if ($scope.manager.restaurant==null)
		{
		popover("There is no restaurant!");
		return;
		}
    	if ($scope.manager.active)
    		$scope.page="menu";   	  
    }
    $scope.changeToNewItem= function(){ 
    	if ($scope.manager.active){
    		$scope.page="new_item_menu";   
    	}
    }
  	$scope.changeToDrinkMenu= function(){
  		if ($scope.manager.restaurant==null)
		{
		popover("There is no restaurant!");
		return;
		}
  		if ($scope.manager.active)
    		$scope.page="drinkmenu";   	
  	}
    $scope.changeToNewDrinkItem= function(){ 
    	if ($scope.manager.active){
    		$scope.page="new_drink_item";   
    	}
    }
    
    $scope.changeToHistorySupply=function(){
    	$http.post("http://localhost:8080/api/manager/supply_hist", $scope.manager.restaurant).success(function(data) {
    		$scope.supplies_hist=data;
    	}).error(function(data) {
    		//popover("error");	
    	});
    	if ($scope.manager.active)
    		$scope.page="history";  
    }
  	$scope.changeToSupplier= function(){
  		if ($scope.manager.restaurant==null)
		{
		popover("There is no restaurant!");
		return;
		}
  		$http.post("http://localhost:8080/api/suppliers/getSuppliersRest", $scope.manager.restaurant).success(function(data) {
    		$scope.existing_suppliers=data;
    		//popover(JSON.stringify($scope.existing_suppliers));
    	}).error(function(data) {
    		//popover("error");	
    	});
  		if ($scope.manager.active)
    		$scope.page="supplier";   	
  	}
  	$scope.changeToCreateSupplier= function(){  
  		if ($scope.manager.active)
    		$scope.page="create_supplier"; 
  	}
  	$scope.changeToProcurements= function(){  
  		if ($scope.manager.restaurant==null)
		{
		popover("There is no restaurant!");
		return;
		}
  		$http.post("http://localhost:8080/api/manager/supply", $scope.manager.restaurant).success(function(data) {
    		$scope.supplies=data;
    	}).error(function(data) {
    		//popover("error");	
    	});
  		if ($scope.manager.active)
    		$scope.page="supplies";
  	}
  	$scope.changeToCreateSupply= function(){  
  		
  		if ($scope.manager.active){
  			
  			$scope.date = new Date();
  			
  		    $scope.supply={};
  		  
  		   $scope.supply.from_date= new Date();
  		   
  		    $scope.supply.to_date= $scope.supply.from_date;
  		    
  		    $scope.minDate = moment().subtract(1, 'month');
  		    $scope.minDate2 = $scope.supply.from_date;
  		    
  		   // $scope.maxDate = moment().add(1, 'month');
    		$scope.page="create_supply";
    		//popover("123")
  		}
  	}
  	$scope.changeToEmployee= function(){
  		if ($scope.manager.restaurant==null)
		{
		popover("There is no restaurant!");
		return;
		}
  		if ($scope.manager.active)
    		$scope.page="employee"; 
  	}
  	$scope.changeToCreateEmployee= function(){
  		//employee.date= new Date(employee.date);
  		if ($scope.manager.active)
    		$scope.page="create_employee"; 
  	}
  	$scope.changeToSchedule= function(){
  		if ($scope.manager.restaurant==null)
		{
		popover("There is no restaurant!");
		return;
		}
  		if ($scope.manager.active)
    		$scope.page="schedule"; 
  	}
  	$scope.changeToRegions= function(){
  		if ($scope.manager.restaurant==null)
		{
		popover("There is no restaurant!");
		return;
		}
  		$http.post("http://localhost:8080/api/manager/regions", {"id":$scope.manager.restaurant.id}).then(function(data){
			$scope.regions=data.data;	
			$scope.model2=[
	                   [{
	                	 "name":"New item",
	                     "items": [
	                       {
	                         "label": "chair 2",
	                         "effectAllowed": "copy",
	                         "id":0,
	                         "numberOfChairs":2
	                       },
	                       {
	                         "label": "chair 4",
	                         "effectAllowed": "copy"
	                        , "id":0 ,
	  	                    "numberOfChairs":4
	                       },
	                       {
	                         "label": "chair 6",
	                         "effectAllowed": "copy"
	                        	, "id":0 ,
	  	                         "numberOfChairs":6
	                       },
	                       {
	                         "label": "chair 8",
	                         "effectAllowed": "copy"
	                        	, "id":0 ,
	  	                         "numberOfChairs":8
	                       }
	                     ],
	                     "effectAllowed": "all",
	                   "copying": false
	                   }
	                   ]];
			$scope.model=[];
	  		//popover(JSON.stringify($scope.regions));
	  		
	  		for (var i=0; i<$scope.regions.length;i++){
	  			var container = {name:$scope.regions[i].name, id:$scope.regions[i].id, items: [], effectAllowed: 'all', copying:true};
	  			items=[];
	  			for (var j=0; j<$scope.regions[i].tables.length;j++){
	  
	  				item= {
		                         "label": $scope.regions[i].tables[j].number,
		                         "effectAllowed": "move",
		                         "id":$scope.regions[i].tables[j].id,
	  	                         "numberOfChairs":$scope.regions[i].tables[j].numberOfChairs
		                       };
	  				items.push(item)
	  			}
	  			container.items=items;
	  			$scope.model.push([container]);
	  			
	  		}
		});
  		
  		
  		if ($scope.manager.active)
    		$scope.page="regions"; 
  	}
  	$scope.changeToScores= function(){
  		if ($scope.manager.restaurant==null)
		{
		popover("There is no restaurant!");
		return;
		}
  		$http.post("http://localhost:8080/api/manager/getAvgRest",$scope.manager.restaurant).success(
	    		function(data){
	    			$scope.avg=data;
	    		});
  		if ($scope.manager.active)
    		$scope.page="scores"; 
  	}
  	
  	$scope.changeToFoodScores= function(){
  		$http.post("http://localhost:8080/api/manager/getAvgFood",$scope.manager.restaurant).success(
	    		function(data){
	    			$scope.food_rate=data;
	    		});
  		if ($scope.manager.active)
    		$scope.page="scores_food"; 
  		
  	}
  	
  	$scope.changeToWaiterScores= function(){
  		$http.post("http://localhost:8080/api/manager/getAvgWaiter",$scope.manager.restaurant).success(
	    		function(data){
	    			$scope.waiter_rate=data;
	    		});
  		if ($scope.manager.active)
    		$scope.page="scores_waiter"; 
  	}
  	
  	$scope.changeToVisits=function(){
  		$http.post("http://localhost:8080/api/manager/getVisits",$scope.manager.restaurant).success(
	    		function(data){
	    			$scope.visits=data;
	    		});
  		$scope.labels2 = [];
		$scope.data2 = [[]];
		$scope.series2 = [''];
		for (var i=0; i<$scope.visits.length;i++){
			$scope.labels2.push($scope.visits[i].day);
			$scope.data2[0].push($scope.visits[i].numb);
		}
  		
  		if ($scope.manager.active)
    		$scope.page="visits"; 
  	}
	$scope.changeToIncomes=function(){
		$scope.incomes_show=false;
		$scope.income={from_date:""};
		//$scope.income.from_date="";
		$scope.income.to_date= "";
		if ($scope.manager.active)
    		$scope.page="incomes"; 		
	}
	
	
	  function formatDate(date){
	    	var day = date.getDate();
	    	day= day > 10 ? day : "0"+day;
	        var monthIndex = date.getMonth()+1;
	        monthIndex= monthIndex > 10 ? monthIndex : "0"+monthIndex;
	        var year = date.getFullYear();
	        
	        
	    	return day+"/"+monthIndex+"/"+year;
	    }
	
	$scope.getIncomes=function(){
		//popover("234");
		
		maxDate = new Date("9999-12-31");
		minDate = new Date("1000-01-01");
		if ($scope.income.to_date=="")
			$scope.income.to_date=maxDate;
		if ($scope.income.from_date=="")
			$scope.income.from_date=minDate;
		begin=formatDate($scope.income.from_date);
		end = formatDate($scope.income.to_date);
		seend={"begin":begin, "end":end,"r":$scope.manager.restaurant};
		//popover(typeof begin);

		
		
		$http.post("http://localhost:8080/api/manager/getIncomes",seend).success(
	    		function(data){
	    			$scope.incomes=data;
	    			$scope.labels3 = [];
	    			$scope.data3 = [[]];
	    			$scope.series3 = [''];
	    			for (var i=0; i<$scope.incomes.length;i++){
	    				$scope.labels3.push($scope.incomes[i].day);
	    				$scope.data3[0].push($scope.incomes[i].price);
	    			}
	    			$scope.incomes_show=true;
	    		});
		//popover("mit syorakozik?");	
	}
	
	$scope.changeToIncomesWaiters=function(){
		$http.post("http://localhost:8080/api/manager/getIncomesByWaiter",$scope.manager.restaurant).success(
	    		function(data){
	    			$scope.income_waiter=data;
	    		});
		$scope.labels =[];
		$scope.data =[];
		for (var i=0; i<$scope.income_waiter.length; i++){
			$scope.labels.push($scope.income_waiter[i].firstName+" "+$scope.income_waiter[i].lastName);
			$scope.data.push($scope.income_waiter[i].price);
		}
  		if ($scope.manager.active)
    		$scope.page="income_waiter"; 
	}
	
  	$scope.changeToChangePass= function(){
  		if ($scope.manager.active)
    		$scope.page="non-active"; 
  	}
  	$scope.changeToUpdateMenu= function(i){
  		$scope.menuUpdate=i;
  		$scope.menuUpdate2=i;
  		if ($scope.manager.active)
    		$scope.page="updateMenu"; 
  	}
  	$scope.changeToUpdateDrinkMenu= function(i){
  		$scope.drinkMenuUpdate=i;
  		$scope.drinkMenuUpdate2=i;
  		if ($scope.manager.active)
    		$scope.page="updateDrinkMenu"; 
  	}
  	
  	$scope.createEmployee= function(){
  		if ($scope.manager.restaurant!=null){
  			//popover($scope.selected);
  			$scope.employee.type=$scope.selected.value;
	  		$scope.employee.password="pass";
	  		$scope.employee.numbC= Number($scope.employee.numbC);
			$scope.employee.numbS= Number($scope.employee.numbS);
	  		//popover(JSON.stringify($scope.employee));
	  		$http.post("http://localhost:8080/api/manager/createEmployee",{"e":$scope.employee, "r":$scope.manager.restaurant}).success(
					function(data){
						$route.reload();
					}).error(
							function(data){
								popover("Email address is used.");
							});
  		}
  	}

	$scope.createSupplier= function(){
		$scope.supplier.password="pass";
		
  		//popover(JSON.stringify($scope.supplier));
  		$http.post("http://localhost:8080/api/manager/createSupplier",{"s":$scope.supplier, "r":$scope.manager.restaurant}).success(
				function(data){
					$route.reload();
				}).error(
					function(data){
						popover("Email address is used.");
					});
  	}
	
	$scope.createNewItem= function(){
		if ($scope.manager.restaurant.menu!=null){
			for (var i=0; i<$scope.manager.restaurant.menu.items.length;i++){
				if ($scope.manager.restaurant.menu.items[i].food.name==$scope.menuitem.food.name){
					popover("Food with this name is exist.");
					return;
				}
			}
			menu=$scope.manager.restaurant.menu;
			menu.items=[];
		}else{
			menu={items:[]};
		}
		menu.dateUpdate=new Date();
		menu.items.push($scope.menuitem);
		$http.post("http://localhost:8080/api/manager/addMenuItem", {"m":menu, "r":$scope.manager.restaurant}).success(function(data) {
			$route.reload();
		}).error(function(data) {
			//popover("error");
		});	
	}
	

	
	$scope.createNewDrinkItem= function(){
		//popover("drink");
		if ($scope.manager.restaurant.drinkMenu!=null){
			for (var i=0; i<$scope.manager.restaurant.drinkMenu.items.length;i++){
				if ($scope.manager.restaurant.drinkMenu.items[i].drink.name==$scope.drinkmenuitem.drink.name){
					popover("Drink with this name is exist.");
					return;
				}
			}
			drinkmenu=$scope.manager.restaurant.drinkMenu;
			drinkmenu.items=[];
		}else{
			drinkmenu={items:[]};
		}
		drinkmenu.dateUpdate=new Date();
		drinkmenu.items.push($scope.drinkmenuitem);
		$http.post("http://localhost:8080/api/manager/addDrinkMenuItem", {"d":drinkmenu, "r":$scope.manager.restaurant}).success(function(data) {
			$route.reload();
		}).error(function(data) {
			//popover("error");
		});	
	}
	
	$scope.updateMenuItem= function(){
		x=0;
		for (var i=0; i<$scope.manager.restaurant.menu.items.length;i++){
			if ($scope.manager.restaurant.menu.items[i].food.name==$scope.menuUpdate.food.name){
				x++;
			}
		}
		if (x>1){
			popover("Food with this name is exist.");
			return;
		}
		menu= $scope.manager.restaurant.menu;
		//delete menu.$$hashKey;
		//popover(JSON.stringify(menu));
		menu.items= [$scope.menuUpdate];
		
		menu.dateUpdate=new Date();
		//popover(JSON.stringify(menu));
		$http.post("http://localhost:8080/api/manager/updateMenu", menu).success(function(data) {
			$route.reload();
		}).error(function(data) {
			//popover("error");
		});	
	}
	
	$scope.updateDrinkMenuItem= function(){

		x=0;
		for (var i=0; i<$scope.manager.restaurant.drinkMenu.items.length;i++){
			if ($scope.manager.restaurant.drinkMenu.items[i].drink.name==$scope.drinkMenuUpdate.drink.name){
				x++;
			}
		}
		if (x>1){
			popover("Drink with this name is exist.");
			return;
		}
		//popover(JSON.stringify($scope.manager.restaurant.drinkMenu));
		drink_menu= $scope.manager.restaurant.drinkMenu;
		//delete menu.$$hashKey;
		//popover(JSON.stringify(drink_menu));
		drink_menu.items= [$scope.drinkMenuUpdate];
		drink_menu.dateUpdate=new Date();
		//popover(JSON.stringify(drink_menu));
		$http.post("http://localhost:8080/api/manager/updateDrinkMenu", drink_menu).success(function(data) {
			$route.reload();
		}).error(function(data) {
			//popover("error");
		});	
	}
	
	$scope.deleteMenuItem= function(i){
		var result = confirm("Want to delete?");
		if (result) {
			//popover(JSON.stringify(i));
			$http.post("http://localhost:8080/api/manager/deleteMenuItem", i).success(function(data) {
				$route.reload();
			}).error(function(data) {
				popover("This item is reservated. You can't delete it.");
			});
		}
			
	}
	
	$scope.deleteDrinkMenuItem= function(i){
		var result = confirm("Want to delete?");
		if (result) {
			//popover(JSON.stringify(i));
			$http.post("http://localhost:8080/api/manager/deleteDrinkMenuItem", i).success(function(data) {
				$route.reload();
			}).error(function(data) {
				popover("This item is reservated. You can't delete it.");
			});
		}
			
	}
	
	$scope.deleteRegion= function(reg){
		//popover(JSON.stringify(reg));
		var result = confirm("Want to delete?");
		if (result) {
			region=null;
			for (var i=0; i<$scope.regions.length;i++){
	  			if ($scope.regions[i].name==reg.name){
	  	  				region= $scope.regions[i];
	  			}
	  		}	
			
			
			$http.post("http://localhost:8080/api/manager/deleteRegion", region).error(function(data) {
				popover("Error! In the region, there are tables which are reservated.");
			}).then(function(data) {
				
				for (var i=0; i< $scope.model.length;i++){
					if ($scope.model[i][0].name==reg.name){
						delete $scope.model[i];
						$scope.changeToRegions(); 
					}
				}
			});
		}
			
	}
	
	$scope.dragoverCallback = function(index, external, type, callback) {
        $scope.logListEvent('dragged over', index, external, type);
        // Invoke callback to origin for container types.
        if (type == 'container' && !external) {
            console.log('Container being dragged contains ' + callback() + ' items');
        }
        return index < 10; // Disallow dropping in the third row.
    };
    
    $scope.moved=false;
    $scope.changed_label="";
    $scope.effect="";
    $scope.dropCallback = function(index, item, external, type) {
    	if (item.effectAllowed=="copy"){ 
    		item.label=findMin();
    		$scope.changed_label=item.label;
    		item.effectAllowed="all";
    		$scope.effect="all";
    	}else{
    		$scope.moved=true;
    	}
        $scope.logListEvent('dropped at', index, external, type);
        // Return false here to cancel drop. Return true if you insert the item yourself.
        return item;
    };

    function addTable(label){

    	region=null;
		region_send=null;
		numb=0;
		
		for (var i=0; i<$scope.model.length;i++){
  			for (var j=0; j<$scope.model[i][0].items.length;j++){
  				if ($scope.model[i][0].items[j].label==label){
  	  					region= $scope.model[i];
  	  					numb=$scope.model[i][0].items[j].numberOfChairs;
  				}
  			}
  		}
		//popover(region[0].name);
		
		//popover(JSON.stringify($scope.regions));
		for (var i=0; i<$scope.regions.length;i++){
			//popover($scope.regions[i].name);
  			if ($scope.regions[i].name == region[0].name){
  					//popover("Mi a francot szorakozik???");
  	  				region_send= $scope.regions[i];
  			}
  		}
		//popover("r2: "+JSON.stringify(region_send));
		table={"number":label,"numberOfChairs": numb, "restaurant":$scope.manager.restaurant, "region":region_send};
		$http.post("http://localhost:8080/api/manager/newTable", {"t":table, "r":region_send}).then(function(data){
			//popover("atment");											
		});
		
    }
    
    function move(label){
    	//popover("move table");
    	region=null;
    	old_r=null;
    	new_r=null;    	
		numb=0;
		
		for (var i=0; i<$scope.model.length;i++){
  			for (var j=0; j<$scope.model[i][0].items.length;j++){
  				if ($scope.model[i][0].items[j].label==label){
  	  					region= $scope.model[i];
  	  					numb=$scope.model[i][0].items[j].numberOfChairs;
  				}
  			}
  		}
		//popover(JSON.stringify(region));
		table={"number":label,"numberOfChairs": numb, "restaurant":$scope.manager.restaurant};
		new_r={"id":region[0].id,"name":region[0].name, "restaurant":$scope.manager.restaurant};
		//popover(JSON.stringify(new_r));
		$http.post("http://localhost:8080/api/manager/updateTable",{"t":table, "r":new_r}).error(function(data){
			//popover("error");
		});
		$http.post("http://localhost:8080/api/manager/regions", {"id":$scope.manager.restaurant.id}).then(function(data){
			$scope.regions=data.data;		
			//popover("ds");
		});
    }
    
    $scope.deleteTable=function(item){
    	label=item.label;
    	if (typeof item.label === 'string'){
    		if ($scope.effect==""){
        		return;
        	}
    		label=$scope.changed_label;
    	}
    	if ($scope.moved==true)
    	{
    		$scope.moved=false;
    		move(label);
    		return;
    	}
    	$scope.moved=false;
    	$scope.effect="";
    	var x=0;
		for (var i=0; i<$scope.model.length;i++){
  			for (var j=0; j<$scope.model[i][0].items.length;j++){
  				if ($scope.model[i][0].items[j].label==label){
  						addTable(label);
  	  					return;
  				}
  			}
  		}
		
		$http.post("http://localhost:8080/api/manager/deleteTable", {"number":item.label, "numberOfChairs":item.numberOfChairs, "restaurant":$scope.manager.restaurant}).error(function(data){
			region=null;
			
			for (var i=0; i<$scope.regions.length;i++){
	  			for (var j=0; j<$scope.regions[i].tables.length;j++){
	  				if ($scope.regions[i].tables[j].number==item.label){
	  	  					region= $scope.regions[i];
	  				}
	  			}
	  		}	
			//popover(JSON.stringify(region.name));
			for (var i=0; i<$scope.model.length;i++){
	  			if ($scope.model[i][0].name== region.name){
	  				$scope.model[i][0].items.push(item);
	  			}
	  		}
			popover("This table is reserved.");
			
		}).then(function(data){
			//popover("success");											
		});
    }
    
    function sortNumber(a,b) {
        return a - b;
    }
    
    function findMin(){
    	l=[];
    	for (var i=0; i<$scope.model.length;i++){
  			for (var j=0; j<$scope.model[i][0].items.length;j++){
  				if (typeof $scope.model[i][0].items[j].label === "number"){
  					//popover("ah");
  					l.push($scope.model[i][0].items[j].label);
  				}
  			}
    	}
   
    	l.sort(sortNumber);
    	x=1;
    	while (true){
    		if (!l.includes(x)){
    			return x;
    		}
    		x++;	
    	}
    }
    
    $scope.logEvent = function(message) {
        console.log(message);
    };

    $scope.logListEvent = function(action, index, external, type) {
        var message = external ? 'External ' : '';
        message += type + ' element was ' + action + ' position ' + index;
        console.log(message);
    };

    
    $scope.$watch('model', function(model) {
        $scope.modelAsJson = angular.toJson(model, true);
    }, true);
    
    $scope.newSupply=function(){
    	$scope.supply.restaurant=$scope.manager.restaurant;
    	$scope.supply.chosed=false;

    	
    	if ($scope.supply.from_date>$scope.supply.to_date ){
    		popover("Finish date is before start date.");
    		return;
    	}
    	//popover(JSON.stringify($scope.supply));
    	$http.post("http://localhost:8080/api/manager/addSupply", $scope.supply).success(function(data) {
			$route.reload();
		}).error(function(data) {
			//popover("error");
		});	
    	
    }

      function DialogController($scope, $mdDialog) {
        $scope.hide = function() {
          $mdDialog.hide();
        };

        $scope.cancel = function() {
          $mdDialog.cancel();
        };

        $scope.answer = function(answer) {
          $mdDialog.hide(answer);
        };
      }
      
      $scope.chooseOffer=function(o){
    	  	//popover(o);
    	  	$http.post("http://localhost:8080/api/manager/chooseOffer",{"o":o, "s": $scope.supply}).success(
    				function(data){
    					$route.reload();
    			}).error(
    				function(data){
    					popover("The offer is changed. Please, refresh the page!");
    			}).then(
    				function(data){
    					$route.reload();
  				});
      }
 

}]);

