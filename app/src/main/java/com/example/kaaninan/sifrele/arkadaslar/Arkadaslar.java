package com.example.kaaninan.sifrele.arkadaslar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.kaaninan.sifrele.R;
import com.example.kaaninan.sifrele.RehberAdapter;
import com.example.kaaninan.sifrele.RehberConstructor;

import java.util.Locale;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class Arkadaslar extends Fragment {

    String LOG = "Arkadaslar.java";
    Boolean listBos;

    EditText editsearch;

    public static final String ARG_SECTION_NUMBER = "section_number";

    public Arkadaslar() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.arkadaslar2, container, false);

        final StickyListHeadersListView list = (StickyListHeadersListView) rootView.findViewById(R.id.listRehber);
        FrameLayout layoutBos = (FrameLayout) rootView.findViewById(R.id.layoutBos);

        final RehberAdapter rehberAdapter = new RehberAdapter(getActivity());
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
            }
        });

        return rootView;
    }

}