package com.nguyenoanh.demoactivity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nguyenoanh.demoactivity.Model.ItemChat;
import com.nguyenoanh.demoactivity.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemMessageAdapter extends RecyclerView.Adapter<ItemMessageAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context context;
    private ArrayList<ItemChat> listChat;
//    private String imageURL;

    public ItemMessageAdapter(Context context, ArrayList<ItemChat> listChat) {
        this.context = context;
        this.listChat = listChat;
    }

    @NonNull
    @Override
    public ItemMessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from (context);
        if(viewType == MSG_TYPE_RIGHT) {
            View view = inflater.inflate (R.layout.item_chat_right, null);
            return new ItemMessageAdapter.ViewHolder (view);
        }else{
            View view = inflater.inflate (R.layout.item_chat_left, null);
            return new ItemMessageAdapter.ViewHolder (view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ItemMessageAdapter.ViewHolder holder, int i) {

        ItemChat chat = listChat.get (i);

        switch (holder.getItemViewType ()){
            case 0:
                holder.showMess.setText (chat.getMessage ());
//                if (imageURL.equals("default")){
//                    holder.profileImage.setImageResource(R.drawable.anh);
//                } else {
//                    Glide.with(context).load(imageURL).into(holder.profileImage);
//                }
                break;
            case 1:
                holder.showMess.setText (chat.getMessage ());
                break;
            default:
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public CircleImageView profileImage;
        public TextView showMess;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            profileImage = (CircleImageView) itemView.findViewById (R.id.profileImage);
            showMess = (TextView) itemView.findViewById (R.id.show_mess);
        }
    }

    @Override
    public int getItemViewType(int i){
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance ().getCurrentUser ();
        if(listChat.get (i).getSender () == getItemId (i)){
            return MSG_TYPE_LEFT;
        }else {
            return MSG_TYPE_RIGHT;
        }
    }

    @Override
    public int getItemCount() {
        return listChat.size ();
    }
}
