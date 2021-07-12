package wook.co.coc.viewmodels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import wook.co.coc.models.NicePlace;
import wook.co.coc.repositories.NicePlaceRepository;

public class MainActivityViewModel extends ViewModel {

    //live date 클래스의 서브 클래스이다. 즉 이건 livedata 클래스를 상속하는 클래스이다.
    //mutable 이건 바뀔수있다는 뜻이다. 반면에 livedata는 immutable이다 즉 바뀔수 없다는 뜻이다.
    // 따라서 MutableLiveData는 mNicePlaces.setValue(),  mNicePlaces.postValue()로 값을 바꿀수 있지만
    // LiveData는 바꿀수 없다. 따라서 LiveData는 오직 관찰만가능하다.
    private MutableLiveData<List<NicePlace>> mNicePlaces;
    private NicePlaceRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>(); //데이터를 받는게 완료되면 progress bar 숨기고 아니면 보여주려고 만든 변수

    public void init(){
        if(mNicePlaces!= null){
            return;
        }
        mRepo = NicePlaceRepository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();
    }

    public void addNewValue(final NicePlace nicePlace){
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<NicePlace> currentPlace = mNicePlaces.getValue();
                currentPlace.add(nicePlace);
                mNicePlaces.postValue(currentPlace);
                mIsUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<List<NicePlace>> getNicePlaces(){
        return mNicePlaces;
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }


}
