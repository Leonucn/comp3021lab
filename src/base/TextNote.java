package base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextNote extends Note{

	String content;
	private static final long serialVersionUID = 1L;

	public TextNote(String title) {
		super(title);
	}

	public TextNote(String title, String content){
		super(title);
		this.content = content;
	}

	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}

	public String getContent(){
		return content;
	}

	private String getTextFromFile(String absolutePath) {
		String result = "";
		try {
			result = new String(Files.readAllBytes(Paths.get(absolutePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void exportTextToFile(String pathFolder) {
		if (pathFolder == ""){
			pathFolder = ".";
		}
		try{
			File file = new File( pathFolder + File.separator + this.getTitle().replaceAll(" ", "_") + ".txt");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(this.getContent());
			bw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public boolean isnull() {
		if (content == null)
			return true;
		else
			return false;
	}

	public void setcontent(String new_content){
		content = new_content;
	}

}
