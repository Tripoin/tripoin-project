package com.tripoin.web.view.base.container.event;

import com.tripoin.web.common.EWebSessionConstant;
import com.tripoin.web.view.base.container.ATripoinMenuItemGridDefault;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;

/**
* @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
*/
public abstract class AMenuItemGridEventDefault<T> {
	
	private Integer indexSelected = null;

	public AMenuItemGridEventDefault() {
		gridClickEvent();
		gridSelectEvent();
	}
	
	protected void gridClickEvent() {
		getGrid().addItemClickListener(new ItemClickListener() {
			private static final long serialVersionUID = -2614893307330224109L;
			@Override
			public void itemClick(ItemClickEvent event) {
				boolean isSelectEdited = false;
				for(Object object : getFieldHeader()){
					if(object.equals(event.getPropertyId())){
						getGrid().getSelectionModel().reset();
						indexSelected = getGrid().getContainerDataSource().indexOfId(event.getItemId());
						isSelectEdited = true; break;
					}
				}
				if(isSelectEdited){
					isSelectEdited = false;
					VaadinSession.getCurrent().getSession().setAttribute(EWebSessionConstant.SESSION_GRID_DATA.toString(), event.getItemId());					
					UI.getCurrent().getNavigator().navigateTo(getClickNavigate());
				}
			}
		});
	}
	
	protected void gridSelectEvent() {
		getGrid().addSelectionListener(new SelectionListener() {
			private static final long serialVersionUID = -6491823805538480108L;
			@SuppressWarnings("unchecked")
			@Override
			public void select(SelectionEvent event) {
				indexSelected = null;
				if(event.getSelected().isEmpty()){
					if(getTripoinMenuItemGridDefault().getMenuItemDelete().isEnabled())getTripoinMenuItemGridDefault().getMenuItemDelete().setEnabled(false);
					if(getTripoinMenuItemGridDefault().getMenuItemExportSelected().isEnabled())getTripoinMenuItemGridDefault().getMenuItemExportSelected().setEnabled(false);
				}else{
					if(!getTripoinMenuItemGridDefault().getMenuItemDelete().isEnabled())getTripoinMenuItemGridDefault().getMenuItemDelete().setEnabled(true);
					if(!getTripoinMenuItemGridDefault().getMenuItemExportSelected().isEnabled())getTripoinMenuItemGridDefault().getMenuItemExportSelected().setEnabled(true);
				}		
				getTripoinMenuItemGridDefault().getDataObjectSelect().clear();
				for(Object object : event.getSelected())
					getTripoinMenuItemGridDefault().getDataObjectSelect().add((T)object);
			}
		});
	}

	public Integer getIndexSelected() {
		return indexSelected;
	}
	
	public void setIndexSelected(Integer indexSelected) {
		this.indexSelected = indexSelected;
	}

	protected abstract Grid getGrid();
	protected abstract ATripoinMenuItemGridDefault<T> getTripoinMenuItemGridDefault();
	protected abstract Object[] getFieldHeader();
	protected abstract String getClickNavigate();
	
}
