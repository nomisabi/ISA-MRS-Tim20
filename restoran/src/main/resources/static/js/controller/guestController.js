'use strict';

angular.module('myApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.user={id:null,email:'',password:'',password2:'',lastName:'',lastName:''};

    self.submit = submit;
    self.reset = reset;
    self.reg = reg;
    self.log = log;
    self.facebookLogIn = facebookLogIn;
    self.updateUser = updateUser;
    self.logIn = logIn;
    
    fetchUser();
 
    function fetchUser(){
        UserService.fetchUser()
            .then(
            function(d) {
                self.user = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }

    
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
    
    function logIn(){
        UserService.logIn(self.user)
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
    
    function updateUser(){
        UserService.updateUser(self.user, self.user.id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating User');
            }
        );
        
    }


    
}]);
