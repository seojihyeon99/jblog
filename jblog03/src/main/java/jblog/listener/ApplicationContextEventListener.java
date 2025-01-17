//package jblog.listener;
//
//import org.springframework.beans.MutablePropertyValues;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.GenericBeanDefinition;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.context.event.EventListener;
//
//import jblog.service.BlogService;
//import jblog.vo.BlogVo;
//
//public class ApplicationContextEventListener {
//	@Autowired
//	private ApplicationContext applicationContext;
//	
//	@EventListener({ContextRefreshedEvent.class})
//	public void handlerContextRefreshedEvent() {
//		BlogService blogService = applicationContext.getBean(BlogService.class);
//		BlogVo vo = blogService.getBlog();
//		
//		MutablePropertyValues propertyValues = new MutablePropertyValues();
//		propertyValues.add("blogId", vo.getBlogId());
//		propertyValues.add("title", vo.getTitle());
//		propertyValues.add("profile", vo.getProfile());
//		
//		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//		beanDefinition.setBeanClass(BlogVo.class);
//		beanDefinition.setPropertyValues(propertyValues);
//		
//		BeanDefinitionRegistry registry = (BeanDefinitionRegistry)applicationContext.getAutowireCapableBeanFactory();
//		registry.registerBeanDefinition("BlogVo", beanDefinition);
//	}
//
//}
