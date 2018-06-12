package com.stsdev.votingbox.ui.CreateVote.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;

import com.stsdev.votingbox.ui.Base.BasePresenterImp;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by stavros on 8/6/2018.
 */

public class CreateSettingsPresenterImp<V extends CreateSettingsView> extends BasePresenterImp<V> {
    private int mYear, mMonth, mDay;

    public CreateSettingsPresenterImp() {
    }

    public void onEditDateClick(Context context){
        Calendar now = Calendar.getInstance();
        mYear = now.get(Calendar.YEAR);
        mMonth = now.get(Calendar.MONTH);
        mDay = now.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog timeSetting =new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker v , int year, int monthOfYear, int dayOfMonth) {
                        Log.d("DATE CHOSEN",
                                String.valueOf(dayOfMonth) +"/"+ String.valueOf(monthOfYear)+"/"+String.valueOf(year));
                                //getView().setDate(String.valueOf(dayOfMonth) +"/"+ String.valueOf(monthOfYear+1)+"/"+String.valueOf(year));
                        Calendar calendar = Calendar.getInstance();
                       calendar.set(Calendar.HOUR_OF_DAY, 0);
                        calendar.set(Calendar.MINUTE, 0);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);
                        calendar.set(year, monthOfYear, dayOfMonth);
                        getView().setDate(calendar);

                }

                },
                mYear,mMonth,mDay);
        timeSetting.show();




    }
}
