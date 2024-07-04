package com.hun.market.core.context;

import com.hun.market.core.util.FastStack;

public class RoutingDataSource {
    private static ThreadLocal<FastStack<String>> threadLocal = new ThreadLocal<>() {
        @Override
        protected FastStack<String> initialValue() {
            return new FastStack<String>(5);
        }
    };

    public static boolean empty() {
        return threadLocal.get().isEmpty();
    }

    public static String target() {

        if(threadLocal.get().isEmpty()) {
            return MainContext.FOLLOWER;
        } else {
            String target = peek();
            if(target.contains(".service.") && target.contains("Service")) {
                return MainContext.LEADER;
            } else {
                return MainContext.FOLLOWER;
            }
        }
    }

    public static String peek() {

        FastStack<String> fastStack = threadLocal.get();
        if(fastStack.isEmpty()) {
            return MainContext.NO_TRANSACTIONS;
        }

        return fastStack.peek();

    }

    public static void push(String item) {
        threadLocal.get().push(item);
    }

    public static String pop() {
        String pop = threadLocal.get().pop();

        if(threadLocal.get().empty()) {
            threadLocal.remove();
        }

        return pop;
    }
}
