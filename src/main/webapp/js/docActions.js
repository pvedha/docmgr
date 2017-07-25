function readAllDocuments() {
    readDocuments('/doc/all');
}

function readMyDocuments() {
    readDocuments('/doc/' + currentUserId);
}

function readDocuments(url) {
    $.ajax({
        url: baseURL + url,
        type: 'get',
        accept: 'application/json',
        global: false,
        success: function (response) {
            displayDocuments(response);
        }
    })
}

function displayDocuments(response) {
    myDocuments = response;
    docControllerAngular.addDocuments(response);
    docControllerAngular.$apply();
    showDocumentsPage();
}

function addDocument() {

    var docName = "YetToBeAdded.doc";
    var revision = 1;
    var childId = $("#AddDocument-child-id").val();
    var childName = $("#AddDocument-child-name").val();
    var owner = $("#staff-about").val();
    var creator = $("#staff-email").val();
    var remarks = $("#staff-phone").val();
    var status = $("#staff-job-title").val();


    if (docName.trim().length === 0 || childName.trim().length === 0) {
        $("#status-message").html("Invalid details");
        return;
    }

    $("#status-message").html("Please wait, adding document...");
    var data = {
        docId: userId,
        docId: userName,
        revision: revision,
        childId: childId,
        childName: childName,
        owner: owner,
        creator: creator,
        createdOn: '',
        lastUpdated: '',
        remarks: remarks,
        status: status
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

function saveCurrentDocument(document) {

    document.owner = $("#UpdateDocument-owner").val();
    document.status = $("#UpdateDocument-status").val();
    document.remarks += "; " + $("#UpdateDocument-remarks").val();


    $("#status-message").html("Updating Document...");
    $.ajax({
        url: baseURL + '/doc/update',
        type: 'post',
        contentType: 'application/json',
        global: false,
        success: function (response) {
            //TODO try to update the current page if possible. with updated time, state and remarks. 
            $("#status-message").html("Document details updated");
            sleep(2000);
            //readAllActions();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("Http responseText: " + XMLHttpRequest.responseText + ", Status : " + XMLHttpRequest.status + ", ErrorThrown: " + errorThrown);
            $("#status-message").html("Error Updating Document");
        },
        data: JSON.stringify(document)
    })
}







function readAllActions() {
    readActions('/action/all');
};

function readAllOpenActions() {
    readActions('/action/allOpen');
};

function readMyActions() {
    readActions('/action/myActions' + '/' + currentUserId);
};

function readMyOpenActions() {
    readActions('/action/myOpenActions' + '/' + currentUserId);
};

function readActions(url) {
    $.ajax({
        url: baseURL + url,
        type: 'get',
        accept: 'application/json',
        global: false,
        success: function (response) {
            displayActions(response);
        }
    })
};

function displayActions(response) {
    myActions = response;
    actionControllerAngular.addActions(response);
    actionControllerAngular.$apply();
    showActionsPage();
}

//TODO where is this called?
function addAction() {
    action.remarks += action.remarks + "; " + $("#update-action-remark").val();
    action.state = $("#update-action-state").val();
    $.ajax({
        url: baseURL + '/action/add',
        type: 'post',
        contentType: 'application/json',
        global: false,
        success: function (response) {
            setActionStates(response);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("Http responseText: " + XMLHttpRequest.responseText + ", Status : " + XMLHttpRequest.status + ", ErrorThrown: " + errorThrown);
            $("#status-message").html("Error Updating Action");
        },
        data: JSON.stringify(action)
    })
}


function saveCurrentAction(action) {

    action.state = $("#update-action-state").val();
    action.remarks += "; " + $("#update-action-remark").val();

    $("#status-message").html("Updating Action...");
    $.ajax({
        url: baseURL + '/action/update',
        type: 'post',
        contentType: 'application/json',
        global: false,
        success: function (response) {
            //TODO try to update the current page if possible. with updated time, state and remarks. 
            $("#status-message").html("Action item updated");
            sleep(2000);
            readAllActions();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("Http responseText: " + XMLHttpRequest.responseText + ", Status : " + XMLHttpRequest.status + ", ErrorThrown: " + errorThrown);
            $("#status-message").html("Error Updating Action");
        },
        data: JSON.stringify(action)
    })
}

function retrieveDocStates() {
    $.ajax({
        url: baseURL + '/doc/states',
        type: 'get',
        accept: 'application/json',
        global: false,
        success: function (response) {
            setDocStates(response);
        }
    })
};

function setDocStates(docStates) {
    var addDocumentStatus = document.getElementById("AddDocument-status");
    var updateDocumentStatus = document.getElementById("UpdateDocument-status");
    for (i = 0; i < docStates.length; i++) {
        addValuesToSelect(addDocumentStatus, docStates[i].docState);
        addValuesToSelect(updateDocumentStatus, docStates[i].docState);
    }
}

function retrieveActionStates() {
    $.ajax({
        url: baseURL + '/action/states/all',
        type: 'get',
        accept: 'application/json',
        global: false,
        success: function (response) {
            setActionStates(response);
        }
    })
};

function setActionStates(actionStates) {
    var teachers = document.getElementById("update-action-state");
    for (i = 0; i < actionStates.length; i++) {
        addValuesToSelect(teachers, actionStates[i].state);
    }
}

function addValuesToSelect(selectOption, text) {
    var option = document.createElement("option");
    option.text = text;
    //option.value = staff.userId;
    selectOption.add(option);
}
