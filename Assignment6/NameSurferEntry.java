/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;
//import acm.program.*;//

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		// You fill this in //
		int Begin = 0;
		int End = line.indexOf(" ");
		Name=line.substring(Begin,End);
		ranks = new int[NDECADES];
		for(int i = 0; i < NDECADES-1; i++){
			Begin = End+1;
			End=line.indexOf(" ",Begin);
			int value = Integer.parseInt(line.substring(Begin, End));
			ranks[i]=value;
		}
		ranks[NDECADES-1]=Integer.parseInt(line.substring(End+1));
	}
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		return Name;
	}
/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation //
		return ranks[decade];
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		String Entry=Name+" [";
		for(int i=0;i<NDECADES-1;i++){
			Entry+=Integer.toString(ranks[i])+" ";
		}
		return Entry+Integer.toString(ranks[10])+"]";
	}
	private String Name;
	private int [] ranks;
}

