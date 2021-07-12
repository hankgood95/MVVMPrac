package wook.co.coc;

        import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.bumptech.glide.Glide;

        import java.util.ArrayList;

        import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter"; //디버깅을 위한것

    private ArrayList<String> mImageNames = new ArrayList<>(); //이미지이름을 담는 ArraysList
    private ArrayList<String> mImage = new ArrayList<>(); //이미지 URL을 담는 ArrayList
    private Context mContext; // Context를 담는 변수

    public RecyclerViewAdapter(ArrayList<String> mImageNames, ArrayList<String> mImage, Context mContext) {
        this.mImageNames = mImageNames;
        this.mImage = mImage;
        this.mContext = mContext;
    }

    //화면에 표시될 아이템 뷰를 저장하는 객체를 정의하는 부분
    public class ViewHolder extends RecyclerView.ViewHolder{ //

        CircleImageView image; //동그란 이미지뷰를 가져옴
        TextView imageName; //textview 가져오고
        RelativeLayout parentLayout; //relativeLayout을 거기서 가져옴 layoutListItem에서 가져옴

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }

    //뷰홀더를 생성하는 부분
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //부모로부터 Context를 가져오고 해당 Context에 있는것으로 R.layout.layout_listitem를 만듬
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //뷰 홀더가 재활용될때 호출되는 메소드
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) { //position은 해당 ViewHolder의 위치index를 나타내는것 같음
        Log.d(TAG,"onBindViewHolder : called");

        Glide.with(mContext)
                .asBitmap()     //bitmap으로 받을거고
                .load((mImage.get(position)))  //해당 url로 가져올거고
                .into(holder.image);           //ViewHolder에 잇는 Image에다가 집어 넣는다.여기에다가 집어넣을것이다.

        holder.imageName.setText(mImageNames.get(position)); //mImageName List에 있는것준 해당 ViewHolder의 index 위치 값에 있는 imageName을 받는다.
        //해당 Layout이 클릭됐을때
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on : "+mImageNames.get(position));
                Toast.makeText(mContext, mImageNames.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    //listItem안에 몇개의 item들이 있는지 return 하는것 그래서 return 되는 숫자만큼 item들을 보여줌줌
    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

}
