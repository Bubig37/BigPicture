package th.ac.rmutsv.bigpicture;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder>{

    private Context context;
    private ArrayList<String> iconStringArrayList, namStringArrayList, emailStringArrayList;
    private LayoutInflater layoutInflater;

    public FriendAdapter(Context context,
                         ArrayList<String> iconStringArrayList,
                         ArrayList<String> namStringArrayList,
                         ArrayList<String> emailStringArrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.iconStringArrayList = iconStringArrayList;
        this.namStringArrayList = namStringArrayList;
        this.emailStringArrayList = emailStringArrayList;
    }  // constructor

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {



        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder friendViewHolder, int i) {

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
