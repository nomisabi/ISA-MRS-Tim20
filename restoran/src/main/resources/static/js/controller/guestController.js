'use strict';

angular.module('myApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.user={id:null,email:'',password:'',password2:''};
    self.users=[];

    self.submit = submit;
    self.reset = reset;


    
    function createUser(user){
        UserService.createUser(user)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }
    
    function submit() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }
        reset();
    }
    
    function reset(){
        self.user={id:null,email:'',pass:'',pass2:''};
        $scope.myForm.$setPristine(); //reset Form
    }


    
}]);
