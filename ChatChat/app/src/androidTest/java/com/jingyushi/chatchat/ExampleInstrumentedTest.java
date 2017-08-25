package com.jingyushi.chatchat;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        Log.i("test", "hello world");
        System.out.println("hello world");

        assertEquals("com.jingyushi.chatchat", appContext.getPackageName());
    }

    @Test
    public void testFirebaseDB(){
        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        System.out.println(fdb.toString());
        DatabaseReference fref = fdb.getReference();
    }
}
