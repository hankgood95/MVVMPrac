package wook.co.mvvm_prac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import wook.co.mvvm_prac.adapters.RecyclerViewAdapter;
import wook.co.mvvm_prac.models.NicePlace;
import wook.co.mvvm_prac.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private FloatingActionButton mFab; //+ FloatingButton 담기위한 변수
    private RecyclerView mRecyclerView; // RecyclerView 담기위한 변수
    private RecyclerViewAdapter mAdapter; //RecyclerAdapter 담기위한 변수
    private ProgressBar mProgressBar; // ProgressBar 담기위한 변수
    private MainActivityViewModel mMainActivityViewModel; //MainActivityViewModel 담기위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");

        //각각의 버튼의 뷰들을 연결 시킴
        mFab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.recycler_view);
        mProgressBar = findViewById(R.id.progress_bar);

        //ViewModel을 생성하는 부분
        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        //ViewModel의 init() 호출
        mMainActivityViewModel.init();

        //getNicePlaces()에서 변화가 생기면 진입하는데 해당 액티비티의 상태가 Actice가 아니면 바뀐내용을 받아오지 않고
        //Active가 되면 가장최신의 업데이트 내용을 받아온다.
        mMainActivityViewModel.getNicePlaces().observe(this, new Observer<List<NicePlace>>() {
            @Override
            public void onChanged(List<NicePlace> nicePlaces) {
                //해당 메소드 호출
                mAdapter.notifyDataSetChanged(); //해당 메소드르 호출해서 Adapter를 update
            }
        });

        mMainActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            //데이터가 바뀌었다면 호출
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){ //값이 바뀌었다면 진입
                    showProgressBar(); //ProgressBar 보여줌
                }else{ //값이 바뀌지 않았다
                    hideProgressBar();
                    mRecyclerView.smoothScrollToPosition(mMainActivityViewModel.getNicePlaces().getValue().size()-1);
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainActivityViewModel.addNewValue(new NicePlace( // 새로 값을 추가하는 버튼을 눌렀을때
                        "https://i.imgur.com/ZcLLrkY.jpg",
                        "Washington"
                ));
            }
        });

        initRecyclerView(); //위에서 가져온 ImageList를 리사이클 뷰에 집어 넣는다.
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void initRecyclerView(){
        mAdapter = new RecyclerViewAdapter(mMainActivityViewModel.getNicePlaces().getValue(),this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}