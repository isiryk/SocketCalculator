import java.io.*;
import java.net.*;
class UDPClient {
   public static void main(String args[]) throws Exception
   {
	  String integer1;
	  String integer2;
	  String operator;
	  String errorRate;
	  String con = "y";
	  String modifiedSentence;
	  while(con.toLowerCase().equals("y")){
	      BufferedReader inFromUser =
	         new BufferedReader(new InputStreamReader
	                     (System.in));
	      DatagramSocket clientSocket = new DatagramSocket();
	      InetAddress IPAddress = 
	                      InetAddress.getByName("127.0.0.1");
	      //Data structures for sending
	      byte[] sendData = new byte[1024];
	      byte[] receiveData = new byte[1024];
	      //Read and send integer 1
	      System.out.println("Please enter the first number");
	      integer1 = inFromUser.readLine();
	      sendData = integer1.getBytes();
	      DatagramPacket sendPacket1 =
	         new DatagramPacket(sendData, sendData.length, 
	                      IPAddress, 52001);
	      clientSocket.send(sendPacket1);
	      //Read and send integer 2
	      System.out.println("Please enter the second number");
	      integer2 = inFromUser.readLine();
	      sendData = integer1.getBytes();
	      DatagramPacket sendPacket2 =
	         new DatagramPacket(sendData, sendData.length, 
	                      IPAddress, 52001);
	      clientSocket.send(sendPacket2);
	      //Read and send integer 3
	      System.out.println("Please enter a valid operator(+,-,*,/)");
	      operator = inFromUser.readLine();
	      sendData = operator.getBytes();
	      DatagramPacket sendPacket3 =
	         new DatagramPacket(sendData, sendData.length, 
	                      IPAddress, 52001);
	      clientSocket.send(sendPacket3);
	      //This is for extra credit error handling
	      System.out.println("Please enter an error rate: (0.0 - 1.0; Default 0.5)");
	      errorRate = inFromUser.readLine();
	      sendData = errorRate.getBytes();
	      DatagramPacket sendPacket4 =
	         new DatagramPacket(sendData, sendData.length, 
	                      IPAddress, 52001);
	      clientSocket.send(sendPacket4);
	      //This is for receiving the UDP Packet
	      DatagramPacket receivePacket =
	         new DatagramPacket(receiveData,
	                      receiveData.length);
	      clientSocket.receive(receivePacket);
	      modifiedSentence =
	         new String(receivePacket.getData());  
	      System.out.println("FROM SERVER:" +
	                      modifiedSentence);
	      //This is for doing multiple equations
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


