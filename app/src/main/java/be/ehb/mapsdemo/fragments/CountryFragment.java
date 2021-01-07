package be.ehb.mapsdemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import be.ehb.mapsdemo.R;
import be.ehb.mapsdemo.model.Country;
import be.ehb.mapsdemo.model.CountryModel;

public class CountryFragment extends Fragment implements OnMapReadyCallback {

    //fields
    private MapView mMapView;
    private GoogleMap mMap;
    private FragmentActivity mContext;

    //lifeCycle
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = (FragmentActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_country, container, false);

        mMapView = rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMapView.onDestroy();
    }

    //implementations
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        drawMarkers();
        centerMap();
    }

    private void centerMap() {
        LatLng coordBXL = new LatLng(50.858712, 4.347446);
        CameraUpdate moveToBXL = CameraUpdateFactory.newLatLngZoom(coordBXL, 4);
        mMap.animateCamera(moveToBXL);
    }

    //own methods
    private void drawMarkers() {
        CountryModel model = new ViewModelProvider(mContext).get(CountryModel.class);
        model.getCountryList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Country>>() {
            @Override
            public void onChanged(ArrayList<Country> countries) {
                for(Country currentCountry : countries){
                    mMap.addMarker(new MarkerOptions().position(currentCountry.getCoordinate())
                    .title(currentCountry.getName())
                    .snippet(currentCountry.getInfo())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_country))
                    );
                }
            }
        });
    }


}
