package sockets.programming;

import java.net.*;
import java.io.*;

public class Client {

	private Socket socket; 
	private BufferedReader input;
	private DataOutputStream output;
	
	public Client(String address, int port) { //uses the ip address and port number for connection
		//starting the connection between client and server
		try {
			
			socket = new Socket(address, port); //creating socket with ip address and port #
			System.out.println("connected"); //message to user
			//input and output reader for socket when connected
			input = new BufferedReader(new InputStreamReader(System.in));
			
			output = new DataOutputStream(socket.getOutputStream());
			
		} // exception for any errors to occur
		catch(UnknownHostException error){
			System.out.println(error);
		}
		catch(IOException io) {
			System.out.println(io);
				}
		
		
		String keyWord = ""; //reads in key words that are inputed
		String message = ""; //reads in new daily message
		//reads till the key word is found on line
		while(!keyWord.equals("MSGGET")) {
			try {
				//reading the the word in
				keyWord = input.readLine();
				output.writeUTF(keyWord);
				//sending to output
			}//if problem found
			catch(IOException io) {
				System.out.println(io);
			}
		}
		
		//reads till the key word is found on line
		while(!keyWord.equals("MSGSTORE")) {
			try {
				
				//reading the the word in
				keyWord = input.readLine();
				//sending to output
				output.writeUTF(keyWord);
				
			}
			catch(IOException i) {
				System.out.println(i);
			}
			try {
				//reading in client daily message 
				message = input.readLine();
				//sending to output the new message
				output.writeUTF(message);
				
			}
			catch(IOException i) {
				System.out.println(i);
			}
			
		}
		
		while(!keyWord.equals("MSGGET")) {
			try {
				//reading in the key word
				keyWord = input.readLine();
				output.writeUTF(keyWord); // sending to the output 
				
			}
			catch(IOException i) {
				System.out.println(i);
			}
		}
		

		//reading in line till the final key word quit
		while(!keyWord.equals("QUIT")) {
			try {
				//reading in the word
				keyWord = input.readLine();
				output.writeUTF(keyWord);
				
			}
			catch(IOException i) {
				System.out.println(i);
			}
		}
		try {
			//closing the client side of the socket 
			input.close();
			output.close();
			socket.close();
		}
		catch(IOException i) {
			System.out.println(i);
		}
		
	}
	
	public static void main(String[] args) {
		Client client = new Client("127.0.0.1", 8641);       //calling client function with respective ip address and port number 
	}
	
}
