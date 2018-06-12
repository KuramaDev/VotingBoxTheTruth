package com.stsdev.votingbox.ui.Base;

import com.stsdev.votingbox.data.DataManager;

/**
 * Created by stavros on 30/4/2018.
 */

public class BasePresenterImp<V extends BaseView> implements BasePresenter<V> {

    private DataManager mdataManager ;
    private V view ;

    @Override
    public void onAttach(V view){
        this.view = view;
    }

    @Override
    public void onDetach(){
        this.view = null;
    }

    public boolean isViewAttached(){
        return  this.view != null;
    }

    public DataManager getDataManager(){
        return  this.mdataManager;
    }

    public  void checkViewAttached(){
        if(!isViewAttached()) throw  new VTPContractNotAttachedException();
    }

    public V  getView(){
        return view;

    }

    public  static  class VTPContractNotAttachedException extends  RuntimeException{
        public VTPContractNotAttachedException(){
            super("Please call Presenter.onAttach(View) before" +
                    " requesting data to the Presenter");
        }
    }

}
