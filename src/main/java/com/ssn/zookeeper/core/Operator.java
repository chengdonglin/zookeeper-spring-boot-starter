package com.ssn.zookeeper.core;

import org.apache.curator.framework.CuratorFramework;

/**
 * @Author linchengdong
 * @Date 2024-05-28 19:56
 * @PackageName:com.ssn.zookeeper.core
 * @ClassName: Operator
 * @Description: TODO
 * @Version 1.0
 */
public interface Operator {

    CuratorFramework getCurator();

}
