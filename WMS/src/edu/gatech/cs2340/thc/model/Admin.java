package edu.gatech.cs2340.thc.model;

/**
 * This is the Admin class it follows the Liskov Substitution principle. Defines
 * what it means to be an administrator. Admins can lock, delete, promote and
 * demote other users.
 * 
 * @author Trigger Happy Coders
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Admin extends User {
	/**
	 * Admin constructor, identical to User constructor
	 * 
	 * @see edu.gatech.cs2340.thc.model.User.java
	 * 
	 */
	public Admin(String name, String password, String email, boolean isLocked,
			boolean isAdmin) {
		super(name, password, email, isLocked, isAdmin);
	}

	/**
	 * @see edu.gatech.cs2340.thc.model.User.java
	 */
	public boolean getIsAdmin() {
		return super.getIsAdmin();
	}

	/**
	 * Is called by AdminProfileActivity to lock a user out of the system.
	 * 
	 * @param u
	 *            - User user that will undergo the change
	 * @param uc
	 *            - UserCollection collection of all registered user's
	 */
	public void lock(User u, UserCollection uc) {
		u.lockedUser();
		deleteOldData(u, uc);
	}

	/**
	 * Is called by AdminProfileActivity to unlock user's account.
	 * 
	 * @param u
	 *            - User user that will undergo the change
	 * @param uc
	 *            - UserCollection collection of all registered user's
	 */
	public void unLock(User u, UserCollection uc) {
		u.unLockedUser();
		deleteOldData(u, uc);
	}

	/**
	 * Is called by AdminProfileActivity to promote user's account.
	 * 
	 * @param u
	 *            - User user that will undergo the change
	 * @param uc
	 *            - UserCollection collection of all registered user's
	 */
	public void promoteToAdmin(User u, UserCollection uc) {
		u.setToAdmin();
		deleteOldData(u, uc);
	}

	/**
	 * Is called by AdminProfileActivity to demote user's account.
	 * 
	 * @param u
	 *            - User user that will undergo the change
	 * @param uc
	 *            - UserCollection collection of all registered users
	 */
	public void demoteToUser(User u, UserCollection uc) {
		u.revokeAdmin();
		deleteOldData(u, uc);
	}

	/**
	 * Is called by AdminProfileActivity to remove user's account.
	 * 
	 * @param u
	 *            - User user that will undergo the change
	 * @param uc
	 *            - UserCollection collection of all registered users
	 */
	public void removeUser(User u, UserCollection uc) {
		uc.deleteUser(u.getEmail());
	}

	/**
	 * Delete's old information about the users to keep the persistence files
	 * clean.
	 * 
	 * @param u
	 *            - User user that will undergo the change
	 * @param uc
	 *            - UserCollection collection of all registered users
	 */
	private void deleteOldData(User u, UserCollection uc) {
		uc.deleteUser(u.getEmail());// needs to delete all its old data
		uc.addUser(u);// re-add to collection
	}
}