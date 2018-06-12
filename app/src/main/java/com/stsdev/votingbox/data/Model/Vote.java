package com.stsdev.votingbox.data.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.stsdev.votingbox.Utils.CommonUtils;

import java.sql.Date;
import java.util.List;

/**
 * Created by stavros on 4/3/2018.
 */

public class Vote implements Parcelable{


   @SerializedName("Votecode")
   private String voteCode;
   @SerializedName("Question")
   @Expose
   private String question;  // Η ερώτηση προς ψήφιση
   private String StartingDate; //Ημερομηνία έναρξης της ψηφοφρίας
   @SerializedName("EndingDate")
   @Expose
   private String EndingDate;


   public Date get_endingDate() {
      return _endingDate;
   }

   public void set_endingDate(Date _endingDate) {
      this._endingDate = _endingDate;
      //this.EndingDate = CommonUtils.ConvertDateToString(_endingDate);

   }

   @SerializedName("send_date")
   @Expose
   private Date _endingDate;

   //Ημερομηνία λήξης της ψηφοφορίας
   @SerializedName("options")
   @Expose
   private List<Option> options;   //Λίστα με όλες τις απαντήσεις

   @SerializedName("Authorname")
   @Expose
   private String authorName;

   public String getAuthorName() {
      return authorName;
   }

   public void setAuthorName(String authorName) {
      this.authorName = authorName;
   }
   //Settings of the open vote

   private boolean isMultipleChoice;   //Αν μπορεί να διαλέξει παραπάνω από μία ο χρήστης
   private int optionMaxCount;         //Μέγιστο αριθμό επιλογών του χρήστη που μπορεί να διαλέξει
   private int optionMinCount;         //Ελάχιστο αριθμό επιλογών που πρέπει να επιλέξει ο χρήστης

   //Optional attributes of a vote

   private String category;   //Σε τι κατηγορία ανήκει η ψηφοφρία.

   // Utility based attributes

   @SerializedName("NumOfVotes")
   @Expose
   private int pollCount; // Πόσες απαντήσεις επέλεξε ο χρήστης.
   private int optionCount; //Πλήθος πιθανών απαντήσεων ψηφοφορίας.
   //Basic Attributes of a vote


   public Vote() {
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

   public int getOptionCount() {
      return optionCount;
   }

   public void setOptionCount(int optionCount) {
      this.optionCount = optionCount;
   }


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


}
