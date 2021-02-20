package babinvas.jpaintroduction.model;

public class Topic {
	private Long id;
	private String title;
	private Category category;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
