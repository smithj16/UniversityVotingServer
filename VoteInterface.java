import java.rmi.*;

public interface VoteInterface extends Remote {
  
    public void vote(int ans)
      throws java.rmi.RemoteException; 
    
    public  int voteCount(String option)
      throws java.rmi.RemoteException;
   
     public String processRequest(String message)
      throws java.rmi.RemoteException;
 
} //end interface