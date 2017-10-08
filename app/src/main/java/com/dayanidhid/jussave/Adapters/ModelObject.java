package com.dayanidhid.jussave.Adapters;

import com.dayanidhid.jussave.R;

/**
 * Created by dayanidhi.d on 04/10/17.
 */

public enum ModelObject {

    RED(R.string.red, R.layout.view_education_slide_1),
    BLUE(R.string.blue, R.layout.view_education_slide_2),
    GREEN(R.string.green, R.layout.view_education_slide_3);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
