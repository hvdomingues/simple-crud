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
import com.hvdomingues.simpleCrud.exception.DatabaseException;
import com.hvdomingues.simpleCrud.exception.NotFoundException;

@Service
public class UserServiceImpl implements UserService, Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	UserRepository userRepository;

	@Override
	public Page<UserDto> findAll(Boolean deleted, Integer page, Integer size) {

		PageRequest pageRequest = PageRequest.of(page, size);

		Page<User> userPage = userRepository.findByIsDeleted(deleted, pageRequest);
		List<UserDto> listUserDto = new ArrayList<UserDto>();
		
		if(userPage.isEmpty()) {
			
			throw new NotFoundException("Não foi achado nenhum usuário");
			
		}

		for (User foundUser : userPage) {

			listUserDto.add(userToDto(foundUser));

		}

		Page<UserDto> userDtoPage = new PageImpl<UserDto>(listUserDto, userPage.getPageable(), listUserDto.size());

		return userDtoPage;
	}

	@Override
	public Page<UserDto> findBy(UserDto user, Integer page, Integer size) {

		PageRequest pageRequest = PageRequest.of(page, size);

		User exampleUser = dtoToUser(user);

		Page<User> userPage = userRepository.findAll(
				Example.of(exampleUser,
						ExampleMatcher.matchingAll().withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase()),
				pageRequest);
		

		
		if(userPage.isEmpty()) {
			
			throw new NotFoundException("Não foi achado nenhum usuário com os critérios");
			
		}

		List<UserDto> listUserDto = new ArrayList<UserDto>();

		for (User foundUser : userPage) {

			listUserDto.add(userToDto(foundUser));

		}

		Page<UserDto> userDtoPage = new PageImpl<UserDto>(listUserDto, userPage.getPageable(), listUserDto.size());

		return userDtoPage;
	}

	@Override
	public UserDto create(UserDto toCreate) {

		User created;

		if (userRepository.findByLoginIgnoreCase(toCreate.getLogin()) != null) {

			throw new NotFoundException("Nome de usuário não disponível");

		} else {

			if (toCreate.isFullFilled()) {

				created = userRepository.save(dtoToUser(toCreate));

				return userToDto(created);

			} else {

				throw new DatabaseException("Todos os campos devem ser preenchidos para que o usuário seja criado.");

			}

		}

	}

	@Override
	public UserDto update(UserDto toUpdate) {

		User foundUser = userRepository.findByLoginIgnoreCase(toUpdate.getLogin());

		if (foundUser == null || foundUser.getIsDeleted()) {

			throw new NotFoundException("Não foi encontrado usuário ativo com esse login.");

		} else {

			if (!isBlankOrNull(toUpdate.getFullName())) {

				foundUser.setFullName(toUpdate.getFullName());

			}

			if (!isBlankOrNull(toUpdate.getBirthday())) {

				foundUser.setBirthday(toUpdate.getBirthday());

			}

			if (!isBlankOrNull(toUpdate.getZipCode())) {

				foundUser.setZipCode(toUpdate.getZipCode());

			}

			User savedUser = userRepository.save(foundUser);

			return userToDto(savedUser);

		}

	}

	@Override
	public UserDto delete(String login) {

		User userFound = userRepository.findByLoginIgnoreCase(login);

		if (userFound == null || userFound.getIsDeleted()) {

			throw new NotFoundException("Não foi encontrado usuário ativo com esse login.");

		} else {

			userFound.setIsDeleted(true);

			User savedUser = userRepository.save(userFound);

			return userToDto(savedUser);

		}

	}

	@Override
	public UserDto changeLogin(String login, String newLogin) {

		User oldLoginUser = userRepository.findByLoginIgnoreCase(login);
		User newLoginUser = userRepository.findByLoginIgnoreCase(newLogin);

		if (!oldLoginUser.getIsDeleted() && newLoginUser == null) {

			oldLoginUser.setLogin(newLogin);

			newLoginUser = userRepository.save(oldLoginUser);

			return userToDto(newLoginUser);

		} else if (oldLoginUser.getIsDeleted()) {

			throw new NotFoundException("Não foi encontrado usuário ativo com esse login.");

		} else {

			throw new NotFoundException("Nome de usuário não disponível");

		}

	}

	private User dtoToUser(UserDto userDto) {

		User user = new User();

		if (!isBlankOrNull(userDto.getLogin())) {

			user.setLogin(userDto.getLogin());

		}

		if (!isBlankOrNull(userDto.getFullName())) {

			user.setFullName(userDto.getFullName());

		}

		if (!isBlankOrNull(userDto.getBirthday())) {

			user.setBirthday(userDto.getBirthday());

		}

		if (!isBlankOrNull(userDto.getZipCode())) {

			user.setZipCode(userDto.getZipCode());

		}

		return user;

	}

	private UserDto userToDto(User user) {

		UserDto userDto = new UserDto(user.getLogin(), user.getFullName(), user.getBirthdayAsString(),
				user.getZipCode());

		return userDto;

	}

	private Boolean isBlankOrNull(String toTest) {

		if (toTest != null && !toTest.isBlank()) {

			return false;

		}

		return true;

	}

}
