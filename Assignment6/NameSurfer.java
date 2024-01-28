/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
		graph = new NameSurferGraph();
		add(graph);
		JLabel Name = new JLabel("Name ");
		add(Name,SOUTH);
		TextName = new JTextField(20);
		TextName.addActionListener(this);
		add(TextName,SOUTH);
		JButton Graph = new JButton("Graph");
		add(Graph,SOUTH);
		JButton Clear = new JButton("Clear");
		add(Clear,SOUTH);
		db = new NameSurferDataBase(NAMES_DATA_FILE);
		addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		String cmd = e.getActionCommand();
		if(cmd.equals("Graph")){
			//if(db.findEntry(TextName.getText())!=null)
			//println("Graph: "+db.findEntry(TextName.getText()).toString());
			//else println(TextName.getText()+" wasn't found. Try again.");
			if(db.findEntry(TextName.getText())!= null)
			graph.addEntry(db.findEntry(TextName.getText()));
		}
		else if(cmd.equals("Clear")){
			graph.clear();
		}
	}
	private JTextField TextName;
	private NameSurferDataBase db;
	private NameSurferGraph graph;
}

