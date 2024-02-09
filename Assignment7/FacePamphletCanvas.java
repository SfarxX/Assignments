/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
		ApplicationMessage = new GLabel("");
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		// You fill this in
		ApplicationMessage.setLabel(msg);
		ApplicationMessage.setFont(MESSAGE_FONT);
		ApplicationMessage.setLocation(getWidth()/4+ApplicationMessage.getDescent()/2, getHeight()-BOTTOM_MESSAGE_MARGIN);
		add(ApplicationMessage);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		// You fill this in
		removeAll();
		
		String name = profile.getName();
		displayName(name);
		
		GImage image = profile.getImage();
		displayImage(image);
		
		String status = profile.getStatus();
		displayStatus(status, name);
		
		Iterator<String> friends = profile.getFriends();
		displayFriends(friends);
	}
	private void displayName(String name){
		GLabel profileName = new GLabel(name,LEFT_MARGIN,TOP_MARGIN);
		profileName.setFont(PROFILE_NAME_FONT);
		profileName.setColor(Color.BLUE);
		add(profileName);
	}
	private void displayImage(GImage image){
		if(image == null) drawEmptyRect();
		else{
			double widthScale = IMAGE_WIDTH/image.getWidth();
			double heightScale = IMAGE_HEIGHT/image.getHeight();
			image.scale(widthScale,heightScale);
			image.setLocation(LEFT_MARGIN,TOP_MARGIN+IMAGE_MARGIN);
			add(image);
		}
	}
	private void displayStatus(String status, String name){
		GLabel profileStatus;
		if(status==null) profileStatus = new GLabel("No current status");
		else profileStatus = new GLabel(name+" is "+status);
		profileStatus.setFont(PROFILE_STATUS_FONT);
		profileStatus.setLocation(LEFT_MARGIN,TOP_MARGIN+ STATUS_MARGIN+IMAGE_MARGIN+IMAGE_HEIGHT);
		add(profileStatus);
	}
	private void displayFriends(Iterator<String> it){
		GLabel header = new GLabel("Friends:");
		header.setFont(PROFILE_FRIEND_LABEL_FONT);
		header.setLocation(getWidth()/2,IMAGE_MARGIN);
		add(header);
		
		for(int i = 0; it.hasNext() ; i++){
			GLabel friend = new GLabel(it.next());
			friend.setFont(PROFILE_FRIEND_FONT);
			double height = IMAGE_MARGIN+header.getAscent()+i*friend.getAscent();
			friend.setLocation(getWidth()/2, height);
			add(friend);
		}
	}
	private void drawEmptyRect(){
		GLine leftBorder = new GLine(LEFT_MARGIN,TOP_MARGIN+IMAGE_MARGIN,LEFT_MARGIN,TOP_MARGIN+IMAGE_HEIGHT+IMAGE_MARGIN);
		add(leftBorder);
		
		GLine rightBorder = new GLine(LEFT_MARGIN + IMAGE_WIDTH, TOP_MARGIN+ IMAGE_MARGIN, LEFT_MARGIN + IMAGE_WIDTH, TOP_MARGIN+ IMAGE_MARGIN + IMAGE_HEIGHT);
		add(rightBorder);
		
		GLine upperBorder = new GLine(LEFT_MARGIN, TOP_MARGIN+ IMAGE_MARGIN, LEFT_MARGIN + IMAGE_WIDTH, TOP_MARGIN+ IMAGE_MARGIN);
		add(upperBorder);
		
		GLine bottomBorder = new GLine(LEFT_MARGIN, TOP_MARGIN+ IMAGE_MARGIN + IMAGE_HEIGHT, LEFT_MARGIN + IMAGE_WIDTH, TOP_MARGIN+ IMAGE_MARGIN + IMAGE_HEIGHT);
		add(bottomBorder);
		
		GLabel empty = new GLabel("No Image",LEFT_MARGIN+(IMAGE_WIDTH/4),TOP_MARGIN + IMAGE_MARGIN+(IMAGE_HEIGHT/2));
		empty.setFont(PROFILE_NAME_FONT);
		add(empty);
	}
	private GLabel ApplicationMessage;
	
}
