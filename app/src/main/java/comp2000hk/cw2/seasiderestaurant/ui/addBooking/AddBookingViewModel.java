package comp2000hk.cw2.seasiderestaurant.ui.addBooking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddBookingViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    // Add properties for input values
    private MutableLiveData<String> phoneNumber = new MutableLiveData<>();
    private MutableLiveData<String> meal = new MutableLiveData<>();
    private MutableLiveData<String> seatingArea = new MutableLiveData<>();
    private MutableLiveData<String> tableSize = new MutableLiveData<>();
    private MutableLiveData<String> date = new MutableLiveData<>();

    private String temp_username = new String("Natasha");

    public AddBookingViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("Make Your Booking");

        // Initialize default values or leave them empty based on your requirements
        phoneNumber.setValue("");
        meal.setValue("");
        seatingArea.setValue("");
        tableSize.setValue("");
        date.setValue("");
    }

    // Add methods to get and set input values

    public LiveData<String> getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String value) {
        phoneNumber.setValue(value);
    }
    public LiveData<String> getMealPeriod() {
        return meal;
    }
    public void setMealPeriod(String value) {
        meal.setValue(value);
    }
    public LiveData<String> getSeatingArea() {
        return seatingArea;
    }
    public void setSeatingArea(String value) {
        seatingArea.setValue(value);
    }
    public LiveData<String> getTableSize() {
        return tableSize;
    }
    public void setTableSize(String value) {
        tableSize.setValue(value);
    }
    public LiveData<String> getReserveDate() {
        return date;
    }
    public void setReserveDate(String value) {
        date.setValue(value);
    }

    public LiveData<String> getText() {
        return mText;
    }


}
