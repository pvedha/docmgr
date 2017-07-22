function initAllUsers() {
    $.ajax({
        url: baseURL + '/user/all',
        type: 'get',
        accept: 'application/json',
        global: false,
        success: function (response) {
            staffs = response;
        }
    })
};

function readAllUsers() {
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

function readAllChildren() {
    $.ajax({
        url: baseURL + '/child/all',
        type: 'get',
        accept: 'application/json',
        global: false,
        success: function (response) {
            displayChildren(response);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus + "Error Retrieving children" + errorThrown);
            $("#status-message").html("Error Retrieving children");
        }
    })
};


function displayChildren(response) {
    console.log(response);
    childControllerAngular.addChildren(response);
    childControllerAngular.$apply();
    showChildrenPage();
}

function addChild() {

    var id = $("#child-roll-no").val();
    var name = $("#child-name").val();
    var dob = $("#child-dob").val();
    var doj = $("#child-doj").val();
    var remarks = $("#child-remarks").val();
    var message = $("#child-message").val();
    var teacher = $("#child-teacher").val();
    var councillor = $("#child-councillor").val();
    var therapist = $("#child-therapist").val();
    var tags = $("#child-tags").val();

    if (name.trim().length === 0) {
        setStatus("Invalid details");
        return;
    }
    setStatus("Please wait, adding child...");

    var data = {
        id: id,
        name: name,
        dob: dob,
        doj: doj,
        remarks: remarks,
        message: message,
        teacher: teacher,
        councillor: councillor,
        therapist: therapist,
        tags: tags
    };
    $.ajax({
        url: baseURL + '/user/addChild',
        type: 'post',
        contentType: 'application/json',
        global: false,
        success: function (response) {
            $("#status-message").html("Child successfully added");
            sleep(2000);
            showWelcomePage();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus + "Error adding child" + errorThrown);
            $("#status-message").html("Error adding, please check the details");
        },
        data: JSON.stringify(data)
    })
}
