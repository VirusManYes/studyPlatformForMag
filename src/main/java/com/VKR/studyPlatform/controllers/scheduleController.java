package com.VKR.studyPlatform.controllers;

import com.VKR.studyPlatform.dao.ReserveDao;
import com.VKR.studyPlatform.models.Reserve;
import com.VKR.studyPlatform.models.Good;
import com.VKR.studyPlatform.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@EnableScheduling
@Controller
public class scheduleController {

    @Autowired
    private ReserveDao reserveDao;

    @Scheduled(fixedDelay = 60000*5)
    public void deleteReserve(){
        List<Reserve> outReserves = reserveDao.getOutOfDeadlineReserves(Calendar.getInstance().getTime());
        for (Reserve r:outReserves) {
            reserveDao.removeReserve(r);
        }
    }

    public Date getDeadlineToBook(){
        Calendar deadlineToBook = Calendar.getInstance();
        return deadlineToBook.getTime();
    }
}
