package com.example.hackin.demo;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.hackin.demo.domain.User;


@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private UserResolver userResolver;

	@Override
	public Object resolveArgument(MethodParameter param, ModelAndViewContainer arg1, NativeWebRequest req,
			WebDataBinderFactory arg3) throws Exception {
		Optional<User> resolveUser = userResolver.resolveUser((HttpServletRequest) req.getNativeRequest());
		return resolveUser.orElseThrow(() -> {
			String methodName = param.getMethod().getName();
			String className = param.getContainingClass().getName();
			return new IllegalArgumentException(className + " declares a User argument in method " + methodName+ " but it is not authenticated, so there is no user");
		});
	}

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterType().equals(User.class);
	}

	@Configuration
	public static class ArgumentResolverConfiguration extends WebMvcConfigurerAdapter {
		@Autowired
		private UserArgumentResolver resolver;

		@Override
	    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
	        argumentResolvers.add(resolver);
	    }
	}

}
