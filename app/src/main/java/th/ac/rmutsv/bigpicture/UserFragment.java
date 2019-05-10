package th.ac.rmutsv.bigpicture;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment userInstance(String name,String email,String urlImage){

        UserFragment userFragment = new UserFragment();
        Bundle bundle = new Bundle();

        bundle.putString("Name",name);
        bundle.putString("Email",email);
        bundle.putString("Image",urlImage);

        userFragment.setArguments(bundle);

        return userFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // show view
        showview();

        // Back controller
        backController();
        //Thailand COntroller
        thailandCOntroller();
    }// main method

    private void thailandCOntroller() {
        Button button = getView().findViewById(R.id.btnThailand);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                final String[] name = new String[1];
                final String[] email = new String[1];
                final String[] urlImage = new String[1];

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference()
                        .child("User")
                        .child(firebaseAuth.getUid());

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        UserModel userModel = dataSnapshot.getValue(UserModel.class);
                        name[0] = userModel.getName();
                        email[0] = userModel.getEmail();
                        urlImage[0] = userModel.getImage();

                        editFirebase(firebaseAuth.getUid(),"Thailand",email[0],urlImage[0]);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void editFirebase(String uid, String name, String email, String urlImage) {
        UserModel userModel = new UserModel(email, urlImage, name);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference()
                .child("User")
                .child(uid);
        databaseReference.setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    private void backController() {
        Button button = getView().findViewById(R.id.btnBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void showview() {
        // get value from argument
        String name = getArguments().getString("Name");
        String email = getArguments().getString("Email");
        String urlImage = getArguments().getString("Image");

        ImageView imageView = getView().findViewById(R.id.imgIcon);
        Picasso.get()
                .load(urlImage)
                .resize(800,600)
                .into(imageView);

        TextView nameTextView = getView().findViewById(R.id.txtName);
        nameTextView.setText(name);

        TextView emailTextView = getView().findViewById(R.id.txtEmail);
        emailTextView.setText(email);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

}
