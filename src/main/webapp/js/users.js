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

function addStaff() {
    var userId = $("#staff-userId").val();
    var userName = $("#staff-name").val();
    var about = $("#staff-about").val();
    var email = $("#staff-email").val();
    var phone = $("#staff-phone").val();
    var jobTitle = $("#staff-job-title").val();
    if (userName.trim().length === 0 || userId.trim().length === 0) {
        $("#status-message").html("Invalid details");
        //console.log("Title / message cannot be empty");
        return;
    }

    $("#status-message").html("Please wait, adding staff...");
    var data = {
        userId: userId,
        userName: userName,
        about: about,
        email: email,
        phone: phone,
        jobTitle: jobTitle
    };
    $.ajax({
        url: baseURL + '/user/addStaff',
        type: 'post',
        contentType: 'application/json',
        global: false,
        success: function (response) {
            $("#status-message").html("Staff successfully added");
            sleep(2000);
            showWelcomePage();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus + "Error adding user" + errorThrown);
            $("#status-message").html("Error adding, please check the details");
        },
        data: JSON.stringify(data)
    })
}
