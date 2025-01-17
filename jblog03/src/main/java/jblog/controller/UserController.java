package jblog.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jblog.dto.JsonResult;
import jblog.service.UserService;
import jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("join")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("join")
	public String join(UserVo vo) {
		System.out.println("UserController.join에서 호출함===== " + vo);
		userService.join(vo);
		
		return "redirect:/user/joinsuccess";
	}
	
	@GetMapping("joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}

	@GetMapping("login")
	public String login() {
		return "user/login";
	}
	
	@ResponseBody
	@GetMapping("/check")
	public JsonResult checkId(@RequestParam(value="id", required=true) String id) {
		UserVo userVo = userService.getUser(id);
		return JsonResult.success(Map.of("exist", userVo != null));
	}
	
	
}
