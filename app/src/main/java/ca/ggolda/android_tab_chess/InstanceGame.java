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
    private String mUsername_white;
    private String mUsername_black;



    // Required for firebase
    public InstanceGame() {
    }

    // Constructor for InstanceGame with all attributes
    public InstanceGame(String match_id, String board, String white, String black, String turn_color, String username_white, String username_black) {
        mMatch_id = match_id;
        mBoard = board;
        mWhite = white;
        mBlack = black;
        mTurn_color = turn_color;
        mUsername_white = username_white;
        mUsername_black = username_black;

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

    public String getUsername_white() {
        return mUsername_white;
    }
    public void setUsername_white(String mUsername_white) {
        this.mUsername_white = mUsername_white;
    }

    public String getUsername_black() {
        return mUsername_black;
    }
    public void setUsername_black(String mUsername_black) {
        this.mUsername_black = mUsername_black;

    }


}