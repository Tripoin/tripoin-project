package com.tripoin.dto.app;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 4/3/2016 : 2:41 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class DTOMenu {

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

    @SerializedName("parent")
    DTOMenu dtoMenu;

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

    public DTOMenu getDtoMenu() {
        return dtoMenu;
    }

    public void setDtoMenu(DTOMenu dtoMenu) {
        this.dtoMenu = dtoMenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DTOMenu dtoMenu1 = (DTOMenu) o;

        if (level != dtoMenu1.level) return false;
        if (order != dtoMenu1.order) return false;
        if (code != null ? !code.equals(dtoMenu1.code) : dtoMenu1.code != null) return false;
        if (name != null ? !name.equals(dtoMenu1.name) : dtoMenu1.name != null) return false;
        if (tree != null ? !tree.equals(dtoMenu1.tree) : dtoMenu1.tree != null) return false;
        if (viewType != null ? !viewType.equals(dtoMenu1.viewType) : dtoMenu1.viewType != null)
            return false;
        return !(dtoMenu != null ? !dtoMenu.equals(dtoMenu1.dtoMenu) : dtoMenu1.dtoMenu != null);

    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + order;
        result = 31 * result + (tree != null ? tree.hashCode() : 0);
        result = 31 * result + (viewType != null ? viewType.hashCode() : 0);
        result = 31 * result + (dtoMenu != null ? dtoMenu.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DTOMenu{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", order=" + order +
                ", tree='" + tree + '\'' +
                ", viewType='" + viewType + '\'' +
                ", dtoMenu=" + dtoMenu +
                '}';
    }
}
