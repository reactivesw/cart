package io.reactivesw.cart.infrastructure.exception;

import io.reactivesw.exception.handler.ExceptionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cart exception handler.
 */
@Component
public class CartExceptionHandler extends ExceptionHandler implements HandlerExceptionResolver {

  /**
   * exception resolver.
   *
   * @param request  HttpServletRequest
   * @param response HttpServletResponse
   * @param handler  Object
   * @param ex       Exception
   * @return ModelAndView
   */
  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                       Object handler, Exception ex) {
    setResponse(request, response, handler, ex);
    return new ModelAndView();
  }
}
