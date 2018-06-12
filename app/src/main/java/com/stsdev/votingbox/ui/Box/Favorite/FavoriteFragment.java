package com.stsdev.votingbox.ui.Box.Favorite;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.data.Model.User;
import com.stsdev.votingbox.ui.Base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoriteFragment extends BaseFragment implements FavoriteView {

    private View view;
    private int usercode;
    FavoritePresenterImpImp<FavoriteView> presenter;

    @BindView(R.id.ryMainPage)
    RecyclerView rcView;


//    private OnFragmentInteractionListener mListener;

    public FavoriteFragment() {
        // Required empty public constructor

    }


    public static   FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favorite, container, false);
        setUnBinder(ButterKnife.bind(this,view));
        usercode = getArguments().getInt("usercode");

        return view ;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        presenter =  new FavoritePresenterImpImp<>();
        presenter.onAttach(this);
        rcView.setAdapter(presenter.getAdapter());
        presenter.RetrieveFavsFromServer(usercode);



    }


    public  void setUp(View view){

    }






//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
