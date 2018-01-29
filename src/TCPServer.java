import java.io.*;
import java.net.*;
class TCPServer {
	
   static String error;
   static TCPServer object = new TCPServer();
   
   public static void main(String argv[]) throws Exception
      {
         String integer1;
         String integer2;
         String operator;
         String answer;
         String response;
         
         //Step 1: Open Server
         ServerSocket welcomeSocket = new ServerSocket(52000);

         while(true) {
        	//Step 2: Listen to Socket 
            Socket connectionSocket = welcomeSocket.
            accept();
            BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(
                  connectionSocket.getInputStream()));
            DataOutputStream outToClient =
               new DataOutputStream(
                  connectionSocket.getOutputStream());
            //Step 3: Receive Information From Client
            //You will receive line by line integer 1, integer 2, and string operator
            integer1 = inFromClient.readLine();
            integer2 = inFromClient.readLine();
            operator = inFromClient.readLine();
            //find the answer
            answer = object.parser(integer1, integer2, operator);
            if(object.operationCheck(integer1, integer2, operator)) {
            	response = "Status Code 200 -- Success -- Answer: " + answer + "\n";
            	outToClient.writeBytes(response);
            }
            else {
            	response = error + "\n";
            	outToClient.writeBytes(response);
            }
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



