package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Folder implements Comparable<Folder>, Serializable{

	private ArrayList<Note> notes;
	private String name;
	private static final long serialVersionUID = 1L;

	public Folder(String name){
		this.name = name;
		notes = new ArrayList<Note>();
	}

	public void addNote(Note note){
		notes.add(note);
	}

	public String getName(){
		return name;
	}

	public ArrayList<Note> getNotes(){
		return notes;
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;

		 for (Note n : notes){
			 if(n instanceof TextNote)
				 ++nText;
			 else
				 ++nImage;
	       }

		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Folder))
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public int compareTo(Folder o) {
		int result = name.compareTo(o.getName());

		if (result == 0)
			return 0;
		else if (result > 0)
			return 1;
		else
			return -1;
	}

	public void sortNotes() {
		Collections.sort(notes);
	}

	public List<Note> searchNotes(String keywords) {
		List<Note> result = new ArrayList<Note>();

		String[] keysplit = keywords.toLowerCase().split(" "); int i = 0;
		String[] key = new String[100];
		for (String k : keysplit){
			if (!k.equalsIgnoreCase("or")){
				key[i++] = k;
			}
		}

		for (Note n : notes){
			boolean added = false;
			if(n instanceof TextNote){
				String c1 = ((TextNote) n).getContent().toLowerCase(); // Content Handling

				if (c1.contains(keywords.toLowerCase())){
					result.add(n); added = true;
				}
				if (!added){
					// Title Handling
					if ( n.getTitle().toLowerCase().contains(keywords.toLowerCase()) ){
								result.add(n);
							}
				}
			}
			else{ // ImageNote
				if ( (n.getTitle().toLowerCase().contains(key[0]) ||
					  n.getTitle().toLowerCase().contains(key[1])) &&
					 (n.getTitle().toLowerCase().contains(key[2]) ||
					  n.getTitle().toLowerCase().contains(key[3])) ){
						result.add(n);
					}
			}
		}

		return result;
	}

}
