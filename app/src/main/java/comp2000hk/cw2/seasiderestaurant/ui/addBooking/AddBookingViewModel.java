package comp2000hk.cw2.seasiderestaurant.ui.addBooking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddBookingViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AddBookingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is add booking fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
