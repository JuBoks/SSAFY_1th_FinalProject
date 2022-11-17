package com.ssafy.board.model.dto;


//@ApiModel(value = "CommentDto : 도서 상세정보에 작성하는 도서평에 대한 글정보")
public class Comment {

	//@ApiModelProperty(value = "글번호")
	private int commentNo;
	//@ApiModelProperty(value = "작성자이름")
	private String userName;
	//@ApiModelProperty(value = "도서평")
	private String comment;
	//@ApiModelProperty(value = "작성시각")
	private String commentTime;
	//@ApiModelProperty(value = "글 번호", example = n번 글)
	private Integer articleNo;
	
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	public Integer getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
	}
	
	public Comment(int commentNo, String userName, String comment, String commentTime, Integer articleNo) {
		this.commentNo = commentNo;
		this.userName = userName;
		this.comment = comment;
		this.commentTime = commentTime;
		this.articleNo = articleNo;
	}

	
}
