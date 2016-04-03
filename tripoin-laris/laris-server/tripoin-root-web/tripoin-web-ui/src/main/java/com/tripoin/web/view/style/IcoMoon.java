package com.tripoin.web.view.style;

import com.vaadin.server.FontIcon;

/**
* @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
*/
public enum IcoMoon implements FontIcon {

    PAYMENT_CART(0xe900),
    NOTIFICATION(0xe901),
    ORDER(0xe902),
    HOME(0xe903),
    HISTORY_TRANSACTION(0xe904);

    private int codepoint;
    private String fontFamily = "icomoon";

    IcoMoon(int codepoint) {
        this.codepoint = codepoint;
    }

    @Override
    public String getFontFamily() {
        return fontFamily;
    }

    @Override
    public int getCodepoint() {
        return codepoint;
    }

    @Override
    public String getHtml() {
        return "<span class=\"v-icon icomoon\">&#x"
                + Integer.toHexString(codepoint) + ";</span>";
    }

    @Override
    public String getMIMEType() {
        // Font icons are not real resources
        throw new UnsupportedOperationException(
                FontIcon.class.getSimpleName()
                        + " should not be used where a MIME type is needed.");
    }

}
