package jblog.vo;

public class PostVo {
	private Integer id;
	private String title;
	private String contexts;
	private String regDate;
	private Integer categoryId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContexts() {
		return contexts;
	}
	public void setContexts(String contexts) {
		this.contexts = contexts;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	@Override
	public String toString() {
		return "PostVo [id=" + id + ", title=" + title + ", contexts=" + contexts + ", regDate=" + regDate
				+ ", categoryId=" + categoryId + "]";
	}
	
}
