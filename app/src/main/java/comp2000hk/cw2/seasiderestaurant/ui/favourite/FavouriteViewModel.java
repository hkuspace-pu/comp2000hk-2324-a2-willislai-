package comp2000hk.cw2.seasiderestaurant.ui.favourite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavouriteViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FavouriteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is favourite fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}