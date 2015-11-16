package com.tripoin.web.view.page.usertracking;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.service.IUserService;
import com.tripoin.web.servlet.VaadinView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.InfoWindowClosedListener;
import com.vaadin.tapio.googlemaps.client.events.MapClickListener;
import com.vaadin.tapio.googlemaps.client.events.MapMoveListener;
import com.vaadin.tapio.googlemaps.client.events.MarkerClickListener;
import com.vaadin.tapio.googlemaps.client.events.MarkerDragListener;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component
@Scope("prototype")
@VaadinView(value = "salesTracking", cached = true)
public class SalesTrackingView extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = -4592518571070450190L;

    @Autowired
    private IUserService userService;
    
    @Autowired
    private IStateFullRest stateFullRest;

    private GoogleMap googleMap;
    private GoogleMapMarker kakolaMarker = new GoogleMapMarker("DRAGGABLE: Kakolan vankila", new LatLon(60.44291, 22.242415), true, null);
    private GoogleMapInfoWindow kakolaInfoWindow = new GoogleMapInfoWindow("Kakola used to be a provincial prison.", kakolaMarker);
    
    @PostConstruct
    public void init() throws Exception {        
        setMargin(true);
        addStyleName("tripoin-custom-screen");
        HorizontalLayout row = new HorizontalLayout();
        row.setMargin(false);
        row.setWidth("100%");
        addComponent(row);
        final FormLayout formTitle = new FormLayout();
        formTitle.setMargin(false);
        formTitle.addStyleName("light");        
        Label title = new Label("Sales Tracking");
        title.addStyleName("h1");
        formTitle.addComponent(title);        
        row.addComponent(formTitle);

        Panel panelContent = new Panel("Information of Sales Location");
        panelContent.setSizeFull();
        VerticalLayout mapContent = new VerticalLayout();
        mapContent.setHeight("500px");
        panelContent.setContent(mapContent);
        addComponent(panelContent);

        googleMap = new GoogleMap(null, null, null);
        googleMap.setCenter(new LatLon(60.440963, 22.25122));
        googleMap.setZoom(10);
        googleMap.setSizeFull();
        kakolaMarker.setAnimationEnabled(false);
        googleMap.addMarker(kakolaMarker);
        googleMap.addMarker("DRAGGABLE: Paavo Nurmi Stadion", new LatLon(60.442423, 22.26044), true, "VAADIN/icon-legend/stadium.png");
        googleMap.addMarker("NOT DRAGGABLE: Iso-Heikkilä", new LatLon(60.450403, 22.230399), false, null);
        googleMap.setMinZoom(4);
        googleMap.setMaxZoom(16);

        kakolaInfoWindow.setWidth("80%");

        mapContent.addComponent(googleMap);
        mapContent.setExpandRatio(googleMap, 1.0f);

        Panel console = new Panel();
        console.setHeight("100px");
        final CssLayout consoleLayout = new CssLayout();
        console.setContent(consoleLayout);
        mapContent.addComponent(console);

        OpenInfoWindowOnMarkerClickListener infoWindowOpener = new OpenInfoWindowOnMarkerClickListener(googleMap, kakolaMarker, kakolaInfoWindow);
        googleMap.addMarkerClickListener(infoWindowOpener);

        googleMap.addMarkerClickListener(new MarkerClickListener() {
			private static final long serialVersionUID = -8078120859744201621L;
			@Override
            public void markerClicked(GoogleMapMarker clickedMarker) {
                Label consoleEntry = new Label("Marker \""
                        + clickedMarker.getCaption() + "\" at ("
                        + clickedMarker.getPosition().getLat() + ", "
                        + clickedMarker.getPosition().getLon() + ") clicked.");
                consoleLayout.addComponent(consoleEntry, 0);
            }
        });

        googleMap.addMapMoveListener(new MapMoveListener() {
			private static final long serialVersionUID = 2916767315709961453L;
			@Override
            public void mapMoved(int zoomLevel, LatLon center, LatLon boundsNE, LatLon boundsSW) {
                Label consoleEntry = new Label("Map moved to ("
                        + center.getLat() + ", " + center.getLon() + "), zoom "
                        + zoomLevel + ", boundsNE: (" + boundsNE.getLat()
                        + ", " + boundsNE.getLon() + "), boundsSW: ("
                        + boundsSW.getLat() + ", " + boundsSW.getLon() + ")");
                consoleLayout.addComponent(consoleEntry, 0);
            }
        });

        googleMap.addMapClickListener(new MapClickListener() {
			private static final long serialVersionUID = -5062463306840145099L;
			@Override
            public void mapClicked(LatLon position) {
                Label consoleEntry = new Label("Map click to ("
                        + position.getLat() + ", " + position.getLon() + ")");
                consoleLayout.addComponent(consoleEntry, 0);
            }
        });

        googleMap.addMarkerDragListener(new MarkerDragListener() {
			private static final long serialVersionUID = 6861475682324503247L;
			@Override
            public void markerDragged(GoogleMapMarker draggedMarker,
                                      LatLon oldPosition) {
                Label consoleEntry = new Label("Marker \""
                        + draggedMarker.getCaption() + "\" dragged from ("
                        + oldPosition.getLat() + ", " + oldPosition.getLon()
                        + ") to (" + draggedMarker.getPosition().getLat()
                        + ", " + draggedMarker.getPosition().getLon() + ")");
                consoleLayout.addComponent(consoleEntry, 0);
            }
        });

        googleMap.addInfoWindowClosedListener(new InfoWindowClosedListener() {
			private static final long serialVersionUID = 5095437415720210262L;
			@Override
            public void infoWindowClosed(GoogleMapInfoWindow window) {
                Label consoleEntry = new Label("InfoWindow \""
                        + window.getContent() + "\" closed");
                consoleLayout.addComponent(consoleEntry, 0);
            }
        });
    }

    @Override
    public void enter(ViewChangeEvent event) {
    	
    }

	@Override
	public void buttonClick(ClickEvent event) {	
		
	}
    
}
