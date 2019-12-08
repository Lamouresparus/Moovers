package com.moovers.moovers.ui.category;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moovers.moovers.Constants;
import com.moovers.moovers.ServiceProvidersActivity;
import com.moovers.moovers.R;
import com.moovers.moovers.model.ServiceCategories;
import com.moovers.moovers.model.ServicesCategoryAdapter;
import com.moovers.moovers.ui.CallbackListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private ServicesCategoryAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerView = findViewById(R.id.rvCategoriesServiceProviders);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ServicesCategoryAdapter(this, serviceCategoriesList(), new CallbackListener() {
            @Override
            public void onItemClick(String category) {

                Intent intent = new Intent(CategoryActivity.this, ServiceProvidersActivity.class);

                intent.putExtra(Constants.SERVICE_CATEGORY,category);

                startActivity(intent);


            }
        });
        recyclerView.setAdapter(adapter);




    }


    private List<ServiceCategories> serviceCategoriesList (){
        List<ServiceCategories> serviceCategories = new ArrayList<>();
        serviceCategories.add(new ServiceCategories("Carpentry", R.drawable.carpentry));
        serviceCategories.add(new ServiceCategories("Electricals", R.drawable.electric));
        serviceCategories.add(new ServiceCategories("Plumbing", R.drawable.plumbing));
        serviceCategories.add(new ServiceCategories("Cleaning", R.drawable.cleaning));
        serviceCategories.add(new ServiceCategories("Painting", R.drawable.painting));

        return serviceCategories;
    }




}
