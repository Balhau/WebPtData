package org.pub.global.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Utility class that uses reflection for hacking purposes
 */
public class ReflectionUtils {

    /**
     * This method will receive a protected field and set their value. This works with static final objects
     *
     * @param field The {@link Field} object that corresponds to the object we wanna change
     * @param value The value as a {@link Object} we wanna set
     * @throws Exception
     */
    public static void setStaticValue(Field field, Object value) throws Exception {
        //Make the field accessible for change
        field.setAccessible(true);

        //Get all the modifiers available
        Field modifiers = Field.class.getDeclaredField("modifiers");

        modifiers.setAccessible(true);
        //Change the modifiers to disable final attribute for the field
        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        //set the value on the field
        field.set(null, value);

    }
}
