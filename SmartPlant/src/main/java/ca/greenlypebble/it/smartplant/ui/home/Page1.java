//Deo Anthony Madrid (N01361264)
//Patrick Gomulka (N01347564)
//Erni Banaag (N01221990)
//Ricci Gamiao (N01363076)
package ca.greenlypebble.it.smartplant.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Page1 extends ViewModel {

    private MutableLiveData<String> mText;

    public Page1() {
        mText = new MutableLiveData<>();
       // mText.setValue("The Smart Plant");
    }

    public LiveData<String> getText() {
        return mText;
    }
}