package com.stsdev.votingbox.ui.CreateVote.OptionsRecycler;

import android.util.Log;

import com.stsdev.votingbox.data.Model.Option;

import java.util.List;

/**
 * Created by stavros on 14/4/2018.
 */

public class OptionListPresenter {

    private  List<Option> options;

    public OptionListPresenter(List<Option> options) {
        this.options = options;
    }

    public static int VIEW_TYPE_NORMAL_OPTION = 1;
    public static int VIEW_TYPE_ADD_OPTION = 2;

    public void onBindOptionsRowViewAtPosition(int position, OptionRowViewContract holder) {

        Log.d("OPTION LIST SIZE" , String.valueOf(options.size()));
        Log.d("POSITION" , String.valueOf(position));
        if (position == options.size()) {
            holder.setLayout(VIEW_TYPE_ADD_OPTION, 0, null);
        } else {
            holder.setLayout(VIEW_TYPE_NORMAL_OPTION, position, options.get(position));
        }

    }

    public int getRepositoriesRowsCount() {
        return options == null ? 0 : options.size()  ;
    }

    public void deleteFromList(int position){
        options.remove(position);

    }

    public List<Option> getList(){
        return this.options;
    }

    public OptionListPresenter() {
    }

    public void UpdateList(List<Option> newList){
        options.clear();
        options = newList;

    }
}
