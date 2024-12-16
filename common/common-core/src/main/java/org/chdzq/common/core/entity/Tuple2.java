package org.chdzq.common.core.entity;

/**
 * 二元组
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/16 10:08
 */
public class Tuple2<T1, T2> {
    private T1 element1;
    private T2 element2;

    public Tuple2(T1 element1, T2 element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    static public <T1, T2> Tuple2<T1, T2> of(T1 element1, T2 element2) {
        return new Tuple2<>(element1, element2);
    }

    public T1 getElement1() {
        return element1;
    }

    public T2 getElement2() {
        return element2;
    }

}