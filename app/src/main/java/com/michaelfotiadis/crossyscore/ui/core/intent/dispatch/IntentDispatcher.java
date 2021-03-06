package com.michaelfotiadis.crossyscore.ui.core.intent.dispatch;

import android.content.Intent;
import android.view.View;

import com.michaelfotiadis.crossyscore.ui.core.intent.factory.IntentFactory;

/**
 *
 */
public interface IntentDispatcher {

    void dispatch(View source, Intent intent);

    void dispatchForResult(View source, Intent intent, int requestCode);

    void openHomeActivity();

    void openCreateActivity(View source, int code);

    void openAddPlayerActivityForResult(View source, int code);

    void openMascotPickerActivityForResult(View source, int code);

    IntentFactory getIntentFactory();

}
