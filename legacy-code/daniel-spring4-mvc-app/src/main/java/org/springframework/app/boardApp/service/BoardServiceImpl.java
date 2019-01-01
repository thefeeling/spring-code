package org.springframework.app.boardApp.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.abstractClass.AbstractService;
import org.springframework.app.boardApp.domain.model.Board;
import org.springframework.app.boardApp.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("app.boardApp.boardService")
public class BoardServiceImpl extends AbstractService implements BoardService{

	@Resource(name = "app.boardApp.boardRepository")
	private BoardRepository repository;
	
	@Override
	public List<Board> boardList(Map<String, Object> paramMap) throws Exception {
		log.info("boardListSvcImpl");
		List<Board> boardList = null;
		try {
			int currentPage = (int) paramMap.get("currentPage");
			int rowsPerpage = (int) paramMap.get("rowsPerPage");

			// MySQL LIMIT 사용때문에 startIndex 지정
			if(currentPage == 1 || currentPage <= 0){
				currentPage = 0;
			}else{
				currentPage = (currentPage-1)*rowsPerpage;
			}

			paramMap.put("startIndex", currentPage);			
			boardList = repository.boardList(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public Board boardDetail(Map<String, Object> paramMap) throws Exception {
		Board rtnBoard = null;
		try {
			rtnBoard = repository.boardDetail(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtnBoard;
	}

	@Transactional
	@Override
	public int boardInsert(Board paramBoard) throws Exception {
		int executeRtn = 0;
		try {
			executeRtn = repository.boardInsert(paramBoard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return executeRtn;
	}

	@Override
	public int boardDetailUpdate(Board paramBoard) throws Exception {
		int executeRtn = 0;
		try {
			executeRtn = repository.boardDetailUpdate(paramBoard);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return executeRtn;
	}

	@Override
	public int boardDetailDelete(Board paramBoard) throws Exception {
		int executeRtn = 0;
		try {
			executeRtn = repository.boardDetailDelete(paramBoard);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return executeRtn;
	}

	@Override
	public Map<String, Object> boardPageInfo(Map<String, Object> paramMap) throws Exception {
		return repository.boardPageInfo(paramMap);
	}
}
