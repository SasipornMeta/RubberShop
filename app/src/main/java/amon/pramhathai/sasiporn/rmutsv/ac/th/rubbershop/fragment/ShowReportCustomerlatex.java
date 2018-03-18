package amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import amon.pramhathai.sasiporn.rmutsv.ac.th.rubbershop.R;

/**
 * Created by sasiporn on 3/9/2018 AD.
 */

public class ShowReportCustomerlatex extends BaseAdapter {

    private Context context;
    private String[] dateTimeStrings, totalStrings, weightStrings, percentStrings, dryStrings, priceStrings;

    public ShowReportCustomerlatex(Context context,
                                   String[] dateTimeStrings,
                                   String[] totalStrings,
                                   String[] weightStrings,
                                   String[] percentStrings,
                                   String[] dryStrings,
                                   String[] priceStrings) {
        this.context = context;
        this.dateTimeStrings = dateTimeStrings;
        this.totalStrings = totalStrings;
        this.weightStrings = weightStrings;
        this.percentStrings = percentStrings;
        this.dryStrings = dryStrings;
        this.priceStrings = priceStrings;
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
        View view = layoutInflater.inflate(R.layout.listview_report_customer_latex, parent, false);

        TextView dateTimeTextView = view.findViewById(R.id.txtShowDate);
        TextView totalTextView = view.findViewById(R.id.txtShowTotal);
        TextView weightTextView = view.findViewById(R.id.txtWeight);
        TextView percentTextView = view.findViewById(R.id.txtPercent);
        TextView dryTextView = view.findViewById(R.id.txtDry);
        TextView priceTextView = view.findViewById(R.id.txtPrice);

        dateTimeTextView.setText(dateTimeStrings[position]);
        totalTextView.setText(totalStrings[position]);
        weightTextView.setText(weightStrings[position]);
        percentTextView.setText(percentStrings[position]);
        dryTextView.setText(dryStrings[position]);
        priceTextView.setText(priceStrings[position]);


        return view;
    }
}
