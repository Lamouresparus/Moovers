package com.moovers.moovers.model;

import android.content.Context;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moovers.moovers.R;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

import okhttp3.OkHttpClient;

public class ServiceProvidersAdapter extends RecyclerView.Adapter<ServiceProvidersAdapter.ViewHolder> {

    private List<ServiceProviders> mServiceProviders;
    private Context mContext;

    public ServiceProvidersAdapter(Context context, List<ServiceProviders> serviceProviders){
        mServiceProviders = serviceProviders;
        mContext = context;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        public TextView businessName;
        public TextView businessAddress;
        public TextView businessPhone;
        public TextView businessAbout;
        public ImageView businessImage;


        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            businessName = itemView.findViewById(R.id.business_name);
            businessAddress = itemView.findViewById(R.id.business_address);
            businessPhone = itemView.findViewById(R.id.business_phone);
            businessAbout = itemView.findViewById(R.id.business_about);
            businessImage = itemView.findViewById(R.id.business_logo);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View serviceProvidersView = inflater.inflate(R.layout.service_providers_card_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(serviceProvidersView);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     ServiceProviders serviceProviders = mServiceProviders.get(position);

     holder.businessName.setText(serviceProviders.getmName());

     holder.businessAbout.setText(serviceProviders.getmAbout());

     holder.businessPhone.setText(serviceProviders.getmPhone());

     holder.businessAddress.setText(serviceProviders.getmAddress());

     Picasso.Builder builder = new Picasso.Builder(mContext);
     builder.downloader(new OkHttp3Downloader(mContext));
     builder.build().load(serviceProviders.getmImageUrl()).placeholder(R.drawable.ic_launcher_background)
             .error(R.drawable.ic_launcher_background).into(holder.businessImage);
    }

    @Override
    public int getItemCount() {
        return mServiceProviders.size();
    }
}