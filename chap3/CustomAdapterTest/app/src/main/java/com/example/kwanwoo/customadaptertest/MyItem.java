package com.example.kwanwoo.customadaptertest;

import android.graphics.drawable.Drawable;

/**
 * Created by Kwanwoo on 2016-09-05.
 */
class MyItem {
    int mIcon; // image resource
    String nName; // text
    String nAge;  // text

    MyItem(int aIcon, String aName, String aAge) {
        mIcon = aIcon;
        nName = aName;
        nAge = aAge;
    }
}
