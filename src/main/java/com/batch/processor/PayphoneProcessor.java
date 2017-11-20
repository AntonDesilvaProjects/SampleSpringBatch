package com.batch.processor;

import com.batch.common.PayphoneConstants;
import com.batch.dto.Payphone;
import com.batch.dto.ProcessedPayphone;
import org.springframework.batch.item.ItemProcessor;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PayphoneProcessor implements ItemProcessor<Payphone,ProcessedPayphone> {

    @Override
    public ProcessedPayphone process(Payphone payphone) throws Exception {
        ProcessedPayphone processedPayphone = new ProcessedPayphone();
        processedPayphone.setId( payphone.getId() );
        processedPayphone.setActive( payphone.isActive() );
        processedPayphone.setAddress( this.generateAddress( payphone ) );
        processedPayphone.setCompany( payphone.getCompany() );

        double[] latlon = this.extractLatLong( payphone.getPointStr() );
        processedPayphone.setLatitude( latlon[0] );
        processedPayphone.setLongitude( latlon[1] );

        processedPayphone.setGoogleMapsURL( this.getGoogleMapsURL( latlon[0], latlon[1] ) );
        return processedPayphone;
    }
    /*
        Generates an address based on the payphone object passed.
    */
    private String generateAddress( Payphone payphone )
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append( payphone.getBuilding() );
        buffer.append( payphone.getStreet() );
        buffer.append( payphone.getBorough() );
        buffer.append( payphone.getZoning() );
        buffer.append( "NY" );
        return buffer.toString();
    }
    /*
        Extracts the latitude and longitude from a point string
        in the following form: 'POINT (-73.96868384492147 40.75657795630701)'
        and return a double array where indx 0 is lat. and indx 1 is long.
    */
    public double[] extractLatLong( String rawPoint )
    {
        double[] coord = new double[2];
        String matchCoordPattern = "(-?\\d+(\\.\\d+)?)";
        Pattern coordPattern = Pattern.compile( matchCoordPattern );
        Matcher matches = coordPattern.matcher( rawPoint );

        for(int i = 1; i >= 0 && matches.find(); i--)
            coord[i] = Double.parseDouble( matches.group(0) );
        return coord;
    }
    /*
        Generates a Google maps URL based on the passed in latitude and
        longitude.
    */
    public String getGoogleMapsURL( double latitude, double longitude )
    {
        String googleMapUrl = PayphoneConstants.GOOGLE_MAPS_URL;
        googleMapUrl = googleMapUrl.replace("{0}", Double.toString( latitude ) );
        googleMapUrl = googleMapUrl.replace("{1}", Double.toString( longitude ) );
        return googleMapUrl;
    }
}
