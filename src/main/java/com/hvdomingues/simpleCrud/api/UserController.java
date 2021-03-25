package com.hvdomingues.simpleCrud.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hvdomingues.simpleCrud.dto.UserDto;
import com.hvdomingues.simpleCrud.service.UserService;

@RestController
@RequestMapping(value = "/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<Page<UserDto>> findAll(@RequestParam( value = "page", required = false, defaultValue = "0") int page,
		    @RequestParam (value = "size", required = false, defaultValue = "10") int size) {
		
		Page<UserDto> result = userService.findAll(false, page, size);
		
		return ResponseEntity.ok(result);
		
	}
	
	@GetMapping(value = "/deleted")
	public ResponseEntity<Page<UserDto>> findAllDeleted(@RequestParam( value = "page", required = false, defaultValue = "0") int page,
		    @RequestParam (value = "size", required = false, defaultValue = "10") int size) {
		
		Page<UserDto> result = userService.findAll(true, page, size);
		
		return ResponseEntity.ok(result);
		
	}

}
