package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by masterung on 26/2/2018 AD.
 */

public class PostSale extends AsyncTask<String, Void, String>{
    private Context context;

    public PostSale(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("s_date", strings[0])
                    .add("s_weight", strings[1])
                    .add("s_price", strings[2])
                    .add("s_total", strings[3])
                    .add("c_id", strings[4])
                    .add("s_status", strings[5])
                    .add("s_balance", strings[6])
                    .build();

            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[7]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();


        } catch (Exception e) {                                 // การ error ที่ยอมรับได้
            e.printStackTrace();
            return null;
        }
    }
}
