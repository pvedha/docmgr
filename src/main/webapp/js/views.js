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
    retrieveSystemProperties();
    showWelcomePage();
    //loadDashboardLinks(response.jobTitle);
    retrieveJobTitles();
}


function retrieveSystemProperties() {
    $.ajax({
        url: baseURL + '/gen/properties',
        type: 'get',
        accept: 'application/json',
        global: false,
        success: function (response) {
            setSystemProperties(response);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            log("Error Loading system properties, " + textStatus);
        }
    })
}

function setSystemProperties(response) {
    propertiesControllerAngular.addProperties(response);
    propertiesControllerAngular.$apply();

    for (i = 0; i < response.length; i++) {
        if (response[i].name == "BasePath") {
            fileBasePath = response[i].property;
        }
    }
}

function updateSystemProperty(name, value) {
    var data = {
        name: name,
        property: $(document.getElementById(value)).val()
    }

    $.ajax({
        url: baseURL + '/gen/properties',
        type: 'post',
        contentType: 'application/json',
        global: false,
        success: function (response) {
            setStatus("System properties updated, kindly relogin");
            sleep(2000);
            //showWelcomePage();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus + "Error updating system properties" + errorThrown);
            setStatus("Error updating system properties, please check the details");
        },
        data: JSON.stringify(data)
    })
}

function updateSystemProperties(propertyList) {
    $.ajax({
        url: baseURL + '/gen/properties',
        type: 'post',
        contentType: 'application/json',
        global: false,
        success: function (response) {
            setStatus("System properties updated, kindly relogin");
            sleep(2000);
            showWelcomePage();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus + "Error updating system properties " + errorThrown);
            setStatus("Error updating system properties, please check the details");
        },
        data: JSON.stringify(propertyList)
    })
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
            updateManageControlsPageValues();
            //            console.log("added items");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            log("Error Loading jobTitles");
        }
    })
}

function setJobTitles() {

    $("#staff-job-title").find('option').remove().end();
    $("#ManageControls-user-type").find('option').remove().end();
    var jobTitle = document.getElementById("staff-job-title");
    var manageControlsJobTitle = document.getElementById("ManageControls-user-type");

    for (i = 0; i < jobTitles.length; i++) {
        addJobTitleToSelect(jobTitle, jobTitles[i].title);
        addJobTitleToSelect(manageControlsJobTitle, jobTitles[i].title);
    }
}

function addJobTitleToSelect(selectElement, title) {
    var option = document.createElement("option");
    option.text = title;
    selectElement.add(option);
}

function loadDashboardLinks() {

    //if (jobTitles.length == 0)
    // return;


    var myControls;
    for (i = 0; i < jobTitles.length; i++) {
        if (jobTitles[i].title == myJobTitle) {
            myControls = jobTitles[i];
        }
    }

    //  protected boolean viewAllActions;
    //	protected boolean addStaff;
    //	protected boolean addChildren;
    //	protected boolean viewAllChildren;
    //	protected boolean manageUserControls;
    //	protected boolean manageSettings;	


    dashBoardLinkHtml = "";

    addDashBoardLinkHeading("Actions");

    addDashBoardLinkEntry("readMyOpenActions", "My Open Actions");
    addDashBoardLinkEntry("readMyActions", "All My Actions");

    if (myControls.viewAllActions) {
        addDashBoardLinkEntry("readAllActions", "All Actions");
        addDashBoardLinkEntry("readAllOpenActions", "All Open Actions");
    }



    addDashBoardLinkHeading("Views");

    if (myControls.viewAllChildren) {
        //do we need this?
    }

    addDashBoardLinkEntry("readAllChildren", "Show All Students");

    //TODO view all documents??
    addDashBoardLinkEntry("readMyOpenDocuments", "My Open Documents");
    addDashBoardLinkEntry("readMyDocuments", "All My Documents");

    if (myControls.viewAllDocuments) {
        addDashBoardLinkEntry("readAllOpenDocuments", "All Open Documents");
        addDashBoardLinkEntry("readAllDocuments", "All Documents");
    }

    addDashBoardLinkHeading("Manage");
    addDashBoardLinkEntry("readAllUsers", "Show All Users");

    if (myControls.addStaff) {
        addDashBoardLinkEntry("showAddStaffPage", "Add Staff");
    }

    if (myControls.addChildren) {
        addDashBoardLinkEntry("showAddChildrenPage", "Add Children");
    }

    if (myControls.manageUserControls) { //Admin role only
        addDashBoardLinkEntry("manageUserControls", "Manage User Controls");
        addDashBoardLinkEntry("showAddStaffRole", "Add Staff Role");
        //TODO needs investigation for obtaining updated Angular object
        addDashBoardLinkEntry("showSystemSettings", "System Settings");
    }

    $("#dashboard-links").html(dashBoardLinkHtml);
}

function addDashBoardLinkEntry(functionName, linkText) {
    dashBoardLinkHtml += "<a class='quicklink-links' href='#' onClick=" + functionName + "()>" + linkText + "</a><br/>";
}

function addDashBoardLinkHeading(heading) {
    dashBoardLinkHtml += "<h2>" + heading + "</h2>";
}


function showWelcomePage() {
    hideAllPages();
    setPageHeading("Welcome to kriyAdveSin Document Management");
    $("#WelcomePage-Div").show();
}

function showUsersPage() {
    hideAllPages();
    setPageHeading("View All Users");
    $("#UserController-Div").show();
}

function showActionsPage() {
    hideAllPages();
    setPageHeading("View Actions");
    $("#ActionController-Div").show();
}

function showChildrenPage() {
    hideAllPages();
    setPageHeading("view All Children");
    $('#ChildController-Div').show();
}

function showAddStaffPage() {
    hideAllPages();
    setPageHeading("Add Staff");
    $("#AddStaff-Div").show();
    $("#staff-userId").val("");
    $("#staff-name").val("");
    $("#staff-about").val("");
    $("#staff-email").val("");
    $("#staff-phone").val("");
    $("#staff-job-title").val("");
}

function showAddChildrenPage() {
    hideAllPages();
    setPageHeading("Add Child");
    $("#AddChild-Div").show();
}

function showDocumentsPage() {
    hideAllPages();
    setPageHeading("View Documents");
    $("#DocController-Div").show();
}

function manageUserControls() {
    hideAllPages();
    setPageHeading("Manage user controls");
    $("#ManageControls-Div").show();
    updateManageControlsPageValues();
}

function showAddStaffRole() {
    hideAllPages();
    setPageHeading("Add Staff Role");
    $("#AddStaffRole-Div").show();
}

function showSystemSettings() {
    hideAllPages();
    setPageHeading("View/Update System Properties");
    $("#PropertiesController-Div").show();
}

function updateManageControlsPageValues() {

    if (selectedUserType != "") {
        $("#ManageControls-user-type").val(selectedUserType);
        selectedUserType = "";
    }

    var userType = $("#ManageControls-user-type").val();
    if (userType == "Administrator") {
        //setStatus("No modification allowed for Administrator"); //This is shown while loading for the first time itself.
        $("#ManageControls-submit-button").prop("disabled", true);
    } else {
        setStatus("");
        $("#ManageControls-submit-button").prop("disabled", false);
    }

    for (i = 0; i < jobTitles.length; i++) {
        if (userType == jobTitles[i].title) {
            var entry = jobTitles[i];
            $("#ManageControls-remarks").val(entry.remarks);
            $("#ManageControls-addStaff").prop('checked', entry.addStaff);
            $("#ManageControls-addChildren").prop('checked', entry.addChildren);
            $("#ManageControls-viewAllChildren").prop('checked', entry.viewAllChildren);
            $("#ManageControls-viewAllDocuments").prop('checked', entry.viewAllDocuments);
            $("#ManageControls-viewAllActions").prop('checked', entry.viewAllActions);
            $("#ManageControls-manageUserControls").prop('checked', entry.manageUserControls);
            $("#ManageControls-manageSettings").prop('checked', entry.manageSettings);
        }
    }

}

function showAddDocumentPage(childId, childName) {
    $("#AddDocument-child-id").html(childId);
    $("#AddDocument-child-name").html(childName);
    $('#ChildController-Div').hide(); //TODO why do we need this?, its not hiding under hideAllPages().
    $("#AddDocument-remarks").val("");
    $("#AddDocument-file-input").val("");
    $("#AddDocument-owner").val(currentUserId);
    $("#AddDocument-creator").val(currentUserId);
    hideAllPages();
    setPageHeading("Add a document");
    $("#AddDocument-Div").show();
}

function showUpdateDocumentPage(docId) {
    $("#UpdateDocument-remarks").val("");
    $("#UpdateDocument-file-input").val("");
    for (i = 0; i < myDocuments.length; i++) {
        if (myDocuments[i].docId == docId) {
            updateDocumentControllerAngular.setDocumentDetail(myDocuments[i]);
            updateDocumentControllerAngular.$apply();
            break;
        }
    }
    hideAllPages();
    setPageHeading("Update document details");
    $("#UpdateDocument-Div").show();
}

function showAddActionPage(docId, docName) {
    $("#AddAction-docId").html(docId);
    $("#AddAction-docName").html(docName);
    $("#DocController-Div").hide();
    $("#AddAction-title").val("");
    $("#AddAction-remarks").val("");
    $("#AddAction-submit-button").prop("disabled", false);
    hideAllPages();
    setPageHeading("Add an Action");
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
    setPageHeading("Update Child details");
    $("#UpdateChild-Div").show();
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
    setPageHeading("Update Action");
    $("#UpdateAction-Div").show();
}


function showMyProfile() {
    $("#MyProfile-id").html(currentUserId);
    $("#MyProfile-name").html(currentUserDetails.name);
    $("#MyProfile-newPassword").val("");
    $("#MyProfile-title").html(currentUserDetails.jobTitle);
    $("#MyProfile-about").val(currentUserDetails.about);
    $("#MyProfile-email").val(currentUserDetails.email);
    $("#MyProfile-phone").val(currentUserDetails.phone);
    hideAllPages();
    setPageHeading("My Profile");
    $("#MyProfile-div").fadeIn(1000);
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

    $("#MyProfile-div").hide();
    $("#ManageControls-Div").hide();
    $("#AddStaffRole-Div").hide();

    $("#PropertiesController-Div").hide();
}
