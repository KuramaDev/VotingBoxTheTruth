package com.stsdev.votingbox.data.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.sql.Date;
import java.util.List;



public class Vote implements Parcelable{

   //##############################################################################################
   //################################### STARTING #################################################
   //##############################################################################################


   //#=========================================Vote Attributes=====================================#

   @Expose
   @SerializedName("Votecode")
   private String voteCode;  //Identifier to differentiate votes

   @SerializedName("Question")
   @Expose
   private String question;  // Questions that a user is called to answer

   @Expose
   @SerializedName("StartingDate")
   private String StartingDate; // Date of vote's creation, otherwise date that the voted started

   @SerializedName("EndingDate")
   @Expose
   private String EndingDate; // Date of vote's ending

   @SerializedName("options")
   @Expose
   private List<Option> options;   // List that holds all the options of a vote

   @SerializedName("Authorname")
   @Expose
   private String authorName;      // Username of the vote's creator

   @SerializedName("Authorcode")
   @Expose
   private int authorCode;

   @SerializedName("Category")
   @Expose
   private String category;   // In which category a vote belongs

   @SerializedName("CategoryId")
   @Expose
   private int categoryId; // Category identifier used to search through categories

   @SerializedName("NumOfVotes")
   @Expose
   private int pollCount; // Πόσες απαντήσεις επέλεξε ο χρήστης..

   @SerializedName("send_date")
   @Expose
   private Date _endingDate;
   @SerializedName("fav")
   @Expose
   private int isFav;
   @SerializedName("part")
   @Expose// is true if the vote can be found in favorites list
   private int participated; // is true if the vote can be found in participated list

   private boolean isMultipleChoice;   //Αν μπορεί να διαλέξει παραπάνω από μία ο χρήστης
   private int optionMaxCount;         //Μέγιστο αριθμό επιλογών του χρήστη που μπορεί να διαλέξει
   private int optionMinCount;         //Ελάχιστο αριθμό επιλογών που πρέπει να επιλέξει ο χρήστης

   //#======================================== Constructors =======================================#


   public Vote() {

   }


   // #==================================== Setter and getters section ============================#

   public Date get_endingDate() {
      return _endingDate;
   }

   public void set_endingDate(Date _endingDate) {
      this._endingDate = _endingDate;
   }

   public String getAuthorName() {
      return authorName;
   }

   public void setAuthorName(String authorName) {
      this.authorName = authorName;
   }

   public String getVoteCode() {

      return voteCode;
   }

   public void setVoteCode(String voteCode) {
      this.voteCode = voteCode;
   }

   public String getQuestion() {
      return question;
   }

   public void setQuestion(String question) {
      this.question = question;
   }

   public String getStartingDate() {
      return StartingDate;
   }

   public void setStartingDate(String startingDate) {
      StartingDate = startingDate;
   }

   public String getEndingDate() {
      return EndingDate;
   }

   public void setEndingDate(String endingDate) {
      EndingDate = endingDate;
   }

   public List<Option> getOptions() {
      return options;
   }

   public void setOptions(List<Option> options) {
      this.options = options;
   }

   public boolean isMultipleChoice() {
      return isMultipleChoice;
   }

   public void setMultipleChoice(boolean multipleChoice) {
      isMultipleChoice = multipleChoice;
   }

   public int getOptionMaxCount() {
      return optionMaxCount;
   }

   public void setOptionMaxCount(int optionMaxCount) {
      this.optionMaxCount = optionMaxCount;
   }

   public int getOptionMinCount() {
      return optionMinCount;
   }

   public void setOptionMinCount(int optionMinCount) {
      this.optionMinCount = optionMinCount;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public int getPollCount() {
      return pollCount;
   }

   public void setPollCount(int pollCount) {
      this.pollCount = pollCount;
   }

    public int getAuthorCode() {
        return authorCode;
    }

    public void setAuthorCode(int authorCode) {
        this.authorCode = authorCode;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int isFav() {
        return isFav;
    }

    public void setFav(int fav) {
        isFav = fav;
    }

    public int isParticipated() {
        return participated;
    }

    public void setParticipated(int participated) {
        this.participated = participated;
    }


    // #================================ Parcelable specific ======================================#


    public void writeToParcel(Parcel dest, int flags) {
      dest.writeString(voteCode);
      dest.writeString(question);
      dest.writeString(EndingDate);
      dest.writeString(authorName);
      dest.writeInt(pollCount);
   }


   public Vote(Parcel parcel){
      voteCode = parcel.readString();
      question = parcel.readString();
      EndingDate = parcel.readString();
      authorName = parcel.readString();
      pollCount = parcel.readInt();
   }

   public static final Parcelable.Creator<Vote> CREATOR = new Parcelable.Creator<Vote>() {

      @Override
      public Vote createFromParcel(Parcel parcel) {
         return new Vote(parcel);
      }

      @Override
      public Vote[] newArray(int size) {
         return new Vote[0];
      }
   };

   @Override
   public int describeContents() {return 0;}

    //##############################################################################################
    //########################################## END ###############################################
    //##############################################################################################
}
