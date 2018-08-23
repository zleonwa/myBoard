package com.lectopia.board.service;

import com.lectopia.board.domain.UserVO;

public interface UserService {
	public UserVO login(String userid) throws Exception;
	public void join(UserVO vo) throws Exception;
}
