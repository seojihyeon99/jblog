package jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Repository
public class BlogRepository {
	private SqlSession sqlSession;
	
	public BlogRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	/* 블로그 기본 설정 */
	public int insert(String blogId, String name) {
		return sqlSession.insert("blog.insertBlog", Map.of("blogId", blogId, "name", name));
	}
	
	public int update(BlogVo blogVo) {
		return sqlSession.update("blog.updateBlog", blogVo);
	}
	
	public BlogVo findBlogByBlogId(String blogId) {
		return sqlSession.selectOne("blog.findBlogByBlogId", blogId);
	}
	
	/* 블로그 카테고리 설정 */
	public int insertCategory(CategoryVo categoryVo) {
		return sqlSession.insert("blog.insertCategory", categoryVo);
	}
	
	public List<CategoryVo> findCategoriesByBlogId(String blogId) {
		return sqlSession.selectList("blog.findCategoriesByBlogId", blogId);
	}
	
	public int deleteCategoryById(int id) {
		return sqlSession.delete("blog.deleteCategoryById", id);
	}
	
	public CategoryVo findDefaultCategoryByBlogId(String blogId) {
		return sqlSession.selectOne("blog.findDefaultCategoryByBlogId", blogId);
	}
	
	/* 블로그 글 작성 */
	public int insertPost(PostVo postVo) {
		return sqlSession.insert("blog.insertPost", postVo);
	}
	
	/* 블로그 메인 */
	public List<PostVo> findPostsByBlogIdAndCategoryId(String blogId, int categoryId) {
		return sqlSession.selectList("blog.findPostsByBlogIdAndCategoryId", Map.of("blogId", blogId, "categoryId", categoryId));
	}
	
	public PostVo findPostById(String blogId, int id) {
		return sqlSession.selectOne("blog.findPostByBlogIdAndId", Map.of("blogId", blogId, "id", id));
	}
	
}
