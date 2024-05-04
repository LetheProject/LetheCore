package org.letheproject.lethecore.profiling;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    public static ProfileResult<?> runOnce(ProfileFunction<?> function) {
        long start = System.nanoTime();
        Object result = function.run();
        long end = System.nanoTime();
        long runtime = end - start;
        return new ProfileResult<>(result, runtime);
    }

    public static ProfileResult<List<?>> runMany(ProfileFunction<?> function, int n) {
        long runtime = 0;
        List<Object> results = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long start = System.nanoTime();
            Object result = function.run();
            long end = System.nanoTime();
            runtime += end - start;
            results.add(result);
        }
        return new ProfileResult<>(results, runtime / n);
    }
}
