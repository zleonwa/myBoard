package com.lectopia.board.persistence;

import java.util.List;

import com.lectopia.board.domain.Criteria;
import com.lectopia.board.domain.ReplyVO;

public interface ReplyMapper {
	public List<ReplyVO> list(Integer bno) throws Exception;
	public List<ReplyVO> listpage(Integer bno, Criteria cri) throws Exception;
	public int count(Integer bno) throws Exception;
	public void create(ReplyVO vo) throws Exception;
	public void update(ReplyVO vo) throws Exception;
	public void delete(Integer rno) throws Exception;
}
