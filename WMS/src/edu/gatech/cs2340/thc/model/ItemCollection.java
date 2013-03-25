package edu.gatech.cs2340.thc.model;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import android.content.Context;
import android.util.Log;

public class ItemCollection {
    
    private ArrayList<Item> itemArray;//contains all items created by all users
    private Context fileContext;
    private User user;
    
    public ItemCollection(Context fc, User user){
        itemArray = new ArrayList<Item>(); 
        //makeUpItems();
        
        fileContext = fc;
        this.user = user;
        
        //eraseTextFile();
        refillArray();
    }
    
    public void setUser(User u){
        user = u;
    }
    
    public void eraseTextFile(){//for testing purposes
        try {
            FileOutputStream fos = fileContext.getApplicationContext()
                    .openFileOutput("ItemCollection", Context.MODE_PRIVATE);
        } catch (IOException e1) {
            e1.printStackTrace();
        }    
    }
    
    /*
    public void makeUpItems(){
        itemArray.add(new Item("item1","item1 des", "1", "lost","user1"));
        itemArray.add(new Item("item2","item2 des", "2", "lost","user2"));
        itemArray.add(new Item("item3","item3 des", "3", "lost","user3"));
    }
    */
    
    public void refillArray(){
        int linesCount = 0;
        Log.d("in refillArray() num", Integer.toString(getNumOfUserItems()));
        
        //for(int i = 0; i < getNumOfUserItems(); i++){
            try {
                String FILENAME = "ItemCollection";
                BufferedReader inputReader = new BufferedReader
                        (new InputStreamReader(fileContext.getApplicationContext().openFileInput(FILENAME)));
                    
                String inputString;
                ArrayList<String> info = new ArrayList<String>();

                while ((inputString = inputReader.readLine()) != null) {
                    info.add(inputString);
                    linesCount++;
                    if(linesCount % 5 == 0){
                        //itemArray.add(new Item(info.get(1),info.get(2),info.get(3), info.get(4), info.get(0)));
                        info.clear();
                    }            
                }
            } //close try statement
            
            catch (IOException e) {
                e.printStackTrace();
            }
        //}
        Log.d("in refill method", Integer.toString(itemArray.size()));
    }
    
    /*
    public void setNumOfItems(int num){
        String FILENAME = "NumberOfItems";
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
    */
    
    public int getNumOfAllItems(){
        int numItems = 0;
        int linesCount = 0;
        try {
            BufferedReader inputReader = 
            new BufferedReader(new InputStreamReader(fileContext.getApplicationContext().
            openFileInput("ItemCollection")));
                
            String inputString;
            
            while ((inputString = inputReader.readLine()) != null) {
                linesCount++;
                if (linesCount % 5 == 0) {
                    numItems++;
                }
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        Log.d("numItems",Integer.toString(numItems));
        return numItems;
    }
    
    public int getNumOfUserItems(){
        int numItems = 0;
        try {
            BufferedReader inputReader = 
            new BufferedReader(new InputStreamReader(fileContext.getApplicationContext().
            openFileInput("ItemCollection")));
                
            String inputString;
            
            while ((inputString = inputReader.readLine()) != null) {
                if(inputString.equals(user.getEmail())){
                    numItems++;
                }
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("numItems",Integer.toString(numItems));
        return numItems;
    }
    
    public void addItem(Item i){
        if(i == null){
            //throw exception
        }
        else{
            itemArray.add(i);    
            addToTextFile(i);
        }
    }
    
    public void deleteItem(Item i){
        for(Item item: itemArray){
            if(item.equals(i)){
                itemArray.remove(i);        
            }    
        }
    }
    
    public ArrayList<Item> getItemsOfUser(String owner){
        ArrayList<Item> userItems = new ArrayList<Item>();
        //Log.d("itemCollection size ", Integer.toString(itemArray.size()));
        for(int i = 0; i < itemArray.size(); i++){
            if(itemArray.get(i).getOwner().equals(owner)){
                userItems.add(itemArray.get(i));    
            }
        }
        return userItems;
    }
    
    public void addToTextFile(Item item){
        Log.d("adding to file", item.getItemName());
        String FILENAME = "ItemCollection";
        
        String owner = "owner: ";
        String itemName = "ItemName: ";
        String itemDes = "ItemDescription: ";
        String reward = "Reward: ";
        String type = "Type: ";
        
        try{
            FileOutputStream fos = fileContext.getApplicationContext().openFileOutput(FILENAME, Context.MODE_APPEND);
            
            fos.write(item.getOwner().getBytes());
            fos.write(System.getProperty("line.separator").getBytes());
    
            fos.write(item.getItemName().getBytes());
            fos.write(System.getProperty("line.separator").getBytes());
            
            fos.write(item.getItemDes().getBytes());
            fos.write(System.getProperty("line.separator").getBytes());
            
            fos.write(item.getReward().getBytes());
            fos.write(System.getProperty("line.separator").getBytes());
            
            fos.write(item.returnType().getBytes());
            fos.write(System.getProperty("line.separator").getBytes());
            fos.close();
        }
        catch(IOException e1){
            e1.printStackTrace();
        }        
    }
    
    public ArrayList<Item> getItemsArray(){
        return itemArray;
    }
    
}