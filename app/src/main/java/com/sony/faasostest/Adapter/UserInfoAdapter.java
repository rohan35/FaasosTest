package com.sony.faasostest.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;

import com.sony.faasostest.Model.UserInfo;
import com.sony.faasostest.R;

import java.util.List;

/**
 * Created by Dell on 25-07-2018.
 */

public class UserInfoAdapter  extends RecyclerView.Adapter<UserInfoAdapter.MyViewHolder> {
    private Context mContext;
    private List<UserInfo> users;

    public UserInfoAdapter(Context mContext, List<UserInfo> users) {
        this.mContext = mContext;
        this.users = users;
    }

    @NonNull
    @Override
    public UserInfoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.layout_rv,parent,false);
     return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserInfoAdapter.MyViewHolder holder, int position) {
        UserInfo userInfo=users.get(position);
        holder.name.setText("Name"+":"+userInfo.name);
        holder.email.setText(userInfo.email);

    }

    @Override
    public int getItemCount() {
      return users.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,email;
        CheckBox checkBox;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textView6);
            email=itemView.findViewById(R.id.textView7);
            checkBox=itemView.findViewById(R.id.checkBox);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    if (users.get(adapterPosition).getChecked()) {
                        checkBox.setChecked(false);
                        users.get(adapterPosition).setChecked(false);
                    }
                    else {
                        checkBox.setChecked(true);
                        users.get(adapterPosition).setChecked(true);
                    }
                }
            });

        }

    }
}
