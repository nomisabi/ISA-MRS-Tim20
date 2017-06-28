
angular.module('myApp').controller('AllUserController',['$scope','$http','$window', '$ocLazyLoad',function($scope,$http, $window, $ocLazyLoad) {
	$ocLazyLoad.load('assets/js/common-scripts.js');
	function init(){
		//$http.get("http://localhost:8080/api/users/sysman");
		//$.backstretch("assets/img/login-bg.jpg", {speed: 500});
		$http.get("http://localhost:8080/api/users/login").then(
				function(data){	
					//alert(data);
					$scope.type= data.data.type;
					if ($scope.type=="SYS_MAN"){
						$window.location.href = '#/sysman/index';
					} else if ($scope.type=="MANAGER"){
						$window.location.href = '#/man/index';
					}else if ($scope.type=="GUEST"){
						$window.location.href = '#/guest/index';
					}else if ($scope.type=="SUPPLIER"){
						$window.location.href = '#/supp/index';
					}else if ($scope.type=="EMPLOYEE"){
						$window.location.href = '#/employee/index';
					}
					
				});
	}
	
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
	
	$scope.login= function(){		
		$http.post("https://rest-cupcake.herokuapp.com/api/users/login", {"email":$scope.mail,"password":$scope.pass }).error(
				function(data){
					popover("Email address and password doesn't match! Please, try again!");
			}).then(
						function(data){
							
							$scope.type= data.data.type;
							if ($scope.type=="SYS_MAN"){
								$window.location.href = '#/sysman/index';
							} else if ($scope.type=="MANAGER"){
								$window.location.href = '#/man/index';
							}else if ($scope.type=="GUEST"){
								$window.location.href = '#/guest/index';
							}else if ($scope.type=="SUPPLIER"){
								$window.location.href = '#/supp/index';
							}else if ($scope.type=="EMPLOYEE"){
								$window.location.href = '#/employee/index';
							}else{
								alert("Error in login");
							}
					});	
		
	}
}]);