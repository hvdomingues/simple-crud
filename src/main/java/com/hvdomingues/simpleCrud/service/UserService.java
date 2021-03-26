package com.hvdomingues.simpleCrud.service;

import org.springframework.data.domain.Page;

import com.hvdomingues.simpleCrud.dto.UserDto;

public interface UserService {
	
	Page<UserDto> findAll(Boolean deleted, Integer page, Integer size);
	Page<UserDto> findBy(UserDto user, Integer page, Integer size);

	UserDto create(UserDto toCreate);
	UserDto update(UserDto toUpdate);
	
	UserDto delete(String login);
	UserDto changeLogin(String login, String newLogin);
	

}
