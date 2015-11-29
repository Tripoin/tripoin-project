package com.tripoin.phonenetwork.gps;

import android.location.Address;
import android.location.Location;

import java.util.List;

/**
 * Created on 5/29/2015 : 3:38 PM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IGPSInformation {

    public void stopUsingGPS();

    public boolean isGetLocation();

    public void showGPSSettingAlert();

    public float getGpsAccuracy();

    public void updateGPSCoordinates();

    public Location getLocation();

    public double getLatitude();

    public double getLongitude();

    public List<Address> getGeocoderAddress();

    public String getAddressLine();

    public String getLocality();

    public String getPostalCode();

    public String getCountryName();
}
