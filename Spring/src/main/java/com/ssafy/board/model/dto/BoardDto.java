package com.ssafy.board.model.dto;

public class BoardDto {

	private Integer articleNo;
	private String userId;
	private String userName;
	private String subject;
	private String content;
	private Integer hit;
	private String registerTime;

	private Integer wordCnt;
	
	public BoardDto() {}

	public BoardDto(Integer articleNo, String userId, String userName, String subject, String content, Integer hit,
			String registerTime, Integer wordCnt) {
		super();
		this.articleNo = articleNo;
		this.userId = userId;
		this.userName = userName;
		this.subject = subject;
		this.content = content;
		this.hit = hit;
		this.registerTime = registerTime;
		this.wordCnt = wordCnt;
	}

	public Integer getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getWordCnt() {
		return wordCnt;
	}

	public void setWordCnt(Integer wordCnt) {
		this.wordCnt = wordCnt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardDto [articleNo=");
		builder.append(articleNo);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", content=");
		builder.append(content);
		builder.append(", hit=");
		builder.append(hit);
		builder.append(", registerTime=");
		builder.append(registerTime);
		builder.append(", wordCnt=");
		builder.append(wordCnt);
		builder.append("]");
		return builder.toString();
	}
	
	
}
