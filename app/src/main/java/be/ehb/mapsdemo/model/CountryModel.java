package be.ehb.mapsdemo.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class CountryModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Country>> countryList;

    public CountryModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<ArrayList<Country>> getCountryList() {
        if(countryList == null){
            countryList = new MutableLiveData<>();
            ArrayList<Country> countries = new ArrayList<>();
            countries.add(new Country(new LatLng(51.528308, -0.381789),
                    "UK",
                    "Londen"));
            countries.add(new Country(new LatLng(60.1098678, 24.738504),
                    "Finland",
                    "Helsinki"));
            countryList.setValue(countries);
        }
        return countryList;
    }
}
