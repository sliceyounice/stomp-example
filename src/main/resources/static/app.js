var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('http://localhost:8080/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    var header = {Authorization: "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InJvZmxhbiIsImlhdCI6MTU4OTc4NzcyNCwiZXhwIjoxNTg5ODc0MTI0fQ.I3-mFaeUXd3lKRiNf98HBQHxlvp5QZNKyWVqtHC32YY"};
    stompClient.connect(header, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
            console.log("recieved")
        });
    }, function (e) {

    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

