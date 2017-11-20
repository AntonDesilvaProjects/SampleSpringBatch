package com.batch.mapper;

import com.batch.dto.Payphone;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class PayphoneFieldSetMapper implements FieldSetMapper<Payphone> {
    @Override
    public Payphone mapFieldSet(FieldSet fieldSet) throws BindException {
        Payphone payphone = new Payphone();

        payphone.setId( fieldSet.readInt("id"));
        payphone.setActive( ( fieldSet.readString("active").equals("Y") ) ? true : false );
        payphone.setBorough( fieldSet.readString("boro") );
        payphone.setBuilding( fieldSet.readString("building"));
        payphone.setCompany( fieldSet.readString("company"));
        payphone.setZoning( fieldSet.readString("zoning"));
        payphone.setPointStr( fieldSet.readString("the_geom"));
        payphone.setStreet( fieldSet.readString("street"));

        return payphone;
    }
}
