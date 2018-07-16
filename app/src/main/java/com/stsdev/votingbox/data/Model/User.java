package com.stsdev.votingbox.data.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class User implements Parcelable {

    //##############################################################################################
    //################################### STARTING #################################################
    //##############################################################################################


    //#=========================================User Attributes=====================================#

    @Expose
    @SerializedName("usercode")
    private int usercode ;     //Identifier to differentiate users

    @Expose
    @SerializedName("name")
    private String name ;   //Username of every user

    @Expose
    @SerializedName("email")
    private String email ;  //Email of every user(login via email)

    @Expose
    @SerializedName("password")
    private String password ; // Specific password of every user(with email are the 2credentials for login)

    @Expose
    @SerializedName("register")
    private String registeredDate ; // User registered date


    //#======================================== Constructors =======================================#

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // #==================================== Setter and getters section ============================#
    public void setUsercode(int usercode) {
        this.usercode = usercode;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUsercode() {
        return usercode;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }
// #================================ Parcelable specific ===========================================#

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(usercode);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(registeredDate);
    }


    public User(Parcel parcel){
        usercode = parcel.readInt();
        name = parcel.readString();
        email = parcel.readString();
        password = parcel.readString();
        registeredDate = parcel.readString();

    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        @Override
        public User[] newArray(int size) {
            return new User[0];
        }
    };

    @Override
    public int describeContents() {return 0;}



    //##############################################################################################
    //########################################## END ###############################################
    //##############################################################################################
}
