package com.lectopia.board.service;

import java.util.List;

import com.lectopia.board.domain.BoardVO;
import com.lectopia.board.domain.Criteria;

public interface BoardService {
	public void regist(BoardVO vo) throws Exception;
	public BoardVO get(Integer bno) throws Exception;
	public void modify(BoardVO vo) throws Exception;
	public void remove(Integer bno) throws Exception;
	public List<BoardVO> getAll() throws Exception;
	public List<BoardVO> listPage(int page) throws Exception;
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	public int listCountCriteria(Criteria cri) throws Exception;
}
