package com.tripoin.web.view.base;

import com.tripoin.web.common.EWebUIConstant;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

public abstract class TripoinPageable {

	private MenuBar menuBarPaging;
	private int totalPage;
	private int positionPage;

	
	
	public TripoinPageable(MenuBar menuBarPaging, int totalPage,
			int positionPage) {
		super();
		this.menuBarPaging = menuBarPaging;
		this.totalPage = totalPage;
		this.positionPage = positionPage;
	}

	protected void getPaging() {
		menuBarPaging.removeItems();
		if (totalPage > 1) {
			Command eventPageClick = new Command() {
				private static final long serialVersionUID = 2102926468458330518L;

				@Override
				public void menuSelected(MenuItem selectedItem) {
					selectedItem.setCheckable(true);
					selectedItem.setChecked(true);
					if ("".equals(selectedItem.getText())
							|| selectedItem.getText().isEmpty()
							|| selectedItem.getText() == null) {
						if (FontAwesome.BACKWARD == selectedItem.getIcon())
							positionPage = 1;
						else if (FontAwesome.CARET_LEFT == selectedItem
								.getIcon()) {
							positionPage--;
							if (positionPage < 1)
								positionPage = 1;
						} else if (FontAwesome.CARET_RIGHT == selectedItem
								.getIcon()) {
							positionPage++;
							if (positionPage > totalPage)
								positionPage = totalPage;
						} else
							positionPage = totalPage;
					} else {
						try {
							positionPage = Integer.parseInt(selectedItem
									.getText());
						} catch (Exception e) {
							positionPage = 1;
						}
					}
					if (positionPage > 0) {
						new TripoinDataLoader() {

							@Override
							protected void constructDataContainer() {
								constructInternalDataContainer();

							}
						};
						getPaging();
					}
				}
			};
			Integer posMin = positionPage
					- (new Double(
							EWebUIConstant.BUTTON_PAGING.getOperatorInt() / 2)
							.intValue());
			if (posMin < 1)
				posMin = 1;
			Integer posMax = positionPage
					+ (new Double(
							EWebUIConstant.BUTTON_PAGING.getOperatorInt() / 2)
							.intValue());
			if (posMax > totalPage)
				posMax = totalPage;
			if (positionPage > 1) {
				if (posMin > 1)
					menuBarPaging.addItem("", FontAwesome.BACKWARD,
							eventPageClick);
				menuBarPaging.addItem("", FontAwesome.CARET_LEFT,
						eventPageClick);
			}
			for (Integer i = posMin; i <= posMax; i++) {
				if (i == positionPage) {
					MenuItem currentPageItem = menuBarPaging.addItem(
							i.toString(), eventPageClick);
					currentPageItem.setCheckable(true);
					currentPageItem.setChecked(true);
				} else
					menuBarPaging.addItem(i.toString(), eventPageClick);
			}
			if (positionPage < totalPage) {
				menuBarPaging.addItem("", FontAwesome.CARET_RIGHT,
						eventPageClick);
				if (posMax < totalPage)
					menuBarPaging.addItem("", FontAwesome.FORWARD,
							eventPageClick);
			}
		}
	}

	protected abstract void constructInternalDataContainer();

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPositionPage() {
		return positionPage;
	}

	public void setPositionPage(int positionPage) {
		this.positionPage = positionPage;
	}
	
	
}
