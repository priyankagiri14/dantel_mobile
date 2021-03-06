package com.app.dantel.mobile.OpenCloseBatches;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.dantel.mobile.Agent.Sim_allocation;
import com.app.dantel.mobile.R;
import java.util.List;


public class OpenCloseListAdapter extends BaseAdapter {

    private List<String> serialsGetResponses;

    private Context context;

    public OpenCloseListAdapter(Context context, List<String> serialsGetResponses) {
        this.context = context;
        this.serialsGetResponses = serialsGetResponses;

    }

    class MyViewHolder {
        public TextView serialopenedtext;
        public LinearLayout linear_layout_serials;
        public Button btn_rica;

        MyViewHolder(View view) {
            serialopenedtext = (TextView) view.findViewById(R.id.agents_batches_opened_list_text);
            linear_layout_serials = (LinearLayout) view.findViewById(R.id.linear_layout_serials);
            btn_rica = view.findViewById(R.id.btn_rica);
        }
    }


    @Override
    public int getCount() {
        return serialsGetResponses.size();
    }

    @Override
    public Object getItem(int position)
    {
        return serialsGetResponses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View row = view;
        MyViewHolder holder = null;

        if (row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.open_close_list_item, parent, false);
            holder = new MyViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (MyViewHolder) row.getTag();
        }
        //batchesgetcheckbox.setChecked(fa);
        holder.serialopenedtext.setText(serialsGetResponses.get(position));
        holder.btn_rica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = serialsGetResponses.get(position);
                Log.d("Value is: ",value);
                Intent intent = new Intent(context, Sim_allocation.class);
                intent.putExtra("batches_received",value);
                context.startActivity(intent);
            }
        });//        holder.batchesreceivedcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked)
//                {
//                    bodyList.get(position).setIschecked(true);
//                }
//                else
//                {
//                    bodyList.get(position).setIschecked(false);
//                }
//            }
//        });
        return row;
    }
}