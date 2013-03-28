package edu.gatech.cs2340.thc.model;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.util.Log;

@SuppressWarnings("serial")
public class UserCollection implements Serializable {

	private List<User> userArray;
	private transient Context fileContext = null;

	public UserCollection(Context context) {
		fileContext = context;
		userArray = new ArrayList<User>();
		refillArray();
	}
	
	public void loadContext(Context fileContext) {
		this.fileContext = fileContext;
	}
	
	public void eraseTextFile(){//for testing purposes
		try {
			FileOutputStream fos = fileContext.getApplicationContext()
					.openFileOutput("UserCollection", Context.MODE_PRIVATE);
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
	}
	
	public int getNumOfUsers(){
		int numUsers = 0;
		try {
			
			int linesCount = 0;
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(fileContext.getApplicationContext()
							.openFileInput("UserCollection")));
			String inputString;	
			while ((inputString = inputReader.readLine()) != null) {
				linesCount++;
				if (linesCount % 5 == 0) {
					numUsers++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.d("numUsers",Integer.toString(numUsers));
		return numUsers;
	}
		
	public void setNumOfUsers(int num){
		String FILENAME = "NumberOfUsers";
		String number = Integer.toString(num);
		try {
			FileOutputStream fos = fileContext.getApplicationContext()
					.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.write(number.getBytes());	
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void refillArray(){
		int linesCount = 0;
		//for(int i = 0; i < getNumOfUsers(); i++){
			try {
				String FILENAME = "UserCollection";
				BufferedReader inputReader = new BufferedReader(
						new InputStreamReader(fileContext.getApplicationContext().
								openFileInput(FILENAME)));
					
				String inputString;
				ArrayList<String> info = new ArrayList<String>();
				boolean isLocked;
				boolean isAdmin;
				while ((inputString = inputReader.readLine()) != null) {
					info.add(inputString);
					linesCount++;
					if(linesCount % 5 == 0){
						if(info.get(3).equals("true")){
							isLocked = true;
						}
						else{
							isLocked = false;
						}
						if(info.get(4).equals("true")){
							isAdmin = true;
							
						}
						else{
							isAdmin = false;
						}
						userArray.add(new User(info.get(0),info.get(1),info.get(2), isLocked, isAdmin));
						//Log.d("refill array", info.get(0) + "\n" + info.get(1) + "\n" + info.get(2) + "\n"+ isLocked + "\n"+isAdmin);
						info.clear();
					}				
				}
			} //close try statement			
				catch (IOException e) {
				e.printStackTrace();
			}		
	 //}close for loop	
	}

	/*public void makeUpUsers() {

		
		userArray.add(new User("user1", "user1pw", "user1@gmail.com"));
		userArray.add(new User("user2", "user2pw", "user2@gmail.com"));
		userArray.add(new User("user3", "user3pw", "user3@gmail.com"));
		
		String FILENAME = "UserCollection";

		String name = "name: ";
		String email = "email: ";
		String password = "password: ";

		// erase the file first
		try {
			FileOutputStream fos = fileContext.getApplicationContext()
					.openFileOutput(FILENAME, Context.MODE_PRIVATE);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		// then re-add fake users
		for (int i = 0; i < getNumOfUsers(); i++) {
			try {
				FileOutputStream fos = fileContext.getApplicationContext()
						.openFileOutput(FILENAME, Context.MODE_APPEND);
				
				fos.write(userArray.get(i).getName().getBytes());
				fos.write(System.getProperty("line.separator").getBytes());
				Log.d("name: " , userArray.get(i).getName());
				
				fos.write(userArray.get(i).getPassword().getBytes());
				fos.write(System.getProperty("line.separator").getBytes());
				Log.d("pw: " , userArray.get(i).getPassword());
				
				fos.write(userArray.get(i).getEmail().getBytes());
				fos.write(System.getProperty("line.separator").getBytes());
				Log.d("email: " , userArray.get(i).getEmail());
				
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	
	}
*/
	public User getUser(String email) {
		try {
			int index = 0;
			int count = 0;		
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(fileContext.getApplicationContext()
							.openFileInput("UserCollection")));		
			String inputString;
			while ((inputString = inputReader.readLine()) != null) {	
				if (inputString.equals(email)) {
					return userArray.get(index);					
				}
				count++;
				if (count % 5 == 0) {
					index++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return null;
		// return userArray.get(0);
	}

	public void addUser(User u) {
		userArray.add(u);
		int incrementNum = getNumOfUsers() + 1;
		setNumOfUsers(incrementNum);
		saveToFile(fileContext, u);
	}
	
	public void deleteUser(String email){	
		/*
		for (int i = 0; i < getNumOfUsers(); i++) {
			if (email.equals(userArray.get(i).getEmail())) {
				userArray.remove(i);
			}
		}
		*/
		
		//remove from text file
		try {//this is the copy text file on which the old text file will be copied onto
			FileOutputStream fosUserCollectionCopy = fileContext.getApplicationContext()
					.openFileOutput("UserCollectionCopy", Context.MODE_PRIVATE);
		} catch (IOException e){
			e.printStackTrace();
		}
		
		try {
			int count = 0;
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(fileContext.getApplicationContext()
							.openFileInput("UserCollection")));
			
			FileOutputStream fosUserCollectionCopy = fileContext.getApplicationContext()
					.openFileOutput("UserCollectionCopy", Context.MODE_PRIVATE);
			
			String inputString;
			
			ArrayList<String> info = new ArrayList<String>();
			
			while ((inputString = inputReader.readLine()) != null) {
				count++;
				info.add(inputString);
				if(count % 3 == 0){
					if (!inputString.equals(email)) {//if they're not the user to be removed
						inputString = inputReader.readLine();//get the isLocked
						info.add(inputString);
						inputString = inputReader.readLine();//get the isAdmin
						info.add(inputString);
						fosUserCollectionCopy.write(info.get(0).getBytes());
						fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
						fosUserCollectionCopy.write(info.get(1).getBytes());
						fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
						fosUserCollectionCopy.write(info.get(2).getBytes());
						fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
						fosUserCollectionCopy.write(info.get(3).getBytes());
						fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
						fosUserCollectionCopy.write(info.get(4).getBytes());
						fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
					}
					else{
						inputString = inputReader.readLine();
						inputString = inputReader.readLine();
					}
					info.clear();
				}
			}
			fosUserCollectionCopy.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		try {
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(fileContext.getApplicationContext()
							.openFileInput("UserCollectionCopy")));
			FileOutputStream fos0 = fileContext.getApplicationContext()
					.openFileOutput("UserCollection", Context.MODE_PRIVATE);
			FileOutputStream fos = fileContext.getApplicationContext()
					.openFileOutput("UserCollection", Context.MODE_APPEND);
			
			String inputString;
			while ((inputString = inputReader.readLine()) != null) {
				fos.write(inputString.getBytes());
				fos.write(System.getProperty("line.separator").getBytes());
				//Log.d("inputString", inputString);	
			}
			fos0.close();
			fos.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}	
		refillArray();		
	}//end deleteUser() method
	
	public List<User> getUserList(){
		return userArray;
	}

	/*
	 * public void numOfUsers(){ String FILENAME = "NumOfUsers";
	 * FileOutputStream fos =
	 * fileContext.getApplicationContext().openFileOutput(FILENAME,
	 * Context.MODE_PRIVATE); fos.write(num.getBytes); }
	 */

	public void saveToFile(Context fileContext, User user) {
		String FILENAME = "UserCollection";
		
		String name = "name: ";
		String email = "email: ";
		String password = "password: ";

		try {
			FileOutputStream fos = fileContext.getApplicationContext()
					.openFileOutput(FILENAME, Context.MODE_APPEND);
			// change this to MODE_APPEND later to not erase over the file
			// everytime
			// fos.write(name.getBytes());
			fos.write(user.getName().getBytes());
			fos.write(System.getProperty("line.separator").getBytes());
			// fos.write(email.getBytes());
			fos.write(user.getPassword().getBytes());
			fos.write(System.getProperty("line.separator").getBytes());
			// fos.write(password.getBytes());
			fos.write(user.getEmail().getBytes());
			fos.write(System.getProperty("line.separator").getBytes());
			
			
			fos.write(String.valueOf(user.getLockedStatus()).getBytes());
			fos.write(System.getProperty("line.separator").getBytes());
			
			fos.write(String.valueOf(user.getIsAdmin()).getBytes());
			fos.write(System.getProperty("line.separator").getBytes());
			
			fos.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}