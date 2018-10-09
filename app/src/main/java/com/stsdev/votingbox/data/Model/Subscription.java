package com.stsdev.votingbox.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stavros on 18/7/2018.
 */

public class Subscription {

    //##############################################################################################
    //################################### STARTING #################################################
    //##############################################################################################


    //#=========================================Subscription Attributes============================#

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("token")
    @Expose
    private String token;

    //#======================================== Constructors ======================================#

    public Subscription() {
    }

    // #==================================== Setter and getters section ===========================#

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //##############################################################################################
    //########################################## END ###############################################
    //##############################################################################################
}
