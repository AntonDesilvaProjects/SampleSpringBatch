package com.batch.writer;

import com.batch.dto.Payphone;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

@Component
public class PayphoneRawDataWriter implements ItemWriter<Payphone> {

    @Resource(name="payphone_sql")
    HashMap<String,String> payphoneSQL;
    JdbcTemplate jdbcTemplate;

    public PayphoneRawDataWriter()
    {}

    public PayphoneRawDataWriter(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate( dataSource);
    }

    @Override
    public void write(List<? extends Payphone> payphoneList) throws Exception {
       for(Payphone payphone : payphoneList )
       {
           int updateCount = jdbcTemplate.update(
                   payphoneSQL.get("update_raw_payphone"), payphone.getId(),
                                                                        payphone.getCompany(),
                                                                       payphone.getLatitude(),
                                                                       payphone.getLongitude(),
                                                                       payphone.getBorough(),
                                                                       payphone.getBuilding(),
                                                                       payphone.getStreet(),
                                                                       payphone.isActive(),
                                                                       payphone.getZoning(),
                                                                       payphone.getPointStr(),
                                                                       payphone.getId()
           );
           if( updateCount == 0 )
               jdbcTemplate.update( payphoneSQL.get("insert_raw_payphone"), payphone.getId(),
                       payphone.getCompany(),
                       payphone.getLatitude(),
                       payphone.getLongitude(),
                       payphone.getBorough(),
                       payphone.getBuilding(),
                       payphone.getStreet(),
                       payphone.isActive(),
                       payphone.getZoning(),
                       payphone.getPointStr());
       }
    }
}
