//Deo Anthony Madrid (N01361264)
//Patrick Gomulka (N01347564)
//Erni Banaag (N01221990)
//Ricci Gamiao (N01363076)
package ca.greenlypebble.it.smartplant.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Page3 extends ViewModel {

    private MutableLiveData<String> mText;

    public Page3() {
        mText = new MutableLiveData<>();
        mText.setValue("PLACERHOLDER (3)");
    }

    public LiveData<String> getText() {
        return mText;
    }
}