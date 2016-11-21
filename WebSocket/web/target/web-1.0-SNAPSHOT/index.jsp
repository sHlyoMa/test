<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat</title>

    <script language="JavaScript" type="text/javascript" src="jquery.js"></script>
    <script language="JavaScript" type="text/javascript" src="script.js"></script>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="nick_window">
    <div class="bottom_wrapper clearfix">
        <div class="message_input_wrapper">
            <input id="enter_chat_input" class="message_input" placeholder="Enter your nickname..."/></div>
        <div id="enter_chat" class="send_message">
            <div class="icon"></div>
            <div class="text">Enter chat</div>
        </div>
    </div>
</div>
<div class="chat_window">
    <div class="top_menu">
        <div class="buttons">
            <div class="button close"></div>
        </div>
        <div class="title">Chat</div>
    </div>
    <ul class="messages"></ul>
    <div class="bottom_wrapper clearfix">
        <div class="message_input_wrapper">
            <input id="message_input" class="message_input" placeholder="Type your message here..."/></div>
        <div id="send_message" class="send_message">
            <div class="icon"></div>
            <div class="text">Send</div>
        </div>
    </div>
</div>
<div class="message_template">
    <li class="message">
        <div class="text_wrapper">
            <div class="text mess"></div>
            <div class="date"></div>
        </div>
    </li>
</div>
</body>
</html>