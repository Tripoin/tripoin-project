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
@VaadinView(value = OrderSellerView.BEAN_NAME, cached = false)
public class OrderSellerView extends VerticalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2273624883858209189L;
	public static final String BEAN_NAME = "order";

	@Override
	public void enter(ViewChangeEvent event) {
	}
	
}
