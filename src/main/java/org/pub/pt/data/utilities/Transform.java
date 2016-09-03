package org.pub.pt.data.utilities;

/**
 * Created by vitorfernandes on 10/17/15.
 */
@FunctionalInterface
public interface Transform<T,U> {
    /**
     * Transformation method
     * @param element
     * @return
     */
    T transform(U element);
}
