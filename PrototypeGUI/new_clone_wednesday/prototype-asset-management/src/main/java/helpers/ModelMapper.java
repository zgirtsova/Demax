package helpers;

import annotations.FieldName;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ModelMapper<T, K> {

    private Map<String, Method> sourceGetters;
    private Map<String, Method> destinationSetters;

    public ModelMapper() {
        sourceGetters = new HashMap<>();
        destinationSetters = new HashMap<>();
    }

    public K map(T source, Class clazz) {

        K destination = null;

        Method[] destinationMethods = clazz.getMethods();
        Method[] sourceMethods = source.getClass().getMethods();

        setSourceGetters(sourceMethods);
        setDestinationSetters(destinationMethods);

        Constructor constructor = null;

        try {
            constructor = clazz.getConstructor(null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (constructor != null) {
            try {

                destination = (K) constructor.newInstance();

            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        if (destination != null && sourceGetters.size() != 0 && destinationSetters.size() != 0) {
            for (Map.Entry<String, Method> setterEntry : destinationSetters.entrySet()) {
                String fieldName = setterEntry.getKey();

                if (sourceGetters.containsKey(fieldName) && destinationSetters.containsKey(fieldName)) {
                    Method destinationSetter = setterEntry.getValue();
                    Method sourceGetter = sourceGetters.entrySet().stream().filter(kvp -> kvp.getKey().equals(fieldName)).findFirst().get().getValue();

                    try {
                        destinationSetter.invoke(destination, sourceGetter.invoke(source));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return destination;
    }

    private void setSourceGetters(Method[] methods) {

        for (Method method : methods) {
            if (method.isAnnotationPresent(FieldName.class) && method.getName().substring(0,3).equals("get")) {
                String fieldName = method.getAnnotation(FieldName.class).fieldName();
                sourceGetters.put(fieldName, method);
            }
        }
    }

    private void setDestinationSetters(Method[] methods) {

        for (Method method : methods) {
            if (method.isAnnotationPresent(FieldName.class) && method.getName().substring(0,3).equals("set")) {
                String fieldName = method.getAnnotation(FieldName.class).fieldName();
                destinationSetters.put(fieldName, method);
            }
        }
    }
}
