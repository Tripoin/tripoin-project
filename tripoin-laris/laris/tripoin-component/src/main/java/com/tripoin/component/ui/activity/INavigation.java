package com.tripoin.component.ui.activity;


/**
 * <p>
 *      This base navigation can be used both in Activity and Fragment
 * </p>
 *
 * Created by Achmad Fauzi on 5/7/2015 : 4:53 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 *
 */
public interface INavigation {

    /**
     * <p>
     *     This method is used to access main key from current active Context
     * </p>
     * @param p_ExtraKey String
     * @param p_ExtraContent String
     */
    void goToMainView(String p_ExtraKey, String p_ExtraContent);

    /**
     * <p>
     *     This method is used to exit Application from active Context
     * </p>
     */
    void exitApplication();

}
