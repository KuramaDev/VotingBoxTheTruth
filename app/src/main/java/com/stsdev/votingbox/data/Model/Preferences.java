package com.stsdev.votingbox.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stavros on 20/10/2018.
 */

public class Preferences {

    @Expose
    @SerializedName("Email")
    String email;

    @Expose
    @SerializedName("Category")
    String category;

    @Expose
    @SerializedName("Checked")
    int checked;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public Preferences() {

    }
}
