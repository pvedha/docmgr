var docModule = angular.module("DocApp", []);
docModule.controller("DocController", function ($scope) {

    $scope.baseFilePathUrl = "file:///c:/temp"

    $scope.users = [];

    $scope.addUsers = function (response) {
        $scope.users = [];
        for (i = 0; i < response.length; i++) {
            var user = response[i];
            $scope.users.push({
                userId: user.userId,
                userName: user.userName,
                jobTitle: user.jobTitle,
                about: user.about,
                email: user.email,
                phone: user.phone,
                actions: 5
            });
        }
    };


    $scope.children = [];

    $scope.addChildren = function (response) {
        $scope.children = [];
        for (i = 0; i < response.length; i++) {
            var child = response[i];
            $scope.children.push({
                id: child.id,
                name: child.name,
                dob: child.dob,
                doj: child.doj,
                remarks: child.remarks,
                message: child.message,
                teacher: child.teacher,
                councillor: child.councillor,
                therapist: child.therapist,
                tags: child.tags
            });
        }
    };


    $scope.docs = [];

    $scope.addDocuments = function (response) {
        $scope.docs = [];
        for (i = 0; i < response.length; i++) {
            var doc = response[i];
            $scope.docs.push({
                docId: doc.docId,
                docName: doc.docName,
                revision: doc.revision,
                childName: doc.childName,
                owner: doc.owner,
                creator: doc.creator,
                createdOn: doc.createdOn,
                lastUpdated: doc.lastUpdated,
                remarks: doc.remarks,
                status: doc.status
            });
        }
    };


    $scope.actions = [];

    $scope.addActions = function (response) {
        $scope.actions = [];
        for (i = 0; i < response.length; i++) {
            var action = response[i];
            $scope.actions.push({
                actionId: action.actionId,
                docName: action.docName,
                actionTitle: action.actionTitle,
                action_creator: action.action_creator,
                action_owner: action.action_owner,
                created_on: action.created_on,
                updated_on: action.updated_on,
                state: action.state,
                remarks: action.remarks
            });
        }
    };


    $scope.action;

    $scope.setActionDetail = function (action) {
        $scope.action = action;
        $("#update-action-state").val(action.state);
    };

    $scope.child;

    $scope.setChildDetail = function (child) {
        $scope.child = child;
        $("#UpdateChild-Teacher").val(child.teacher);
        $("#UpdateChild-Councillor").val(child.councillor);
        $("#UpdateChild-Therapist").val(child.therapist);
    };

    $scope.document;

    $scope.setDocumentDetail = function (document) {
        $scope.document = document;
        $("#UpdateDocument-owner").val(document.owner);
        $("#UpdateDocument-status").val(document.status);
    };









    $scope.posts = [];
    $scope.add = function () {
        $scope.posts.push({
            title: $scope.contact.name,
            message: $scope.post.message
        });
        $scope.addPost("Inside", "addFunction");
    };

    $scope.clear = function () {
        $scope.posts = [];
    };

    $scope.addPost = function (titleIn, messageIn, postIdIn, response) {
        $scope.posts.push({
            title: titleIn,
            message: messageIn,
            postId: postIdIn
        });
    };

    $scope.addPosts = function (response) {
        $scope.posts = [];
        for (i = 0; i < response.length; i++) {
            var post = response[i];
            var postMessage = "";
            if (post.message.length > 200) {
                postMessage = post.message.substring(0, 200) + "....."; //<a href='#' onClick=viewPost(" + response[i].postId
                //+ ")> read more </a>";
                //Check to make this contents as html
            } else {
                postMessage = post.message;
            }
            $scope.posts.push({
                title: post.title,
                message: postMessage,
                postId: post.postId,
                userName: post.userName,
                postedOn: post.postedOn,
                commentsCount: post.comments.length
            });
        }
    };


});
