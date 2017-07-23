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
    actionControllerAngular.addActions(response);
    actionControllerAngular.$apply();
    showActionsPage();
}
