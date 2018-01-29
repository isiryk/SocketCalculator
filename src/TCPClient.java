
import java.io.*;
import java.net.*;
class TCPClient {
 public static void main(String argv[]) throws Exception
 {
  String integer1;
  String integer2;
  String operator;
  String con = "y";
  String modifiedSentence;
  while(con.toLowerCase().equals("y")) {
	  BufferedReader inFromUser =
	   new BufferedReader(
	     new InputStreamReader(System.in));
	  Socket clientSocket = new Socket("127.0.0.1", 52000);
	  DataOutputStream outToServer =
	   new DataOutputStream(
	     clientSocket.getOutputStream());
	  BufferedReader inFromServer =
	   new BufferedReader(new InputStreamReader(
	     clientSocket.getInputStream()));
	  System.out.println("Please enter the first number");
	  integer1 = inFromUser.readLine();
	  System.out.println("Please enter the second number");
	  integer2 = inFromUser.readLine();
	  System.out.println("Please enter a valid operator(+,-,*,/)");
	  operator = inFromUser.readLine();
	  //Send input
	  outToServer.writeBytes(integer1 + '\n');
	  outToServer.writeBytes(integer2 + '\n');
	  outToServer.writeBytes(operator + '\n');
	  modifiedSentence = inFromServer.readLine();
	  System.out.println("FROM SERVER: " + 
	          modifiedSentence);
	  System.out.println("Continue?(y/n)");
	  con = inFromUser.readLine();
	  if(con.toLowerCase().equals("y")) {
		  System.out.println("Continuing");
	  } else {
		  System.out.println("Closing Socket");
		  clientSocket.close();
		  System.exit(0);
	  }
  }
 }
}


