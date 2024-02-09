/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program //Program
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		// You fill this in
		add(new JLabel("Name"),NORTH);
		NameText = new JTextField(TEXT_FIELD_SIZE);
		add(NameText,NORTH);
		add(new JButton("Add"),NORTH);
		add(new JButton("Delete"),NORTH);
		add(new JButton("Lookup"),NORTH);
		
		StatusText = new JTextField(TEXT_FIELD_SIZE);
		add(StatusText,WEST);
		add(new JButton("Change Status"),WEST);
		add(new JLabel(EMPTY_LABEL_TEXT),WEST);
		
		PictureText = new JTextField(TEXT_FIELD_SIZE);
		add(PictureText,WEST);
		add(new JButton("Change Picture"),WEST);
		add(new JLabel(EMPTY_LABEL_TEXT),WEST);
		
		FriendText = new JTextField(TEXT_FIELD_SIZE);
		add(FriendText,WEST);
		add(new JButton("Add Friend"),WEST);
		add(new JLabel(EMPTY_LABEL_TEXT),WEST);
		
		db = new FacePamphletDatabase();
		
		canvas = new FacePamphletCanvas();
		add(canvas);
		
		addActionListeners();
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	String cmd = e.getActionCommand();
    	String name = NameText.getText();    	
    	String friend = FriendText.getText();
    	if(! name.equals("") ){
    		ADLProfile(name,cmd);
    	}
    	String status = StatusText.getText();
    	if( (! status.equals(""))&& cmd.equals("Change Status") ){
    		if(isCurrentProfile){
    			db.getProfile(name).setStatus(status);
    			canvas.displayProfile(CurrentProfile);
    			canvas.showMessage("Status updated to "+status);
    		}
    		else canvas.showMessage("Please select a profile to change status");
    	}
    	String picture = PictureText.getText();
    	if((! picture.equals("")) && cmd.equals("Change Picture")){
    		if(isCurrentProfile){
    			if(pictureIsSet(picture,CurrentProfile)){
    				canvas.displayProfile(CurrentProfile);
    				canvas.showMessage("Picture updated");
    			}
    			else canvas.showMessage("Unable to open image file: "+picture);
    		}
    		else canvas.showMessage("Please select a profile to change picture");
    	}
    	if((! friend.equals("")) && cmd.equals("Add Friend")){
    		if(isCurrentProfile){
    			if(db.containsProfile(friend)){
    				if(CurrentProfile.addFriend(friend)){
    					db.getProfile(friend).addFriend(CurrentProfile.getName());
    					canvas.displayProfile(CurrentProfile);
    					canvas.showMessage(friend+" added as a friend");
    				}
    				else canvas.showMessage("Such a friend already exist");
    			}
    			else canvas.showMessage("There is no such a profile");	
    		} 
    		else canvas.showMessage("Please select a profile to add friends");
    	}
    }
    private void ADLProfile(String name, String command){
    	if(command.equals("Add")){
			if(! db.containsProfile(name)){ 
				CurrentProfile = new FacePamphletProfile(name);
				db.addProfile(CurrentProfile);
				canvas.displayProfile(CurrentProfile);
				canvas.showMessage("New profile created");
				isCurrentProfile = true;
				}
			else canvas.showMessage("A profile with the name "+name+" already exists");
		} 
		if(command.equals("Delete")){
			canvas.removeAll();
			if(db.containsProfile(name)) {
				db.deleteProfile(name);
				canvas.showMessage("Profile of "+name+" deleted");
			}
			else canvas.showMessage("A profile with name "+name+" does not exist");
			if(isCurrentProfile) isCurrentProfile = false;
		}
		if(command.equals("Lookup")){
			if(db.containsProfile(name)) {
				CurrentProfile = db.getProfile(name);
				canvas.displayProfile(CurrentProfile);
				canvas.showMessage("Displaying "+name);
				isCurrentProfile = true;
			}
			else{
				canvas.removeAll();
				canvas.showMessage("A profile with name "+name+" does not exist");
				isCurrentProfile = false;
			}
		}
    }
    private boolean pictureIsSet(String filename, FacePamphletProfile profile){
    	GImage picture = null;
    	try{
    		picture = new GImage(filename);
    		if(picture != null){
    			profile.setImage(picture);
    			return true;
    		}
    		else return false;
    	}catch(ErrorException ex){
    	}
    	return false;
    }
    private JTextField NameText,StatusText,PictureText,FriendText;
    private FacePamphletDatabase db;
    private boolean isCurrentProfile=false;
    private FacePamphletProfile CurrentProfile;
    private FacePamphletCanvas canvas;
}
