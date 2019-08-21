package com.nguyenoanh.demoactivity.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nguyenoanh.demoactivity.Adapter.ItemUserAdapter;
import com.nguyenoanh.demoactivity.Model.ItemUser;
import com.nguyenoanh.demoactivity.R;

import java.util.ArrayList;

public class MessageFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<ItemUser> listUser, list;

    ItemUserAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_message, container, false);

        recyclerView = (RecyclerView) view.findViewById (R.id.recycler_view_message);
        recyclerView.setHasFixedSize (true);
        LinearLayoutManager layoutManager = new LinearLayoutManager (getContext ());
        recyclerView.setLayoutManager (layoutManager);

        list = new ArrayList<> ();
        listUser = new ArrayList<> ();

        ItemUser itemUser1 = new ItemUser (1,"online",getResources ().getString (R.string.martin_palmer),
                getResources ().getString(R.string.what_your_name),
                getResources ().getString (R.string._5_45_pm),
                getResources ().getString(R.string._3),
                R.drawable.anh, R.color.colorAccent ,
                R.drawable.background_number_mess );
        ItemUser itemUser2 = new ItemUser (2,"online",getResources ().getString(R.string.json),
                getResources ().getString(R.string.how_old_are_you),
                getResources ().getString (R.string._5_45_pm),
                null,
                R.drawable.anh1, R.color.colorAccent ,
                0 );
        ItemUser itemUser3 = new ItemUser (3, "offline", getResources ().getString(R.string.alex),
                getResources ().getString(R.string.i_wait),
                getResources ().getString (R.string._5_45_pm),
                null,
                R.drawable.anh2, 0 ,
                0 );


        list.add(itemUser1);
        list.add(itemUser2);
        list.add(itemUser3);

        int count = 0;
        Bundle bundle = getArguments();
        if( bundle != null) {
            ImageView line_mess= (ImageView) getActivity ().findViewById (R.id.line_mess);
            ImageView imv_mess= (ImageView) getActivity ().findViewById (R.id.imv_message);
            line_mess.setVisibility (View.VISIBLE);
            imv_mess.setImageDrawable (getResources ().getDrawable (R.drawable.ic_group8_black));

            ImageView line_home= (ImageView) getActivity ().findViewById (R.id.line_home);
            ImageView imv_home= (ImageView) getActivity ().findViewById (R.id.imv_home);
            line_home.setVisibility (View.INVISIBLE);
            imv_home.setImageDrawable (getResources ().getDrawable (R.drawable.ic_group_7));

            int id = getArguments ().getInt ("id");
            String nameUser = getArguments () .getString ("name");
            int avatar = getArguments () .getInt ("avatar");

            for (int i=0; i < list.size (); i++){
                if (list.get (i).getId () == id){
                    listUser.add (list.get (i));
                    count++;
                }
            }
            if (count == 0) {
                listUser.add (
                        new ItemUser(id, "offline", nameUser,
                                getResources ().getString(R.string.i_wait),
                                getResources ().getString (R.string._5_45_pm),
                                null,
                                avatar, 0 , 0));
            }
            for (int i=0; i < list.size (); i++) {
                if (list.get (i).getId () != id) {
                    listUser.add (list.get (i));
                }
            }
//            Intent intent = new Intent (getActivity (), MessageFragment.class);
//            intent.putExtra ("listUser", listUser);
        }else {
            listUser = list;
        }


        adapter = new ItemUserAdapter (getContext (), listUser);
        recyclerView.setAdapter (adapter);

        return view;
    }


}
