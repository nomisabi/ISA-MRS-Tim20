'use strict';

angular.module('myApp').controller('UserController', ['$scope', 'UserService','$ocLazyLoad', function($scope, UserService, $ocLazyLoad) {
    var self = this;
    self.user={id:null,email:'',password:'',password2:'',firstName:'',lastName:''};

    self.submit = submit;
    self.reset = reset;
    self.reg = reg;
    self.log = log;
    self.facebookLogIn = facebookLogIn;
    self.updateUser = updateUser;
    self.logIn = logIn;
	$ocLazyLoad.load('assets/js/common-scripts.js');
	
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
    
    function createUser(user){
        UserService.createUser(user)
            .then(
            	function() {
            		window.location.href = '#';
            		popover("Account successfully created. To be able to use your account. Please, authenticate your registration via the link from the email.");
            		
                },
            function(errResponse){
                popover("User with email "+ user.email + " alredy exist.");
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
