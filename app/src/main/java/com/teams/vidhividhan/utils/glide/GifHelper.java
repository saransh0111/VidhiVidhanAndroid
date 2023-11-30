package com.teams.vidhividhan.utils.glide;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.ImageViewTarget;

public class GifHelper extends ImageViewTarget<Drawable> {

    private int mLoopCount = GifDrawable.LOOP_FOREVER;

    public GifHelper(ImageView view, int loopCount) {
        super(view);
        mLoopCount = loopCount;
    }

    public GifHelper(ImageView view, int loopCount, boolean waitForLayout) {
        super(view, waitForLayout);
        mLoopCount = loopCount;
    }

    @Override
    protected void setResource(@Nullable Drawable resource) {
        if (resource instanceof GifDrawable) {
            ((GifDrawable) resource).setLoopCount(mLoopCount);
        }
        view.setImageDrawable(resource);
    }
}

