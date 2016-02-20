package com.tripoin.web.view.base.container;

import java.util.ArrayList;
import java.util.List;

import com.tripoin.core.dto.GeneralPagingTransferObject;
import com.tripoin.web.common.EWebUIConstant;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

public abstract class ATripoinPageable<T> {

	private GeneralPagingTransferObject<T> generalPagingTransferObject = new GeneralPagingTransferObject<T>() {
		@Override
		public List<T> getDatas() {
			return new ArrayList<T>();
		}
	};
	
	public ATripoinPageable() {
		refreshPageable();
	}
	
	public ATripoinPageable(GeneralPagingTransferObject<T> generalPagingTransferObject) {
		this.generalPagingTransferObject = generalPagingTransferObject;
	}

	protected void initPaging() {
		this.getMenuBar().removeItems();
		if (generalPagingTransferObject.getTotalPage() > 1) {
			Command eventPageClick = new Command() {
				private static final long serialVersionUID = 2102926468458330518L;
				@Override
				public void menuSelected(MenuItem selectedItem) {
					selectedItem.setCheckable(true);
					selectedItem.setChecked(true);
					if ("".equals(selectedItem.getText()) || selectedItem.getText().isEmpty() || selectedItem.getText() == null) {
						if (FontAwesome.BACKWARD == selectedItem.getIcon())
							generalPagingTransferObject.setPositionPage(1);
						else if (FontAwesome.CARET_LEFT == selectedItem.getIcon()) {
							generalPagingTransferObject.setPositionPage(generalPagingTransferObject.getPositionPage()-1);
							if (generalPagingTransferObject.getPositionPage() < 1)
								generalPagingTransferObject.setPositionPage(1);
						} else if (FontAwesome.CARET_RIGHT == selectedItem.getIcon()) {
							generalPagingTransferObject.setPositionPage(generalPagingTransferObject.getPositionPage()+1);
							if (generalPagingTransferObject.getPositionPage() > generalPagingTransferObject.getTotalPage())
								generalPagingTransferObject.setPositionPage(generalPagingTransferObject.getTotalPage());
						} else
							generalPagingTransferObject.setPositionPage(generalPagingTransferObject.getTotalPage());
					} else {
						try {
							generalPagingTransferObject.setPositionPage(Integer.parseInt(selectedItem.getText()));
						} catch (Exception e) {
							generalPagingTransferObject.setPositionPage(1);
						}
					}
					if (generalPagingTransferObject.getPositionPage() > 0) {
						refreshPageable();
					}
				}
			};
			Integer posMin = generalPagingTransferObject.getPositionPage() - (new Double(EWebUIConstant.BUTTON_PAGING.getOperatorInt()/2).intValue());
			if (posMin < 1) posMin = 1;
			Integer posMax = generalPagingTransferObject.getPositionPage() + (new Double(EWebUIConstant.BUTTON_PAGING.getOperatorInt()/2).intValue());
			if (posMax > generalPagingTransferObject.getTotalPage())
				posMax = generalPagingTransferObject.getTotalPage();
			if (generalPagingTransferObject.getPositionPage() > 1) {
				if (posMin > 1)
					this.getMenuBar().addItem("", FontAwesome.BACKWARD,eventPageClick);
				this.getMenuBar().addItem("", FontAwesome.CARET_LEFT,eventPageClick);
			}
			for (Integer i = posMin; i <= posMax; i++) {
				if (i == generalPagingTransferObject.getPositionPage()) {
					MenuItem currentPageItem = this.getMenuBar().addItem(i.toString(), eventPageClick);
					currentPageItem.setCheckable(true);
					currentPageItem.setChecked(true);
				} else
					this.getMenuBar().addItem(i.toString(), eventPageClick);
			}
			if (generalPagingTransferObject.getPositionPage() < generalPagingTransferObject.getTotalPage()) {
				this.getMenuBar().addItem("", FontAwesome.CARET_RIGHT,eventPageClick);
				if (posMax < generalPagingTransferObject.getTotalPage())
					this.getMenuBar().addItem("", FontAwesome.FORWARD,eventPageClick);
			}
		}
	}

	public abstract void refreshPageable();
	
	protected abstract MenuBar getMenuBar();

	public GeneralPagingTransferObject<T> getGeneralPagingTransferObject() {
		return generalPagingTransferObject;
	}

	public void setGeneralPagingTransferObject(GeneralPagingTransferObject<T> generalPagingTransferObject) {
		this.generalPagingTransferObject = generalPagingTransferObject;
	}
	
}