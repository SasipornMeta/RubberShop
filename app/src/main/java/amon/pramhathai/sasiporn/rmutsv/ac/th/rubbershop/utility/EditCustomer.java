package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by sasiporn on 2/8/2018 AD.
 */

public class EditCustomer extends AsyncTask<String, Void, String> {

    private Context context;

    public EditCustomer(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("c_id", strings[0])
                    .add("c_name", strings[1])
                    .add("c_lname", strings[2])
                    .add("c_address", strings[3])
                    .add("c_tel", strings[4])
                    .add("c_user", strings[5])
                    .add("c_password", strings[6])
                    .add("o_idshop", strings[7])
                    .build();

            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[8]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}   // main class
