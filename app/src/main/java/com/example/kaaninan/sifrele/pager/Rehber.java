package com.example.kaaninan.sifrele.pager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.kaaninan.sifrele.R;
import com.example.kaaninan.sifrele.adapter.RehberAdapter;
import com.example.kaaninan.sifrele.constructor.RehberConstructor;

import java.util.Locale;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class Rehber extends Fragment {

    String LOG = "Arkadaslar.java";
    private RelativeLayout layoutSearchEdit;
    private EditText editsearch;

    private static Context context;
    private StickyListHeadersListView list;

    private boolean search_open = false;

    private View rootView;

    public static final String ARG_SECTION_NUMBER = "section_number";

    public Rehber() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_rehber, container, false);

        context = getActivity();

        list = (StickyListHeadersListView) rootView.findViewById(R.id.listRehber);
        RelativeLayout layoutBos = (RelativeLayout) rootView.findViewById(R.id.layoutBos);
        EditText editArama = (EditText) rootView.findViewById(R.id.editArama);

        final RehberAdapter rehberAdapter = new RehberAdapter(getActivity());

        rehberAdapter.rehberiGetir();

        if(rehberAdapter.listBos){
            list.setVisibility(View.GONE);
            editArama.setVisibility(View.GONE);
            layoutBos.setVisibility(View.VISIBLE);

        }else {

            list.setAdapter(rehberAdapter);
            list.setFastScrollEnabled(true);

            editsearch = (EditText) rootView.findViewById(R.id.editArama);
            editsearch.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable arg0) {
                    String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                    rehberAdapter.filter(text);
                }
                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
                @Override
                public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
            });


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    RehberConstructor value = (RehberConstructor) parent.getItemAtPosition(position);
                    Log.i(LOG, value.getIsim());


                    Toast.makeText(getActivity(), "Aranacak Kişinin İsmidir Ha: "+value.getIsim(), Toast.LENGTH_LONG).show();

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse(("tel:" + value.getNumara())));
                    startActivity(callIntent);
                }
            });

        }

        layoutSearchEdit = (RelativeLayout) rootView.findViewById(R.id.layoutEditArama);

        return rootView;
    }



    public void SlideToUp() {
        search_open = false;
        Animation slide = null;
        slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, -1);

        slide.setDuration(200);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        layoutSearchEdit.startAnimation(slide);
        list.startAnimation(slide);

        slide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                editsearch.setText("");

                layoutSearchEdit.clearAnimation();
                list.clearAnimation();

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(layoutSearchEdit.getWidth(), layoutSearchEdit.getHeight());
                lp.setMargins(0, -(layoutSearchEdit.getHeight()), 0, 0);
                layoutSearchEdit.setLayoutParams(lp);

            }

        });

    }

    public void SlideToDown() {
        search_open = true;
        Animation slide = null;
        slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1);

        slide.setDuration(200);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        layoutSearchEdit.startAnimation(slide);
        list.startAnimation(slide);

        slide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                layoutSearchEdit.clearAnimation();
                list.clearAnimation();

                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(layoutSearchEdit.getWidth(), layoutSearchEdit.getHeight());
                lp.setMargins(0, 0, 0, 0);
                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                layoutSearchEdit.setLayoutParams(lp);

            }

        });

    }

    public boolean isSearch_open() {
        return search_open;
    }

    public void setSearch_open(boolean search_open) {
        this.search_open = search_open;
    }
}