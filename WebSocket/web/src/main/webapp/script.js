(function () {
    var Message;
    var wsocket;
    var $nickName;
    var audio = new Audio('sound.mp3');
    var message_side = 'left';
    Message = function (data) {
        var msg = JSON.parse(data);
        this.text = msg.message,
            this.sender = msg.sender,
            this.date = msg.received,
            this.message_side = message_side;
        this.draw = function (_this) {
            return function () {
                var $message;
                $message = $($('.message_template').clone().html());
                $message.addClass(_this.message_side).find('.text').html(_this.sender + ': ' + _this.text);
                $message.addClass(_this.message_side).find('.date').html(_this.date);
                $('.messages').append($message);
                return setTimeout(function () {
                    return $message.addClass('appeared');
                }, 0);
            };
        }(this);
        return this;
    };
    function getMessageText() {
        return $('#message_input').val();
    };

    function printMessage(evt) {
        var $messages, message;
        $messages = $('.messages');
        message_side = message_side === 'left' ? 'right' : 'left';
        message = new Message(evt.data);
        audio.play();
        message.draw();
        return $messages.animate({scrollTop: $messages.prop('scrollHeight')}, 300);
    };
    $(function () {
        $('.chat_window').hide();
        $('#enter_chat_input').focus();
        $('#send_message').click(function (e) {
            var msg = '{"message":"' + getMessageText() + '", "sender":"' + $nickName + '", "received":""}';
            wsocket.send(msg);
            $('.message_input').val('');
        });
        $('#enter_chat').click(function (e) {
            enterChat();
        });
        $('.close').click(function (e) {
            wsocket.close();
            $('.messages').empty()
            $('#enter_chat_input').val('');
            $nickName = '';
            $('.nick_window').show();
            $('.chat_window').hide();
            $('#enter_chat_input').focus();
        });
        $('#message_input').keyup(function (e) {
            if (e.which === 13) {
                var msg = '{"message":"' + getMessageText() + '", "sender":"' + $nickName + '", "received":""}';
                wsocket.send(msg);
                $('.message_input').val('');
            }
        });
        $('#enter_chat_input').keyup(function (e) {
            if (e.which === 13) {
                enterChat();
            }
        });
    });
    function enterChat() {
        wsocket = new WebSocket("ws://10.131.6.154/web/chat");
        wsocket.onmessage = printMessage;
        $nickName = $('#enter_chat_input').val();
        $('.nick_window').hide();
        $('.chat_window').show();
        $('#message_input').focus();
    }
}.call(this));


