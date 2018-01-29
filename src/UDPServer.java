import java.io.*;
import java.net.*;
class UDPServer {
   static UDPServer object = new UDPServer();
   static String error;
   public static void main(String args[]) throws Exception
      {
	   
         DatagramSocket serverSocket = new
                              DatagramSocket(52001);
            byte[] receiveData1 = new byte[1024];
            byte[] receiveData2 = new byte[1024];
            byte[] receiveData3 = new byte[1024];
            byte[] receiveData4 = new byte[1024];
            byte[] sendData = new byte[1024];
            while(true)
               {
            	  //Packet 1
                  DatagramPacket receivePacket1 =
                     new DatagramPacket(receiveData1, 
                              receiveData1.length);
                  serverSocket.receive(receivePacket1);
                  String integer1 = new String(
                              receivePacket1.getData());
                  InetAddress IPAddress =
                              receivePacket1.getAddress();
                  int port1 = receivePacket1.getPort();
                  //Packet 2
                  DatagramPacket receivePacket2 =
                          new DatagramPacket(receiveData2, 
                                   receiveData2.length);
                       serverSocket.receive(receivePacket2);
                       String integer2 = new String(
                                   receivePacket2.getData());
                  //Packet 3
	              DatagramPacket receivePacket3 =
	                      new DatagramPacket(receiveData3, 
	                               receiveData3.length);
	                   serverSocket.receive(receivePacket3);
	                   String operator = new String(
	                               receivePacket3.getData());
                  //Packet 4
                   DatagramPacket receivePacket4 =
                           new DatagramPacket(receiveData4, 
                                    receiveData4.length);
                        serverSocket.receive(receivePacket4);
                        String errorRate = new String(
                                    receivePacket4.getData());
                  //END OF PACKET RECEIVING
                  System.out.println("I GOT HERE");
                  System.out.println(integer1);
                  System.out.println(integer2);
                  System.out.println(operator);
                  System.out.println(errorRate);
                  System.out.println("I GOT HERE");
                  String capitalizedSentence = 
                              "apple";
                  sendData = capitalizedSentence.
                              getBytes();
                  DatagramPacket sendPacket =
                  new DatagramPacket(sendData,
                           sendData.length, 
                              IPAddress, port1);
                  serverSocket.send(sendPacket);
               }
      }
   
   public String parser(String integer1, String integer2, String operator) {
		 String ans;
	  	 if(operationCheck(integer1, integer2, operator)) {
	  		 if(operator.equals("+")) {
	  			 ans = object.addition(integer1, integer2);
	  			 return ans;
	  		 } else if (operator.equals("-")) {
	  			 ans = object.subtraction(integer1, integer2);
	  			 return ans;
	  		 } else if (operator.equals("*")) {
	  			 ans = object.multiplication(integer1, integer2);
	  			 return ans;
	  		 } else if (operator.equals("/")) {
	  			 ans = object.division(integer1, integer2);
	  			 return ans;
	  		 } else {
	  			 error = "Status Code 400 -- Server Error -- Result: -1";
	  			 return error;
	  		 }
	  	 } else {
	  		 return error;
	  	 }
	   }
	   
	   public boolean operationCheck(String integer1, String integer2, String operator) {
		   if(integer1.equals(null) || integer2.equals(null)) {
			   error = "Invalid numbers -- Error code 300: Result -1";
			   return false;
		   } else if (!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/")) {
			   error = "Invalid operator -- Error code 300: Result -1";
			   return false;
		   } else if (operator.equals("/") && integer2.equals("0")) {
			   error = "Dividing by 0 -- Error code 300: Result -1";
			   return false;
		   } else {
			   return true;
		   }
	   }
	   
	   public String addition(String integer1, String integer2) {
		   int operator = Integer.parseInt(integer1);
		   int operand = Integer.parseInt(integer2);
		   int ans = operator + operand;
		   String ret = Integer.toString(ans);
		   return ret;
	   }
	   
	   public String subtraction(String integer1, String integer2) {
		   int operator = Integer.parseInt(integer1);
		   int operand = Integer.parseInt(integer2);
		   int ans = operator - operand;
		   String ret = Integer.toString(ans);
		   return ret;
	   }
	   
	   public String multiplication(String integer1, String integer2) {
		   int operator = Integer.parseInt(integer1);
		   int operand = Integer.parseInt(integer2);
		   int ans = operator * operand;
		   String ret = Integer.toString(ans);
		   return ret;
	   }
	   
	   public String division(String integer1, String integer2) {
		   int operator = Integer.parseInt(integer1);
		   int operand = Integer.parseInt(integer2);
		   double ans = (double)operator / (double)operand;
		   String ret = Double.toString(ans);
		   return ret;
	   }
}


