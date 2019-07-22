package com.submission.studio.moohat.bukatoko.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.submission.studio.moohat.bukatoko.R;
import com.submission.studio.moohat.bukatoko.activity.SignupActivity;
import com.submission.studio.moohat.bukatoko.data.model.User;
import com.submission.studio.moohat.bukatoko.data.retrofit.Api;
import com.submission.studio.moohat.bukatoko.data.retrofit.ApiInterface;
import com.submission.studio.moohat.bukatoko.utils.Converter;
import com.xwray.passwordview.PasswordView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    EditText edtName, edtEmail;
    PasswordView edtPass,edtConfirm;
    Button btnSignup;



    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_signup, container, false);
        View view =  inflater.inflate(R.layout.fragment_signup, container, false);

        edtName = view.findViewById(R.id.edtName);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPass = view.findViewById(R.id.edtPass);
        edtConfirm = view.findViewById(R.id.edtConfirm);
        btnSignup = view.findViewById(R.id.btnSignup);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtName.length() > 0 &&edtEmail.length() > 0 && edtPass.length() > 0 && edtConfirm.length() > 0){

                    //mengecek apakah beda password  dengan confirm password
                    if(!edtPass.getText().toString().equals( edtConfirm.getText().toString() )){
                        Toast.makeText(getContext(), "Konfirmasi Password dengan benar",
                                Toast.LENGTH_SHORT).show();

                    }else if(!Converter.isValidEmailId(edtEmail.getText().toString())){
                        Toast.makeText(getContext(), "Isi format email dengan benar",
                                Toast.LENGTH_SHORT).show();
                    }else{

                        Auth();
//                        Toast.makeText(getContext(), "Selamat Datang, pendaftaran berhasil",
//                                Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getContext(), "Isi Data dengan benar",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


    private void Auth(){


        ApiInterface apiInterface = Api.getUrl().create((ApiInterface.class));
        Call<User> call = apiInterface.authRegister(edtName.getText().toString(), edtEmail.getText().toString(), edtPass.getText().toString());
        call.enqueue(new Callback<User>(){

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){

                    Toast.makeText(getContext(), "Berhasil Menjadi Pengguna Baru! ", Toast.LENGTH_LONG).show();
                    SignupActivity.tabLayout.getTabAt(1).select();


                }else{
                    Toast.makeText(getContext(), response.message(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

}
