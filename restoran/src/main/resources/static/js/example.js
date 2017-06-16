'use strict';

angular.module('myApp').controller('KitchenSinkCtrl',function(moment, alert, calendarConfig, $http, $timeout) {

    var vm = this;
    vm.users1=null;
    vm.event=null;
    vm.events = [];
    
    var actions = [/*{
        label: '<i class=\'glyphicon glyphicon-pencil\'></i>',
        onClick: function(args) {
          alert.show('Edited', args.calendarEvent);
        }
      },*/ {
        label: '<i class=\'glyphicon glyphicon-remove\'></i>',
        onClick: function(args) {
         // alert.show('Deleted', args.calendarEvent);
          vm.events.splice(args.calendarEvent.calendarEventId,1);
          $http.post("http://localhost:8080/api/manager/deleteEmployeeSchedule",args.calendarEvent).success(
  				function(data){							
  					//vm.manager.restaurant=data;	
  			});	
        }
      }];
    

    
    function init() {
		$http.get("http://localhost:8080/api/users/login").success(
				function(data){	
					$http.post("http://localhost:8080/api/manager/login",data).success(
							function(data){
								vm.manager=data;
								$http.post("http://localhost:8080/api/manager/getRest",vm.manager).success(
										function(data){							
											vm.manager.restaurant=data;	
											vm.users1= vm.manager.restaurant.employee;
											
									});		
							});
				});
		
		$http.post("http://localhost:8080/api/manager/employeeSchedule",{}).success(
				function(data){							
					for (var i=0; i<data.length; i++){
						
						var asd="";
						for (var u=0; u<data[i].schedule.regions.length;u++){
				        	if (u==data[i].schedule.regions.length-1)
				        		asd+=data[i].schedule.regions[u].name;
				        	else
				        		asd+=data[i].schedule.regions[u].name+", ";
						}
						
				       	vm.events.push({
					            title: data[i].employee.firstName+' '+data[i].employee.lastName+" - "+asd,
					            id:data[i].id,
					            employee: data[i].employee,
					            regions: data[i].schedule.regions,
					            startsAt: new Date(data[i].startTime),
					            endsAt: new Date(data[i].endTime),
					            color:{
					            	primary: data[i].c1,
					                secondary:data[i].c2	
					            },
					            draggable: true,
					            resizable: false,
					            actions: actions
						});
					}
					
			});
	}
	init();
	
	vm.loadUsers = function() {
		  vm.users1 =  vm.users1  || [
	  		                          	        { id: 1, name: 'Scooby Doo' }
	  		                          	      ]; 
	    // Use timeout to simulate a 650ms request.

	    return $timeout(function() {
	    	vm.users1 = vm.users1 || null;
	    }, 650);
	  }; 

    //These variables MUST be set as a minimum for the calendar to work
    vm.calendarView = 'month';
    vm.show=false;
    vm.viewDate = new Date();
    //document.write(vm.viewDate);
   
   

    vm.cellIsOpen = true;

    vm.changeToAddEvent = function(){
    	vm.show=true;
    	$http.post("http://localhost:8080/api/manager/regions", {"id":vm.manager.restaurant.id}).then(function(data){
			vm.regions=data.data;	
			//alert.show("Regions",vm.regions);
			}
    	);
    }
    
    vm.addEvent = function(event) {
    	var e= ""+event.day;
    	
    	var date1=e.slice(0,15);
    	var date2= (""+event.start).slice(16,24);
    	var date3= (""+event.end).slice(16,24);
    	if (event.end<=event.start){
    		alert.Show("Error",{});
			alert("Error");
			return;
		}
    	//alert.show("123",{});
    	var start_time=date1+" "+date3;
    	var end_time =date1+" "+date2;
    	//alert.show("3",{});
        var employeeschedule={"employee":event.employee, "c1":event.color.primary,"c2":event.color.secondary,"day":event.day, "endTime":end_time,"startTime":start_time,"schedule":{"regions":vm.selected}}
    	var id=null;
        
    	//alert.show("Send", employeeschedule);
        $http.post("http://localhost:8080/api/manager/addEmployeeSchedule",employeeschedule).success(
				function(data){							
					id=data.id;
			});
      //geting id, and setting
        var asd="";
        for (var u=0; u<vm.selected.length;u++){
        	if (u==vm.selected.length-1)
        		asd+=vm.selected[u].name;
        	else
        		asd+=vm.selected[u].name+", ";
        }
    	vm.show=false;
    	vm.events.push({
            title: event.employee.firstName+' '+event.employee.lastName+" - "+asd,
            id:id,
            employee: event.employee,
            regions: vm.selected,
            startsAt: new Date(date1+" "+date2),
            endsAt: new Date (date1+" "+date3),
            color:{
            	primary: event.color.primary,
                secondary:event.color.secondary   	
            },
            draggable: true,
            resizable: false,
            actions: actions
          });
    	vm.event=null;
    };

    vm.eventClicked = function(event) {
      alert.show('Clicked', event);
    };

    vm.eventEdited = function(event) {
     // alert.show('Edited', event);
      
      $http.post("http://localhost:8080/api/manager/updateEmployeeSchedule",event).success(
				function(data){							
					//vm.manager.restaurant=data;	
			});	
    };

    vm.eventDeleted = function(event) {  	
     // alert.show('Deleted', event);
      $http.post("http://localhost:8080/api/manager/deleteEmployeeSchedule",event).success(
				function(data){							
					//vm.manager.restaurant=data;	
			});	
    };

    vm.eventTimesChanged = function(event) {
    	//var new_event=event;
//    	alert.show("edit",new_event.startsAt);

    	//alert.show("3",{});
        var employeeschedule={"id":event.id,"employee":event.employee, "c1":event.color.primary,"c2":event.color.secondary,"day":event.startsAt, "endTime":event.endsAt,"startTime":event.startsAt,"schedule":{"regions":event.regions}}
    	//alert.show("edit", employeeschedule);
        
    	
      $http.post("http://localhost:8080/api/manager/updateEmployeeSchedule",employeeschedule).success(
				function(data){							
					//vm.manager.restaurant=data;	
			});	
    };

    vm.toggle = function($event, field, event) {
      $event.preventDefault();
      $event.stopPropagation();
      event[field] = !event[field];
    };

    vm.timespanClicked = function(date, cell) {

      if (vm.calendarView === 'month') {
        if ((vm.cellIsOpen && moment(date).startOf('day').isSame(moment(vm.viewDate).startOf('day'))) || cell.events.length === 0 || !cell.inMonth) {
          vm.cellIsOpen = false;
        } else {
          vm.cellIsOpen = true;
          vm.viewDate = date;
        }
      } else if (vm.calendarView === 'year') {
        if ((vm.cellIsOpen && moment(date).startOf('month').isSame(moment(vm.viewDate).startOf('month'))) || cell.events.length === 0) {
          vm.cellIsOpen = false;
        } else {
          vm.cellIsOpen = true;
          vm.viewDate = date;
        }
      }

    };
    
    vm.selected = [];

    vm.toggle2 = function (item, list) {
      var idx = list.indexOf(item);
      if (idx > -1) {
        list.splice(idx, 1);
      }
      else {
        list.push(item);
      }
    };

    vm.exists = function (item, list) {
      return list.indexOf(item) > -1;
    };

  });