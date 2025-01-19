package jblog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Service
public class BlogService {
	private BlogRepository blogRepository;

	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	/* 블로그 기본 설정 */
	public BlogVo getBlog(String blogId) {
		return blogRepository.findBlogByBlogId(blogId);
	}
	
	public int updateBlog(BlogVo blogVo) {
		return blogRepository.update(blogVo);
	}
	
	/* 블로그 카테고리 설정 */
	public int addCategory(CategoryVo categoryVo) {
		return blogRepository.insertCategory(categoryVo);
	}
	
	public List<CategoryVo> getCategories(String blogId) {
		return blogRepository.findCategoriesByBlogId(blogId);
	}
	
	public int deleteCategory(int id) {
		return blogRepository.deleteCategoryById(id);
	}
	
	/* 블로그 글 작성 */
	public int addPost(String blogId, PostVo postVo) {
		// 카테고리가 없는 경우
		if(postVo.getCategoryId() == null || postVo.getCategoryId().equals("")) {
			Optional<CategoryVo> isExist = Optional.ofNullable(blogRepository.findDefaultCategoryByBlogId(blogId));
			
			// 미분류 카테고리가 없는 경우 => 생성
			if(isExist.isEmpty()) {
				CategoryVo categoryVo = new CategoryVo();
				categoryVo.setName("미분류");
				categoryVo.setDescription("카테고리를 지정하지 않은 경우");
				categoryVo.setBlogId(blogId);
				blogRepository.insertCategory(categoryVo);
				
				isExist = Optional.ofNullable(blogRepository.findDefaultCategoryByBlogId(blogId));
			}
			
			// 미분류 카테고리로 설정
			postVo.setCategoryId(isExist.get().getId());
		}
		
		return blogRepository.insertPost(postVo);
	}
	
	/* 블로그 메인 */
	public List<PostVo> getPosts(String blogId, int categoryId) {
		return blogRepository.findPostsByBlogIdAndCategoryId(blogId, categoryId);
	}
	
	public PostVo getPost(String blogId, int categoryId, int postId) {
		return blogRepository.findPost(blogId, categoryId, postId);
	}
}
