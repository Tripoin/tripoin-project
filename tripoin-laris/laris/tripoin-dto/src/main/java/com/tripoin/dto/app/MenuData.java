package com.tripoin.dto.app;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 4/3/2016 : 2:41 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class MenuData {

    @SerializedName("code")
    String code;

    @SerializedName("name")
    String name;

    @SerializedName("level")
    int level;

    @SerializedName("order")
    int order;

    @SerializedName("tree")
    String tree;

    @SerializedName("viewType")
    String viewType;

    @SerializedName("function")
    String function;

    @SerializedName("parent")
    MenuData dtoMenu;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTree() {
        return tree;
    }

    public void setTree(String tree) {
        this.tree = tree;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public MenuData getDtoMenu() {
		return dtoMenu;
	}

	public void setDtoMenu(MenuData dtoMenu) {
		this.dtoMenu = dtoMenu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((dtoMenu == null) ? 0 : dtoMenu.hashCode());
		result = prime * result + ((function == null) ? 0 : function.hashCode());
		result = prime * result + level;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + order;
		result = prime * result + ((tree == null) ? 0 : tree.hashCode());
		result = prime * result + ((viewType == null) ? 0 : viewType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuData other = (MenuData) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (dtoMenu == null) {
			if (other.dtoMenu != null)
				return false;
		} else if (!dtoMenu.equals(other.dtoMenu))
			return false;
		if (function == null) {
			if (other.function != null)
				return false;
		} else if (!function.equals(other.function))
			return false;
		if (level != other.level)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (order != other.order)
			return false;
		if (tree == null) {
			if (other.tree != null)
				return false;
		} else if (!tree.equals(other.tree))
			return false;
		if (viewType == null) {
			if (other.viewType != null)
				return false;
		} else if (!viewType.equals(other.viewType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MenuData [code=" + code + ", name=" + name + ", level=" + level + ", order=" + order + ", tree=" + tree
				+ ", viewType=" + viewType + ", function=" + function + ", dtoMenu=" + dtoMenu + "]";
	}
	
}
