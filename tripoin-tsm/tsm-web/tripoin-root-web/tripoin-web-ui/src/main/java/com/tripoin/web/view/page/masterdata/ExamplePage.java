package com.tripoin.web.view.page.masterdata;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.web.servlet.VaadinView;
import com.tripoin.web.view.base.ATripoinForm;
import com.tripoin.web.view.page.masterdata.occupation.DataOccupationView;

/**
 * @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = ExamplePage.BEAN_NAME, cached = false)
public class ExamplePage extends ATripoinForm {

	private static final long serialVersionUID = -4592518571070450190L;
	public static final String BEAN_NAME = "examplePage";

	@Override
	protected List<com.vaadin.ui.Component> designFormComponent() {
		return null;
	}

	@Override
	protected void doOkButtonEvent() {
		super.doOkButtonEvent();
	}

	@Override
	protected void doCancelEvent() {
		super.doCancelEvent();
	}

	@Override
	protected String getPageTitle() {
		return "Data Occupation";
	}

	@Override
	protected String afterButtonClickNavigate() {
		return DataOccupationView.BEAN_NAME;
	}

	@Override
	protected Class<? extends ATripoinForm> getViewClass() {
		return this.getClass();
	}

}
