package jblog.controller.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jblog.dto.JsonResult;
import jblog.service.BlogService;
import jblog.vo.CategoryVo;

@RestController("blogApiController")
@RequestMapping("/{blogId:(?!assets).*}") // assets로 시작하지 않는 모든 문자열
public class BlogController {
	private BlogService blogService;
	
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	/* 블로그 카테고리 설정 */
	@PostMapping("/admin/category")
	public JsonResult adminCategory(@PathVariable("blogId") String blogId, @RequestBody CategoryVo vo) {
		vo.setBlogId(blogId);
		blogService.addCategory(vo);
		
		return JsonResult.success();
	}	
	
	@DeleteMapping("/admin/category")
	public JsonResult adminCategory(@PathVariable("blogId") String blogId, @RequestParam("categoryId") int categoryId) {
		blogService.deleteCategory(categoryId);
		
		return JsonResult.success();
	}
	
}
