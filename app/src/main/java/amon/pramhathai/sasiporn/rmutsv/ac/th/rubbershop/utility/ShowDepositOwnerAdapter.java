package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;

/**
 * Created by sasiporn on 3/1/2018 AD.
 */

public class ShowDepositOwnerAdapter extends BaseAdapter {

    private Context context;
    private String[] dateTimeStrings, nameStrings, totalStrings;

    public ShowDepositOwnerAdapter(Context context,
                                   String[] dateTimeStrings,
                                   String[] nameStrings,
                                   String[] totalStrings) {
        this.context = context;
        this.dateTimeStrings = dateTimeStrings;
        this.nameStrings = nameStrings;
        this.totalStrings = totalStrings;
    }


    @Override
    public int getCount() {
        return dateTimeStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.listview_deposit_owner, parent, false);

        TextView dateTimeTextView = view.findViewById(R.id.txtShowDate);
        TextView nameTextView = view.findViewById(R.id.txtShowName);
        TextView totalTextView = view.findViewById(R.id.txtShowTotal);

        dateTimeTextView.setText(dateTimeStrings[position]);
        nameTextView.setText(nameStrings[position]);
        totalTextView.setText(totalStrings[position]);

        return view;
    }
}
