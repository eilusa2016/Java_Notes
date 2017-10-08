package com.itstyle.model;

public class Question {
    private Integer id;
    private String quesContent;
    
	public Question() {
		super();
	}
	public Question(Integer id, String quesContent) {
		super();
		this.id = id;
		this.quesContent = quesContent;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQuesContent() {
		return quesContent;
	}
	public void setQuesContent(String quesContent) {
		this.quesContent = quesContent;
	}
    
}
