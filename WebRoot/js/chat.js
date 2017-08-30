/**
 * 
 */
var Chat = {};
Chat.socket = null;
Chat.connect = function(host) {
	if ('WebSocket' in window) {
		Chat.socket = new WebSocket(host);
	} else {
		alert("抱歉，您的浏览器不支持WebSocket！");
		return;
	}

	//和服务器连接建立成功
	Chat.socket.onopen = function() {
		console.log(user.id + " 连接WebSocket服务端成功");
	};

	//和服务器断开连接
	Chat.socket.onclose = function() {
		console.log(user.id + " 断开ws连接");
	};

	/* 接到服务器发来的消息 */
	Chat.socket.onmessage = function(message) {
		console.log(user.id + " 收到服务端消息 " + message.data);
	};

	/**
	 * 刷新、页面跳转、关闭页面等事件发生前关闭连接
	 */
	window.onbeforeunload = function() {
		Chat.socket.close();
	}
}
Chat.initialize = function() {
	if (window.location.protocol == 'http:') {
		Chat.connect('ws://' + window.location.host + '/minecraft/websocket/' + user.id);
	} else {
		Chat.connect('wss://' + window.location.host + '/minecraft/websocket/' + user.id);
	}
};	