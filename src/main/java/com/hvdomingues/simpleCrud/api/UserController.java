package com.hvdomingues.simpleCrud.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hvdomingues.simpleCrud.dto.UserDto;
import com.hvdomingues.simpleCrud.service.UserService;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<Page<UserDto>> findAll(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {

		Page<UserDto> result = userService.findAll(false, page, size);

		return ResponseEntity.ok(result);

	}
	

	@GetMapping(value = "/deleted")
	public ResponseEntity<Page<UserDto>> findAllDeleted(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {

		Page<UserDto> result = userService.findAll(true, page, size);

		return ResponseEntity.ok(result);

	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public ResponseEntity<Page<UserDto>> findAllBy(@RequestBody UserDto userDto,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {

		Page<UserDto> result = userService.findBy(userDto, page, size);

		return new ResponseEntity<Page<UserDto>>(result, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = { "application/json" }, produces = {
			"application/json" })
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

		UserDto createdUserDto = userService.create(userDto);

		return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);

	}

	@RequestMapping(value = "", method = RequestMethod.PUT, consumes = { "application/json" }, produces = {
			"application/json" })
	public ResponseEntity<UserDto> updateUserByLogin(@RequestBody UserDto userDto) {

		UserDto updatedUserDto = userService.update(userDto);

		return new ResponseEntity<UserDto>(updatedUserDto, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.DELETE, consumes = { "application/json" }, produces = {
			"application/json" })
	public ResponseEntity<UserDto> deleteUserByLogin(@RequestBody String login) {

		UserDto deletedUserDto = userService.delete(login);

		return new ResponseEntity<UserDto>(deletedUserDto, HttpStatus.OK);

	}

	@RequestMapping(value = "/login", method = RequestMethod.PUT, produces = { "application/json" })
	public ResponseEntity<UserDto> changeUserLogin(@RequestParam String login, @RequestParam String newLogin) {

		UserDto updatedUserDto = userService.changeLogin(login, newLogin);

		return new ResponseEntity<UserDto>(updatedUserDto, HttpStatus.OK);

	}

}
