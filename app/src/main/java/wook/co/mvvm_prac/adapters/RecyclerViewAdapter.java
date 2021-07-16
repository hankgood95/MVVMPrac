package wook.co.mvvm_prac.adapters;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.request.RequestOptions;

        import java.util.ArrayList;
        import java.util.List;

        import de.hdodenhof.circleimageview.CircleImageView;
        import wook.co.mvvm_prac.R;
        import wook.co.mvvm_prac.models.NicePlace;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter"; //디버깅을 위한것


    private List<NicePlace> mNicePlaces = new ArrayList<>();
    private Context mContext; // Context를 담는 변수

    public RecyclerViewAdapter(List<NicePlace> niceplaces, Context mContext) {
        mNicePlaces = niceplaces;
        this.mContext = mContext;
    }

    //화면에 표시될 아이템 뷰를 저장하는 객체를 정의하는 부분
    public class ViewHolder extends RecyclerView.ViewHolder{ //

        CircleImageView mImage; //동그란 이미지뷰를 가져옴
        TextView mName; //textview 가져오고

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.image_name);

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) { //position은 해당 ViewHolder의 위치index를 나타내는것 같음
        // Set the name of the 'NicePlace'
        ((ViewHolder)viewHolder).mName.setText(mNicePlaces.get(position).getTitle());

        // Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mNicePlaces.get(position).getImageUrl())
                .into(((ViewHolder)viewHolder).mImage);
    }

    //listItem안에 몇개의 item들이 있는지 return 하는것 그래서 return 되는 숫자만큼 item들을 보여줌줌
    @Override
    public int getItemCount() {
        return mNicePlaces.size();
    }

}
