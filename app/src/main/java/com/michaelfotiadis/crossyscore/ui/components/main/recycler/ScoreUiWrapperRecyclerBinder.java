package com.michaelfotiadis.crossyscore.ui.components.main.recycler;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.data.models.ScoreUiWrapper;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.viewbinder.BaseRecyclerViewBinder;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;
import com.michaelfotiadis.crossyscore.utils.date.DateUtils;

import java.util.Random;

/**
 *
 */
public class ScoreUiWrapperRecyclerBinder extends BaseRecyclerViewBinder<ScoreUiWrapperRecyclerViewHolder, ScoreUiWrapper> {

    private static final int DEFAULT_IMAGE_PLACEHOLDER = R.drawable.ic_android_light_blue_300_18dp;

    protected ScoreUiWrapperRecyclerBinder(final Context context, final IntentDispatcher intentDispatcher) {
        super(context, intentDispatcher);
    }

    @Override
    public void bind(final ScoreUiWrapperRecyclerViewHolder holder, final ScoreUiWrapper item) {
        if (item != null) {
            holder.scoreText.setText(String.valueOf(item.getValue()));
            holder.imageAvatar.setImageDrawable(getDrawable(item.getPlayerResId()));
            holder.imageMascot.setImageDrawable(getDrawable(item.getMascotResId()));
            holder.playerName.setText(item.getPlayerName());
            holder.mascotName.setText(item.getMascotName());
            holder.timeStamp.setText(DateUtils.getTimeAgoForScore(item.getTimeStamp()));
        }
    }

    @Override
    public void reset(final ScoreUiWrapperRecyclerViewHolder holder) {
        holder.scoreText.setText("");
        holder.imageAvatar.setImageDrawable(getDrawable(null));
        holder.imageMascot.setImageDrawable(getDrawable(null));
        holder.playerName.setText("");
        holder.mascotName.setText("");
    }

    private Drawable getDrawable(final Integer resId) {
        final Drawable drawable;
        if (resId == null) {
            drawable = ActivityCompat.getDrawable(getContext(), DEFAULT_IMAGE_PLACEHOLDER);
            final Random rnd = new Random();
            final int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            DrawableCompat.setTint(drawable, color);
        } else {
            drawable = ActivityCompat.getDrawable(getContext(), resId);
        }
        return drawable;
    }

}
