package com.ssn.zookeeper.core;

import com.ssn.zookeeper.core.node.DataNodeOperator;
import com.ssn.zookeeper.core.node.NodeOperator;
import com.ssn.zookeeper.core.sequence.Sequence;
import com.ssn.zookeeper.core.sequence.SequenceOperator;
import org.apache.curator.framework.CuratorFramework;

/**
 * @Author linchengdong
 * @Date 2024-05-28 20:09
 * @PackageName:com.ssn.zookeeper.core
 * @ClassName: ZookeeperTemplate
 * @Description: TODO
 * @Version 1.0
 */
public class ZookeeperTemplate {

    private final Sequence sequence;

    private final NodeOperator nodeOperator;
    public ZookeeperTemplate(CuratorFramework curatorFramework) {
        this.sequence = new SequenceOperator(curatorFramework);
        this.nodeOperator = new DataNodeOperator(curatorFramework);
    }


    /**
     * 自增
     * @return
     */
    public Sequence opsForSeq() {
        return sequence;
    }


    /**
     * 节点
     * @return
     */
    public NodeOperator opsForNode() {
        return nodeOperator;
    }
}
