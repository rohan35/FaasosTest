package com.sony.faasostest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sony.faasostest.Model.Address;
import com.sony.faasostest.Model.UserInfo;
import com.sony.faasostest.R;
import com.sony.faasostest.Utils.RXBus;
import com.sony.faasostest.Utils.ShowMapActivity;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Dell on 25-07-2018.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {
    private Context mContext;
    private List<Address> addresses;

    public AddressAdapter(Context mContext, List<Address> address) {
        this.mContext = mContext;
        this.addresses = address;
    }

    @NonNull
    @Override
    public AddressAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.layout_rv_address,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.MyViewHolder holder, int position) {
        Address address=addresses.get(position);
        holder.street.setText("Address: "+address.street);
        holder.suit.setText(address.suite);
        holder.city.setText(address.city+"-"+address.zipcode);

    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView street,suit,city;
        Button button;
        public MyViewHolder(View itemView) {
            super(itemView);
            street=itemView.findViewById(R.id.textView2);
            suit=itemView.findViewById(R.id.textView3);
            city=itemView.findViewById(R.id.textView4);
            button=itemView.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // display the map activity

                    RXBus.getSubject2().onNext(addresses.get(getAdapterPosition()));
                    mContext.startActivity(new Intent(mContext, ShowMapActivity.class));

                }
            });
        }
    }
}
