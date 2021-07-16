package wook.co.mvvm_prac.viewmodels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import wook.co.mvvm_prac.models.NicePlace;
import wook.co.mvvm_prac.repositories.NicePlaceRepository;

public class MainActivityViewModel extends ViewModel {

    //live date 클래스의 서브 클래스이다. 즉 이건 livedata 클래스를 상속하는 클래스이다.
    //mutable 이건 바뀔수있다는 뜻이다. 반면에 livedata는 immutable이다 즉 바뀔수 없다는 뜻이다.
    // 따라서 MutableLiveData는 mNicePlaces.setValue(),  mNicePlaces.postValue()로 값을 바꿀수 있지만
    // LiveData는 바꿀수 없다. 따라서 LiveData는 오직 관찰만가능하다.
    private MutableLiveData<List<NicePlace>> mNicePlaces; //MutuableLiveData 만들었음
    private NicePlaceRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>(); //데이터를 받는게 완료되면 progress bar 숨기고 아니면 보여주려고 만든 변수


    public void init(){
        if(mNicePlaces!= null){ //null이 아니라면 함수 종료
            return;
        }
        //mNicePlate가 Null이 아닐떄
        mRepo = NicePlaceRepository.getInstance(); //NicePlaceRepository 객체 전달 받는다.
        mNicePlaces = mRepo.getNicePlaces(); //전달 받은 NicePlaceRepository로  NicePlace 데이터 전달 받음
    }

    public void addNewValue(final NicePlace nicePlace){
        mIsUpdating.setValue(true); //true로 해준다.

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<NicePlace> currentPlace = mNicePlaces.getValue(); //가장 최신의 NicePlace가 담긴 List를 받음
                currentPlace.add(nicePlace); //List에 매개변수로 받은 값 추가
                mNicePlaces.postValue(currentPlace); //mNicePlace 수정
                mIsUpdating.postValue(false); //true로 했음
            }

            @Override
            protected Void doInBackground(Void... voids) { //2초 뒤에 실행시킴
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
    } //mNicePlace return

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    } //misUpdating return
}
