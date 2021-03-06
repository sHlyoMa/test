<%--
  Created by IntelliJ IDEA.
  User: yhrush
  Date: 10.11.2016
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
      <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
      <script>
          var wsocket;
          var serviceLocation = "ws://10.131.6.154/web/chat";
          var $nickName;
          var $message;
          var $chatWindow;
          var room = '';

          var audio = new Audio('sound.mp3')

          function onMessageReceived(evt) {
              //var msg = eval('(' + evt.data + ')');
              var msg = JSON.parse(evt.data); // native API
              var $messageLine = $('<tr><td class="received">' + msg.received
                      + '</td><td class="user label label-info">' + msg.sender
                      + '</td><td class="message badge">' + msg.message
                      + '</td></tr>');
              $chatWindow.append($messageLine);
              audio.play();
          }
          function sendMessage() {
              var msg = '{"message":"' + $message.val() + '", "sender":"'
                      + $nickName.val() + '", "received":""}';
              wsocket.send(msg);
              $message.val('').focus();
          }

          function connectToChatserver() {
              wsocket = new WebSocket(serviceLocation);
              wsocket.onmessage = onMessageReceived;
          }


          $(document).ready(function() {
              $nickName = $('#nickname');
              $message = $('#message');
              $chatWindow = $('#response');
              $('.chat-wrapper').hide();
              $nickName.focus();

              $('#enterRoom').click(function(evt) {
                  evt.preventDefault();
                  connectToChatserver();
                  $('.chat-wrapper h2').text('Chat # '+$nickName.val());
                  $('.chat-signin').hide();
                  $('.chat-wrapper').show();
                  $message.focus();
              });
              $('#do-chat').submit(function(evt) {
                  evt.preventDefault();
                  sendMessage()
              });

              $('#leave-room').click(function(){
                  wsocket.close();
                  $chatWindow.empty();
                  $('.chat-wrapper').hide();
                  $('.chat-signin').show();
                  $nickName.focus();
              });
          });
      </script>
  </head>
  <body>
  <div class="container chat-signin">
      <form class="form-signin">
          <h2 class="form-signin-heading">Chat sign in</h2>
          <label for="nickname">Nickname</label> <input type="text"
                                                        class="input-block-level" placeholder="Nickname" id="nickname">
          <button class="btn btn-large btn-primary" type="submit"
                  id="enterRoom">Sign in</button>
      </form>
  </div>
  <!-- /container -->

  <div class="container chat-wrapper">
      <form id="do-chat">
          <h2 class="alert alert-success"></h2>
          <table id="response" class="table table-bordered"></table>
          <fieldset>
              <legend>Enter your message..</legend>
              <div class="controls">
                  <input type="text" class="input-block-level" placeholder="Your message..." id="message" style="height:60px"/>
                  <input type="submit" class="btn btn-large btn-block btn-primary"
                         value="Send message" />
                  <button class="btn btn-large btn-block" type="button" id="leave-room">Leave
                      room</button>
              </div>
          </fieldset>
      </form>
  </div>
  </body>
</html>
