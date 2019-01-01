package org.springframework.app.boardApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.abstractClass.AbstractController;
import org.springframework.app.boardApp.domain.model.Board;
import org.springframework.app.boardApp.service.BoardService;
import org.springframework.common.CommandMap;
import org.springframework.common.Message;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/board")
public class BoardRestController extends AbstractController{
	
	@Resource(name = "app.boardApp.boardService")
	private BoardService boardService;

	
	/**
	 * Mybatis Test && log test
	 * @return
	 */
	@RequestMapping("/mybatis")
	public int mybatislogTest() {
		int rtnValue = 0;
		log.info("Logger Interceptor Test");
		log.info(getMessage("app.common.test"));
		log.debug(getMessage("app.common.test"));
		
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtnValue; 
	}	
	
	
	/**
	 * 게시판 리스트 반환
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public Map bdList(
			HttpServletRequest request, 
			HttpServletResponse response, 
			CommandMap commandMap,
			@RequestParam(value="currentPage", defaultValue="1", required=false) int currentPage,
			@RequestParam(value="rowsPerPage", defaultValue="10", required=false) int rowsPerPage) {
		Message rtnMsg = new Message();
		List<Board> boardList = null;
		try {
			rtnMsg = new Message();
			// Parameter Check
			super.commandMapProc(request,commandMap);
			
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("currentPage", currentPage);
			paramMap.put("rowsPerPage", rowsPerPage);
			
			boardList = boardService.boardList(paramMap); 
			Object[] toArraySample = boardList.toArray();
			
			
			rtnMsg.put("currentPage", currentPage)
				  .put("rowsPerPage", rowsPerPage)
				  .put("boardList"  , boardList)
				  .put("pageInfo"   , boardService.boardPageInfo(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtnMsg.get();
	}
	
	/**
	 * 게시물 상세
	 * @Pathvariable boardNo
	 * @param request
	 * @param response
	 * @param commandMap
	 * @return
	 */
	@RequestMapping(value="/detail/{boardNo}", method=RequestMethod.GET)
	public Board bdDetail(
			HttpServletRequest request, 
			HttpServletResponse response, 
			CommandMap commandMap,
			@PathVariable int boardNo) {
		Board boardDetail = null;
		try {
			// Parameter Check
			super.commandMapProc(request,commandMap);
			
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("boardNo", boardNo);
			
			boardDetail = boardService.boardDetail(paramMap); 
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return boardDetail;
	}	

	/**
	 * 게시물 입력
	 * @param request
	 * @param response
	 * @param commandMap
	 * @param boardNo
	 * @return
	 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public Board bdInsert(
			HttpServletRequest request, 
			HttpServletResponse response, 
			CommandMap commandMap,
			@RequestBody Board paramBoard) {
		// Test
		int exeRtn = 0;
		try {
			// Parameter Check
			super.commandMapProc(request,commandMap);
			exeRtn = boardService.boardInsert(paramBoard);
		} catch (Exception e) {
			e.printStackTrace();
			new Exception();
		}
		return paramBoard;
	}	

	/**
	 * 게시물 업데이트/수정
	 * @param request
	 * @param response
	 * @param commandMap
	 * @param boardNo
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public Board bdUpdate(
			HttpServletRequest request, 
			HttpServletResponse response, 
			CommandMap commandMap,
			@RequestBody Board paramBoard) {
		int exeRtn = 0;
		try {
			// Parameter Check
			super.commandMapProc(request,commandMap);
			exeRtn = boardService.boardDetailUpdate(paramBoard);
		} catch (Exception e) {
			e.printStackTrace();
			new Exception();
		}
		return paramBoard;
	}	


	/**
	 * 게시물 삭제
	 * @PathVariable boardNo
	 * @param request
	 * @param response
	 * @param commandMap
	 * @param boardNo
	 * @return
	 */
	@RequestMapping(value="/delete/{boardNo}", method=RequestMethod.DELETE)
	public Board bdDelete(
			HttpServletRequest request, 
			HttpServletResponse response, 
			CommandMap commandMap,
			@PathVariable int boardNo) {
		int exeRtn = 0;
		Board paramBoard = null;
		try {
			// Parameter Check
			super.commandMapProc(request,commandMap);
			
			paramBoard = new Board();
			paramBoard.setBoardNo(boardNo);
			
			// Parameter Check
			super.commandMapProc(request,commandMap);
			exeRtn = boardService.boardDetailDelete(paramBoard);
		} catch (Exception e) {
			e.printStackTrace();
			new Exception();
		}
		return paramBoard;
	}
	
	@RequestMapping(value="/loginProc", method=RequestMethod.GET)
	public String loginProc(
			HttpServletRequest request,
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
		session.setAttribute("UserSession", "User");
		
		return "session Ok";
	}

	@RequestMapping(value="/loginTest", method=RequestMethod.GET)	
	public String loginTest(	
			HttpServletRequest request, 
			HttpServletResponse response){
		
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("UserSession");
		
		if(user == null || user.isEmpty()){
			return "session Fail";
		}
		return "session Ok";
	}
	
	
	
	
	
}
