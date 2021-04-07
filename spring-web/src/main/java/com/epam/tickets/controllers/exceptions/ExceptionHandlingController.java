package com.epam.tickets.controllers.exceptions;

import com.epam.tickets.exceptions.InvalidTicketException;
import com.epam.tickets.exceptions.InvalidUserException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

//@Controller
public class ExceptionHandlingController {

  private static final Logger logger = LogManager.getLogger(ExceptionHandlingController.class);

//  SimpleMappingExceptionResolver
//  HandlerExceptionResolver

  // @RequestHandler methods
  // ...

  // Exception handling methods

  // Convert a predefined exception to an HTTP Status code
  @ResponseStatus(value= HttpStatus.CONFLICT, reason="User operation error")  // 409
  @ExceptionHandler(InvalidUserException.class)
  public ModelAndView  conflict(HttpServletRequest req, Exception ex) {
    logger.error("Request: " + req.getRequestURL() + " raised " + ex);

    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", ex);
    mav.addObject("url", req.getRequestURL());
    mav.setViewName("error");
    return mav;
  }

  // Specify name of a specific view that will be used to display the error:
  @ExceptionHandler({SQLException.class, InvalidTicketException.class})
  public String databaseError() {
    // Nothing to do.  Returns the logical view name of an error page, passed
    // to the view-resolver(s) in usual way.
    // Note that the exception is NOT available to this view (it is not added
    // to the model) but see "Extending ExceptionHandlerExceptionResolver"
    // below.
    return "databaseError";
  }

  // Total control - setup a model and return the view name yourself. Or
  // consider subclassing ExceptionHandlerExceptionResolver (see below).
  @ExceptionHandler(Exception.class)
  public ModelAndView handleError(HttpServletRequest req, Exception ex) {
    logger.error("Request: " + req.getRequestURL() + " raised " + ex);

    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", ex);
    mav.addObject("url", req.getRequestURL());
    mav.setViewName("error");
    return mav;
  }

}

/*
 * Source:
 * https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
 */

// #2
//https://www.baeldung.com/exception-handling-for-rest-with-spring
