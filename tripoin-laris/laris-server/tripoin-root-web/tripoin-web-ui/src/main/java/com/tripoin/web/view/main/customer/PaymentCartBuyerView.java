package com.tripoin.web.view.main.customer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.web.servlet.VaadinView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

/**
* @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
*/
@Component
@Scope("prototype")
@VaadinView(value = PaymentCartBuyerView.BEAN_NAME, cached = false)
public class PaymentCartBuyerView extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7226119674721485579L;
	public static final String BEAN_NAME = "paymentCart";

	@Override
	public void enter(ViewChangeEvent event) {
	}
	
}
