package babinvas.jpaintroduction.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Category {
	private Long id;
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
