package org.letheproject.lethecore.profiling;

public class ProfileResult<E> {
    private E result;
    private long runtime;

    protected ProfileResult(E result, long runtime) {
        this.result = result;
        this.runtime = runtime;
    }

    public E getResult() {
        return result;
    }

    public long getRuntime() {
        return runtime;
    }
}
