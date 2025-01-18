package jblog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.BlogRepository;
import jblog.repository.UserRepository;
import jblog.vo.UserVo;

@Service
public class UserService {
	private UserRepository userRepository;
	private BlogRepository blogRepository;
	
	public UserService(UserRepository userRepository, BlogRepository blogRepository) {
		this.userRepository = userRepository;
		this.blogRepository = blogRepository;
	}
	
	@Transactional
	public void join(UserVo vo) {
		int count = userRepository.insert(vo);
		
		// 회원가입 시, 해당 사용자의 블로그가 생성됨
		if(count == 1) {
			blogRepository.insert(vo.getId(), vo.getName());
		}
	}

	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}
	
	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}
	
}
