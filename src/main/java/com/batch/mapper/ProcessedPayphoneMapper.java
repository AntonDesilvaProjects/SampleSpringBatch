package com.batch.mapper;

import com.batch.dto.Payphone;
import com.batch.dto.ProcessedPayphone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcessedPayphoneMapper implements RowMapper<ProcessedPayphone> {

    @Override
    public ProcessedPayphone mapRow(ResultSet resultSet, int i) throws SQLException {
        ProcessedPayphone payphone = new ProcessedPayphone();
        payphone.setId( resultSet.getInt("id"));
        payphone.setActive(  resultSet.getBoolean("isActive") );
        payphone.setCompany( resultSet.getString("company"));
        payphone.setLongitude( resultSet.getDouble("longitude"));
        payphone.setLatitude( resultSet.getDouble("latitude"));
        payphone.setAddress( resultSet.getString("address"));
        payphone.setGoogleMapsURL( resultSet.getString("googleMapsUrl"));
        return payphone;
    }
}
