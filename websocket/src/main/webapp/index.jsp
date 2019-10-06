<!DOCTYPE html>
<html>
<head>
    <title>chatendpoint.html</title>
    <script language="JavaScript">
        var wsuri = "ws://localhost:8080/websocket/chatendpoint";
        var ws = null;

        var demo = {
            socket : null,  // WebSocket连接对象
            host : '',      // WebSocket连接 url
            connect : function() {  // 连接服务器
                debugger;
                window.WebSocket = window.WebSocket || window.MozWebSocket;
                if (!window.WebSocket) {    // 检测浏览器支持
                    console.log('Error: WebSocket is not supported .');
                    return;
                }
                this.socket = new WebSocket(this.host); // 创建连接并注册响应函数
                this.socket.onopen = function(){console.log("websocket is opened .");};
                this.socket.onmessage = function(message) {document.getElementById("echo").value = message.data;console.log(message.data);};
                this.socket.onclose = function(){
                    document.getElementById("echo").value = 'websocket is closed .';
                    console.log("websocket is closed .");
                    this.close();
//                    demo.socket = null; // 清理
                };
            },
            send : function(message) {  // 发送消息方法
                if (this.socket) {
                    this.socket.send(message);
                    document.getElementById("echo").value = message;
                    return true;
                }
                console.log('please connect to the server first !!!');
                return false;
            }
        };
        // 初始化WebSocket连接 url
        demo.host = 'ws://localhost:8080/websocket/chatendpoint';


//        function connectEndpoint(){
//            window.WebSocket = window.WebSocket || window.MozWebSocket;
//            if (!window.WebSocket){
//                alert("WebSocket not supported by this browser");
//                return;
//            }
//
//            ws = new WebSocket(wsuri);
//            ws.onmessage = function(evt) {
//                debugger;
//                //alert(evt.data);
//                var old = document.getElementById("echo").value;
//                document.getElementById("echo").value = old+evt.data+"\r\n";
//            };
//
//            ws.onclose = function(evt) {
//                //alert("close");
//                document.getElementById("echo").value = "server disconnect.\r\n";
//                ws = null;
//            };
//
//            ws.onopen = function(evt) {
//                //alert("open");
//                document.getElementById("echo").value = "connect server.\r\n";
//            };
//        }

        function sendmsg(){
            demo.send(document.getElementById("send").value);
        }

        function connect() {
            demo.connect();
        }

        function disconnect() {
            demo.socket.onclose();
        }

//        connectEndpoint();
    </script>
</head>
<body>
<input type="text" size="20" value="hi~" id="send"> <input type="button" value="send" onclick="sendmsg()">
<input type="button" value="connect to server" onclick="connect()">
<input type="button" value="disconnect" onclick="disconnect()"><br>
<textarea id="echo"  rows="50" cols="50">
</textarea>
</body>
</html>
