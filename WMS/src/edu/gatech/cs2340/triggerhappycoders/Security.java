package edu.gatech.cs2340.triggerhappycoders;

import android.util.Log;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.model.UserCollection;

/**
 * Keeps a count on how many times a user tried to log in during one session 
 * and locks their account if their number of attempts is greater than three 
 * 
 * @author Trigger Happy Coders
 *
 */
public class Security {
    private int attempts;
    private boolean isLocked;
    private UserCollection collection;
    private User user;
    
    /**
	* This constructor takes in UserCollection uc and initializes the variables.
	* 
	* @param Usercollection uc
	*/

    public Security(UserCollection uc) {
        attempts = 0;
        isLocked = false;
        collection = uc;
        user = null;
    }
    
    /**
	* This method gets the user from the user collection.
	* 
	* @param name - String the users name
	*/
    
    public void findUser(String name){
        user = collection.getUser(name);
        if(user != null){
        	Log.d("secruity",user.getName());	
        	Log.d("secruity", String.valueOf(user.getLockedStatus()));
        }  
    }
    
    /**
	* This method sets the amount of attempts a person uses to log in
	* 
	* @param attempts - int the number of attempts
	*/

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
    
    /**
	* This method gets the amount of attempts a person uses to log in
	* 
	* @return attempts
	*/

    public int getAttempts() {
        return attempts;
    }
    
    /**
	* This method sets the boolean isLocked
	* 
	* @param isLocked - boolean 
	*/

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }
    
    /**
	* This method returns the boolean isLocked
	* 
	* @return isLocked
	*/

    public boolean getIsLocked() {
        return isLocked;
    }
    
    /**
	* This method unlocks the users account
	* 
	* @param name - String the users name 
	*/
    public void unlockUser(String name){
    	findUser(name);
    	user.unLockedUser();
    }
    
    /**
	* This method increments attempts when the user name or password is wrong
	* 
	* @param email - String the users email 
	* @param password - String the users password
	*/
    public void checkAttempts(String email, String password) {
        // and when the attempts are not equal to 3
        if (attempts < 2) {
            attempts++;
        } 
        else {
            if (user != null) {
                user.lockedUser();
            }
            isLocked = true;
        }
    }
    
    /**
	* This method returns true if the users email and password has a match
	* 
	* @param email - String the users email 
	* @return true - boolean if there is a match
	* @return false - boolean if there not is a match
	*/
    
    public boolean checkMatch(String email, String password){    	
    	if(user!= null && user.getLockedStatus() == false && 
    			user.getEmail().equals(email) && user.getPassword().equals(password)){            
        	return true;
        }
        return false;      
    }
    
    /**
	* This method unlocks the users account
	* 
	* @return user.getLockedStatus 
	* @return false - boolean if user is not locked
	*/
    
    public boolean isUserLocked(){
    	if(user != null){
    		return user.getLockedStatus();
    	}
    	return false;	
    }  
}