package com.example.demo.service;

import com.example.demo.model.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class SchedulerService {

    @Autowired
    GenericService genericService;

    Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    @Transactional
    @Scheduled(cron="${cron.cleansing.audittrail}")
    public void cleansingAuditTrail() {
        logger.info("cleansing audit trail is running . . .");
        try
        {
            genericService.deleteByJdbc("DELETE FROM BACKEND_AUDIT_TRAIL where HIT_DATE < SYSDATE - 13");
            logger.info("cleansing audit trail done . . .");
        }
        catch (InvalidResultSetAccessException e)
        {
            throw new RuntimeException(e);
        }
        catch (DataAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(cron="${cron.delete.log.file}")
    public void deleteLogFile() throws ParseException {
        logger.info("delete file log is running . . .");
        File fileLogApplication = new File(Constants.pathLogApplication);

        /* delele application log */
        deleteFileUnmdofiedMoreThanOneMoth(fileLogApplication.listFiles(), 1, Constants.fileLogApplication);
    }

    public void deleteFileUnmdofiedMoreThanOneMoth(File[] files, int minusMonth, String fileName) throws ParseException {
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");

        /* now - minusMonth */
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -minusMonth);
        Date dateMinusMonth = cal.getTime();

        for (File file : files) {
            /*Convert milliseconds into readable date time format*/
            Date lastModifiedDate = format.parse(format.format(file.lastModified()));
            if (file.isDirectory()) {
                deleteFileUnmdofiedMoreThanOneMoth(file.listFiles(), minusMonth, fileName);
            } else {
                if(lastModifiedDate.before(dateMinusMonth) && file.getName().startsWith(fileName)){
                    file.delete();
                    logger.info("File "+file.getName()+" deleted successfully");
                }
            }
        }
    }
}
