package com.arch.service.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arch.mapper.securtity.UserMapper;


@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	// public List<SysUsers> getUsers() {
	//
	// // return userMapper.getUsers();
	// }

	/**
	 * <p>TODO</p>
	 * @return
	 * @author lkd
	 * 
	 */
	public int getUsersCount() {
		return userMapper.getUsersCount();
	}

}
