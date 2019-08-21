package com.nguyenoanh.demoactivity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenoanh.demoactivity.Fragment.MessageFragment;
import com.nguyenoanh.demoactivity.Model.ItemNew;
import com.nguyenoanh.demoactivity.R;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class ItemNewAdapter extends RecyclerView.Adapter<ItemNewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ItemNew> listNew;

    public ItemNewAdapter(Context context, ArrayList<ItemNew> listNew) {
        this.context = context;
        this.listNew = listNew;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from (context);
        View view = inflater.inflate (R.layout.item_new, viewGroup, false);


        return new ItemNewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        final ItemNew itemNew = listNew.get (i);

        holder.tvName.setText (itemNew.getUsername ());
        holder.tvTime.setText (itemNew.getTime ());

        if(itemNew.getContent () != null) {
            holder.tvContent.setText (itemNew.getContent ());
        }else {
            holder.tvContent.setVisibility (View.GONE);
        }

        holder.tvPrice.setText (itemNew.getPrice ());
        holder.profileImage.setImageDrawable (context.getResources ().getDrawable (itemNew.getProfileImage ()));

        if(itemNew.getImage () != null) {
            holder.image.setImageDrawable ((itemNew.getImage ()));
        }else {
            holder.image.setVisibility (View.GONE);
        }

        holder.profileImage.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt ("id", itemNew.getId ());
                bundle.putString ("name", itemNew.getUsername());
                bundle.putInt ("avatar", itemNew.getProfileImage ());

                MessageFragment fragment = new MessageFragment ();
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                try {
                    transaction.add(R.id.frame_layout, fragment,"Message");
                    transaction.addToBackStack (fragmentManager.getClass ().getSimpleName ());
                    transaction.commit();
                } catch (Exception e) {
                    Toast.makeText(context, "ViewHolder " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                fragment.setArguments (bundle);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        CircleImageView profileImage;
        ImageView image;
        TextView tvName, tvTime, tvContent, tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            profileImage = (CircleImageView) itemView.findViewById (R.id.imv_profile);
            image = (ImageView) itemView.findViewById (R.id.image);

            tvName = (TextView) itemView.findViewById (R.id.tv_name);
            tvTime = (TextView) itemView.findViewById (R.id.tv_time);
            tvContent = (TextView) itemView.findViewById (R.id.tv_content);
            tvPrice = (TextView) itemView.findViewById (R.id.tv_price);

            itemView.setOnClickListener (this);
        }

        @Override
        public void onClick(View view) {
        }
    }

    @Override
    public int getItemCount() {
        return listNew.size ();
    }
}
