package jblog.controller;

import java.util.List;
import java.util.Optional;

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
import jblog.repository.BlogRepository;
import jblog.service.BlogService;
import jblog.service.FileUploadService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Controller
@RequestMapping("/{blogId:(?!assets).*}") // assets로 시작하지 않는 모든 문자열
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

	// blogId/categoryId/postId
	@RequestMapping({"", "/{path1}", "/{path1}/{path2}"})
	public String main(
			@PathVariable("blogId") String blogId, 
			@PathVariable("path1") Optional<Integer> path1, 
			@PathVariable("path2") Optional<Integer> path2,
			Model model) {
		
		Integer categoryId = 0;
		Integer postId = 0;
	
		if(path2.isPresent()) {
			categoryId = path1.get();
			postId = path2.get();
		} else if(path1.isPresent()) {
			categoryId = path1.get();
		}
		System.out.println("blogId : " + blogId + ", categoryId : " + categoryId + ", postId : " + postId);
		
		// @@@@@@@@@@@ 블로그 제목은 나중에 aop로 처리 할 수 있을듯
		// 블로그 기본 설정
		BlogVo blogVo = blogService.getBlog(blogId);
		model.addAttribute("blog", blogVo);
		
		// 블로그 카테고리 목록
		List<CategoryVo> categories = blogService.getCategories(blogId);
		model.addAttribute("categories", categories);
		
		// 블로그 글 목록 및 상세
		PostVo postVo = blogService.getPost(blogId, postId);
		model.addAttribute("postDetail", postVo);
		
		List<PostVo> posts = blogService.getPosts(blogId, categoryId);
		model.addAttribute("posts", posts);			
		
		
		// 서비스에서~@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		// categoryId == 0L -> default categoryId 결정
		// postId == 0L -> default postId 결정
		
		return "blog/main";
	}
	
	
	/* 블로그 기본 설정 */
	// @@@@@@@@@@@@@@@@@ @Auth 추천
	@GetMapping("/admin/basic")
	public String adminDefault(@PathVariable("blogId") String blogId, Model model) {
		BlogVo blogVo = blogService.getBlog(blogId);
		model.addAttribute("blog", blogVo);
		
		return "blog/admin-basic";
	}
	
	@PostMapping("/admin/basic")
	public String adminDefault(@PathVariable("blogId") String blogId, BlogVo blogVo, @RequestParam("logo-file") MultipartFile multipartFile) {
		String profile = fileUploadService.restore(multipartFile);
		if(profile != null) {
			blogVo.setProfile(profile);
		}
		blogVo.setBlogId(blogId);
		blogService.updateBlog(blogVo);
		
		// Servlet Context 업데이트 => View(JSP)
		servletContext.setAttribute("blog", blogVo);
		
		// Application Context 업데이트 => Service 및 Repository
//		BeanUtils.copyProperties(blogVo, applicationContext.getBean(BlogVo.class));
		
		return "redirect:/" + blogId + "/admin/basic";
	}	

	/* 블로그 카테고리 설정 */
	@GetMapping("/admin/category")
	public String adminCategory(@PathVariable("blogId") String blogId, Model model) {
		List<CategoryVo> list = blogService.getCategories(blogId);
		
		model.addAttribute("list", list);
		return "blog/admin-category";
	}
	
	/* 블로그 글 작성 */
	@GetMapping("/admin/write")
	public String adminWrite(@PathVariable("blogId") String blogId, Model model) {
		List<CategoryVo> list = blogService.getCategories(blogId);
		
		model.addAttribute("list", list);
		return "blog/admin-write";
	}
	
	@PostMapping("/admin/write")
	public String adminWrite(@PathVariable("blogId") String blogId, PostVo postVo) {
		blogService.addPost(blogId, postVo);
				
		return "redirect:/" + blogId + "/admin/write";
	}
	
	/* 블로그 메인 */
	
	
}
