package edu.gatech.cs2340.thc.model;
 
@SuppressWarnings("serial")
public class Admin extends User{
 
    public Admin(String name, String password, String email, boolean isLocked, boolean isAdmin) {
            super(name, password, email, isLocked, isAdmin);   
    }
    public boolean getIsAdmin(){
        return super.getIsAdmin();
    }
   
    public void lock(User u, UserCollection uc){
           u.lockedUser();
           deleteOldData(u, uc);
    }
   
    public void unLock(User u, UserCollection uc){
         	u.unLockedUser();
         	deleteOldData(u, uc);           
    }
  
    public void promoteToAdmin(User u, UserCollection uc){
    	u.setToAdmin();
    	deleteOldData(u, uc);
    }
    
    public void demoteToUser(User u, UserCollection uc) {
    	u.revokeAdmin();
    	deleteOldData(u,uc);
    }
   
    public void removeUser(User u,UserCollection uc){
    	uc.deleteUser(u.getEmail());
    }
    
    private void deleteOldData(User u, UserCollection uc){
    	uc.deleteUser(u.getEmail());//needs to delete all its old data
    	uc.addUser(u);//re-add to collection
    } 
}