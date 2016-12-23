package ca.ggolda.android_tab_chess;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gcgol on 11/01/2016.
 */
public class InstanceGame {


    private String mMatch_id;
    private String mBoard;
    private String mWhite;
    private String mBlack;
    private String mTurn_color;



    // Required for firebase
    public InstanceGame() {
    }

    // Constructor for InstanceGame with all attributes
    public InstanceGame(String match_id, String board, String white, String black, String turn_color) {
        mMatch_id = match_id;
        mBoard = board;
        mWhite = white;
        mBlack = black;
        mTurn_color = turn_color;
    }


    public String getMatch_id() {
        return mMatch_id;
    }
    public void setMatch_id(String mMatch_id) {
        this.mMatch_id = mMatch_id;
    }

    public String getBoard() {
        return mBoard;
    }
    public void setBoard(String mBoard) {
        this.mBoard = mBoard;
    }

    public String getWhite() {
        return mWhite;
    }
    public void setWhite(String mWhite) {
        this.mWhite = mWhite;
    }

    public String getBlack() {
        return mBlack;
    }
    public void setBlack(String mBlack) {
        this.mBlack = mBlack;
    }

    public String getTurn_color() {
        return mTurn_color;
    }
    public void setTurn_color(String mTurn_color) {
        this.mTurn_color = mTurn_color;
    }

}