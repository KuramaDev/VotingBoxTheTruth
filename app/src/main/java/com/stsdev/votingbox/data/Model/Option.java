package com.stsdev.votingbox.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stavros on 5/3/2018.
 */

public class Option {

    //##############################################################################################
    //################################### STARTING #################################################
    //##############################################################################################


    //#=========================================Option Attributes================================#

    private int id ;

    @SerializedName("Votecode")
    @Expose
    private int voteCode;

    @SerializedName("OptionCode")
    @Expose
    private int optionCode;

    @SerializedName("Title")
    @Expose
    private String title;

    @SerializedName("Count")
    @Expose
    private int count;

    private boolean isUserChosen;

    //#======================================== Constructors ======================================#

    public Option() {
    }


    // #==================================== Setter and getters section ===========================#

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOptionCode(int optionCode) {
        this.optionCode = optionCode;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public int getOptionCode() {
        return optionCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (optionCode != option.optionCode) return false;
        return title != null ? title.equals(option.title) : option.title == null;
    }

    @Override
    public int hashCode() {
        int result = optionCode;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    //##############################################################################################
    //########################################## END ###############################################
    //##############################################################################################


}
