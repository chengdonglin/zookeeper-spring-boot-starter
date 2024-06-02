package com.ssn.zookeeper.core.sequence;

/**
 * @Author linchengdong
 * @Date 2024-05-29 9:43
 * @PackageName:com.ssn.zookeeper.core.sequence
 * @ClassName: Sequence
 * @Description: TODO
 * @Version 1.0
 */
public interface Sequence {

    Long nextId(String path) throws Exception;
}
