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
    
    });
});