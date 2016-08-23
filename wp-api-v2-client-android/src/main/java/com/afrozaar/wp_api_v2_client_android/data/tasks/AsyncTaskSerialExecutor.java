package com.afrozaar.wp_api_v2_client_android.data.tasks;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/03/30.
 */
public class AsyncTaskSerialExecutor implements Executor {

    private static AsyncTaskSerialExecutor sExecutor = null;

    public static AsyncTaskSerialExecutor getInstance() {
        if (sExecutor == null) {
            sExecutor = new AsyncTaskSerialExecutor();
        }

        return sExecutor;
    }

    final ArrayDeque<Runnable> tasks = new ArrayDeque<>();
    Runnable active;

    private AsyncTaskSerialExecutor() {
    }

    public synchronized void execute(final Runnable runnable) {
        tasks.offer(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } finally {
                    scheduleNext();
                }
            }
        });

        if (active == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            AsyncTaskPoolExecutor.getInstance().execute(active);
        }
    }
}
