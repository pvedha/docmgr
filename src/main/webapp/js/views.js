function loadMainPage(response) {
    token = response.token; //new token?
    $("#current-user-icon").html("<img src='img/48px-User_icon_2.svg.png' class='img-normal'/>");
    $("#user-detail-div").html("<b>" + response.name + "</b><p><i>" + response.about);
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
    $("#DocController-Div").show();
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

function hideAllPages() {
    $("#status-message").html("");
    $("#WelcomePage-Div").hide();
    $("#DocController-Div").hide();
    $('#ChildController-Div').hide();
    $("#AddStaff-Div").hide();
    $("#AddChild-Div").hide();
    $("#user-profile-div").hide();
}
