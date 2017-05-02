
angular.module('myApp').controller('AllUserController',['$scope','$http','$window',function($scope,$http, $window) {
	function init(){
		$http.get("http://localhost:8080/api/users/sysman");
		//$.backstretch("assets/img/login-bg.jpg", {speed: 500});
	}
	
	init();

	
	$scope.login= function(){		
		$http.post("http://localhost:8080/api/users/login", {"email":$scope.mail,"password":$scope.pass }).then(
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
								$window.location.href = '#/emplyee/index';
							}else{
								alert("Error in login");
							}
					});	
		
	}
}]);