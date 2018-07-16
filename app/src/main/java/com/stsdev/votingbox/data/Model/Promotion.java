package com.stsdev.votingbox.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stavros on 15/7/2018.
 */

public class Promotion {


    @SerializedName("usercode")
    @Expose
    private int userCode;

    @SerializedName("votecode")
    @Expose
    private int voteCode;

    @SerializedName("optioncode")
    @Expose
    private int optionCode;

    public Promotion() {
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public int getVoteCode() {
        return voteCode;
    }

    public void setVoteCode(int voteCode) {
        this.voteCode = voteCode;
    }

    public int getOptionCode() {
        return optionCode;
    }

    public void setOptionCode(int optionCode) {
        this.optionCode = optionCode;
    }
}
