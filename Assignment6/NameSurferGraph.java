/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
	}
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//	 You fill this in //
		Entries.clear();
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		if(!Entries.contains(entry)) Entries.add(entry);
		update();
	}
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		//	 You fill this in //
		removeAll();
		buildGrid(); 
		drawEntries();
	}
	private void buildGrid(){
		GLine bottom_border = new GLine(0,getHeight()-GRAPH_MARGIN_SIZE,getWidth(),getHeight()-GRAPH_MARGIN_SIZE);
		add(bottom_border);
		GLine upper_border = new GLine(0,GRAPH_MARGIN_SIZE,getWidth(),GRAPH_MARGIN_SIZE);
		add(upper_border);
		for(int i=0;i<NDECADES;i++){
			add(new GLine(i*(getWidth()/NDECADES),0,i*(getWidth()/NDECADES),getHeight()));
			String decade = Integer.toString(10*i+START_DECADE);
			add(new GLabel(decade, i*(getWidth()/NDECADES)+1, getHeight()-5));
		}
	}
	private void drawEntries(){
		if(! Entries.isEmpty()){
			for(int i = 0; i < Entries.size(); i++){
				Color EntryColor = choseColor(i+1);
				for(int j = 0; j < NDECADES-1; j++){
					double x1,x2,y1,y2;
					x1=j*(getWidth()/NDECADES);
					y1=getY(Entries.get(i).getRank(j));
					x2=(j+1)*(getWidth()/NDECADES);
					y2=getY(Entries.get(i).getRank(j+1));
					GLine Data = new GLine(x1,y1,x2,y2);
					Data.setColor(EntryColor);
					add(Data);
					GLabel Name1,Name2;
					if(Entries.get(i).getRank(j) != 0) Name1 = new GLabel(Entries.get(i).getName()+" "+Entries.get(i).getRank(j),x1,y1);
					else Name1 = new GLabel(Entries.get(i).getName()+"*",x1,y1);
					Name1.setColor(EntryColor);
					add(Name1);
					if(Entries.get(i).getRank(j) != 0) Name2 = new GLabel(Entries.get(i).getName()+" "+Entries.get(i).getRank(j+1),x2,y2);
					else Name2 = new GLabel(Entries.get(i).getName()+"*",x2,y2);
					Name2.setColor(EntryColor);
					add(Name2);
				}
			}
		}
	}
	private double getY(double rank){
		if(rank==0) return getHeight()-GRAPH_MARGIN_SIZE;
		else{
			rank = (rank/MAX_RANK)*(getHeight()-2*GRAPH_MARGIN_SIZE)+GRAPH_MARGIN_SIZE;
			return rank;
		}
	}
	private Color choseColor(int number){
		if(number%4==0) return Color.MAGENTA;
		if(number%4==1) return Color.BLACK;
		if(number%4==2) return Color.RED;
		if(number%4==3) return Color.BLUE;
		return Color.BLACK;
	}
	private ArrayList<NameSurferEntry> Entries = new ArrayList<NameSurferEntry>();
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
