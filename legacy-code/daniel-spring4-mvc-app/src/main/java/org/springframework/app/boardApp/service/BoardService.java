package org.springframework.app.boardApp.service;

import java.util.List;
import java.util.Map;

import org.springframework.app.boardApp.domain.model.Board;


public interface BoardService {
	
	public List<Board> boardList(Map<String, Object> paramMap) throws Exception;

	public Board boardDetail(Map<String, Object> paramMap) throws Exception;
	
	public int boardInsert(Board paramBoard) throws Exception;
	
	public int boardDetailUpdate(Board paramBoard) throws Exception;
	
	public int boardDetailDelete(Board paramBoard) throws Exception;
	
	public Map<String, Object> boardPageInfo(Map<String, Object> paramMap) throws Exception;
}
