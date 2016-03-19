package com.tripoin.phone.network.gps;

import android.location.Address;
import android.location.Location;

import java.util.List;

/**
 * <p>
 *     Retrieve GPS information from whether from Network Provider or GPS Provider
 * </p>
 *
 * Created on 5/29/2015 : 3:38 PM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IGPSInformation {

    void stopUsingGPS();

    boolean isGetLocation();

    void showGPSSettingAlert();

    float getGpsAccuracy();

    void updateGPSCoordinates();

    Location getLocation();

    double getLatitude();

    double getLongitude();

    List<Address> getGeocoderAddress();

    String getAddressLine();

    String getLocality();

    String getPostalCode();

    String getCountryName();
}
