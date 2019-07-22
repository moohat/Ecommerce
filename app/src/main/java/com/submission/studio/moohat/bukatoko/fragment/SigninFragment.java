package com.submission.studio.moohat.bukatoko.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.submission.studio.moohat.bukatoko.App;
import com.submission.studio.moohat.bukatoko.R;
import com.submission.studio.moohat.bukatoko.data.model.User;
import com.submission.studio.moohat.bukatoko.data.retrofit.Api;
import com.submission.studio.moohat.bukatoko.data.retrofit.ApiInterface;
import com.submission.studio.moohat.bukatoko.utils.Converter;
import com.xwray.passwordview.PasswordView;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment {

    EditText edtEmail;
    PasswordView edtPass;
    Button btnLogin;


    public SigninFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_signin, container, false);

        edtEmail = view.findViewById(R.id.edtEmail);
        edtPass = view.findViewById(R.id.edtPass);
        btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(edtEmail.length() > 0 && edtPass.length() > 0){
                    if(Converter.isValidEmailId(edtEmail.getText().toString())){

                    Auth(edtEmail.getText().toString(), edtPass.getText().toString());
                    }else {
                        Toast.makeText(getContext(), "Isi Email Dengan Benar ",Toast.LENGTH_SHORT).show();


                    }
                }else {
                    Toast.makeText(getContext(), "Isi Data Dengan Benar ",Toast.LENGTH_SHORT).show();


                }
            }
        });

        return  view;
    }




    private  void Auth(String email, final String pass){
        ApiInterface apiInterface = Api.getUrl().create((ApiInterface.class));
        Call<User> call = apiInterface.authLogin(email, pass);
        call.enqueue(new Callback<User>(){

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User.Data data = response.body().getData();
                    Toast.makeText(getContext(), "Selamat Datang "+data.getName(), Toast.LENGTH_LONG).show();
                    App.prefsManager.createLoginSession(String.valueOf(data.getId()), data.getName(), data.getEmail(), pass);
                    getActivity().finish();

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
