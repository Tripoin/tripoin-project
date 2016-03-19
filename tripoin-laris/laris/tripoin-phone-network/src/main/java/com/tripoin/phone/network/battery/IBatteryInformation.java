package com.tripoin.phone.network.battery;

/**
 * <p>
 *     Retrieve Information of Battery within device
 * </p>
 * Created on 5/29/2015 : 5:46 PM.
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IBatteryInformation {

    /**
     *
     * @return Level of Battery
     */
    float getBatteryLevel();

    /**
     *
     * @return String health condition of battery
     */
    String getBatteryHealth();
}
