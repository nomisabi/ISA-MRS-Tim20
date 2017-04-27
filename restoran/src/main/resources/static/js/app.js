var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "main.html",
    })
    .when("/registration", {
        templateUrl : "registration.html",
        controller : "UserController"
    })
    .when("/home", {
        templateUrl : "home.html",
        controller : "UserController"
    })
    .when("/sysman/info", {
   	 	templateUrl : "sysmaninfo.html",
   	 	controller: "SystemManagerController"
    })
    .when("/sysman/manres", {
   	 	templateUrl : "listManagerRestaurants.html",
   	 	controller: "SystemManagerController"
    })
    .when("/sysman/login", {
   	 	templateUrl : "sysmanlogin.html",
   	 	controller: "SystemManagerController"
    })
    .when("/sysman/signup", {
   	 	templateUrl : "signUpSysMan.html",
   	 	controller: "SystemManagerController"
    })
    .when("/man/login", {
   	 	templateUrl : "loginMan.html"
    })
    .when("/man/info", {
    	templateUrl : "showManager.html"
    })
    .when("/supplier/login", {
    	templateUrl : "loginSupplier.html"
    })
    .when("/supplier/info", {
    	templateUrl : "showSupplier.html"
    });
});