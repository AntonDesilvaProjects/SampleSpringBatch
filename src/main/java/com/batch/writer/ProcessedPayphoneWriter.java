package com.batch.writer;

import com.batch.dto.Payphone;
import com.batch.dto.ProcessedPayphone;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

public class ProcessedPayphoneWriter implements ItemWriter<ProcessedPayphone> {

    @Resource(name="payphone_sql")
    private HashMap<String,String> processDataSqlMap;
    private JdbcTemplate jdbcTemplate;

    public ProcessedPayphoneWriter(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate( dataSource );
    }
    @Override
    public void write(List<? extends ProcessedPayphone> list) throws Exception {
        for(ProcessedPayphone processedPayphone : list )
        {
            int updateCount = jdbcTemplate.update( processDataSqlMap.get("update_processed_payphone"),
                                                        processedPayphone.getId(),
                                                        processedPayphone.isActive(),
                                                        processedPayphone.getCompany(),
                                                        processedPayphone.getLatitude(),
                                                        processedPayphone.getLongitude(),
                                                        processedPayphone.getAddress(),
                                                        processedPayphone.getGoogleMapsURL(),
                                                        processedPayphone.getId() );
            if( updateCount == 0 )
            {
                jdbcTemplate.update( processDataSqlMap.get("insert_processed_payphone"),
                                                        processedPayphone.getId(),
                                                        processedPayphone.isActive(),
                                                        processedPayphone.getCompany(),
                                                        processedPayphone.getLatitude(),
                                                        processedPayphone.getLongitude(),
                                                        processedPayphone.getAddress(),
                                                        processedPayphone.getGoogleMapsURL() );
            }
        }
    }
}
