function loadMainPage(response) {
    token = response.token; //new token?
    myJobTitle = response.jobTitle;
    $("#current-user-icon").html("<img src='img/48px-User_icon_2.svg.png' class='img-normal'/>");
    $("#user-detail-div").html("<b>" + response.name + "</b><p><i>" + response.about);
    $("#my-action-count").html(response.myOpenActionCount);
    currentUserId = response.userId;
    currentUserDetails = response;
    $("#user-button").html("<span class='glyphicon glyphicon-user' > </span>" + response.name);
    localStorage.setItem("userId", response.userId);
    localStorage.setItem("token", response.token);
    //    console.log("user id assigned" + currentUserId + "complete response " + response);
    $("#LoginForm").hide();
    $("#NotLogged").hide();
    $("#LoggedInForm").show();
    showWelcomePage();
    //loadDashboardLinks(response.jobTitle);
    retrieveJobTitles();

}

function retrieveJobTitles() {
    $.ajax({
        url: baseURL + '/gen/jobTitles',
        type: 'get',
        accept: 'application/json',
        global: false,
        success: function (response) {
            jobTitles = response;
            setJobTitles();
            loadDashboardLinks();
            //            console.log("added items");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            log("Error Loading jobTitles");
        }
    })
}

function setJobTitles() {
    var jobTitle = document.getElementById("staff-job-title");
    for (i = 0; i < jobTitles.length; i++) {
        var option = document.createElement("option");
        option.text = jobTitles[i].title;
        jobTitle.add(option);
    }
}


function loadDashboardLinks() {
    //if (jobTitles.length == 0)
    //   return;
    var myControls;
    for (i = 0; i < jobTitles.length; i++) {
        if (jobTitles[i].title == myJobTitle) {
            myControls = jobTitles[i];
        }
    }

    /* protected boolean viewAllActions;
	protected boolean addStaff;
	protected boolean addChildren;
	protected boolean viewAllChildren;
	protected boolean manageUserControls;
	protected boolean manageSettings;	*/
    dashBoardLinkHtml = "";

    if (myControls.viewAllActions) {
        createDashBoardLinkEntry("meow", "This is a meow");
    }



    if (myJobTitle == "Administrator") {
        dashBoardLinkHtml += "<a class='quicklink-links' href='#' onClick=readMyOpenActions()>My Open Action Items</a><p>";
        dashBoardLinkHtml += "<a class='quicklink-links' href='#' onClick=readAllUsers()>Show All Users</a><p>";
        dashBoardLinkHtml += "<a class='quicklink-links' href='#' onClick=readAllChildren()>Show All Students</a><p>";
        dashBoardLinkHtml += "<br>";
        dashBoardLinkHtml += "<a class='quicklink-links' href='#' onClick=showAddStaffPage()>Add Staff</a><p>";
        dashBoardLinkHtml += "<a class='quicklink-links' href='#' onClick=showAddChildrenPage()>Add Children</a><p>";

        dashBoardLinkHtml += "<a class='quicklink-links' href='#' onClick=readMyActions()>All My Actions</a><p>";
        dashBoardLinkHtml += "<a class='quicklink-links' href='#' onClick=readAllOpenActions()>All Open Actions</a><p>";
        dashBoardLinkHtml += "<a class='quicklink-links' href='#' onClick=readMyOpenActions()>My Open Actions</a><p>";
        dashBoardLinkHtml += "<a class='quicklink-links' href='#' onClick=readAllActions()>All Actions</a><p>";
        dashBoardLinkHtml += "<a class='quicklink-links' href='#' onClick=readAllDocuments()>All Documents</a><p>";
        dashBoardLinkHtml += "<a class='quicklink-links' href='#' onClick=readMyDocuments()>All My Documents</a><p>";

        $("#dashboard-links").html(dashBoardLinkHtml);
    }
}

function createDashBoardLinkEntry(functionName, linkText) {
    dashBoardLinkHtml += "<a class='quicklink-links' href='#' onClick=" + functionName + "()>" + linkText + "</a><p>";
}

function showWelcomePage() {
    hideAllPages();
    $("#WelcomePage-Div").show();
}

function showUsersPage() {
    hideAllPages();
    $("#UserController-Div").show();
}

function showActionsPage() {
    hideAllPages();
    $("#ActionController-Div").show();
}

function showChildrenPage() {
    hideAllPages();
    $('#ChildController-Div').show();
}

function showAddStaffPage() {
    hideAllPages();
    $("#AddStaff-Div").show();
}

function showAddChildrenPage() {
    hideAllPages();
    $("#AddChild-Div").show();
}

function showDocumentsPage() {
    hideAllPages();
    $("#DocController-Div").show();
}

function showAddDocumentPage(childId, childName) {
    $("#AddDocument-child-id").html(childId);
    $("#AddDocument-child-name").html(childName);
    $('#ChildController-Div').hide();
    $("#AddDocument-remarks").val("");
    $("#AddDocument-file-input").val("");
    hideAllPages();
    $("#AddDocument-Div").show();
}

function showAddActionPage(docId, docName) {
    $("#AddAction-docId").html(docId);
    $("#AddAction-docName").html(docName);
    $("#DocController-Div").hide();
    hideAllPages();
    $("#AddAction-Div").show();
}

function showUpdateChildPage(chidId) {
    for (i = 0; i < myChildren.length; i++) {
        if (myChildren[i].id == chidId) {
            updateChildControllerAngular.setChildDetail(myChildren[i]);
            updateChildControllerAngular.$apply();
            break;
        }
    }

    hideAllPages();
    $("#UpdateChild-Div").show();
}

function showUpdateDocumentPage(docId) {
    for (i = 0; i < myDocuments.length; i++) {
        if (myDocuments[i].docId == docId) {
            updateDocumentControllerAngular.setDocumentDetail(myDocuments[i]);
            updateDocumentControllerAngular.$apply();
            break;
        }
    }
    hideAllPages();
    $("#UpdateDocument-Div").show();
}

function showUpdateActionPage(actionId) {

    for (i = 0; i < myActions.length; i++) {
        if (myActions[i].actionId == actionId) {
            updateActionControllerAngular.setActionDetail(myActions[i]);
            updateActionControllerAngular.$apply();
            break;
        }
    }

    hideAllPages();
    $("#UpdateAction-Div").show();
}

function hideAllPages() {
    $("#status-message").html("");
    $("#WelcomePage-Div").hide();

    $("#UserController-Div").hide();
    $("#AddStaff-Div").hide();

    $('#ChildController-Div').hide();
    $("#AddChild-Div").hide();
    $("#UpdateChild-Div").hide();

    $("#DocController-Div").hide();
    $("#AddDocument-Div").hide();
    $("#UpdateDocument-Div").hide();

    $("#AddAction-Div").hide();
    $("#ActionController-Div").hide();
    $("#UpdateAction-Div").hide();

    $("#user-profile-div").hide();
}
