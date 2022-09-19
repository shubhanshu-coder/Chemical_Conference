package com.shubh.conference;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment{

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
    TextView linkTextview,linkTextView2,announcements;
    ArrayList<String>list;
    RecyclerView rcView;
    Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        linkTextview=view.findViewById(R.id.link);
        announcements=view.findViewById(R.id.ann);
        rcView=view.findViewById(R.id.rcChats);
        list=new ArrayList<>();
        adapter=new Adapter(list,getContext());
        rcView.setLayoutManager(new LinearLayoutManager(getContext()));
        rcView.setAdapter(adapter);
        rcView.hasFixedSize();

        getUpdates();

        String content="<a href='https://www.iitism.ac.in/index.php/Departments/dept_chem'>Department of Chemical Engineering Indian Institute of Technology(ISM) Dhanbad </a>";
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
        Button asbtn = (Button) view.findViewById(R.id.asbtn);
        TextView ann=(TextView) view.findViewById(R.id.ann);
        /*Button ksbtn = (Button) view.findViewById(R.id.ksbtn);
        Button ospbtn = (Button) view.findViewById(R.id.ospbtn);
        Button annbtn = (Button) view.findViewById(R.id.annbtn);*/
        Animation animation=new AlphaAnimation(0.0f,1.0f);
        animation.setDuration(400);
        animation.setStartOffset(20);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        asbtn.startAnimation(animation);
        asbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                golink("https://docs.google.com/forms/d/e/1FAIpQLSeqCj5wF8WFi2jm-Advy81KahjNK5hACOHt8yt6b6R0kzI6cA/viewform");
            }
        });
        /*ksbtn.setOnClickListener((View.OnClickListener) this);
        ospbtn.setOnClickListener((View.OnClickListener) this);
        annbtn.setOnClickListener((View.OnClickListener) this);*/
        return view;
    }

    private void getUpdates() {

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Updates");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot sn:snapshot.getChildren())
                {
                    //modelClass modelClass=sn.getValue(com.shubh.conference.modelClass.class);
                    String s=sn.getValue(String.class);
                    //list.add(modelClass);
                    list.add(s);
                }
                Collections.reverse(list);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void golink(String s){
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    /*@Override
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
    }*/
}