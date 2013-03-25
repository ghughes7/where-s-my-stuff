package edu.gatech.cs2340.triggerhappycoders;

import android.util.Log;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.model.UserCollection;

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
        // user = new User(name, password, email);
        

    }
    
    public void findUser(String name){
        user = collection.getUser(name);
        Log.d("secruity",user.getName());
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
    
    public void unlockUser(String name){
    	findUser(name);
    	user.unLockedUser();
    	
    }

    public void checkAttempts(String email, String password) { // increments
                                                                // attempts when
                                                                // the username
                                                                // or password
                                                                // is wrong
        // and when the attempts are not equal to 3
        if ((user == null || !(user.getEmail().equals(email) || user.getPassword().equals(password))) && attempts < 3) {
            attempts++;
        } else {
            if (user != null) {
                user.lockedUser();
            }
            isLocked = true;
        }
    }
    
    public boolean checkMatch(String email, String password){
    	String x = "true";
        if(user!= null && user.getEmail().equals(email) && user.getPassword().equals(password)){
            Log.d("checkMatch returns true",x);
        	return true;
            
        }
        return false;
        
    }

   
}