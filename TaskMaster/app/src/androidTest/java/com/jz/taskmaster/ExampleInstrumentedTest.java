package com.jz.taskmaster;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.jz.taskmaster", appContext.getPackageName());

        //working on tests
//        NewAdapter adapter = new NewAdapter(new ArrayList<String>());
//
//        assertEquals(0, adapter.getItemCount());
//
//        List<String> test = Arrays.asList(
//                "A", "B", "C"
//        );
//        adapter.updateData(test);
//
//        assertEquals(3, adapter.getItemCount());
    }
}
