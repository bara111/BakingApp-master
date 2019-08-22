package com.lawerance.bakingapp.Widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.lawerance.bakingapp.CakeResponse.ingredients;

import java.util.ArrayList;

public class UpdateUIWidgetService extends IntentService {
    public static final String ACTION_UPDATE_FOOD = "android.appwidget.action.APPWIDGET_UPDATE";
public ArrayList<ingredients> mIngredients;
    public UpdateUIWidgetService(String name) {
        super(name);
    }

    public UpdateUIWidgetService() {
        super("UpdateUIWidgetService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null && intent.getAction().equals(ACTION_UPDATE_FOOD))
        {
            Bundle bundle = intent.getBundleExtra("bundle");

           mIngredients = bundle.getParcelableArrayList("ingredients");
            String name=bundle.getString("name");
            Log.d("+test",name);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, IngredientsWidget.class));
            IngredientsWidget.updateAppWidget(this, appWidgetManager, appWidgetIds,mIngredients,name);
        }
    }
}
