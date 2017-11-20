package com.batch.tasklets;

import com.batch.common.ExcelUtility;
import com.batch.common.PayphoneConstants;
import com.batch.dto.Payphone;
import com.batch.dto.ProcessedPayphone;
import com.batch.mapper.ProcessedPayphoneMapper;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

@Component
public class GenerateReportTasklet implements Tasklet {

    final static Logger logger = Logger.getLogger( GenerateReportTasklet.class );

    @Autowired
    @Qualifier("primaryDataSource")
    private JdbcTemplate jdbcTemplate;

    @Resource(name="payphone_sql")
    private HashMap<String,String> processedPayphoneSQL;

    private String targetDirectory = null;
    private String targetFileName = null;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        logger.info("Objects necessary for generating report not available!" +
                "\nJdbcTemplate: " + jdbcTemplate +
                "\nTarget Directory: " + targetDirectory +
                "\nTarget File:" + targetFileName );

        if( jdbcTemplate == null || targetDirectory == null || targetFileName == null ) {
           logger.error("Objects necessary for generating report not available!" +
                   "\nJdbcTemplate: " + jdbcTemplate +
                   "\nTarget Directory: " + targetDirectory +
                   "\nTarget File:" + targetFileName );
           return RepeatStatus.FINISHED;
        }
       List<ProcessedPayphone> processedPayphoneList = jdbcTemplate.query(processedPayphoneSQL.get("get_processed_payphone"), new ProcessedPayphoneMapper());

       Field[] fields = ProcessedPayphone.class.getDeclaredFields();
       String[] fieldNames = new String[ fields.length ];
       String[] columnHeaders = new String[ fields.length ];
       for(int k = 0; k < fieldNames.length; k++) {
           fieldNames[k] = fields[k].getName();
           columnHeaders[k] = fieldNames[k].toUpperCase();
       }
       ExcelUtility.generateSimpleExcelFile( columnHeaders, fieldNames, processedPayphoneList, PayphoneConstants.EXCEL_EXPORT_DIR, PayphoneConstants.EXCEL_EXPORT_FILENAME, "NYC Payphones");
       return RepeatStatus.FINISHED;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getTargetDirectory() {
        return targetDirectory;
    }

    public void setTargetDirectory(String targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

    public String getTargetFileName() {
        return targetFileName;
    }

    public void setTargetFileName(String targetFileName) {
        this.targetFileName = targetFileName;
    }
}
