/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */
import java.io.*;
import java.util.*;
import acm.util.*;
public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		// You fill this in //
		try{
			BufferedReader FileReader = new BufferedReader(new FileReader(filename));
			while(true){
				String line = FileReader.readLine();
				if(line==null) break;
				String name = line.substring(0,line.indexOf(" "));
				String ranks = line.substring(line.indexOf(" "));
				Names.put(name, ranks);
			}
		   FileReader.close();
		}
		catch(IOException ex){
			throw new ErrorException(ex);
		}
	}
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		if(name!=null){ 
			name = correctName(name);
			if(Names.containsKey(name)) return new NameSurferEntry(name+Names.get(name));
		}
		return null;
	}
	private String correctName(String name){
		String NewName="";
		char firstLetter = name.charAt(0);
		if(Character.isLowerCase(firstLetter)) firstLetter=Character.toUpperCase(firstLetter);
		NewName += Character.toString(firstLetter);
		if(name.length()>1){
			for(int i = 1;i<name.length();i++){
				char ch = name.charAt(i);
				if(Character.isUpperCase(ch)) ch = Character.toLowerCase(ch);
				NewName += Character.toString(ch);
			}
		}
		return NewName;
	}
	private Map<String,String> Names = new HashMap<String,String>();
}

