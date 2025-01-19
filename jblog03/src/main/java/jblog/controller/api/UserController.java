package jblog.controller.api;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jblog.service.UserService;
import jblog.vo.UserVo;

@RestController("userApiController")
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/checkid")
	public Object checkEmail(@RequestParam(value="id", required=true, defaultValue="") String id) {
		UserVo userVo = userService.getUser(id);
		return Map.of("exist", userVo != null);
	}
	
}
