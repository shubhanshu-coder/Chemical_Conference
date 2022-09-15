package com.shubh.conference;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Html;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        View v = getLayoutInflater(R.id.homing)
//        View innerView = v.findViewById(R.id.homing);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    TextView linkTextview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        linkTextview=view.findViewById(R.id.link);
        String content="<a href='https://www.iitism.ac.in/index.php/Departments/dept_chem'> Department of Chemical Engineering Indian Institute of Technology(ISM) Dhanbad </a>";
        Spannable s = (Spannable) Html.fromHtml(content);
        for (URLSpan u: s.getSpans(0, s.length(), URLSpan.class)) {
            s.setSpan(new UnderlineSpan() {
                public void updateDrawState(TextPaint tp) {
                    tp.setUnderlineText(false);
                }
            }, s.getSpanStart(u), s.getSpanEnd(u), 0);
        }
        linkTextview.setText(s);
        linkTextview.setMovementMethod(LinkMovementMethod.getInstance());
        Button spbtn = (Button) view.findViewById(R.id.spbtn);
        Button ksbtn = (Button) view.findViewById(R.id.ksbtn);
        Button ospbtn = (Button) view.findViewById(R.id.ospbtn);
        Button annbtn = (Button) view.findViewById(R.id.annbtn);

        spbtn.setOnClickListener((View.OnClickListener) this);
        ksbtn.setOnClickListener((View.OnClickListener) this);
        ospbtn.setOnClickListener((View.OnClickListener) this);
        annbtn.setOnClickListener((View.OnClickListener) this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.spbtn:
                fragment = new sponsorship();
                replaceFragment(fragment);
                break;

            case R.id.ksbtn:
                fragment = new keynoteSpeakers();
                replaceFragment(fragment);
                break;
            case R.id.ospbtn:
                fragment = new OurSponsors();
                replaceFragment(fragment);
                break;
            case R.id.annbtn:
                fragment = new Announcements();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.homing, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}