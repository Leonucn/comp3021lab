package base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook implements Serializable{

	private ArrayList<Folder> folders;
	private static final long serialVersionUID = 1L;

	public NoteBook(){
		folders = new ArrayList<Folder>();
	}

	public NoteBook(String file){
		try{
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook) in.readObject();
			this.folders = n.getFolders(); // get folders
			in.close();
			fis.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName,note);
	}
	// Overloading method createTextNote
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName,note);
	}

	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName,note);
	}

	public ArrayList<Folder> getFolders(){
		return folders;
	}

	public boolean insertNote(String folderName, Note note) {

		Folder f = null;
		for (Folder f1 : folders) {
			if(f1.getName().equals(folderName)){
				f = f1;
			}
		}

		if (f == null) {
			f = new Folder(folderName);
			folders.add(f);
                }

		for (Note n : f.getNotes()) {
			if(n.equals(note)){
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
		}
		f.addNote(note);
		return true;
	}

	public void sortFolders() {
		for (Folder f1 : folders) {
			f1.sortNotes();
		}
		Collections.sort(folders);
	}

	public List<Note> searchNotes(String keywords) {
		List<Note> result = new ArrayList<Note>();
		for (Folder f1 : folders) {
			List<Note> temp = f1.searchNotes(keywords);
			result.addAll(temp);
		}
		return result;
	}

	public boolean save(String file){
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;

			try {
				fileOut = new FileOutputStream(file);
				out = new ObjectOutputStream(fileOut);
				out.writeObject(this);
				out.close();
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			    return false;
			}
			return true;

	}

	public void addFolder(String folderName) {
		Folder folder = new Folder(folderName);
		folders.add(folder);
	}

}
