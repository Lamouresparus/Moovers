package com.moovers.moovers.model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.moovers.moovers.R;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ServiceProvidersAdapter extends RecyclerView.Adapter<ServiceProvidersAdapter.ViewHolder> {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 123;
    private List<ServiceProviders> mServiceProviders;
    private Activity mContext;

    public ServiceProvidersAdapter(Activity context, List<ServiceProviders> serviceProviders){
        mServiceProviders = serviceProviders;
        mContext = context;
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

     holder.businessEmail.setText(serviceProviders.getmEmail());

     Picasso.Builder builder = new Picasso.Builder(mContext);
     builder.downloader(new OkHttp3Downloader(mContext));

     if (serviceProviders.getmImageUrl().isEmpty())return;
     builder.build().load(serviceProviders.getmImageUrl()).placeholder(R.drawable.ic_launcher_background)
             .error(R.drawable.ic_launcher_background).into(holder.businessImage);
    }

    @Override
    public int getItemCount() {
        return mServiceProviders.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        public TextView businessName;
        public TextView businessAddress;
        public TextView businessPhone;
        public TextView businessAbout;
        public ImageView businessImage;
        public TextView businessEmail;
        public LinearLayout call;
        public LinearLayout email;



        public ViewHolder(final View itemView) {
            super(itemView);
            mView = itemView;

            businessName = itemView.findViewById(R.id.business_name);
            businessAddress = itemView.findViewById(R.id.business_address);
            businessPhone = itemView.findViewById(R.id.business_phone);
            businessAbout = itemView.findViewById(R.id.business_about);
            businessImage = itemView.findViewById(R.id.business_logo);
            businessEmail = itemView.findViewById(R.id.business_email);
            call = itemView.findViewById(R.id.layout_phone);
            email = itemView.findViewById(R.id.layout_email);

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (ContextCompat.checkSelfPermission(mContext,
                            Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(mContext,
                                new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);

                        // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.

                    }

                    int i = getAdapterPosition();
                    ServiceProviders serviceProviders = mServiceProviders.get(i);

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+serviceProviders.getmPhone()));
                    mContext.startActivity(intent);
                }
            });

            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int i = getAdapterPosition();
                    ServiceProviders serviceProviders = mServiceProviders.get(i);

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("*/*");
                    intent.putExtra(Intent.EXTRA_EMAIL, serviceProviders.getmEmail());
                    intent.putExtra(Intent.EXTRA_SUBJECT, "");

                    if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }
}