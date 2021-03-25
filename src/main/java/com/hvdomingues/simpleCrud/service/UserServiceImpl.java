package com.hvdomingues.simpleCrud.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.hvdomingues.simpleCrud.dao.jpa.UserRepository;
import com.hvdomingues.simpleCrud.dto.UserDto;

@Service
public class UserServiceImpl implements UserService, Serializable{
	private static final long serialVersionUID = 1L;

	@Autowired
	UserRepository userRepository;

	@Override
	public Page<UserDto> findAll(Boolean deleted, Integer page, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Page<UserDto> findBy(UserDto user, Integer page, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto create(UserDto toCreate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto update(UserDto toUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto delete(UserDto toDelete) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	
}
