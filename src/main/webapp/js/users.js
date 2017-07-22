function readAllUsers() {
    //    console.log("receiving user ids");
    $.ajax({
        url: baseURL + '/user/all',
        type: 'get',
        accept: 'application/json',
        global: false,
        success: function (response) {
            displayUsers(response);
        }
    })
};

function displayUsers(response) {
    console.log(response);
    docControllerAngular.addUsers(response);
    docControllerAngular.$apply();
    showUsersPage();
}
