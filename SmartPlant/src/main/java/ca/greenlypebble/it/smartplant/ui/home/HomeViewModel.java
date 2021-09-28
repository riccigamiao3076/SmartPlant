//Deo Anthony Madrid (N01361264)
//Patrick Gomulka (N01347564)
//Erni Banaag (N01221990)
//Ricci Gamiao (N01363076)
package ca.greenlypebble.it.smartplant.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("PAGE #2");
    }

    public LiveData<String> getText() {
        return mText;
    }
}