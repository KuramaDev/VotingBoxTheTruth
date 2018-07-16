package com.stsdev.votingbox.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stavros on 17/6/2018.
 */

public class Category {

    //##############################################################################################
    //################################### STARTING #################################################
    //##############################################################################################


    //#=========================================Category Attributes================================#

    @Expose
    @SerializedName("CategoryCode")
    int id;     // Category identifier to diferetiate categories

    @Expose
    @SerializedName("Category")
    String category; // Category title

    @Expose
    @SerializedName("Desc")
    String description;     // Category description

    boolean isOnPreferences; // if it belongs to user preferences

    //#======================================== Constructors ======================================#

    public Category() {
    }

    // #==================================== Setter and getters section ===========================#

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOnPreferences() {
        return isOnPreferences;
    }

    public void setOnPreferences(boolean onPreferences) {
        isOnPreferences = onPreferences;
    }
}
