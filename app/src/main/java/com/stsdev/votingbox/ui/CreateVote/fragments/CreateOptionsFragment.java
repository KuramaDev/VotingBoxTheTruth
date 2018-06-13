package com.stsdev.votingbox.ui.CreateVote.fragments;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.stsdev.votingbox.data.Model.Option;
import com.stsdev.votingbox.R;
import com.stsdev.votingbox.ui.Base.BaseFragment;
import com.stsdev.votingbox.ui.CreateVote.OptionsRecycler.OptionListPresenter;
import com.stsdev.votingbox.ui.CreateVote.OptionsRecycler.OptionsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;


public class CreateOptionsFragment extends BaseFragment {

    private List<Option> optionList;
    private OptionsAdapter optAdapter;
    private OptionListPresenter presenter;
    private  View rootView;
    private Unbinder unbinder;




    //@BindView(R.id.steve)
    RecyclerView ryOptions;

    public CreateOptionsFragment() {
        // Required empty public constructor
    }

    public static CreateOptionsFragment newTabFragment() {
        return new CreateOptionsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_create_options_tab, container, false);
        ryOptions = (RecyclerView) rootView.findViewById(R.id.steve);
        //unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //unbinder = ButterKnife.bind(this, rootView);
        optionList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Option option = new Option();
            option.setId(i);
            option.setCount(0);
            optionList.add(option);
        }

        presenter = new OptionListPresenter(optionList);
        optAdapter = new OptionsAdapter(presenter);
        ryOptions.setAdapter(optAdapter);

       optAdapter.setOnItemClickListener(new OptionsAdapter.OptionsHandlingListener() {
           @Override
           public void addNewOption(View view, int position) {
               //String name = optionList.get(position-1).getTitle();
               //Toast.makeText(CreateOptionsFragment.this.getContext(), name + " was clicked!", Toast.LENGTH_SHORT).show();
               Option option = new Option();
               option.setId(position);
               option.setCount(0);
               optionList.add(option);
               optAdapter.notifyItemChanged(position);

           }

           @Override
           public void deleteOption(View view,int position){
               if(optionList.size()<=2){
                   Log.d("OPTIONS SIZE" , "Need to have at least 2 options ");
                   return;
               }
               optionList.remove(position);
               //presenter.deleteFromList(position);
//               presenter.UpdateList(optionList);
               optAdapter.notifyItemRemoved(position);



           }

           @Override
           public void setOptionTitle(String title,int position){
               optionList.get(position).setTitle(title);
           }

        });


    }
    @Override public void onDestroyView() {
        super.onDestroyView();

        //unbinder.unbind();
    }

    @Override
    protected  void setUp(View view){

    }

    public List<Option> getOptionList() {
        return optionList;
    }
}
