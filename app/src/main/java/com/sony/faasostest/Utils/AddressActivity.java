package com.sony.faasostest.Utils;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.sony.faasostest.Adapter.AddressAdapter;
import com.sony.faasostest.Model.Address;
import com.sony.faasostest.Model.UserInfo;
import com.sony.faasostest.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Dell on 25-07-2018.
 */

public class AddressActivity extends AppCompatActivity {
    Disposable disposable;
    List<Address> addresses=new ArrayList<>();
    List<UserInfo> userInfo;
    RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("CUSTOMER DETAILS");
        recyclerView=findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        eventListener();
    }

    private void eventListener() {
        disposable=RXBus.getSubject().subscribeWith(new DisposableObserver<Object>()
        {

            @Override
            public void onNext(Object o) {
              userInfo=(List<UserInfo>) o ;
              for(int i=0;i<userInfo.size();i++)
              {
                  if(userInfo.get(i).getChecked())
                  {
                      addresses.add(userInfo.get(i).address);
                  }
              }
                AddressAdapter addressAdapter=new AddressAdapter(AddressActivity.this,addresses);
             recyclerView.setAdapter(addressAdapter);

            }

            @Override
            public void onError(Throwable e) {
System.out.println(e);
            }

            @Override
            public void onComplete() {
                System.out.println("completed");
            }
        });
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
