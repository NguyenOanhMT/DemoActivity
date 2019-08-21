package com.nguyenoanh.demoactivity.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenoanh.demoactivity.Adapter.ItemNewAdapter;
import com.nguyenoanh.demoactivity.Model.ItemNew;
import com.nguyenoanh.demoactivity.R;

import java.util.ArrayList;


public class NewFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<ItemNew> listNew;

    ItemNewAdapter adapter;
    private static final String Tag = "Tag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_new, container, false);
        recyclerView = (RecyclerView) view.findViewById (R.id.recycler_view_home);

        listNew = new ArrayList<> ();

        recyclerView.setHasFixedSize (true);
        recyclerView.setItemAnimator(new DefaultItemAnimator ());
        LinearLayoutManager layoutManager = new LinearLayoutManager ( getContext ());
        recyclerView.setLayoutManager (layoutManager);

        ItemNew itemNew1 = new ItemNew (1, getResources ().getString(R.string.martin_palmer),
                getResources ().getString (R.string.today_03_24_pm),
                getResources ().getString (R.string.content),
                getResources ().getString (R.string._340_00),
                R.drawable.anh,
                getResources ().getDrawable (R.drawable.anh) );
        ItemNew itemNew2 = new ItemNew (2, getResources ().getString(R.string.json),
                getResources ().getString (R.string.today_03_24_pm),
                null,
                getResources ().getString (R.string._340_00),
                R.drawable.anh1,
                getResources ().getDrawable (R.drawable.anh1) );
        ItemNew itemNew3 = new ItemNew ( 3, getResources ().getString(R.string.alex),
                getResources ().getString (R.string.today_03_24_pm),
                getResources ().getString (R.string.content),
                getResources ().getString (R.string._340_00),
                R.drawable.anh2,
                null);
        ItemNew itemNew4 = new ItemNew ( 4, getResources ().getString(R.string.alice),
                getResources ().getString (R.string.today_03_24_pm),
                getResources ().getString (R.string.content),
                getResources ().getString (R.string._340_00),
                R.drawable.anh,
                null);

        listNew.add(itemNew1);
        listNew.add(itemNew2);
        listNew.add(itemNew3);
        listNew.add(itemNew4);

        adapter = new ItemNewAdapter (getContext (), listNew);
        recyclerView.setAdapter (adapter);
        return view;
    }
}
