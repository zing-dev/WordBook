package com.zing.wordbook.listener;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.zing.wordbook.MainActivity;


/**
 * Created by zhang on 2017/6/27.
 */

public class EditChangedListener implements TextWatcher {

    private MainActivity context = null;

    public EditChangedListener() {
    }

    public EditChangedListener(Context context) {
        if (context instanceof MainActivity) {
            this.context = (MainActivity) context;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        context.str_search = String.valueOf(s);
        Toast.makeText(context, context.str_search, Toast.LENGTH_LONG).show();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
