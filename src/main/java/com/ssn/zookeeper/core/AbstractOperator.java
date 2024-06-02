package com.ssn.zookeeper.core;

import com.ssn.zookeeper.config.ZookeeperProperties;
import com.ssn.zookeeper.core.defaults.DefaultZookeeperSession;
import org.apache.curator.framework.CuratorFramework;

/**
 * @Author linchengdong
 * @Date 2024-05-28 19:57
 * @PackageName:com.ssn.zookeeper.core
 * @ClassName: AbstractOperator
 * @Description: TODO
 * @Version 1.0
 */

public class AbstractOperator implements Operator{

    private final CuratorFramework curatorFramework;

    public AbstractOperator(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    @Override
    public CuratorFramework getCurator() {
        return curatorFramework;
    }

}
