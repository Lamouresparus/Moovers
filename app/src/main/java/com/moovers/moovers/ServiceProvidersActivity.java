package com.moovers.moovers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.moovers.moovers.model.ServiceProviders;
import com.moovers.moovers.model.ServiceProvidersAdapter;
import com.moovers.moovers.netwok.Api;
import com.moovers.moovers.netwok.RetrofitClientInstance;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceProvidersActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    private RecyclerView recyclerView;
    private ServiceProvidersAdapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

       String category =  intent.getStringExtra(Constants.SERVICE_CATEGORY);
       setTitle(category);

        progressDialog = new ProgressDialog(ServiceProvidersActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Api getService = RetrofitClientInstance.getRetrofitInstance().create(Api.class);

        Call <List<ServiceProviders> >call = getService.getServiceProviders(category);
        call.enqueue(new Callback<List<ServiceProviders>>() {
            @Override
            public void onResponse(Call<List<ServiceProviders>> call, Response<List<ServiceProviders>> response) {
                progressDialog.dismiss();
                if (response.body() != null){
                    generateServiceProvidersListI(response.body());
                }else {
                    Snackbar
                            .make(findViewById(R.id.category_layout),
                                    "Records not found for this category",
                                    Snackbar.LENGTH_INDEFINITE).show();

                }
            }

            @Override
            public void onFailure(Call<List<ServiceProviders>> call, Throwable t) {
                progressDialog.dismiss();
                Log.d(TAG, Objects.requireNonNull(t.getLocalizedMessage()));
                Toast.makeText(ServiceProvidersActivity.this, "Something went wrong...Please try later!"+t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });


    }

    private void generateServiceProvidersListI(List<ServiceProviders> serviceProvidersList){
        recyclerView = findViewById(R.id.rvServiceProviders);
        adapter = new ServiceProvidersAdapter(this, serviceProvidersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ServiceProvidersActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }
}
