package com.stsdev.votingbox.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by stavros on 20/10/2018.
 */

public class Preferences {

    @Expose
    @SerializedName("Usercode")
    int usercode;

    @Expose
    @SerializedName("CategoryId")
    int category_id;

    @Expose
    @SerializedName("Checked")
    int checkedl;

    public int getUsercode() {
        return usercode;
    }

    public void setUsercode(int usercode) {
        this.usercode = usercode;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getCheckedl() {
        return checkedl;
    }

    public void setCheckedl(int checkedl) {
        this.checkedl = checkedl;
    }

    public Preferences() {

    }
}
