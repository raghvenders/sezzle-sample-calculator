import SockJS from "sockjs-client";
require("stompjs");



export default function register(registrations) {
	const socket = SockJS('/calcResult'); // <3>
	const stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		registrations.forEach(function (registration) { // <4>
			stompClient.subscribe(registration.route, registration.callback);
		});
	});
}

