package in.helpingdevelop.shortfilm.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.helpingdevelop.shortfilm.R;


public class MyProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView profile_details_list = view.findViewById(R.id.profile_details_list);

        String[] titleArr = new String[]{
                getString(R.string.name),
                getString(R.string.mobile),
                getString(R.string.date_of_birth),
                getString(R.string.email_id),
                getString(R.string.profile_password),
                getString(R.string.ticket_booked)
        };
        String[] valueArr = {"Yogesh Kumar", "8668357435", "13/04/1982", "xyz@gmail.com", "**********","0"};

        ArrayList<Map<String, Object>> itemDataList = new ArrayList<Map<String, Object>>();

        int titleLen = titleArr.length;
        for (int i = 0; i < titleLen; i++) {
            Map<String, Object> listItemMap = new HashMap<String, Object>();
            listItemMap.put("title", titleArr[i]);
            listItemMap.put("description", valueArr[i]);
            itemDataList.add(listItemMap);
        }

        SimpleAdapter adapter = new SimpleAdapter(getContext(), itemDataList, R.layout.list_item_user_profile,
                new String[]{"title", "description"}, new int[]{R.id.text1, R.id.text2});

        profile_details_list.setAdapter(adapter);
    }
}
