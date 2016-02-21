package com.tripoin.web.view.base.container;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tripoin.core.common.ParameterConstant;
import com.vaadin.server.ErrorMessage;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextField;

/**
* @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
*/
public class TripoinDataField {

	public static Map<String, Object> getDataField(HasComponents component, boolean isResetField) {
		Map<String, Object> searchPanelDatas = new HashMap<String, Object>();
		if(dataField(component, isResetField, searchPanelDatas, null).isEmpty())
			return null;
		return searchPanelDatas;
	}

	public static void setErrorComponents(HasComponents component, Map<String, ErrorMessage> errorComponents) {
		dataField(component, false, null, errorComponents);
	}
	
	private static Map<String, Object> dataField(HasComponents component, boolean isResetField, Map<String, Object> dataFields, Map<String, ErrorMessage> errorComponents) {
		for (Component cmpnnt : component){
			try {
				if(cmpnnt instanceof TextField){
					TextField textField = (TextField)cmpnnt;
					if(errorComponents != null){
						if(errorComponents.containsKey(textField.getId()))
							textField.setComponentError(errorComponents.get(textField.getId()));
						else
							textField.setComponentError(null);
					}else{
						if(isResetField){
							textField.setValue("");
							textField.setComponentError(null);
						}else{
							if(textField.getValue() != null && !textField.getValue().isEmpty()){
								dataFields.put(textField.getId(), textField.getValue());
							}
						}
						
					}
				}else if(cmpnnt instanceof DateField){
					DateField dateField = (DateField)cmpnnt;
					if(errorComponents != null){
						if(errorComponents.containsKey(dateField.getId()))
							dateField.setComponentError(errorComponents.get(dateField.getId()));
						else
							dateField.setComponentError(null);
					}else{
						if(isResetField) {
							dateField.setValue(new Date());
							dateField.setComponentError(null);
						}else{
							if(dateField.getValue() != null){
								dataFields.put(dateField.getId(), ParameterConstant.FORMAT_DEFAULT.format(dateField.getValue()));
							}
						}
					}
				}else if(cmpnnt instanceof ComboBox){
					ComboBox comboBox = (ComboBox)cmpnnt;
					if(errorComponents != null){
						if(errorComponents.containsKey(comboBox.getId()))
							comboBox.setComponentError(errorComponents.get(comboBox.getId()));
						else
							comboBox.setComponentError(null);
					}else{
						if(isResetField) {
							comboBox.setValue(null);
							comboBox.setComponentError(null);
						}else{
							if(comboBox.getValue() != null){
								dataFields.put(comboBox.getId(), comboBox.getValue());
							}
						}
					}	
				}else if(cmpnnt instanceof CheckBox){
					CheckBox checkBox = (CheckBox)cmpnnt;
					if(errorComponents != null){
						if(errorComponents.containsKey(checkBox.getId()))
							checkBox.setComponentError(errorComponents.get(checkBox.getId()));
						else
							checkBox.setComponentError(null);
					}else{
						if(isResetField) {
							checkBox.setValue(null);
							checkBox.setComponentError(null);
						}else{
							if(checkBox.getValue() != null){
								dataFields.put(checkBox.getId(), checkBox.getValue());
							}
						}
					}
				}else if(cmpnnt instanceof Slider){
					Slider slider = (Slider)cmpnnt;
					if(errorComponents != null){
						if(errorComponents.containsKey(slider.getId()))
							slider.setComponentError(errorComponents.get(slider.getId()));
						else
							slider.setComponentError(null);
					}else{
						if(isResetField) {
							slider.setValue(new Double(0));
							slider.setComponentError(null);
						}else{
							if(slider.getValue() != null){
								dataFields.put(slider.getId(), slider.getValue());
							}
						}
					}
				}else if(cmpnnt instanceof OptionGroup){
					OptionGroup optionGroup = (OptionGroup)cmpnnt;
					if(errorComponents != null){
						if(errorComponents.containsKey(optionGroup.getId()))
							optionGroup.setComponentError(errorComponents.get(optionGroup.getId()));
						else
							optionGroup.setComponentError(null);
					}else{
						if(isResetField){
							optionGroup.setValue(null);
							optionGroup.setComponentError(null);
						}else{
							if(optionGroup.getValue() != null){
								dataFields.put(optionGroup.getId(), optionGroup.getValue());
							}
						}
						
					}
				}else if(cmpnnt instanceof AbstractLayout){
					dataField((AbstractLayout)cmpnnt, isResetField, dataFields, errorComponents);
				}
			} catch (ClassCastException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dataFields;
	}
	
}
