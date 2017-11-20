package com.batch.mapper;

import com.batch.dto.Payphone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PayphoneRowMapper implements RowMapper<Payphone> {

    @Override
    public Payphone mapRow(ResultSet resultSet, int i) throws SQLException {
        Payphone payphone = new Payphone();

        payphone.setId( resultSet.getInt("id"));
        payphone.setActive(  resultSet.getBoolean("isActive") );
        payphone.setBorough( resultSet.getString("boro") );
        payphone.setBuilding( resultSet.getString("building"));
        payphone.setCompany( resultSet.getString("company"));
        payphone.setZoning( resultSet.getString("zoning"));
        payphone.setPointStr( resultSet.getString("pointStr"));
        payphone.setStreet( resultSet.getString("street"));

        return payphone;
    }
}
