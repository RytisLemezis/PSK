package com.example.psk1.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Profiled
@Interceptor
public class ProfilingInterceptor {

    @AroundInvoke
    public Object profile(InvocationContext ctx) throws Exception {
        long start = System.currentTimeMillis();
        try {
            return ctx.proceed();
        } finally {
            long duration = System.currentTimeMillis() - start;
            System.out.println("[PROFILED] " + ctx.getMethod().getDeclaringClass().getSimpleName()
                    + "." + ctx.getMethod().getName() + " executed in " + duration + " ms");
        }
    }
}

