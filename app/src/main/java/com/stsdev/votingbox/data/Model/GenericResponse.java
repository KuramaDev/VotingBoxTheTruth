package com.stsdev.votingbox.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stavros on 2/6/2018.
 */

public class GenericResponse {
    @SerializedName("r")
    @Expose
    String Response;
}
