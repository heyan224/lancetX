package com.ss.android.ugc.bytex.method_trace_lib;

import android.os.Trace;
import android.util.Log;

public class MyTrace {
    public static void beginSection(String tag) {
        Log.e("MyTrace", " enter = " + tag);
    }

    public static void endSection(String tag) {
        Log.e("MyTrace", " exit = " + tag);
    }

}
