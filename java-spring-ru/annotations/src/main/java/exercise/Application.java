package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        for (Method method : Address.class.getDeclaredMethods()) {

            // Проверяем, есть ли у метода аннотация @LogExecutionTime
            if (method.isAnnotationPresent(Inspect.class)) {

                var startTime = System.currentTimeMillis();

                try {
                    // Выполняем метод с аннотацией LogExecutionTime
                    String methodName = method.getName();
                    Class<?> returnType = method.getReturnType();
                    System.out.println("Method " + methodName + " returns a value of type " + returnType.getSimpleName() + ".");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // END
    }
}
