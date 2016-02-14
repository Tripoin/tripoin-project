package com.tripoin.web.view.base.container;

import java.util.ArrayList;
import java.util.List;

import com.tripoin.web.view.base.ITripoinConstantComponent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

/**
* @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
*/
public abstract class ATripoinMenuItemGridDefault<T> {

	private MenuItem menuItemCreate;
	private MenuItem menuItemDelete;
	private MenuItem menuItemExport;
	private MenuItem menuItemExportSelected;
	private List<T> dataObjectSelect = new ArrayList<T>();

	public ATripoinMenuItemGridDefault() {
		menuBarOnGrid();
	}
	
	protected void menuBarOnGrid(){
		final MenuItem itemMenuGrid = getMenuBar().addItem("", FontAwesome.COG, null);
        itemMenuGrid.setStyleName("icon-only");
        menuItemCreate = itemMenuGrid.addItem(ITripoinConstantComponent.MenuItem.CREATE, doCreate());
        menuItemExport = itemMenuGrid.addItem("Export", null);
        menuItemExport.addItem(ITripoinConstantComponent.MenuItem.EXPORT_ALL, doGenerateReportAll());
        menuItemExportSelected = menuItemExport.addItem(ITripoinConstantComponent.MenuItem.EXPORT_SELECTED, doGenerateReportSelected());
        menuItemExportSelected.setEnabled(false);
        itemMenuGrid.addSeparator();
        menuItemDelete = itemMenuGrid.addItem(ITripoinConstantComponent.MenuItem.DELETE, FontAwesome.TRASH_O, doDelete());
        menuItemDelete.setEnabled(false);
	}
	
	protected abstract MenuBar getMenuBar();
	protected abstract Command doGenerateReportAll();
	protected abstract Command doGenerateReportSelected();
	protected abstract Command doCreate();
	protected abstract Command doDelete();

	public MenuItem getMenuItemCreate() {
		return menuItemCreate;
	}

	public void setMenuItemCreate(MenuItem menuItemCreate) {
		this.menuItemCreate = menuItemCreate;
	}

	public MenuItem getMenuItemDelete() {
		return menuItemDelete;
	}

	public void setMenuItemDelete(MenuItem menuItemDelete) {
		this.menuItemDelete = menuItemDelete;
	}

	public MenuItem getMenuItemExport() {
		return menuItemExport;
	}

	public void setMenuItemExport(MenuItem menuItemExport) {
		this.menuItemExport = menuItemExport;
	}

	public MenuItem getMenuItemExportSelected() {
		return menuItemExportSelected;
	}

	public void setMenuItemExportSelected(MenuItem menuItemExportSelected) {
		this.menuItemExportSelected = menuItemExportSelected;
	}

	public List<T> getDataObjectSelect() {
		return dataObjectSelect;
	}

	public void setDataObjectSelect(List<T> dataObjectSelect) {
		this.dataObjectSelect = dataObjectSelect;
	}
	
}
