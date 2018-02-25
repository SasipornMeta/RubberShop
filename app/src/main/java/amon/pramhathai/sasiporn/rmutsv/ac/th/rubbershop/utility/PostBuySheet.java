package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by masterung on 25/2/2018 AD.
 */

public class PostBuySheet extends AsyncTask<String, Void, String>{

    private Context context;

    public PostBuySheet(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("b2_date", strings[0])
                    .add("c_id", strings[1])
                    .add("c_name", strings[2])
                    .add("b2_weight", strings[3])
                    .add("b2_price", strings[4])
                    .add("b2_total", strings[5])
                    .build();

            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[6]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();


        } catch (Exception e) {                                 // การ error ที่ยอมรับได้
            e.printStackTrace();
            return null;
        }
    }
}
