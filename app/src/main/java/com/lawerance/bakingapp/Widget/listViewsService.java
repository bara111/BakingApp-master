package com.lawerance.bakingapp.Widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.lawerance.bakingapp.CakeResponse.ingredients;
import com.lawerance.bakingapp.R;

import java.util.ArrayList;



public class listViewsService extends RemoteViewsService
{


    public ListViewsFactory onGetViewFactory(Intent intent)
    {
        return new ListViewsFactory(this.getApplicationContext());
    }
}

class ListViewsFactory implements RemoteViewsService.RemoteViewsFactory
{
    private Context mContext;
    private ArrayList<ingredients> mIngredients;
    public ListViewsFactory(Context mContext)
    {
        this.mContext = mContext;
    }

    @Override
    public void onCreate()
    {

    }

    @Override
    public void onDataSetChanged()
    {
        mIngredients = IngredientsWidget.mIngredients;
    }

    @Override
    public void onDestroy()
    {

    }

    @Override
    public int getCount()
    {
        if (mIngredients == null)
            return 0;
        return mIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position)
    {
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_ingredient_item_layout);
        views.setTextViewText(R.id.tv_ingredient, mIngredients.get(position).getMeasure());
        return views;
    }

    @Override
    public RemoteViews getLoadingView()
    {
        return null;
    }

    @Override
    public int getViewTypeCount()
    {
        return 1;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }
}
