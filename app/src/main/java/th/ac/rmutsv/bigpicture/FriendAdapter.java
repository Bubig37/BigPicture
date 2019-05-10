package th.ac.rmutsv.bigpicture;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder>{

    private Context context;
    private ArrayList<String> iconStringArrayList, namStringArrayList, emailStringArrayList;
    private OnClickItem onClickItem;
    private LayoutInflater layoutInflater;

    public FriendAdapter(Context context,
                         ArrayList<String> iconStringArrayList,
                         ArrayList<String> namStringArrayList,
                         ArrayList<String> emailStringArrayList,
                         OnClickItem onClickItem) {
        this.layoutInflater = LayoutInflater.from(context);
        this.iconStringArrayList = iconStringArrayList;
        this.namStringArrayList = namStringArrayList;
        this.emailStringArrayList = emailStringArrayList;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.layout_list_friend, viewGroup, false);
        FriendViewHolder friendViewHolder = new FriendViewHolder(view);

        return friendViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FriendViewHolder friendViewHolder, int i) {
        String name = namStringArrayList.get(i);
        String email = emailStringArrayList.get(i);
        String urlIcon = iconStringArrayList.get(i);

        friendViewHolder.nameTextView.setText(name);
        friendViewHolder.emailTextView.setText(email);
        Picasso
                .get()
                .load(urlIcon)
                .resize(800,600)
                .into(friendViewHolder.imageView);

        friendViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClickItem(v,friendViewHolder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return namStringArrayList.size();
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView nameTextView,emailTextView;


        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgIcon);
            nameTextView = itemView.findViewById(R.id.txtName);
            emailTextView = itemView.findViewById(R.id.txtEmail);


        }
    }  // class FriendViewHolder

}  // main class
