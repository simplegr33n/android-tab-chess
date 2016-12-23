package ca.ggolda.android_tab_chess;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gcgol on 11/01/2016.
 */
public class InstanceGame {


    private String mGameId;



    public InstanceGame() {
    }

    // Constructor for InstanceGame with all attributes
    public InstanceGame(String game_id) {

        mGameId = game_id;
    }


    public String getmGameId() {
        return mGameId;
    }
    public void setContractId(String mContractId) {
        this.mGameId = mContractId;
    }

}