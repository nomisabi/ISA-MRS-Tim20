
angular.module('myApp').controller('ManagerController',['$scope','$http','$window','$route','$ocLazyLoad', function($scope, $http,$window, $route, $ocLazyLoad) {
	
	$ocLazyLoad.load('js/drag_drop.js');	
	$ocLazyLoad.load('assets/js/common-scripts.js');
	
	$scope.list_of_region=[{name:'1', list:['1','2']},{name:'2', list:['1']},{name:'3', list:['1','2','3']}];

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
	

	function init() {
		$ocLazyLoad.load('assets/js/common-scripts.js');
		$http.get("http://localhost:8080/api/users/login").success(
				function(data){	
					$http.post("http://localhost:8080/api/manager/login",data).success(
							function(data){
								$scope.manager=data;
								$http.post("http://localhost:8080/api/manager/getRest",$scope.manager).success(
										function(data){
											if (data==null)
												$window.location.href="/";
											//alert(JSON.stringify(data));
											$scope.manager.restaurant=data;
											$http.post("http://localhost:8080/api/manager/regions", {"id":$scope.manager.restaurant.id}).then(function(data){
												$scope.regions=data.data;												
											});
											
									});
								
								if ($scope.manager.active)
									$scope.page="profile";
							}).error(function(data){
								$window.location.href="/";
							});
				});
		
		

	}
	
    init();
 
    $scope.logout=function(){
    	$http.get("http://localhost:8080/api/users/logout").success(function(data) {
    		$window.href.location="/#/";
    	});
    	
    }
    
    $scope.update=function(){
    	$http.post("http://localhost:8080/api/manager/update", $scope.manager).success(function(data) {
    		$route.reload();
    	}).error(function(data) {
    		alert("This email address is in our system");	
    		$route.reload();
    	});
    }
    
    $scope.changePass=function(){
    	if ($scope.pw1==$scope.pw2){
    		$scope.login= $scope.manager;
    		$scope.login.password=$scope.pw1;
    		$http.post("http://localhost:8080/api/manager/changePass",$scope.login).success(
					function(data){
						$route.reload();
					});
    	} else {
    		alert("Passwords don't equals");
    		}
    }


    $scope.changeToProfile= function(){
    	if ($scope.manager.active)
    		$scope.page="profile";
    	
    }
    $scope.changeToRestaurants= function(){
    	if ($scope.manager.active)
    		$scope.page="restaurant";   	
    }
    	
    $scope.changeToMenu= function(){ 
    	if ($scope.manager.active)
    		$scope.page="menu";   	  
    }
    $scope.changeToNewItem= function(){ 
    	if ($scope.manager.active){
    		$scope.page="new_item_menu";   
    	}
    }
  	$scope.changeToDrinkMenu= function(){
  		if ($scope.manager.active)
    		$scope.page="drinkmenu";   	
  	}
    $scope.changeToNewDrinkItem= function(){ 
    	if ($scope.manager.active){
    		$scope.page="new_drink_item";   
    	}
    }
  	$scope.changeToSupplier= function(){
  		//alert("supplier");
  		if ($scope.manager.active)
    		$scope.page="supplier";   	
  	}
  	$scope.changeToCreateSupplier= function(){  
  		if ($scope.manager.active)
    		$scope.page="create_supplier";   	
  	}
  	$scope.changeToEmployee= function(){
  		if ($scope.manager.active)
    		$scope.page="employee"; 
  	}
  	$scope.changeToCreateEmployee= function(){
  		if ($scope.manager.active)
    		$scope.page="create_employee"; 
  	}
  	$scope.changeToSchedule= function(){
  		if ($scope.manager.active)
    		$scope.page="schedule"; 
  	}
  	$scope.changeToRegions= function(){
  		$scope.model=[
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
  	                     "effectAllowed": "all"
  	                   }
  	                   ]];
  		
  		//alert(JSON.stringify($scope.regions));
  		
  		for (var i=0; i<$scope.regions.length;i++){
  			var container = {name:$scope.regions[i].name, items: [], effectAllowed: 'all'};
  			items=[];
  			for (var j=0; j<$scope.regions[i].tables.length;j++){
  				//alert(JSON.stringify($scope.regions[i].tables[j].id));
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
  		if ($scope.manager.active)
    		$scope.page="regions"; 
  	}
  	$scope.changeToScores= function(){
  		if ($scope.manager.active)
    		$scope.page="scores"; 
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
  			alert($scope.selected);
  			$scope.employee.type=$scope.selected.value;
	  		$scope.employee.password="pass";
	  		$scope.employee.numbC= Number($scope.employee.numbC);
			$scope.employee.numbS= Number($scope.employee.numbS);
	  		alert(JSON.stringify($scope.employee));
	  		$http.post("http://localhost:8080/api/manager/createEmployee",{"e":$scope.employee, "r":$scope.manager.restaurant}).success(
					function(data){
						$route.reload();
					}).error(
							function(data){
								alert("Email address is used.");
							});
  		}
  	}

	$scope.createSupplier= function(){
		$scope.supplier.password="pass";
		
  		alert(JSON.stringify($scope.supplier));
  		$http.post("http://localhost:8080/api/manager/createSupplier",{"s":$scope.supplier, "r":$scope.manager.restaurant}).success(
				function(data){
					$route.reload();
				}).error(
					function(data){
						alert("Email address is used.");
					});
  	}
	
	$scope.createNewItem= function(){
		if ($scope.manager.restaurant.menu!=null){
			for (var i=0; i<$scope.manager.restaurant.menu.items.length;i++){
				if ($scope.manager.restaurant.menu.items[i].food.name==$scope.menuitem.food.name){
					alert("Food with this name is exist.");
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
			alert("error");
		});	
	}
	
	$scope.createNewDrinkItem= function(){
		//alert("drink");
		if ($scope.manager.restaurant.drinkMenu!=null){
			for (var i=0; i<$scope.manager.restaurant.drinkMenu.items.length;i++){
				if ($scope.manager.restaurant.drinkMenu.items[i].drink.name==$scope.drinkmenuitem.drink.name){
					alert("Drink with this name is exist.");
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
			alert("error");
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
			alert("Food with this name is exist.");
			return;
		}
		menu= $scope.manager.restaurant.menu;
		//delete menu.$$hashKey;
		alert(JSON.stringify(menu));
		menu.items= [$scope.menuUpdate];
		
		menu.dateUpdate=new Date();
		alert(JSON.stringify(menu));
		$http.post("http://localhost:8080/api/manager/updateMenu", menu).success(function(data) {
			$route.reload();
		}).error(function(data) {
			alert("error");
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
			alert("Drink with this name is exist.");
			return;
		}
		alert(JSON.stringify($scope.manager.restaurant.drinkMenu));
		drink_menu= $scope.manager.restaurant.drinkMenu;
		//delete menu.$$hashKey;
		alert(JSON.stringify(drink_menu));
		drink_menu.items= [$scope.drinkMenuUpdate];
		drink_menu.dateUpdate=new Date();
		alert(JSON.stringify(drink_menu));
		$http.post("http://localhost:8080/api/manager/updateDrinkMenu", drink_menu).success(function(data) {
			$route.reload();
		}).error(function(data) {
			alert("error");
		});	
	}
	
	$scope.deleteMenuItem= function(i){
		var result = confirm("Want to delete?");
		if (result) {
			alert(JSON.stringify(i));
			$http.post("http://localhost:8080/api/manager/deleteMenuItem", i).success(function(data) {
				$route.reload();
			}).error(function(data) {
				alert("error");
			});
		}
			
	}
	
	$scope.deleteDrinkMenuItem= function(i){
		var result = confirm("Want to delete?");
		if (result) {
			alert(JSON.stringify(i));
			$http.post("http://localhost:8080/api/manager/deleteDrinkMenuItem", i).success(function(data) {
				$route.reload();
			}).error(function(data) {
				alert("error");
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

    $scope.changed_label="";
    $scope.effect="";
    $scope.dropCallback = function(index, item, external, type) {
    	if (item.effectAllowed=="copy"){ 
    		item.label=findMin();
    		$scope.changed_label=item.label;
    		item.effectAllowed="all";
    		$scope.effect="all";
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
		
		for (var i=0; i<$scope.regions.length;i++){
  			for (var j=0; j<$scope.regions[i].tables.length;j++){
  				if ($scope.regions[i].name==region[0].name){
  	  					region_send= $scope.regions[i];
  				}
  			}
  		}
		//alert("r1: "+JSON.stringify(region_send));
		table={"number":label,"numberOfChairs": numb, "restaurant":$scope.manager.restaurant, "region":region_send};
		$http.post("http://localhost:8080/api/manager/newTable", {"t":table, "r":region_send}).then(function(data){
			//alert("atment");											
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
		$http.post("http://localhost:8080/api/manager/deleteTable", {"number":item.label, "numberOfChairs":item.numberOfChairs}).then(function(data){
			alert("atment");											
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
  					//alert("ah");
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

    // Initialize model
  /*  $scope.model = [[], []];
    var id = 10;
    angular.forEach(['all', 'move', 'copy', 'link', 'copyLink', 'copyMove'], function(effect, i) {
      var container = {items: [], effectAllowed: effect};
      for (var k = 0; k < 7; ++k) {
        container.items.push({label: effect + ' ' + id++, effectAllowed: effect});
      }
      $scope.model[i % $scope.model.length].push(container);
    });

*/
/*
    $scope.model=[
                  [
                   {
                	 "name":"12",
                     "items": [
                       {
                         "label": "all 10",
                         "effectAllowed": "all"
                       },
                       {
                         "label": "all 11",
                         "effectAllowed": "all"
                       },
                       {
                         "label": "all 12",
                         "effectAllowed": "all"
                       },
                       {
                         "label": "all 13",
                         "effectAllowed": "all"
                       },
                       {
                         "label": "all 14",
                         "effectAllowed": "all"
                       },
                       {
                         "label": "all 15",
                         "effectAllowed": "all"
                       },
                       {
                         "label": "all 16",
                         "effectAllowed": "all"
                       }
                     ],
                     "effectAllowed": "all"
                   }],
                   [
                    {
                 	 "name":"123",
                      "items": [
                        {
                          "label": "all 10",
                          "effectAllowed": "all"
                        },
                        {
                          "label": "all 11",
                          "effectAllowed": "all"
                        },
                        {
                          "label": "all 12",
                          "effectAllowed": "all"
                        },
                        {
                          "label": "all 13",
                          "effectAllowed": "all"
                        },
                        {
                          "label": "all 14",
                          "effectAllowed": "all"
                        },
                        {
                          "label": "all 15",
                          "effectAllowed": "all"
                        },
                        {
                          "label": "all 16",
                          "effectAllowed": "all"
                        }
                      ],
                      "effectAllowed": "all"
                    }],
                   [{
                	 "name":"New item",
                     "items": [
                       {
                         "label": "chair 2",
                         "effectAllowed": "copy"
                       },
                       {
                         "label": "chair 4",
                         "effectAllowed": "copy"
                       },
                       {
                         "label": "chair 6",
                         "effectAllowed": "copy"
                       },
                       {
                         "label": "chair 8",
                         "effectAllowed": "copy"
                       }
                     ],
                     "effectAllowed": "all"
                   }
                   ]];
    
    */
    
    $scope.$watch('model', function(model) {
        $scope.modelAsJson = angular.toJson(model, true);
    }, true);
}]);
