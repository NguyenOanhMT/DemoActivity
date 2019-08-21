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
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenoanh.demoactivity.Activity.MessageActivity;
import com.nguyenoanh.demoactivity.Model.ItemUser;
import com.nguyenoanh.demoactivity.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemUserAdapter extends RecyclerView.Adapter<ItemUserAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ItemUser> listUser;

    public ItemUserAdapter(Context context, ArrayList<ItemUser> listUser) {
        this.context = context;
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from (context);

        View view = inflater.inflate (R.layout.item_mess, null);
        return new ItemUserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        ItemUser item = listUser.get (i);

        holder.userName.setText (item.getUsername ());
        if(item.getNumberMess () != null){
            holder.userName.setTextColor (context.getResources().getColor (R.color.colorAccent));
        }else {
            holder.userName.setTextColor (context.getResources().getColor (R.color.dark_blue_grey));
        }
        if (item.getStatus () == "online"){
            holder.imageAction.setImageResource (item.getActionUser ());
        }else {
            holder.imageAction.setVisibility (View.GONE);
        }
        holder.message.setText (item.getMessage ());
        holder.numberMess.setText (item.getNumberMess ());

        holder.imageAvatar.setImageResource (item.getImvAvatar ());
        holder.backgroundNumberMess.setImageResource (item.getImageNumber ());

        holder.userName.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Toast.makeText (context.getApplicationContext (), "hello", Toast.LENGTH_SHORT).show ();
//                Bundle bundle = new Bundle ();
//                bundle.putInt ("avatar", item.getImvAvatar ());
//
//                Intent intent = new Intent (context.getApplicationContext (), MessageActivity.class);
//                intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.putExtra ("bundle", bundle);
//                context.startActivity (intent);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CircleImageView imageAvatar, backgroundNumberMess;
        ImageView imageAction;

        TextView userName, time, numberMess, message;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);
            userName = (TextView) itemView.findViewById (R.id.tv_name);
            message = (TextView) itemView.findViewById (R.id.tv_message);
            time = (TextView) itemView.findViewById (R.id.tv_time);
            numberMess = (TextView) itemView.findViewById (R.id.number_mess);

            imageAvatar = (CircleImageView) itemView.findViewById (R.id.imv_avatar);
            imageAction = (ImageView) itemView.findViewById (R.id.user_online_offline);
            backgroundNumberMess = (CircleImageView) itemView.findViewById (R.id.background_number_mess);

            itemView.setOnClickListener (this);

//            userName.setOnClickListener (new View.OnClickListener () {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText (context.getApplicationContext (), "hello", Toast.LENGTH_SHORT).show ();
//                    Intent intent = new Intent (context.getApplicationContext (), MessageActivity.class);
//                    intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    context.startActivity (intent);
//                }
//            });
        }

        @Override
        public void onClick(View view) {
        }
    }

    @Override
    public int getItemCount() {
        return listUser.size ();
    }

    public void addItemToList(ItemUser itemUser) {
        listUser.add(0, itemUser);
        notifyDataSetChanged();
    }

    public void removeItem(int index){
        listUser.remove(index);
        notifyDataSetChanged();
    }
}

