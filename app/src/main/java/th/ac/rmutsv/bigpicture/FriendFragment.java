package th.ac.rmutsv.bigpicture;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendFragment extends Fragment {


    public FriendFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create RecyclerView
        createRecyclerView();

    }// main method

    private void createRecyclerView() {
        final RecyclerView recyclerView = getView().findViewById(R.id.recyclerFriend);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("User");

        final int[] ints = {0};

        final ArrayList<String> nameStringsArrayList = new ArrayList<>();
        final ArrayList<String> emailStringsArrayList = new ArrayList<>();
        final ArrayList<String> iconStringsArrayList = new ArrayList<>();

        final ArrayList<UserModel> userModelArrayList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    UserModel userModel = dataSnapshot1.getValue(UserModel.class);
                    userModelArrayList.add(userModel);

                    UserModel userModel1 = userModelArrayList.get(ints[0]);

                    nameStringsArrayList.add(userModel1.getName());
                    emailStringsArrayList.add(userModel1.getEmail());
                    iconStringsArrayList.add(userModel1.getImage());

                    ints[0] += 1;

                }// for

                FriendAdapter friendAdapter = new FriendAdapter(getActivity()
                        , iconStringsArrayList, nameStringsArrayList, emailStringsArrayList, new OnClickItem() {
                    @Override
                    public void onClickItem(View view, int position) {
                      //  Log.d("9MayV1", "You Click at position = " + position);
                       // Toast.makeText(getActivity(), "Position",Toast.LENGTH_SHORT).show();
                        getActivity()
                                .getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentServiceFragment, UserFragment.userInstance(
                                        nameStringsArrayList.get(position),
                                        emailStringsArrayList.get(position),
                                        iconStringsArrayList.get(position)))
                                .addToBackStack(null)
                                .commit();
                    }
                });
                recyclerView.setAdapter(friendAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }  // Create Recycler View

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friend, container, false);
    }

}
