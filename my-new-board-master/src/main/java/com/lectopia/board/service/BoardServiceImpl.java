package com.lectopia.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lectopia.board.domain.BoardVO;
import com.lectopia.board.domain.Criteria;
import com.lectopia.board.persistence.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper mapper;
	
	@Override
	public void regist(BoardVO vo) throws Exception {
		mapper.create(vo);
	}

	@Override
	public BoardVO get(Integer bno) throws Exception {
		return mapper.read(bno);
	}

	@Override
	public void modify(BoardVO vo) throws Exception {
		mapper.update(vo);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		mapper.delete(bno);
	}

	@Override
	public List<BoardVO> getAll() throws Exception {
		return mapper.listAll();
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception{
		return mapper.listPage(page);
	}
	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return mapper.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return mapper.countPaging(cri);
	}

}
