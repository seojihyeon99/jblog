package jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	private SqlSession sqlSession;
	
	public BlogRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(String blogId) {
		return sqlSession.insert("blog.insert", blogId);
	}
	
	public int update(BlogVo blogVo) {
		return sqlSession.update("blog.update", blogVo);
	}
	
	public BlogVo findByBlogId(String blogId) {
		return sqlSession.selectOne("blog.findByBlogId", blogId);
	}
}
