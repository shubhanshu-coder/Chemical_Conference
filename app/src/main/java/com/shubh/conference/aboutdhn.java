package com.shubh.conference;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link aboutdhn#newInstance} factory method to
 * create an instance of this fragment.
 */
public class aboutdhn extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public aboutdhn() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment aboutdhn.
     */
    // TODO: Rename and change types and number of parameters
    public static aboutdhn newInstance(String param1, String param2) {
        aboutdhn fragment = new aboutdhn();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_aboutdhn, container, false);
        Button reach = (Button) view.findViewById(R.id.reach);
        Button ism = (Button) view.findViewById(R.id.ism);
        Button dep = (Button) view.findViewById(R.id.dep);
        Button tourist= (Button) view.findViewById(R.id.tourist);
        reach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Golink("https://people.iitism.ac.in/~N0ET-2022/files/Howtoreach.pdf");
            }
        });
        ism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Golink("https://www.iitism.ac.in/iitismnew/");
            }
        });
        dep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Golink("https://www.iitism.ac.in/index.php/Departments/dept_chem");
            }
        });
        tourist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Golink("https://dhanbad.nic.in/tourist-places/");
            }
        });
        return view;
    }
    private void Golink(String s){
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}