package com.hvdomingues.simpleCrud.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hvdomingues.simpleCrud.dao.jpa.UserRepository;
import com.hvdomingues.simpleCrud.domain.User;
import com.hvdomingues.simpleCrud.dto.UserDto;

@Service
public class UserServiceImpl implements UserService, Serializable{
	private static final long serialVersionUID = 1L;

	@Autowired
	UserRepository userRepository;

	@Override
	public Page<UserDto> findAll(Boolean deleted, Integer page, Integer size) {
		
		PageRequest pageRequest = PageRequest.of(page, size);
		
		Page<User> userPage = userRepository.findByIsDeleted(deleted, pageRequest);
		List<UserDto> listUserDto = new ArrayList<UserDto>();
		
		for(User foundUser : userPage) {
			
			listUserDto.add(userToDto(foundUser));
			
		}
		
		Page<UserDto> userDtoPage = new PageImpl<UserDto>(listUserDto, userPage.getPageable(), listUserDto.size());
		
		return userDtoPage;
	}
	
	@Override
	public Page<UserDto> findBy(UserDto user, Boolean deleted, Integer page, Integer size) {
		
		PageRequest pageRequest = PageRequest.of(page, size);
		
		User exampleUser = dtoToUser(user);
		
		exampleUser.setIsDeleted(deleted);
		
		
		Page<User> userPage = userRepository.findAll(Example.of(exampleUser, 
				ExampleMatcher.matchingAll().withStringMatcher(StringMatcher.STARTING).withIgnoreCase()), pageRequest);
		
		List<UserDto> listUserDto = new ArrayList<UserDto>();
		
		for(User foundUser : userPage) {
			
			listUserDto.add(userToDto(foundUser));
			
		}
		
		Page<UserDto> userDtoPage = new PageImpl<UserDto>(listUserDto, userPage.getPageable(), listUserDto.size());
		
		return userDtoPage;
	}

	@Override
	public UserDto create(UserDto toCreate) {
		
		User created;
		
		if(userRepository.findByLogin(toCreate.getLogin()) != null) {
			
			return null; //Criar erro
		
		}else {
			
			if(toCreate.isFullFilled()) {
				
				created = userRepository.save(dtoToUser(toCreate)); //Criar validação
				
				
				return userToDto(created);
			}else {
				
				return null; //Criar erro
				
			}
			
		}
		
		
	}

	@Override
	public UserDto update(UserDto toUpdate) {
		
		User foundUser = userRepository.findByLogin(toUpdate.getLogin());
		
		if(foundUser == null) {
			
			return null; //Criar erro
			
		}else {
			
			if(toUpdate.getFullName() != null || !toUpdate.getFullName().isBlank()) {
				
				foundUser.setFullName(toUpdate.getFullName());
				
			}
			
			if(toUpdate.getBirthDay() != null) {
				
				foundUser.setBirthday(toUpdate.getBirthDay());
				
			}
			
			if(toUpdate.getZipCode() != null || !toUpdate.getZipCode().isBlank()) {
				
				foundUser.setZipCode(toUpdate.getZipCode());
				
			}
			
			User savedUser = userRepository.save(foundUser);
			
			return userToDto(savedUser);
			
		}

	}

	@Override
	public UserDto delete(String login) {
		
		User userFound = userRepository.findByLogin(login);
		
		if(userFound == null || userFound.getIsDeleted()) {
			
			return null; //criar erro
			
		}else {
			
			userFound.setIsDeleted(true);
			
			User savedUser = userRepository.save(userFound);
			
			return userToDto(savedUser);
			
		}

	}
	
	private User dtoToUser(UserDto userDto) {
		
		User user = new User();
		
		if(userDto.getLogin() != null) {
			
			user.setLogin(userDto.getLogin());

		}
		
		if(userDto.getFullName() != null) {
			
			user.setFullName(userDto.getFullName());
			
		}
		
		if(userDto.getBirthDay() != null) {
			
			user.setBirthday(userDto.getBirthDay());
			
		}
		
		if(userDto.getZipCode() != null) {
			
			user.setZipCode(userDto.getZipCode());
			
		}
		
		return user;
		
		
	}
	
	private UserDto userToDto(User user) {
		
		UserDto userDto = new UserDto(user.getLogin(), user.getFullName(), user.getBirthday(), user.getZipCode());
		
		return userDto;
		
	}

	

	
	
}
