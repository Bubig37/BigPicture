package th.ac.rmutsv.bigpicture;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private ImageView imageView;
    private Uri uri;
    private boolean aBoolean = true;


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Toolbar
        createToolbar();

//        Avartar Controller
        avartarController();

    }  // main method

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {

            uri = data.getData();

            try {

                Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 800, 600, false);
                imageView.setImageBitmap(bitmap1);
                aBoolean = false;

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    private void avartarController() {
        imageView = getView().findViewById(R.id.imgAvatar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);   // open other program
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Please Choose App"),5);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_register, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.itemMenuRegister) {
            CheckAndUpload();
        }

        return super.onOptionsItemSelected(item);
    }

    private void CheckAndUpload() {

        MyAlert myAlert = new MyAlert(getActivity());
        EditText nameEditText = getView().findViewById(R.id.edtName);
        EditText emailEditText = getView().findViewById(R.id.edtUser);
        EditText passwordEditText = getView().findViewById(R.id.edtPassword);

        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (aBoolean) {
            // non choose avatar
            myAlert.normalDialog("Non choose Avatar","Please choose Avartar");
        } else if (name.isEmpty()||email.isEmpty()||password.isEmpty()) {
            myAlert.normalDialog("Have Space","Please Fill All Blank");
        } else {

        }
    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Register");
        ((MainActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

}
