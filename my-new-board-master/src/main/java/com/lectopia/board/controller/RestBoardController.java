package com.lectopia.board.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lectopia.board.domain.BoardVO;
import com.lectopia.board.service.BoardService;

@RestController
@RequestMapping(value="/api")
public class RestBoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public List<BoardVO> listAll() throws Exception {
		
	    List<BoardVO> list = boardService.getAll();
	    return list;
	}
	
	@RequestMapping(value="/getAllMap", method=RequestMethod.GET)
	public Map<Integer, BoardVO> listAllMap() throws Exception {
		Map<Integer, BoardVO> map = new TreeMap<Integer, BoardVO>();
		List<BoardVO> list =  boardService.getAll();
		for(BoardVO vo:list) {
			map.put(vo.getBno(), vo);
		}
	    return map;
	}
	
	@RequestMapping(value="/get/{bno}", method=RequestMethod.GET)
	public BoardVO read(@PathVariable("bno") int bno) throws Exception {
		
		BoardVO vo = boardService.get(bno);
	    return vo;
	}
	
	@RequestMapping(value="/add", method= {RequestMethod.POST,RequestMethod.GET} )
	public String add(BoardVO vo) throws Exception {
		try {
			boardService.regist(vo);
			return "{\"result\": \"OK\"}";
		} catch(Exception ex) {
			return "{\"result\": \"NG\"}";
		}
	}
	
}
