package org.springframework.app.boardApp.repository;

import java.util.List;
import java.util.Map;

import org.springframework.abstractClass.AbstractRepository;
import org.springframework.app.boardApp.domain.model.Board;
import org.springframework.stereotype.Repository;



@Repository("app.boardApp.boardRepository")
public class BoardRepository extends AbstractRepository{
	
	
	public int test() throws Exception{
		return getSqlSession().selectOne("danielapp.board.sampleSelect");
	}
	
	/**
	 * 게시판 리스트
	 * @param paramMap(Map)
	 * @return
	 * @throws Exception
	 */
	public List<Board> boardList(Map<String,Object> paramMap) throws Exception{
		return getSqlSession().selectList("danielapp.board.boardList", paramMap);
	}

	/**
	 * 게시물 상세
	 * @param paramMap(Map)
	 * @return
	 * @throws Exception
	 */	
	public Board boardDetail(Map<String,Object> paramMap) throws Exception{
		return getSqlSession().selectOne("danielapp.board.boardDetail", paramMap);
	}

	/**
	 * 게시물 입력
	 * @param paramBoard(Board)
	 * @return
	 * @throws Exception
	 */	
	public int boardInsert(Board paramBoard) throws Exception{
		return getSqlSession().insert("danielapp.board.boardInsert", paramBoard);
	}
	
	
	/**
	 * 게시물 상세 수정
	 * @param paramBoard(Board)
	 * @return
	 * @throws Exception
	 */	
	public int boardDetailUpdate(Board paramBoard) throws Exception{
		return getSqlSession().update("danielapp.board.boardDetailUpdate", paramBoard);
	}	

	/**
	 * 게시물 삭제
	 * @param paramMap(Map)
	 * @return
	 * @throws Exception
	 */	
	public int boardDetailDelete(Board paramBoard) throws Exception{
		return getSqlSession().delete("danielapp.board.boardDetailDelete", paramBoard);
	}	
	
	/**
	 * @param paramMap(Map)
	 */
	public Map<String,Object> boardPageInfo(Map<String, Object> paramMap) throws Exception{
		return getSqlSession().selectOne("danielapp.board.boardPageInfo");
	}
}
