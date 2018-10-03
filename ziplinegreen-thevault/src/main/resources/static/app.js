var stompClient = null;

function setConnected(connected) {
    $('#connect').prop("disabled", connected);
    $('#disconnect').prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

function connect() {
    var socket = new SockJS('/vault-socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/posts', function (greeting) {
            showGreeting(JSON.parse(greeting.body).message, JSON.parse(greeting.body).userName);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

var userId = 1;
var userPostUrl = "/app/posts";

function user1() {
    $('#user1').prop("disabled", true);
    $('#user2').prop("disabled", false);
    userId = 1;
}

function user2() {
    $('#user1').prop("disabled", false);
    $('#user2').prop("disabled", true);
    userId = 2;
}


function sendName() {

    stompClient.send(userPostUrl, {}, JSON.stringify({'id': '','message': $("#message").val(),'userId': userId}))
}

function showGreeting(message, userName) {
    $("#messages").append("<tr><td>" + userName + ": " + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#user1" ).click(function() { user1(); });
    $( "#user2" ).click(function() { user2(); })
});