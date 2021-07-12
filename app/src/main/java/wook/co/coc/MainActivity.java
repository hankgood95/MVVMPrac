package wook.co.coc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");

        initImageBitmaps(); //이미지를 가져올 사이크를 ArrayList에 추가
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Austrailia");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Washington");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view); //Recycler View를 만든것 xml id로 받아옴
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames, mImageUrls,this); //해당 ArrayList와 Content로 Adapter class 생성
        recyclerView.setAdapter(adapter); //recyclerView는 setAdapter를 통해서 adapter를 받아야 한다.
        //recycler view가 화면에 표시될때 아이템 뷰들이 리사이클러 내부에서 배치되는 형태를 관리하는 요소가 LinearLayoutManager이다.
        //따라서 수직수평을 나타낼수 있는 LinearLayoutManager, 바둑판 모양의 GridLayoytManager, 엇갈림 격자 형태인 StaggerdedGridLayoutManager등이 있다.
        //아래 소스와 같은 경우엔 LinearLayoutManager이니 수평 혹은 수직이 된다.
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        //mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

    }
}