# Socket-Program
This programming assignment involves creating a server process that implements the "message of the day" protocol and a client process that communicates with the server over TCP sockets.

The Yamotd server performs two main functions. Firstly, it responds to a client that sends a MSGGET message by returning the current message of the day. Secondly, it allows a client to upload a new message of the day by sending a MSGSTORE message. The newly uploaded message will replace the previously stored message and will be returned to any future clients that send a MSGGET message.

To interact with the server, you will need to create a client process that sends either a MSGGET or MSGSTORE message to the server over TCP sockets. The server will then respond with the requested information or confirmation of the message being stored.
