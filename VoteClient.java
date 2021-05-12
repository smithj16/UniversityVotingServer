import java.io.*;
import java.rmi.*;


/*
   Implementation of voting client
*/ 

public class VoteClient {

   public static void main(String args[]) {
      try {
         boolean done = false; 
         int RMIPort;         
         String hostName, voteCount; 
         String endMessage = "."; 
         InputStreamReader is = new InputStreamReader(System.in);
         BufferedReader br = new BufferedReader(is);
         System.out.println("Enter the RMIRegistry host namer:");
         hostName = br.readLine();
         System.out.println("Enter the RMIregistry port number:");
         String portNum = br.readLine();
         RMIPort = Integer.parseInt(portNum);
         String registryURL = 
            "rmi://" + hostName+ ":" + portNum + "/hello";  
         // find the remote object and cast it to an interface object
         VoteInterface v =
           (VoteInterface)Naming.lookup(registryURL);
         System.out.println("Lookup completed " );
         // invoke the remote method
         String message = v.processRequest("I want to vote.");
         System.out.println(message + "\n");
         
         // Record user input 
         String vote = br.readLine(); 
         System.out.println(v.processRequest(vote)); 
         
         //Optional Messages
         while (!done) {
            System.out.println("Would you like the record count of: \n");
            System.out.println(" Enter y for Yes \n");
            System.out.println(" Enter n for No \n");
            System.out.println(" Enter dc for Dont-Care\n");
            System.out.println("Enter a period to end session.");
            //read user input
            message = br.readLine( );
            if ((message.trim()).equals (endMessage)){
               done = true;
            }
            else {
               message = message.trim();
               voteCount = v.processRequest(message);
               System.out.println("\n/////////////////////////////////////////////\n");
               System.out.println("The number of votes are: " + voteCount);
               System.out.println("/////////////////////////////////////////////\n");
            }
          } // end while
         
      } // end try 
      catch (Exception e) {
         System.out.println("Exception in HelloClient: " + e);
      } 
   } //end main
}//end class
