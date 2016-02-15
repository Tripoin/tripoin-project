package com.tripoin.web.view.base.container;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextField;

/**
* @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
*/
public class TripoinDataField {

	public static Map<String, Object> getDataField(HasComponents component, boolean isResetField) {
		Map<String, Object> searchPanelDatas = new HashMap<String, Object>();
		if(getDataField(component, isResetField, searchPanelDatas).isEmpty())
			return null;
		return searchPanelDatas;
	}
	
	private static Map<String, Object> getDataField(HasComponents component, boolean isResetField, Map<String, Object> searchPanelDatas) {
		for (Component cmpnnt : component){
			try {
				if(cmpnnt instanceof TextField){
					TextField textField = (TextField)cmpnnt;
					if(isResetField){
						textField.setValue("");
					}else{
						if(textField.getValue() != null && !textField.getValue().isEmpty()){
							searchPanelDatas.put(textField.getId(), textField.getValue());
						}
					}
				}else if(cmpnnt instanceof DateField){
					DateField dateField = (DateField)cmpnnt;
					if(isResetField) {
						dateField.setValue(new Date());
					}else{
						if(dateField.getValue() != null){
							searchPanelDatas.put(dateField.getId(), dateField.getValue());
						}
					}
				}else if(cmpnnt instanceof ComboBox){
					ComboBox comboBox = (ComboBox)cmpnnt;
					if(isResetField) {
						comboBox.setValue(null);
					}else{
						if(comboBox.getValue() != null){
							searchPanelDatas.put(comboBox.getId(), comboBox.getValue());
						}
					}	
				}else if(cmpnnt instanceof CheckBox){
					CheckBox checkBox = (CheckBox)cmpnnt;
					if(isResetField) {
						checkBox.setValue(null);
					}else{
						if(checkBox.getValue() != null){
							searchPanelDatas.put(checkBox.getId(), checkBox.getValue());
						}
					}
				}else if(cmpnnt instanceof Slider){
					Slider slider = (Slider)cmpnnt;
					if(isResetField) {
						slider.setValue(new Double(0));;
					}else{
						if(slider.getValue() != null){
							searchPanelDatas.put(slider.getId(), slider.getValue());
						}
					}
				}else if(cmpnnt instanceof AbstractLayout){
					getDataField((AbstractLayout)cmpnnt, isResetField, searchPanelDatas);
				}
			} catch (ClassCastException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return searchPanelDatas;
	}
	
}
