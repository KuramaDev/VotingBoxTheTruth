package com.stsdev.votingbox.ui.Box.Created;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stsdev.votingbox.R;
import com.stsdev.votingbox.ui.Base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ParticipateFragment extends BaseFragment implements CreateFragmentView {

    private View view;
    CreateFragmentPresenterImpImp<CreateFragmentView> presenter;

    @BindView(R.id.ryMainPage2)
    RecyclerView rcView;



//    private OnFragmentInteractionListener mListener;

    public ParticipateFragment() {
        // Required empty public constructor
    }

    public static ParticipateFragment newInstance() {
        ParticipateFragment fragment = new ParticipateFragment();

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
        view = inflater.inflate(R.layout.fragment_participate, container, false);
        setUnBinder(ButterKnife.bind(this,view));
        return view;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        presenter =  new CreateFragmentPresenterImpImp<>();
        presenter.onAttach(this);
        rcView.setAdapter(presenter.getAdapter());
        presenter.RetrieveCreatedFromServer();



    }
    @Override
    protected void setUp(View view) {

    }
}
