package com.VKR.studyPlatform.controllers;

import com.VKR.studyPlatform.dao.GoodsDao;
import com.VKR.studyPlatform.dao.OrderDao;
import com.VKR.studyPlatform.dao.ReserveDao;
import com.VKR.studyPlatform.dao.UsersDao;
import com.VKR.studyPlatform.models.Good;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class errorController {

   /* @ControllerAdvice
    public class ErrorController {

        private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

        @ExceptionHandler(Throwable.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public String exception(final Throwable throwable, final Model model) {
            logger.error("Exception during execution of SpringSecurity application", throwable);
            String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
            model.addAttribute("errorMessage", errorMessage);
            return "error";
        }

    }*/

}
