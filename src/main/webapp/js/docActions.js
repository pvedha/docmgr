function readAllActions() {
    $.ajax({
        url: baseURL + '/action/all',
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
        addActionStatesToSelect(teachers, actionStates[i]);
    }
}

function addActionStatesToSelect(selectOption, actionState) {
    var option = document.createElement("option");
    option.text = actionState.state;
    //option.value = staff.userId;
    selectOption.add(option);
}
