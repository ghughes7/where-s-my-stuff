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

    public Security(UserCollection uc) {
        attempts = 0;
        isLocked = false;
        collection = uc;
        user = null;
    }
    
    /*
     * Finds the user
     */
    public void findUser(String name){
        user = collection.getUser(name);
        if(user != null){
        	Log.d("secruity",user.getName());	
        	Log.d("secruity", String.valueOf(user.getLockedStatus()));
        }  
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public boolean getIsLocked() {
        return isLocked;
    }
    
    /*
     * Unlocks a user's account
     */
    public void unlockUser(String name){
    	findUser(name);
    	user.unLockedUser();
    }

    /*
     * increments attempts when the user name or password is wrong
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
    
    public boolean checkMatch(String email, String password){    	
    	if(user!= null && user.getLockedStatus() == false && 
    			user.getEmail().equals(email) && user.getPassword().equals(password)){            
        	return true;
        }
        return false;      
    }
    
    public boolean isUserLocked(){
    	if(user != null){
    		return user.getLockedStatus();
    	}
    	return false;	
    }  
}