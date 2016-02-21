package com.tripoin.web.view.page.usertracking;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderAddressComponent;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;
import com.google.gwt.geolocation.client.PositionError;
import com.tripoin.util.ui.geo.EGeoTypes;
import com.tripoin.util.ui.geo.Geolocator;
import com.tripoin.util.ui.geo.PositionCallback;
import com.tripoin.util.ui.geo.shared.Position;
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
import com.vaadin.ui.Button;
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

    private GoogleMap googleMap;
    private GoogleMapMarker kakolaMarker = new GoogleMapMarker("DRAGGABLE: Kakolan vankila", new LatLon(-6.266600, 106.659831), true, null);
    private GoogleMapInfoWindow kakolaInfoWindow = new GoogleMapInfoWindow("Kakola used to be a provincial prison.", kakolaMarker);
    private String address = "";
    private final Geocoder geocoder = new Geocoder();
    
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
        googleMap.setCenter(new LatLon(-6.266600, 106.659831));
        googleMap.setZoom(10);
        googleMap.setSizeFull();
        kakolaMarker.setAnimationEnabled(false);
        googleMap.addMarker(kakolaMarker);
        googleMap.addMarker("DRAGGABLE: Paavo Nurmi Stadion", new LatLon(-6.297767, 106.667837), true, "VAADIN/icon-legend/stadium.png");
        googleMap.addMarker("NOT DRAGGABLE: Iso-Heikkilä", new LatLon(-6.2409321898086985, 106.62849426269531), false, null);
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

        Button currentLocationButton = new Button("Position");
        currentLocationButton.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1024740312420785083L;
			@Override
			public void buttonClick(ClickEvent event) {
				Geolocator.detect(new PositionCallback() {
					@Override
					public void onSuccess(Position position) {
						LatLng latLng = new LatLng(new BigDecimal(position.getLatitude()), new BigDecimal(position.getLongitude()));
						GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setLocation(latLng).getGeocoderRequest();
						try {
							GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
							List<GeocoderResult> geocoderResults = geocoderResponse.getResults();
							for(int i=0; i<geocoderResults.size(); i++){
								GeocoderResult geocoderResult = geocoderResults.get(i);
								List<GeocoderAddressComponent> geocoderAddressComponents = geocoderResult.getAddressComponents();
								for(int j=0; j<geocoderAddressComponents.size(); j++){
									GeocoderAddressComponent geocoderAddressComponent = geocoderAddressComponents.get(j);
									System.out.println("Addres : ["+geocoderAddressComponent.getShortName()+"]");
									for(int l=0; l<geocoderAddressComponent.getTypes().size(); l++)
										if(EGeoTypes.ROUTE.toString().equals(geocoderAddressComponent.getTypes().get(l)))
											address = geocoderAddressComponent.getShortName();
								}
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						Label consoleEntry = new Label("Your Location ["+position.getLatitude()+", "+position.getLongitude()+"] : "+address);
		                consoleLayout.addComponent(consoleEntry, 0);	
					}					
					@Override
					public void onFailure(int errorCode) {
						String message = "";
				        switch (errorCode) {
				          case PositionError.UNKNOWN_ERROR:
				            message = "Unknown Error";
				            break;
				          case PositionError.PERMISSION_DENIED:
				            message = "Permission Denied";
				            break;
				          case PositionError.POSITION_UNAVAILABLE:
				            message = "Position Unavailable";
				            break;
				          case PositionError.TIMEOUT:
				            message = "Time-out";
				            break;
				          default:
				            message = "Unknown error code.";
				        }
						System.out.println(message);
						Label consoleEntry = new Label(message);
		                consoleLayout.addComponent(consoleEntry, 0);
					}
				});
			}
		});    
        mapContent.addComponent(currentLocationButton);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    	
    }

	@Override
	public void buttonClick(ClickEvent event) {	
		
	}
    
}
