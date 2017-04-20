'use strict';

angular.module('myApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.user={id:null,email:'',password:'',password2:''};
    self.users=[];

    self.submit = submit;
    self.reset = reset;
    self.reg = reg;
    self.log = log;
    self.facebookLogIn = facebookLogIn;
 

    
    function createUser(user){
        UserService.createUser(user)
            .then(
            	function() {
            		window.location.href = '#';
                },
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }
    
    function logIn(user){
        UserService.logIn(user)
            .then(
            	function() {
                		window.location.href = '#home';
                },
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }
    
    function submit() {
        if(self.user.password2!=''){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }
        else{
        	console.log('User Log In', self.user);
        	logIn(self.user);
        }
        reset();
    }
    
    function reset(){
        self.user={id:null,email:'',password:'',password2:''};
        $scope.myForm.$setPristine(); //reset Form
    }
    
    function reg() {
    	window.location.href = '#registration';
	}
    
    function log() {
    	window.location.href = '#';
	}
    
    function facebookLogIn() {
    	alert("da");
    	 UserService.facebookLogIn()
         .then(
         	function() {
             		window.location.href = '#home';
             },
         function(errResponse){
             console.error('Error while creating User');
         }
     );
    
	}


    
}]);
