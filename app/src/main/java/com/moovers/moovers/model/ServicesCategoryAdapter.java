package com.moovers.moovers.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moovers.moovers.R;
import com.moovers.moovers.ui.CallbackListener;

import java.util.List;

public class ServicesCategoryAdapter extends RecyclerView.Adapter<ServicesCategoryAdapter.ViewHolder> {

    private List<ServiceCategories> mServiceCategoriesList;
    private LayoutInflater layoutInflater;
    private CallbackListener mlistener;

    public ServicesCategoryAdapter(Context context, List<ServiceCategories> serviceCategoriesList,CallbackListener listener){
        mServiceCategoriesList = serviceCategoriesList;
        layoutInflater = LayoutInflater.from(context);
        mlistener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.category_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ServiceCategories categories = mServiceCategoriesList.get(position);

        holder.categoryImageview.setImageResource(categories.getmImageResourceId());
        holder.categoryName.setText(categories.getmCategoryName());


    }

    @Override
    public int getItemCount() {
        return mServiceCategoriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryImageview;
        TextView categoryName;
         ViewHolder(View itemView){
             super(itemView);

             categoryImageview = itemView.findViewById(R.id.service_category_image);
             categoryName = itemView.findViewById(R.id.category_name);

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     ServiceCategories categories = mServiceCategoriesList.get(getAdapterPosition());

                     String categoryName = categories.getmCategoryName();

                     mlistener.onItemClick(categoryName);

                 }
             });


         }


    }

}
