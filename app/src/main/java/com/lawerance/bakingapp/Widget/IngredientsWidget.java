package com.lawerance.bakingapp.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.lawerance.bakingapp.CakeResponse.ingredients;
import com.lawerance.bakingapp.R;

import java.util.ArrayList;


public class IngredientsWidget extends AppWidgetProvider {
    private static String food = "";
    public static ArrayList<ingredients> mIngredients;

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetIds[], ArrayList<ingredients> ingredients, String name) {

//        for (int appWidgetId : appWidgetIds) {
//            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget);
//            views.setTextViewText(R.id.tv_recipe_name, name);
//            ComponentName component = new ComponentName(context, IngredientsWidget.class);
//
//            for (int i = 0; i < ingredients.size(); i++) {
//                food += " " + ingredients.get(i).getQuantity() + " " + ingredients.get(i).getMeasure() + " " + ingredients.get(i).getIngredient() + "\n";
//
//            }
//            views.setTextViewText(R.id.tv_food_name_measure, food);
//            appWidgetManager.updateAppWidget(component, views);
//
//        }
        mIngredients = ingredients;
        for (int appWidgetId : appWidgetIds)
        {
            Intent intent = new Intent(context, listViewsService.class);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_widget);
            views.setRemoteAdapter(R.id.widget_grid_view, intent);
            ComponentName component = new ComponentName(context, IngredientsWidget.class);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_grid_view);
            appWidgetManager.updateAppWidget(component, views);
        }


    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);


    }


    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}

