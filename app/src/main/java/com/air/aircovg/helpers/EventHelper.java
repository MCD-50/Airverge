package com.air.aircovg.helpers;

import com.air.aircovg.events.DatabaseEvents;
import com.air.aircovg.model.News;

/**
 * Created by ayush AS on 27/12/16.
 */

public class EventHelper {
    private static DatabaseEvents mDatabaseEvent;

    public static void initDatabaseEvent(DatabaseEvents databaseEvent){
        mDatabaseEvent = databaseEvent;
    }

    public static void Invoke(News news, boolean isAdded){
        mDatabaseEvent.addOrRemoveNews(news, isAdded);
    }
}
