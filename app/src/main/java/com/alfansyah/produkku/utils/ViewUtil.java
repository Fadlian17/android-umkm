package com.alfansyah.produkku.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class ViewUtil {
    public static void showMessage(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}
