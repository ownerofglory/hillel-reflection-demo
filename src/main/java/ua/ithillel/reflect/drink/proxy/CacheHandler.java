package ua.ithillel.reflect.drink.proxy;

import ua.ithillel.reflect.anno.Cacheable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class CacheHandler implements InvocationHandler {
    private final Map<Object, Object> cache = new WeakHashMap<>(); //
    private final Object target;

    public CacheHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cacheable.class)) {
            if (args.length > 0) {
                Object name =  args[0];

                if (cache.containsKey(name)) {
                    return cache.get(name);
                }

                Object result = method.invoke(target, name);
                cache.put(name, result);
                return result;
            }
        }

        return method.invoke(target, args);
    }
}
