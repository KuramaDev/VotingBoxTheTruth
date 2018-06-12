package com.stsdev.votingbox.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stavros on 5/3/2018.
 */

public class Option {


    private int id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @SerializedName("Votecode")
    @Expose
    private int voteCode;

    @SerializedName("Title")
    @Expose
    private String title;

    private boolean isUserChosen;

    public Option() {
    }

    public int getVoteCode() {

        return voteCode;
    }

    public void setVoteCode(int voteCode) {
        this.voteCode = voteCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isUserChosen() {
        return isUserChosen;
    }

    public void setUserChosen(boolean userChosen) {
        isUserChosen = userChosen;
    }

    public String getOptionCode() {
        return OptionCode;
    }

    public void setOptionCode(String optionCode) {
        OptionCode = optionCode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    private String OptionCode;

    @SerializedName("Count")
    @Expose
    private int count; //How many chose this option







}
