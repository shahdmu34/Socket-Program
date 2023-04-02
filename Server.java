package sockets.programming;

import java.net.*;
import java.io.*;

public class Server {

	
	private Socket socket; //intiallizing socket
	private ServerSocket server; //intiallizing server socket
	private DataInputStream insert;  //intiallizing input
	
	public Server(int port) {
		//using the port # server side is created
		try {
			//creates the socket for server using the port num
			server = new ServerSocket(port);
			System.out.println("server started");
			//output to user
			System.out.println("waiting for client...");
			//out put to user to connect the client
			
			
			//socket for server is made and accepted
			socket = server.accept();
			//client has connected
			System.out.println("client accepted");
			//client input is taken to the socket
			insert = new DataInputStream( new BufferedInputStream(socket.getInputStream()));
			//key word string 
			String keyWord = "";
			//default message when starting
			String defMess = "An apple a day keeps the doctor away \n";
			
			while (!keyWord.equals("MSGGET")) {  //client enters mssget command
				
				try {
					//reads the inputed word
					keyWord = insert.readUTF();
					//outputs the client word thats been read
					System.out.println("C: " + keyWord);
				}
				catch(IOException i ) {
					System.out.println(i);
					
				}
				
			}
			System.out.println("S: 200 OK "); //send message that its been received
			System.out.println(defMess); // default message of the day
			
			while (!keyWord.equals("MSGSTORE")) {   //client entered key word has been read in
				
				try {
					keyWord = insert.readUTF();
					//reading in word
					System.out.println("C: " + keyWord);  //outputting what the client had said on server side
					 
				}
				catch(IOException i ) {
					System.out.println(i);
					
				}
				System.out.println("S: 200 ok"); //okay message that its been received
				try {
					defMess = insert.readUTF();    //reading in new message from client
					System.out.println("C: " + defMess); //outputting the received message from client
					 
				}
				catch(IOException i ) {
					System.out.println(i);
					
				}
				
			}
			
			System.out.println("S: 200 ok \n"); //ok message that its been received
			
			while (!keyWord.equals("MSGGET")) {  //client enters mssget command
				
				try {
					keyWord = insert.readUTF(); // reading in word
					System.out.println("C: " + keyWord); //outputting the command received
				}
				catch(IOException i ) {
					System.out.println(i);
					
				}
				
			}
			System.out.println("S: 200 OK");   //output ok message
			System.out.println(defMess + "\n"); // outputting new/ updated message of the day
			
			
			
			//client entered the quit command
			while (!keyWord.equals("QUIT")) { 
				
				try {
					keyWord = insert.readUTF();  //reads in the command
					System.out.println("C: " + keyWord); //outputting what client said to server side
				}
				catch(IOException i ) {
					System.out.println(i);
					
				}
				
			}
			System.out.println("S: 200 OK"); //okay message from server that its been received
			//closing the socket and insert as  per client
			socket.close();
			insert.close();
		}
		catch(IOException i) {
			System.out.println(i);
		}
		
	}
	
	public static void main(String[] args)
	{
		//intalizing the server function with port number
		Server server_port = new Server(8641);
	}
}
