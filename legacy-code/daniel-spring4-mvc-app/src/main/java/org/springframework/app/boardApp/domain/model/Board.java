package org.springframework.app.boardApp.domain.model;

import org.springframework.common.AbstractVO;

public class Board extends AbstractVO{
	private int boardRowNum;
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardInsertDate;
	private String boardModifyDate;
	
	private int totalCount;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getBoardRowNum() {
		return boardRowNum;
	}
	public void setBoardRowNum(int boardRowNum) {
		this.boardRowNum = boardRowNum;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardInsertDate() {
		return boardInsertDate;
	}
	public void setBoardInsertDate(String boardInsertDate) {
		this.boardInsertDate = boardInsertDate;
	}
	public String getBoardModifyDate() {
		return boardModifyDate;
	}
	public void setBoardModifyDate(String boardModifyDate) {
		this.boardModifyDate = boardModifyDate;
	}


}
