package com.faqgaming.annotation;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.faqgaming.domain.User;
import com.faqgaming.domain.enums.Role;
import com.faqgaming.dto.TokenDto;
import com.faqgaming.exception.EntityNotFoundException;
import com.faqgaming.exception.FaqGamingException;
import com.faqgaming.exception.NotAuthenticatedException;
import com.faqgaming.exception.NotAuthorisedException;
import com.faqgaming.service.UserService;
import com.faqgaming.util.AuthUtils;
import com.faqgaming.util.Constants;

public class AuthenticatedArgumentResolver implements HandlerMethodArgumentResolver {

  private static final Logger LOGGER = Logger.getLogger("AuthenticatedArgumentResolver");

  @Autowired
  private UserService userService;

  @Override
  public User resolveArgument(MethodParameter param, ModelAndViewContainer mavContainer, NativeWebRequest request,
      WebDataBinderFactory binderFactory) throws Exception {
    Annotation[] paramAnns = param.getParameterAnnotations();
    for (Annotation annotation : paramAnns) {
      if (Authenticated.class.isInstance(annotation)) {
        Authenticated authAnnotation = (Authenticated) annotation;
        HttpServletRequest httprequest = (HttpServletRequest) request.getNativeRequest();
        String token = httprequest.getHeader(Constants.AUTH_TOKEN);
        return checkUser(authAnnotation, token);
      }
    }
    return null;
  }

  private User checkUser(Authenticated authAnnotation, String token) throws NotAuthenticatedException {
    TokenDto tokenDto = checkIfTokenEmpty(authAnnotation, token);
    return decodeUser(tokenDto, authAnnotation);
  }

  private User decodeUser(TokenDto token, Authenticated authAnnotation) throws NotAuthenticatedException {
    User user = null;
    try {
      user = getUser(token, user);
      if (user == null) {
        throw new EntityNotFoundException("User not found");
      }
      checkIfTokenInvalid(user.getInvalidTokens(), token.getPayload(), authAnnotation);
      LOGGER.info("Login Request - " + user.getId());
    } catch (FaqGamingException ex) {
      LOGGER.error(ex.getMessage());
      throwException(authAnnotation, ex.getMessage());
    }
    return user;
  }

  private User getUser(TokenDto token, User user) throws EntityNotFoundException {
    if (Role.USER.equals(token.getRole())) {
      user = userService.findOne(token.getPayload());
    }

    return user;
  }

  private void checkIfTokenInvalid(List<String> invalidTokens, String token, Authenticated authAnnotation)
      throws NotAuthenticatedException {
    if (!CollectionUtils.isEmpty(invalidTokens) && invalidTokens.contains(token)) {
      throwException(authAnnotation, "Token is invalid!");
    }
  }

  private TokenDto checkIfTokenEmpty(Authenticated authAnnotation, String token) throws NotAuthenticatedException {
    if (StringUtils.isEmpty(token)) {
      throwException(authAnnotation, "Token is empty!");
    }
    TokenDto tokenDto = new TokenDto();
    try {
      tokenDto = AuthUtils.decodeToken(token);
    } catch (NotAuthorisedException e) {
      throwException(authAnnotation, "Token is invalid or expired !");
    }
    return tokenDto;
  }

  private void throwException(Authenticated authAnnotation, String message) throws NotAuthenticatedException {
    if (authAnnotation.required()) {
      LOGGER.error(message);
      throw new NotAuthenticatedException(message);
    }
  }

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(Authenticated.class);
  }
}
