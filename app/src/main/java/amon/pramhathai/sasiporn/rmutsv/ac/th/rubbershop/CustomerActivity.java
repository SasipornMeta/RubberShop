package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment.CustomerFregment;

public class CustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        String[] logingStrings = getIntent().getStringArrayExtra("Login");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.contentCustomerFragment,
                    CustomerFregment.customerInstance(logingStrings))
            .commit();


        }


    }
}
