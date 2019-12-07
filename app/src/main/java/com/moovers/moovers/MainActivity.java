package com.moovers.moovers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moovers.moovers.model.ServiceProviders;
import com.moovers.moovers.model.ServiceProvidersAdapter;
import com.moovers.moovers.netwok.Api;
import com.moovers.moovers.netwok.RetrofitClientInstance;
import com.moovers.moovers.ui.auth.AuthenticationActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ServiceProvidersAdapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Api getService = RetrofitClientInstance.getRetrofitInstance().create(Api.class);

        Call <List<ServiceProviders> >call = getService.getServiceProviders();
        call.enqueue(new Callback<List<ServiceProviders>>() {
            @Override
            public void onResponse(Call<List<ServiceProviders>> call, Response<List<ServiceProviders>> response) {
                progressDialog.dismiss();
                generateServiceProvidersListI(response.body());
            }

            @Override
            public void onFailure(Call<List<ServiceProviders>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }

        });
       
    }

    private void generateServiceProvidersListI(List<ServiceProviders> serviceProvidersList){
        recyclerView = findViewById(R.id.rvServiceProviders);
        adapter = new ServiceProvidersAdapter(this, serviceProvidersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }
}
