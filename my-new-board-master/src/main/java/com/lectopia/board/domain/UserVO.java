package com.lectopia.board.domain;

import java.util.Date;

public class UserVO {
	private int idx;
	private String userid;
	private String userpwd;
	private Date regdate;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "UserVO [idx=" + idx + ", userid=" + userid + ", userpwd=" + userpwd + ", regdate=" + regdate + "]";
	}
	
}
