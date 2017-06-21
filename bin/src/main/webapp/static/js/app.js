'use strict';

var App = angular.module('myApp',[]);


webProg.config(function($routeProvider) {
	$routeProvider.when('/',
	{
		//controller: 'productsController',// inace je podeseno ng-controller atributom
		templateUrl: 'WEB-INF/home.html'
	}).when('/home',
	{
		//controller: 'shoppingCartController', // inace je podeseno ng-controller atributom
		templateUrl: 'static/home.html'
	})
	.when('/user',
	{
		//controller: 'shoppingCartController', // inace je podeseno ng-controller atributom
		templateUrl: 'views/UserManagement.jsp'
	})
});

webProg.config(function($logProvider){
    $logProvider.debugEnabled(true);
});