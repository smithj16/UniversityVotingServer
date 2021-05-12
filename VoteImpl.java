import java.rmi.*;
import java.rmi.server.*;

public class VoteImpl extends UnicastRemoteObject
     implements VoteInterface {
     
     int yesCount = 0;
     int noCount = 0;
     int dontCareCount = 0;
  
   public VoteImpl() throws RemoteException {
      super( );
   }
  
  
   public void vote(int ans) throws RemoteException{
        if(ans == 1)
            yesCount += 1;
        else if(ans == 2)
            noCount += 1;
        else if(ans == 3)
            dontCareCount += 1;
    }
    
       public int voteCount(String option) throws RemoteException{
        if(option.equals("y"))
            return yesCount;
        else if(option.equals("n"))
            return noCount;
        else if(option.equals("dc"))
            return dontCareCount;

        return -1;
    }
    
    
        public String processRequest(String message) throws RemoteException{

        if(message.equals("I want to vote.")){
            //Send vote options
            return "Vote(1) yes, (2) no, (3) don't care";
        }
        else if (message.equals("1") || message.equals("2") || message.equals("3")){
           vote(Integer.parseInt(message));
           return "";
        }
        else if (message.equals("y") || message.equals("n") || message.equals("dc")){
            int countValue = voteCount(message);
            return String.valueOf(countValue);
        }

        return "Not a valid request";

    }
   
} // end class