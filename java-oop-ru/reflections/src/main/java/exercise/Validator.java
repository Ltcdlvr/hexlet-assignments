package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        Field[] fields = address.getClass().getDeclaredFields();
        List<String> res = new ArrayList<>();

        for (Field field : fields) {
            try {
                if (field.isAnnotationPresent(NotNull.class))  {
                    field.setAccessible(true);
                    Object value = field.get(address);
                    if (Objects.isNull(value)) {
                        res.add(field.getName());
                    }
                }
            } catch (IllegalAccessException e) {
                System.out.println(e);
            }
        }
        return res;
    }
}
// END
