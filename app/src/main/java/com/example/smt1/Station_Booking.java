package com.example.smt1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Station_Booking extends AppCompatActivity {

    private TextView stationName, availableSlots, costPerCharging;
    private Button bookStationButton;
    private MapView mapView;
    private double selectedCost = 0.0; // To store the cost of the selected station
    private String selectedStationName = ""; // To store the name of the selected station

    static class Station {
        String name;
        GeoPoint location;
        int availableSlots;
        double costPerCharging;

        public Station(String name, GeoPoint location, int availableSlots, double costPerCharging) {
            this.name = name;
            this.location = location;
            this.availableSlots = availableSlots;
            this.costPerCharging = costPerCharging;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().setUserAgentValue(getPackageName());
        setContentView(R.layout.activity_station_booking);

        stationName = findViewById(R.id.station_name);

        availableSlots = findViewById(R.id.available_slots);
        costPerCharging = findViewById(R.id.cost_per_charging);
        bookStationButton = findViewById(R.id.bookStationButton);
        mapView = findViewById(R.id.mapView);

        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(10.0);

        List<Station> stations = new ArrayList<>();
        stations.add(new Station("Station 1", new GeoPoint(12.9300, 77.5800), 5, 15.0));
        stations.add(new Station("Station 2", new GeoPoint(12.9400, 77.5900), 3, 20.0));
        stations.add(new Station("Station 3", new GeoPoint(12.9600, 77.6000), 7, 10.0));
        stations.add(new Station("Station 4", new GeoPoint(12.9500, 77.6100), 2, 25.0));
        stations.add(new Station("Station 5", new GeoPoint(12.9700, 77.6200), 10, 12.0));
        stations.add(new Station("Station 6", new GeoPoint(12.9800, 77.6300), 8, 18.0));
        stations.add(new Station("Station 7", new GeoPoint(12.9900, 77.6400), 1, 22.0));
        stations.add(new Station("Station 8", new GeoPoint(13.0000, 77.5500), 6, 17.0));
        stations.add(new Station("Station 9", new GeoPoint(13.0100, 77.5600), 4, 14.0));
        stations.add(new Station("Station 10", new GeoPoint(13.0200, 77.5700), 9, 19.0));
        stations.add(new Station("Station 11", new GeoPoint(12.9400, 77.6200), 4, 16.0));
        stations.add(new Station("Station 12", new GeoPoint(12.9500, 77.6400), 7, 15.0));
        stations.add(new Station("Station 13", new GeoPoint(12.9600, 77.6600), 5, 18.0));
        stations.add(new Station("Station 14", new GeoPoint(12.9700, 77.5500), 3, 20.0));
        stations.add(new Station("Station 15", new GeoPoint(12.9800, 77.5700), 6, 22.0));
        stations.add(new Station("Station 16", new GeoPoint(12.9900, 77.5900), 9, 12.0));
        stations.add(new Station("Station 17", new GeoPoint(13.0000, 77.6100), 1, 14.0));
        stations.add(new Station("Station 18", new GeoPoint(13.0100, 77.6300), 8, 16.0));
        stations.add(new Station("Station 19", new GeoPoint(13.0200, 77.6500), 4, 19.0));
        stations.add(new Station("Station 20", new GeoPoint(13.0300, 77.6700), 2, 21.0));
        if (!stations.isEmpty()) {
            mapView.getController().setCenter(stations.get(0).location);
        }

        for (Station station : stations) {
            Marker marker = new Marker(mapView);
            marker.setPosition(station.location);
            marker.setTitle(station.name);

            marker.setOnMarkerClickListener((clickedMarker, mapView1) -> {
                stationName.setText("Station Name: " + station.name);
                availableSlots.setText("Available Slots: " + station.availableSlots);
                costPerCharging.setText("Cost per Charging: ₹" + station.costPerCharging);

                selectedStationName = station.name;
                selectedCost = station.costPerCharging;

                return true;
            });

            mapView.getOverlays().add(marker);
        }

        bookStationButton.setOnClickListener(v -> {
            if (!selectedStationName.isEmpty()) {
                showDatePickerDialog();
            } else {
                Toast.makeText(Station_Booking.this, "Please select a station first!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    showTimePickerDialog(calendar);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
    }

    private void showTimePickerDialog(Calendar calendar) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);

                    Intent paymentIntent = new Intent(Station_Booking.this, Payment.class);
                    paymentIntent.putExtra("stationName", selectedStationName);
                    paymentIntent.putExtra("stationCost", selectedCost);
                    paymentIntent.putExtra("selectedDate", calendar.getTimeInMillis());
                    startActivity(paymentIntent);
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}
