package com.voc.fr.tool.api;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/04/28 11:38
 */
@FunctionalInterface
public interface IAction<T> {

    /**
     * Performs this action against the given object.
     *
     * @param t The object to perform the action on.
     */
    void execute(T t);

}
