package com.example.servicediscoveryclient.utils;

import org.springframework.util.Assert;

public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

    public static final UserContext getContext() {
        UserContext context = userContext.get();

        if (context == null) {
            context = createEmptyContext();
            userContext.set(context);
        }

        return userContext.get();
    }

    public static final void setUserContext(UserContext context) {
        Assert.notNull(context, "null이 아닌 UserContext 인스턴스만 허용됩니다.");
        userContext.set(context);
    }

    public static final UserContext createEmptyContext() {
        return new UserContext();
    }

}
