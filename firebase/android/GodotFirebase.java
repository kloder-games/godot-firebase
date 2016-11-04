package org.godotengine.godot;

import android.app.Application;

import com.google.firebase.analytics.FirebaseAnalytics;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class GodotFirebase extends Godot.SingletonBase
{

    private Activity activity = null;
    private FirebaseAnalytics firebaseAnalytics;

    /**
     * Initialization
     */
    public void init()
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                firebaseAnalytics = FirebaseAnalytics.getInstance(activity);
                Log.i("godot", "Firebase: Init Firebase Analytics.");
            }
        });
    }

    public void postScore(final int score, final int level, final String character)
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Bundle bundle = new Bundle();
                bundle.putLong(FirebaseAnalytics.Param.SCORE, score);
                if (level != 0) bundle.putLong(FirebaseAnalytics.Param.LEVEL, level);
                if (character != "") bundle.putString(FirebaseAnalytics.Param.CHARACTER, character);
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.POST_SCORE, bundle);
                Log.i("godot", "Firebase: POST_SCORE event.");
            }
        });
    }

    public void logDie(final String level)
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Bundle bundle = new Bundle();
                bundle.putString("level", level);
                firebaseAnalytics.logEvent("die", bundle);
                Log.i("godot", "Firebase: Die event -> " + level);
            }
        });
    }

    public void share(final String content_type, final String item_id)
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, content_type);
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, item_id);
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle);
                Log.i("godot", "Firebase: SHARE event.");
            }
        });
    }

    public void levelUp(final int level, final String character)
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Bundle bundle = new Bundle();
                bundle.putLong(FirebaseAnalytics.Param.LEVEL, level);
                if (character != "") bundle.putString(FirebaseAnalytics.Param.CHARACTER, character);
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LEVEL_UP, bundle);
                Log.i("godot", "Firebase: LEVEL_UP event.");
            }
        });
    }

    public void unlockAchievement(final String achievement_id)
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ACHIEVEMENT_ID, achievement_id);
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.UNLOCK_ACHIEVEMENT, bundle);
                Log.i("godot", "Firebase: UNLOCK_ACHIEVEMENT event.");
            }
        });
    }

    public void tutorialBegin()
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Bundle bundle = new Bundle();
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.TUTORIAL_BEGIN, bundle);
                Log.i("godot", "Firebase: TUTORIAL_BEGIN event.");
            }
        });
    }

    public void tutorialComplete()
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Bundle bundle = new Bundle();
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.TUTORIAL_COMPLETE, bundle);
                Log.i("godot", "Firebase: TUTORIAL_COMPLETE event.");
            }
        });
    }

    public void spend_virtual_currency(final String item_name, final String virtual_currency_name, final int value)
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, item_name);
                bundle.putString(FirebaseAnalytics.Param.VIRTUAL_CURRENCY_NAME, virtual_currency_name);
                bundle.putLong(FirebaseAnalytics.Param.VALUE, value);
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SPEND_VIRTUAL_CURRENCY, bundle);
                Log.i("godot", "Firebase: SPEND_VIRTUAL_CURRENCY event: (" + item_name + ", " + virtual_currency_name + ", " + String.valueOf(value) + ")");
            }
        });
    }

    public void logEvent(final String event)
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Bundle bundle = new Bundle();
                firebaseAnalytics.logEvent(event, bundle);
                Log.i("godot", "Firebase: Custom event -> " + event);
            }
        });
    }

    /* Godot Methods
     * ********************************************************************** */

    /**
    * Singleton
    */
    static public Godot.SingletonBase initialize(Activity activity)
    {
        return new GodotFirebase(activity);
    }

    /**
     * Constructor
     * @param Activity Main activity
     */
    public GodotFirebase(Activity activity) {
        this.activity = activity;
        registerClass("Firebase", new String[] {
            "init",
            "postScore", "logDie", "share", "levelUp", "unlockAchievement", "tutorialBegin", "tutorialComplete", "spend_virtual_currency",
            "logEvent"
        });
    }
}
