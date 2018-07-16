package com.stsdev.votingbox.ui.Account;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.Category;
import com.stsdev.votingbox.ui.Base.BaseViewHolder;
import com.stsdev.votingbox.ui.CreateVote.OptionsRecycler.OptionsAdapter;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by stavros on 17/6/2018.
 */

public class AccountAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    List<Category> categories;

     public AccountAdapter(List<Category> cat){
         this.categories = cat ;
     }

     @Override
     public  void onBindViewHolder (BaseViewHolder holder , int position ){
         holder.onBind(position);
     }

     @Override
     public BaseViewHolder onCreateViewHolder (ViewGroup parent , int viewType){

         return new AccountAdapter.PreferencesViewHolder(LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.cardview_categories, parent, false));

     }

    @Override
    public int getItemCount() {

        return categories.size();

    }



    public  class PreferencesViewHolder extends BaseViewHolder{

        @BindView(R.id.category)
        TextView category;


        public  PreferencesViewHolder(View viewitem){
            super(viewitem);
            ButterKnife.bind(this,viewitem);
        }

        public void onBind(int position){
            super.onBind(position);
            Category categoryItem = categories.get(position);
            category.setText(categoryItem.getCategory());

        }

        @Override
        public void clear(){

        }

    }
}
