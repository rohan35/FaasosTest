package com.sony.faasostest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.sony.faasostest.Adapter.UserInfoAdapter;
import com.sony.faasostest.Model.Address;
import com.sony.faasostest.Model.UserInfo;
import com.sony.faasostest.Retrofit.APIStruct;
import com.sony.faasostest.Retrofit.RetrofitClient;
import com.sony.faasostest.Utils.AddressActivity;
import com.sony.faasostest.Utils.RXBus;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    APIStruct myapi;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    RecyclerView recyclerView;
    Button showDetails;
    Toolbar toolbar;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        Retrofit retrofit= RetrofitClient.getInstance();
        myapi=retrofit.create(APIStruct.class);
        recyclerView=findViewById(R.id.rv);
        progressBar=findViewById(R.id.pb);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        fetchUsers();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("LIST OF CUSTOMERS");
        showDetails=findViewById(R.id.details);
    }

    private void fetchUsers() {
        compositeDisposable.add(myapi.getInfo().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<UserInfo>>() {
            @Override
            public void accept(List<UserInfo> userInfos) throws Exception {
                // Update the ui
                updateUI(userInfos);

            }
        }));
    }

    private void updateUI(List<UserInfo> userInfos) {
        // Update the recycler vieww
        UserInfoAdapter adapter=new UserInfoAdapter(this, userInfos);
        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(adapter);
        RXBus.getSubject().onNext(userInfos);

    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();

    }

    public void showDetails(View view) {
        startActivity(new Intent(MainActivity.this, AddressActivity.class));
    }
}
