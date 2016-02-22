package com.tripoin.phonenetwork.gps.impl;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.tripoin.phonenetwork.gps.IGPSInformation;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * Created on 5/29/2015 : 3:37 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class GPSInformationImpl implements IGPSInformation, LocationListener {

    protected LocationManager locationManager;
    private boolean isGPSEnabled = false;
    private boolean isNetworkEnabled = false;
    private boolean canGetLocation = false;
    protected Location location = null;
    protected double latitude;
    protected double longitude;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
    private Context context;
    private float gpsAccuracy = 0;

    public GPSInformationImpl(LocationManager locationManager, Context context) {
        this.locationManager = locationManager;
        this.context = context;
        getLocation();
    }

    @Override
    public Location getLocation() {
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!isGPSEnabled && !isNetworkEnabled) {
            // no network provider is enabled
        } else {
            this.canGetLocation = true;
            if (isNetworkEnabled) {
                if (location == null) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                }
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    updateGPSCoordinates();
                }
            } else if (isGPSEnabled) {
                if (location == null) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        updateGPSCoordinates();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void stopUsingGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates((LocationListener) this);
        }
    }

    @Override
    public boolean isGetLocation() {
        return this.canGetLocation;
    }

    @Override
    public void showGPSSettingAlert() {
    }

    @Override
    public void updateGPSCoordinates() {
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
    }

    @Override
    public double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude();
        } else {
            location = getLocation();
            if (location != null) {
                latitude = location.getLatitude();
            }
        }
        return latitude;
    }

    @Override
    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        } else {
            location = getLocation();
            if (location != null) {
                longitude = location.getLongitude();
            }
        }
        return longitude;
    }

    @Override
    public List<Address> getGeocoderAddress() {
        List<Address> addresses = null;
        if (location != null) {
            Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return addresses;
    }

    @Override
    public String getAddressLine() {
        List<Address> addresses = getGeocoderAddress();
        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);

            return address.getAddressLine(0);
        } else {
            return null;
        }
    }

    @Override
    public String getLocality() {
        List<Address> addresses = getGeocoderAddress();
        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);
            return address.getLocality();
        } else {
            return null;
        }
    }

    @Override
    public String getPostalCode() {
        List<Address> addresses = getGeocoderAddress();
        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);

            return address.getPostalCode();
        } else {
            return null;
        }
    }

    @Override
    public String getCountryName() {
        List<Address> addresses = getGeocoderAddress();
        if (addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);
            return address.getCountryName();
        } else {
            return null;
        }
    }

    /*Location Listener Implementation*/

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            gpsAccuracy = location.getAccuracy();
        }
    }

    @Override
    public float getGpsAccuracy() {
        if (gpsAccuracy == 0) {
            if (location != null) {
                gpsAccuracy = location.getAccuracy();
            }
        }
        return gpsAccuracy;
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
