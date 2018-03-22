package com.example.gregsquibbs.paws.UserArea;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.gregsquibbs.paws.ChangePassword.ChangePasswordActivity;
import com.example.gregsquibbs.paws.DatabaseInteraction.GetData;
import com.example.gregsquibbs.paws.DatabaseInteraction.NewEntryRequest;
import com.example.gregsquibbs.paws.IntentConstants;
import com.example.gregsquibbs.paws.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gregsquibbs on 09/02/2018.
 */

public class UserAreaFragment extends Fragment {

    private String userID;
    private String name;
    private String username;
    private String email;
    private String password;
    private TextView tvPassword;

    public UserAreaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userID = bundle.getString("userID");
            name = bundle.getString("name");
            username = bundle.getString("username");
            email = bundle.getString("email");
            password = bundle.getString("password");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_area_fragment, container, false);

        // Initializing elements
        final TextView tvName = (TextView) view.findViewById(R.id.tvName);
        final TextView tvUsername = (TextView) view.findViewById(R.id.tvUsername);
        final TextView tvEmail = (TextView) view.findViewById(R.id.tvEmail);
        tvPassword = (TextView) view.findViewById(R.id.tvPassword);
        final Button bEdit = (Button) view.findViewById(R.id.bEdit);
        final Button bUpdate = (Button) view.findViewById(R.id.bUpdate);
        final Button bGet = (Button) view.findViewById(R.id.bGet);



        // Setting text fields with relevant info
        tvName.setText("Name: "+name);
        tvUsername.setText("Username: "+username);
        tvEmail.setText("Email: "+email);
        starPassword();

        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
                intent.putExtra("password", password);
                intent.putExtra("username", username);
                startActivityForResult(intent, IntentConstants.INTENT_REQUEST_CODE);
            }
        });

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                System.out.println("Successful");
                            } else {
                                System.out.println("Unsuccessful");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                NewEntryRequest newEntryRequest = new NewEntryRequest(1, 1, 1, 1, 1, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(newEntryRequest);
            }
        });

        bGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            //Need to parse jsonResponse here

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                GetData getData = new GetData(Integer.valueOf(userID), responseListener);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(getData);

            }
        });



        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(data.getExtras().containsKey(IntentConstants.INTENT_NEW_PASSWORD)) {
            password = data.getStringExtra(IntentConstants.INTENT_NEW_PASSWORD);
            starPassword();
        }
    }

    public void starPassword() {
        int length = password.length();
        String passwordStars="";
        for(int i=0;i<length;i++){
            passwordStars += "*";
        }
        tvPassword.setText("Password: "+passwordStars);
    }


}
