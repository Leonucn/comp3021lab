package base;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Note implements Comparable<Note>, Serializable{

	private transient Date date;
	private String title;
	private static final long serialVersionUID = 1L;

	public Note(String title){
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}

	public String getTitle(){
		return title;
	}

	public Date getDate(){
		return date;
	}

	public String toString() {
		return date.toString() + "\t" + title;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Note))
			return false;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}

	@Override
	public int compareTo(Note o) {
		int result = date.compareTo(o.getDate());

		if (result == 0)
			return 0;
		else if (result < 0)
			return 1;
		else
			return -1;
	}

}
