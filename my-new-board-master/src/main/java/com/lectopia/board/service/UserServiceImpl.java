package com.lectopia.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lectopia.board.domain.UserVO;
import com.lectopia.board.persistence.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;
	
	@Override
	public UserVO login(String userid) throws Exception {
		return mapper.login(userid);
	}

	@Override
	public void join(UserVO vo) throws Exception {
		// TODO Auto-generated method stub
		mapper.join(vo);
	}
}
