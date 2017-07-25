function loadMainPage(response) {
    token = response.token; //new token?
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
    loadDashboardLinks(response.jobTitle);

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
    hideAllForms();
    $("#AddDocument-Div").show();
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
    $("#DocController-Div").hide();
    $("#UserController-Div").hide();
    $('#ChildController-Div').hide();
    $("#AddStaff-Div").hide();
    $("#AddChild-Div").hide();
    $("#UpdateChild-Div").hide();
    $("#user-profile-div").hide();
    $("#ActionController-Div").hide();
    $("#UpdateAction-Div").hide();
    $("#AddDocument-Div").hide();
    $("#UpdateDocument-Div").hide();
}
