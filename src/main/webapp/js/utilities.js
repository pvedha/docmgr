function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds) {
            break;
        }
    }
}

function log(message) {
    if (debugMode)
        console.log(message);
}



//drag drop functions
function allowDrop(ev) {
    ev.preventDefault();
    //    console.log("Allowdrop ID " + ev.target.id);
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
    //    console.log("Event Target ID " + ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    var targetId = "";
    var offsetParentItem = ev.target;
    while (true) {
        targetId = offsetParentItem.id;
        if (targetId == "main-contents-div" || targetId == "quicklinks-div") {
            document.getElementById("droppable-div").insertBefore(document.getElementById(data), document.getElementById(targetId)); //sort of works, identify before item dynamically.
            break;
        }
        if (targetId == "body") {
            break;
        }
        offsetParentItem = offsetParentItem.offsetParent;
    }

}

$(window).scroll(function () {
    // This is the function used to detect if the element is scrolled into view
//    function elementScrolled(elem) {
    //        var docViewTop = $(window).scrollTop();
    //        var docViewBottom = docViewTop + $(window).height();
    //        var elemTop = $(elem).offset().top;
    //        return ((elemTop <= docViewBottom) && (elemTop >= docViewTop));
    //    }
    //    if (elementScrolled('#loading-more')) {
    //        if (loadMoreContents) {
    //            readLimitedPosts();
    //            loadMoreContents = false;
    //        }
    //    }
});