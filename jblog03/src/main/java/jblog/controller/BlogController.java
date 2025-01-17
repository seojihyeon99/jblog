package jblog.controller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jblog.service.BlogService;
import jblog.service.FileUploadService;
import jblog.vo.BlogVo;

@Controller
@RequestMapping("/{id:(?!assets).*}") // assets로 시작하지 않는 모든 문자열
public class BlogController {	
	private BlogService blogService;
	private FileUploadService fileUploadService;
	private final ServletContext servletContext;
	private final ApplicationContext applicationContext;
	
	public BlogController(BlogService blogService, FileUploadService fileUploadService, 
			ServletContext servletContext, ApplicationContext applicationContext) {
		this.blogService = blogService;
		this.fileUploadService = fileUploadService;
		this.servletContext = servletContext;
		this.applicationContext = applicationContext;
	}

	@RequestMapping({"", "/{path1}", "/{path1}/{path2}"})
	public String main(
			@PathVariable("id") String id, 
			@PathVariable("path1") Optional<Long> path1, 
			@PathVariable("path2") Optional<Long> path2) {
		
		Long categoryId = 0L;
		Long postId = 0L;
		
		if(path2.isPresent()) {
			categoryId = path1.get();
			postId = path2.get();
		} else if(path1.isPresent()) {
			categoryId = path1.get();
		}
		
		// 서비스에서~
		// categoryId == 0L -> default categoryId 결정
		// postId == 0L -> default postId 결정
		System.out.println("BlogController.main(" + id + ", " + categoryId + ", " + postId + ")");
		
		return "blog/main";
	}
	
	
	// @Auth 추천
	@GetMapping("/admin/basic")
	public String adminDefault(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blog", blogVo);
		
		return "blog/admin-basic";
	}
	
	@PostMapping("/admin/basic")
	public String adminDefault(@PathVariable("id") String id, BlogVo blogVo, @RequestParam("logo-file") MultipartFile multipartFile) {
		System.out.println(blogVo + ", " + multipartFile);
		String profile = fileUploadService.restore(multipartFile);
		if(profile != null) {
			blogVo.setProfile(profile);
		}
		blogVo.setBlogId(id);
		blogService.updateBlog(blogVo);
		
		// Servlet Context 업데이트 => View(JSP)
		servletContext.setAttribute("blog", blogVo);
		
		// Application Context 업데이트 => Service 및 Repository
//		BeanUtils.copyProperties(blogVo, applicationContext.getBean(BlogVo.class));
		
		return "redirect:/" + id + "/admin/basic";
	}	
}
