package com.tripoin.web.view.valo;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.core.dto.ProductData;
import com.tripoin.web.service.IInventoryService;
import com.tripoin.web.servlet.VaadinView;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Slider;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.Table.RowHeaderMode;
import com.vaadin.ui.Table.TableDragMode;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "tables", cached = true)
public class Tables extends VerticalLayout implements View {

	private static final long serialVersionUID = 7037812110286422303L;
    
    @Autowired
    private IInventoryService inventoryService;
    
    private BeanItemContainer<ProductData> productContainer = new BeanItemContainer<>(ProductData.class);

    CheckBox footer = new CheckBox("Footer", true);
    CheckBox sized = new CheckBox("Sized");
    CheckBox expandRatios = new CheckBox("Expand ratios", true);
    CheckBox stripes = new CheckBox("Stripes", true);
    CheckBox verticalLines = new CheckBox("Vertical lines", true);
    CheckBox horizontalLines = new CheckBox("Horizontal lines", true);
    CheckBox borderless = new CheckBox("Borderless", true);
    CheckBox headers = new CheckBox("Header", true);
    CheckBox compact = new CheckBox("Compact");
    CheckBox small = new CheckBox("Small", true);
    CheckBox rowIndex = new CheckBox("Row index", true);
    CheckBox rowIcon = new CheckBox("Row icon", false);
    CheckBox rowCaption = new CheckBox("Row caption", false);
    CheckBox componentsInCells = new CheckBox("Components in Cells", false);

    Table table;
    
    @PostConstruct
    public void init(){
        setMargin(true);
        setSpacing(true);

        Label h1 = new Label("Tables");
        h1.addStyleName("h1");
        addComponent(h1);

        HorizontalLayout wrap = new HorizontalLayout();
        wrap.addStyleName("wrapping");
        wrap.setSpacing(true);
        addComponent(wrap);

        wrap.addComponents(footer, sized, expandRatios, stripes,
                verticalLines, horizontalLines, borderless, headers, compact,
                small, rowIndex, rowCaption, rowIcon, componentsInCells);

        ValueChangeListener update = new ValueChangeListener() {
			private static final long serialVersionUID = -2039663671423908214L;
			@Override
            public void valueChange(ValueChangeEvent event) {
                if (table == null) {
                    table = new Table();
                    List<ProductData> productDatas = inventoryService.getAllProducts();
                    productContainer.removeAllItems();
                    productContainer.addAll(productDatas);
                    productContainer.removeContainerProperty("id");
                    productContainer.removeContainerProperty("categoryDatas");
                    productContainer.removeContainerProperty("availabilityData");
                    productContainer.addNestedContainerProperty("availabilityData.name");
                    table.setContainerDataSource(productContainer);
                    table.setColumnHeader("productName", "Product Name");
                    table.setColumnHeader("price", "Price");
                    table.setColumnHeader("stockCount", "Stock");
                    table.setColumnHeader("availabilityData.name", "Availability");
                    addComponent(table);
                }
                configure(table, footer.getValue(), sized.getValue(),
                        expandRatios.getValue(), stripes.getValue(),
                        verticalLines.getValue(), horizontalLines.getValue(),
                        borderless.getValue(), headers.getValue(),
                        compact.getValue(), small.getValue(),
                        rowIndex.getValue(), rowCaption.getValue(),
                        rowIcon.getValue(), componentsInCells.getValue());
            }
        };

        footer.addValueChangeListener(update);
        sized.addValueChangeListener(update);
        expandRatios.addValueChangeListener(update);
        stripes.addValueChangeListener(update);
        verticalLines.addValueChangeListener(update);
        horizontalLines.addValueChangeListener(update);
        borderless.addValueChangeListener(update);
        headers.addValueChangeListener(update);
        compact.addValueChangeListener(update);
        small.addValueChangeListener(update);
        rowIndex.addValueChangeListener(update);
        rowCaption.addValueChangeListener(update);
        rowIcon.addValueChangeListener(update);
        componentsInCells.addValueChangeListener(update);

        footer.setValue(false);
    	
    }

    static void configure(Table table, boolean footer, boolean sized,
            boolean expandRatios, boolean stripes, boolean verticalLines,
            boolean horizontalLines, boolean borderless, boolean headers,
            boolean compact, boolean small, boolean rowIndex,
            boolean rowCaption, boolean rowIcon, boolean componentsInRows) {
        table.setSelectable(true);
        table.setMultiSelect(true);
        table.setSortEnabled(true);
        table.setColumnCollapsingAllowed(true);
        table.setColumnReorderingAllowed(true);
        table.setPageLength(6);
        table.addActionHandler(MockActionHandler.getActionHandler());
        table.setDragMode(TableDragMode.MULTIROW);
        table.setDropHandler(new DropHandler() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 3005338302927741078L;

			@Override
            public AcceptCriterion getAcceptCriterion() {
                return AcceptAll.get();
            }

            @Override
            public void drop(DragAndDropEvent event) {
                Notification.show(event.getTransferable().toString());
            }
        });
        table.setColumnAlignment(MockGeneratorContainer.DESCRIPTION_PROPERTY, Align.RIGHT);
        table.setColumnAlignment(MockGeneratorContainer.INDEX_PROPERTY, Align.CENTER);

        table.removeContainerProperty("textfield");
        table.removeGeneratedColumn("textfield");
        table.removeContainerProperty("button");
        table.removeGeneratedColumn("button");
        table.removeContainerProperty("label");
        table.removeGeneratedColumn("label");
        table.removeContainerProperty("checkbox");
        table.removeGeneratedColumn("checkbox");
        table.removeContainerProperty("datefield");
        table.removeGeneratedColumn("datefield");
        table.removeContainerProperty("combobox");
        table.removeGeneratedColumn("combobox");
        table.removeContainerProperty("optiongroup");
        table.removeGeneratedColumn("optiongroup");
        table.removeContainerProperty("slider");
        table.removeGeneratedColumn("slider");
        table.removeContainerProperty("progress");
        table.removeGeneratedColumn("progress");

        if (componentsInRows) {
            table.addContainerProperty("textfield", TextField.class, null);
            table.addGeneratedColumn("textfield", new ColumnGenerator() {
                /**
				 * 
				 */
				private static final long serialVersionUID = 4626604833285717408L;

				@Override
                public Object generateCell(Table source, Object itemId,
                        Object columnId) {
                    TextField tf = new TextField();
                    tf.setInputPrompt("Type here…");
                    // tf.addStyleName("compact");
                    if ((Integer) itemId % 2 == 0) {
                        tf.addStyleName("borderless");
                    }
                    return tf;
                }
            });

            table.addContainerProperty("datefield", TextField.class, null);
            table.addGeneratedColumn("datefield", new ColumnGenerator() {
                /**
				 * 
				 */
				private static final long serialVersionUID = -6970070143533535169L;

				@Override
                public Object generateCell(Table source, Object itemId,
                        Object columnId) {
                    DateField tf = new DateField();
                    tf.addStyleName("compact");
                    if ((Integer) itemId % 2 == 0) {
                        tf.addStyleName("borderless");
                    }
                    return tf;
                }
            });

            table.addContainerProperty("combobox", TextField.class, null);
            table.addGeneratedColumn("combobox", new ColumnGenerator() {
                /**
				 * 
				 */
				private static final long serialVersionUID = -6150454578561932717L;

				@Override
                public Object generateCell(Table source, Object itemId,
                        Object columnId) {
                    ComboBox tf = new ComboBox();
                    tf.setInputPrompt("Select");
                    tf.addStyleName("compact");
                    if ((Integer) itemId % 2 == 0) {
                        tf.addStyleName("borderless");
                    }
                    return tf;
                }
            });

            table.addContainerProperty("button", Button.class, null);
            table.addGeneratedColumn("button", new ColumnGenerator() {
                /**
				 * 
				 */
				private static final long serialVersionUID = 13186558093393802L;

				@Override
                public Object generateCell(Table source, Object itemId,
                        Object columnId) {
                    Button b = new Button("Button");
                    b.addStyleName("small");
                    return b;
                }
            });

            table.addContainerProperty("label", TextField.class, null);
            table.addGeneratedColumn("label", new ColumnGenerator() {
                /**
				 * 
				 */
				private static final long serialVersionUID = 7894762923216166824L;

				@Override
                public Object generateCell(Table source, Object itemId,
                        Object columnId) {
                    Label label = new Label("Label component");
                    label.setSizeUndefined();
                    label.addStyleName("bold");
                    return label;
                }
            });

            table.addContainerProperty("checkbox", TextField.class, null);
            table.addGeneratedColumn("checkbox", new ColumnGenerator() {
                /**
				 * 
				 */
				private static final long serialVersionUID = -7894466340821131305L;

				@Override
                public Object generateCell(Table source, Object itemId,
                        Object columnId) {
                    CheckBox cb = new CheckBox(null, true);
                    return cb;
                }
            });

            table.addContainerProperty("optiongroup", TextField.class, null);
            table.addGeneratedColumn("optiongroup", new ColumnGenerator() {
                /**
				 * 
				 */
				private static final long serialVersionUID = 636426265476771860L;

				@Override
                public Object generateCell(Table source, Object itemId,
                        Object columnId) {
                    OptionGroup op = new OptionGroup();
                    op.addItem("Male");
                    op.addItem("Female");
                    op.addStyleName("horizontal");
                    return op;
                }
            });

            table.addContainerProperty("slider", TextField.class, null);
            table.addGeneratedColumn("slider", new ColumnGenerator() {
                /**
				 * 
				 */
				private static final long serialVersionUID = -7407659340576632546L;

				@Override
                public Object generateCell(Table source, Object itemId,
                        Object columnId) {
                    Slider s = new Slider();
                    s.setValue(30.0);
                    return s;
                }
            });

            table.addContainerProperty("progress", TextField.class, null);
            table.addGeneratedColumn("progress", new ColumnGenerator() {
                /**
				 * 
				 */
				private static final long serialVersionUID = 8997362500818445993L;

				@Override
                public Object generateCell(Table source, Object itemId,
                        Object columnId) {
                    ProgressBar bar = new ProgressBar();
                    bar.setValue(0.7f);
                    return bar;
                }
            });
        }
        table.setFooterVisible(footer);
        if (footer) {
            table.setColumnFooter(MockGeneratorContainer.CAPTION_PROPERTY, "caption");
            table.setColumnFooter(MockGeneratorContainer.DESCRIPTION_PROPERTY,
                    "description");
            table.setColumnFooter(MockGeneratorContainer.ICON_PROPERTY, "icon");
            table.setColumnFooter(MockGeneratorContainer.INDEX_PROPERTY, "index");
        }

        if (sized) {
            table.setWidth("400px");
            table.setHeight("300px");
        } else {
            table.setSizeUndefined();
        }

        if (expandRatios) {
            if (!sized) {
                table.setWidth("100%");
            }
        }
        table.setColumnExpandRatio(MockGeneratorContainer.CAPTION_PROPERTY, expandRatios ? 1.0f : 0);
        table.setColumnExpandRatio(MockGeneratorContainer.DESCRIPTION_PROPERTY,
                expandRatios ? 1.0f : 0);

        if (!stripes) {
            table.addStyleName("no-stripes");
        } else {
            table.removeStyleName("no-stripes");
        }

        if (!verticalLines) {
            table.addStyleName("no-vertical-lines");
        } else {
            table.removeStyleName("no-vertical-lines");
        }

        if (!horizontalLines) {
            table.addStyleName("no-horizontal-lines");
        } else {
            table.removeStyleName("no-horizontal-lines");
        }

        if (borderless) {
            table.addStyleName("borderless");
        } else {
            table.removeStyleName("borderless");
        }

        if (!headers) {
            table.addStyleName("no-header");
        } else {
            table.removeStyleName("no-header");
        }

        if (compact) {
            table.addStyleName("compact");
        } else {
            table.removeStyleName("compact");
        }

        if (small) {
            table.addStyleName("small");
        } else {
            table.removeStyleName("small");
        }

        if (!rowIndex && !rowCaption && rowIcon) {
            table.setRowHeaderMode(RowHeaderMode.HIDDEN);
        }

        if (rowIndex) {
            table.setRowHeaderMode(RowHeaderMode.INDEX);
        }

        if (rowCaption) {
            table.setRowHeaderMode(RowHeaderMode.PROPERTY);
            table.setItemCaptionPropertyId(MockGeneratorContainer.CAPTION_PROPERTY);
        } else {
            table.setItemCaptionPropertyId(null);
        }

        if (rowIcon) {
            table.setRowHeaderMode(RowHeaderMode.ICON_ONLY);
            table.setItemIconPropertyId(MockGeneratorContainer.ICON_PROPERTY);
        } else {
            table.setItemIconPropertyId(null);
        }
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

}
