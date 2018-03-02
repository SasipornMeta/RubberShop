package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;

/**
 * Created by masterung on 27/2/2018 AD.
 */

public class ShowDepositAdapter extends BaseAdapter{

    private Context context;
    private String[] dateTimeStrings, depositStrings;

    public ShowDepositAdapter(Context context, String[] dateTimeStrings, String[] depositStrings) {
        this.context = context;
        this.dateTimeStrings = dateTimeStrings;
        this.depositStrings = depositStrings;
    }



    @Override
    public int getCount() {
        return dateTimeStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.listview_deposit, parent, false);

        TextView dateTimeTextView = view.findViewById(R.id.txtShowDate);
        TextView depositTextView = view.findViewById(R.id.txtb_balance);

        dateTimeTextView.setText(dateTimeStrings[position]);
        depositTextView.setText(depositStrings[position]);

        return view;
    }
}
