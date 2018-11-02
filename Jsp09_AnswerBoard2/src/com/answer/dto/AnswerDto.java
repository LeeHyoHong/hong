package com.answer.dto;

import java.sql.Date;

public class AnswerDto {
	private int boardno;
	private int groupno;
	private int groupsq;
	private int titletab;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private String isdel;
	private int parentno;
	
	private int myno;
	private String myid;
	private String mypw;
	private String myname;
	private String myaddr;
	private String myphone;
	private String myemail;
	private String myenabled;
	private String myrole;
	
	
	
	public AnswerDto(int myno, String myid, String mypw, String myname, String myaddr, String myphone, String myemail,
			String myenabled, String myrole) {
		super();
		this.myno = myno;
		this.myid = myid;
		this.mypw = mypw;
		this.myname = myname;
		this.myaddr = myaddr;
		this.myphone = myphone;
		this.myemail = myemail;
		this.myenabled = myenabled;
		this.myrole = myrole;
	}

	//전체
	public AnswerDto(int boardno, int groupno, int groupsq, int titletab, String title, String content, String writer,
			Date regdate, String isdel, int parentno) {
		super();
		this.boardno = boardno;
		this.groupno = groupno;
		this.groupsq = groupsq;
		this.titletab = titletab;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
		this.isdel = isdel;
		this.parentno = parentno;
	}

	public AnswerDto() {
		super();
	}

	//insert(제목, 내용, 작성자)
	public AnswerDto(String title, String content, String writer) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
	//update(번호,제목, 내용)
	public AnswerDto(int boardno, String title, String content) {
		super();
		this.boardno = boardno;
		this.title = title;
		this.content = content;
	}
	
	

	public int getBoardno() {
		return boardno;
	}


	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}


	public int getGroupno() {
		return groupno;
	}


	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}


	public int getGroupsq() {
		return groupsq;
	}


	public void setGroupsq(int groupsq) {
		this.groupsq = groupsq;
	}


	public int getTitletab() {
		return titletab;
	}


	public void setTitletab(int titletab) {
		this.titletab = titletab;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public Date getRegdate() {
		return regdate;
	}


	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

	public int getParentno() {
		return parentno;
	}

	public void setParentno(int parentno) {
		this.parentno = parentno;
	}

	public int getMyno() {
		return myno;
	}

	public void setMyno(int myno) {
		this.myno = myno;
	}

	public String getMyid() {
		return myid;
	}

	public void setMyid(String myid) {
		this.myid = myid;
	}

	public String getMypw() {
		return mypw;
	}

	public void setMypw(String mypw) {
		this.mypw = mypw;
	}

	public String getMyname() {
		return myname;
	}

	public void setMyname(String myname) {
		this.myname = myname;
	}

	public String getMyaddr() {
		return myaddr;
	}

	public void setMyaddr(String myaddr) {
		this.myaddr = myaddr;
	}

	public String getMyphone() {
		return myphone;
	}

	public void setMyphone(String myphone) {
		this.myphone = myphone;
	}

	public String getMyemail() {
		return myemail;
	}

	public void setMyemail(String myemail) {
		this.myemail = myemail;
	}

	public String getMyenabled() {
		return myenabled;
	}

	public void setMyenabled(String myenabled) {
		this.myenabled = myenabled;
	}

	public String getMyrole() {
		return myrole;
	}

	public void setMyrole(String myrole) {
		this.myrole = myrole;
	}
	
	
	

}
