package com.tripoin.tripoin_service;

/**
 * Created on 9/28/2015 : 10:33 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class TRIPOINISParam<VIEW> {

    private Class<VIEW> viewClass;

    public Class<VIEW> getViewClass() {
        return viewClass;
    }

    public void setViewClass(Class<VIEW> viewClass) {
        this.viewClass = viewClass;
    }
}
