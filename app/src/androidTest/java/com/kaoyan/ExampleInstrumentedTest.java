package com.kaoyan;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.util.Log;
import android.widget.Button;

import com.kaoyan.module.Test2Activity;
import com.kaoyan.utils.CommonUtil;
import com.kaoyan.utils.SharedPreferencesUtil;
import com.kaoyan.utils.ToastUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest

{
    private Context appContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
//        assertEquals("com.kaoyanvip", appContext.getPackageName());
//        onView(withId(R.id.btn)).perform(click())
//                .check(matches(isDisplayed()));
        SharedPreferencesUtil.putString(appContext,"TEST","test");
    }

    @Test
    public void test(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext,Test2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(intent);
//        onView(withId(R.id.btn)).check(matches(isDisplayed()));
        onView(withId(R.id.btn)).perform(click());

    }

    @Test
    public void getTest(){
        SharedPreferencesUtil.getInt(appContext,"TEST",0);
        ToastUtils.showToast(appContext,"11111");
    }

}
