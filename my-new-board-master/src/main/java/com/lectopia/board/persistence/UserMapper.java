package com.lectopia.board.persistence;

import com.lectopia.board.domain.UserVO;

public interface UserMapper {
	public UserVO login(String userid) throws Exception;
	public void join(UserVO vo) throws Exception;
}
