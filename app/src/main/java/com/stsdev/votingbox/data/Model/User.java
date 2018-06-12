package com.stsdev.votingbox.data.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.NameInDb;

/**
 * Created by stavros on 26/4/2018.
 */

@Entity
@NameInDb("USER")
public class User implements Parcelable {

    //Database Id - AutoIncrement

    @Id long id ;
    //Properties


    @Expose
    @SerializedName("usercode")
    private int usercode ;

    public int getUsercode() {
        return usercode;
    }

    public void setUsercode(int usercode) {
        this.usercode = usercode;
    }

    @NameInDb("RealName")
    @Expose
    @SerializedName("name")
    private String name ;
    @Expose
    @SerializedName("email")
    @NameInDb( "Email" )
    private String email ;

    @Expose
    @SerializedName("password")
    @NameInDb("Password")
    private String password ;

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

    public Long getId() {
        return id;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(usercode);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(password);
    }


    public User(Parcel parcel){
        usercode = parcel.readInt();
        name = parcel.readString();
        email = parcel.readString();
        password = parcel.readString();

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
}
