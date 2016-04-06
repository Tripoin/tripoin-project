package com.tripoin.web.view.style;

import com.vaadin.server.FontIcon;

/**
* @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
*/
public enum IcoMoon implements FontIcon {

    PAYMENT_CART(0xe900, "PaymentCartWeb"),
    NOTIFICATION(0xe901, "NotificationWeb"),
    ORDER(0xe902, "OrderWeb"),
    HOME(0xe903, "HomeWeb"),
    HISTORY_TRANSACTION(0xe904, "HistoryTransactionWeb");

    private int codepoint;
    private String fontFamily = "icomoon";
    private String icon;

    IcoMoon(int codepoint, String icon) {
        this.codepoint = codepoint;
        this.icon = icon;
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
        return "<span class=\"v-icon v-icon-" + icon
                + "\" style=\"font-family: " + fontFamily + ";\">&#x"
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
