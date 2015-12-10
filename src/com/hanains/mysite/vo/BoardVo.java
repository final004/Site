package com.hanains.mysite.vo;

public class BoardVo {

	private long no;
	private String title;
	private String member_no;
	private String name;
	private String content;
	private String date;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", member_no=" + member_no + ", name=" + name + ", content="
				+ content + ", date=" + date + "]";
	}
	
	

}
