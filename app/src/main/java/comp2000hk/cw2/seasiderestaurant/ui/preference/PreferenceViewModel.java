package comp2000hk.cw2.seasiderestaurant.ui.preference;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PreferenceViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PreferenceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is preference fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}