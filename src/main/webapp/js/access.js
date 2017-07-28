var userIdsResponse = "";
var userIdsResponseReceived = false;
var validUserId = true;
var currentUserId = "";
var currentUserDetails;
var myJobtitle;
var token = "";

var url = 'http://' + window.location.host;
var baseURL = url + "/docmgr/doc"; //http://hostname:8080/blog/blog
var appURL = url + "/docmgr" //http://hostname:8080/blog

var fileBasePath = "c:/temp";
var fileServiceUrl = url + "/Js/rest";
var fileUploadUrl = fileServiceUrl + "/file/upload?filePath=";
var fileDownloadUrl = "/Js/rest/file/get";

var dashBoardLinkHtml;


var docControllerAngular; // = angular.element($('#BlogPostController-Div')).scope();
var userControllerAngular;
var childControllerAngular;
var actionControllerAngular;
var updateActionControllerAngular;
var updateChildControllerAngular;
var updateDocControllerAngular;


var jobTitles = [];
var actionStates = [];
var docStates = [];
var staffs = [];
var myActions = [];
var myDocuments = [];
var myChildren = [];

var httpPost = 'post';
var httpGet = 'get';
var httpPut = 'put';
//Dev settings
var infiniteScroll = true;
var currentOffset = 1;
var debugMode = false;


$(document).ready(function () {
    $("#signup-form").keypress(function (event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            addUser();
        }
    });
    $("#signin-form").keypress(function (event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            login();
        }
    });
    $("#search-text").keypress(function (event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            searchChildren($("#search-text").val());
        }
    });

    $("#search-text1").keypress(function (event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            //searchAllPosts($("#search-text1").val());
            setStatus('please login');
        }
    });

    $("#user-profile-form").keypress(function (event) {
        $("#user-profile-update").removeClass("disabled-button");
        $("#user-profile-update").addClass("btn1"); //will this keep adding the same class?                  
    });

    $("#ManageControls-user-type").onchange(function (event) {
        updateManageControlsPageValues();
    });


    $('#login-button').click(
        function () {
            login();
        });

    $('#signup-button').click(
        function () {
            addUser();
        });

    $('#newPost-submit-button').click(
        function () {
            newPost();
        });
    $('#addStaff-submit-button').click(
        function () {
            addStaff();
        });
    $('#addChild-submit-button').click(
        function () {
            addChild();
        });
    $('#search-button').click(
        function () {
            searchAllPosts();
        });
    $('#post-comment-button').click(
        function () {
            addComment();
        });

    userControllerAngular = angular.element($('#UserController-Div')).scope();
    docControllerAngular = angular.element($('#DocController-Div')).scope();
    childControllerAngular = angular.element($('#ChildController-Div')).scope();
    actionControllerAngular = angular.element($('#ActionController-Div')).scope();
    updateActionControllerAngular = angular.element($('#UpdateAction-Div')).scope();
    updateChildControllerAngular = angular.element($('#UpdateChild-Div')).scope();
    updateDocumentControllerAngular = angular.element($('#UpdateDocument-Div')).scope();


    $('[data-toggle="tooltip"]').tooltip();


    //    console.log("Data from localStorage", localStorage.getItem("userId"));
    hideAllForms();
    loadContents();
    if (localStorage.getItem("userId") !== null && localStorage.getItem("token")) {
        validateSession();
    } else {
        showLoginPage();
    }
    $('.affixed').affix({
        offset: {
            top: 50
        }
    });

    $("#myCarousel").carousel("pause");

});

function addUser() {
    if (!validUserId) {
        alert("User Id taken. Please try another");
        return;
    }
    $("#validUser").html("Creating User...");
    var userid = $("#userid").val();
    var name = $("#username").val();
    var password = $("#password").val();
    var about = $("#about").val();
    var data = {
        userid: userid,
        name: name,
        password: password,
        about: about
    };
    $
        .ajax({
            url: baseURL + '/user/addUser',
            type: 'post',
            contentType: 'application/json',
            success: function (response) {
                $("#validUser")
                    .html("Thanks for signing. Please login.");
                toggleSignform();
                document.getElementById('myModal').style.display = "none";
                authenticate(userid, password);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $("#validUser")
                    .html("Sorry invalid details, please try again. " + textStatus + " : " + errorThrown);
            },
            data: JSON.stringify(data),
        });
};

function getUserIds() {
    //    console.log("receiving user ids");
    $.ajax({
        url: baseURL + '/user/ids',
        type: 'get',
        accept: 'application/json',
        global: false,
        success: function (response) {
            //$("#viewForm").hide();
            $("#result-div")
                .html(
                    response);
            userIdsResponse = response;
            userIdsResponseReceived = true;
            //            console.log("Rsp 1" + response);
            $("#result-div").show();
            return response;
        }
    })
};


//Main Functionality after logging in. 
function login() {
    $("#loginMessage").html("Please wait, validating credentials...");
    $("#loginMessagee").css({
        'color': 'green',
        'font-size': '100%'
    });
    //    var userId = $("#loginId").val();
    //    var password = $("#loginPassword").val();
    authenticate($("#loginId").val(), $("#loginPassword").val());
};


function authenticate(userId, password) {
    $.ajax({
        url: baseURL + '/user/' + userId + '/' + password,
        type: 'get',
        accept: 'application/json',
        global: false,
        success: function (response) {
            loadMainPage(response);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            log("Invalid user credentials");
            $("#status-message").html("Invalid crendentials, please try again");
            //$("#login-message").css({ 'color': 'green', 'font-size': '100%' });
        }
    })
}

function validateSession() {
    var userId = localStorage.getItem("userId");
    var token = localStorage.getItem("token");
    $.ajax({
        url: baseURL + '/user/validate',
        type: 'get',
        accept: 'application/json',
        global: false,
        headers: {
            "token": token,
            "userId": userId
        },
        success: function (response) {
            loadMainPage(response);

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            log("Invalid user credentials");
            showLoginPage();
            //$("#Logged").hide();
            // $("#LoggedInForm").hide();
            //$("#NotLogged").show();
        }
    })
};



function updateProfile() {
    $("#user-profile-info").html("updating your profile...");
    var userId = currentUserId;
    var userName = currentUserDetails.name;
    var emailId = "";
    var password = "";
    var newPassword = $("#view-profile-newPassword").val().trim();
    var about = $("#view-profile-about").val();
    var data = {
        userId: userId,
        userName: userName,
        emailId: emailId,
        password: password,
        newPassword: newPassword,
        about: about
    };
    $.ajax({
        url: baseURL + '/user/update',
        type: 'post',
        contentType: 'application/json',
        success: function (response) {
            $("#user-profile-info").html("Profile updated successfully");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#user-profile-info")
                .html("Error validating details, please try again. " + textStatus + " : " + errorThrown);
        },
        data: JSON.stringify(data),
    });

}



function viewProfile() {
    $("#post-div").hide();
    $("#view-post-div").hide();
    $("#new-post-div").hide();
    $("#user-profile-fixed").html("User ID : <b>" + currentUserId + "</b><br>Name : <b>" + currentUserDetails.name);
    $("#view-profile-about").val(currentUserDetails.about);
    $("#user-profile-div").fadeIn(1000);

}


function validateUser(value) {
    //console.log(value);
    if (userIdsResponseReceived) {
        for (i = 0; i < userIdsResponse.length; i++)
            if (userIdsResponse[i].toLocaleLowerCase() === value.trim().toLocaleLowerCase()) {
                //                console.log("UserId Exists " + value);
                $("#validUser").html("User ID " + value + " not available");
                $("#validUser").css({
                    'color': 'red',
                    'font-size': '100%'
                });
                validUserId = false;
                return;
            } else {
                $("#validUser").html("User Id " + value + " available");
                $("#validUser").css({
                    'color': 'green',
                    'font-size': '100%'
                });
                validUserId = true;
            }
    }
}

function toggleSignform() {
    $("#signup-form").toggle();
    $("#signin-form").toggle();
}

function signOut() {
    localStorage.setItem("userId", "");
    localStorage.setItem("token", "");
    window.location.href = appURL;
}




function loadContents() {
    //retrieveJobTitles(); not here. 
    initAllUsers();
    retrieveDocStates();
    retrieveActionStates();
}

function showLoginPage() {
    $("#signup-form").hide();
    $("#result-div").hide();
    $("#mainPage").hide();
    //$("#loginPage").hide(); //to hide first
    //$("#post-div").hide(); //This is shown at all times
    $("#new-post-div").hide();
    $("#view-post-div").hide();
    $("#user-profile-div").hide();
    $("#LoginForm").show();
    $("#LoggedInForm").hide();

}

function hideAllForms() {
    $("#LoginForm").hide();
    $("#LoggedInForm").hide();
    $("#NotLogged").hide();
    // $("#post-comment-button").prop("disabled", true);
    //$("#comment-textarea").prop("disabled", true);

    //$("#loading-more").hide();
    //$("#thats-all").hide();

}

function skipLogin() {
    $("#user-div").html("<br>User : debugger<p><i> A quick way to debug");
    $("#user-div").append("<a href='" + url + "'>Sign out</a>");
    currentUserId = "u";
    //    console.log("user id assigned" + currentUserId);
    $("#loginPage").hide();
    $("#mainPage").show().fadeIn(50000);
    $("#mainPage").fadeIn(5000);
    readAllPosts();
    retrieveCategory();
}
