package com.ssn.zookeeper.core.constants;

/**
 * @Author linchengdong
 * @Date 2024-05-29 11:21
 * @PackageName:com.ssn.zookeeper.core.constants
 * @ClassName: ZookeeperErrorCode
 * @Description: TODO
 * @Version 1.0
 */
public interface ZookeeperErrorCode {

    Integer DEFAULT_ERROR = 1000;
    Integer CREATE_NODE_ERROR = 1001;
    Integer READ_NODE_DATA_ERROR = 1002;
    Integer READ_NODE_CHILDREN_ERROR = 1003;
    Integer READ_NODE_STATE_ERROR = 1004;
    Integer DELETE_NODE_ERROR = 1005;
    Integer UPDATE_NODE_ERROR = 1006;
    Integer CHECK_NODE_ERROR = 1007;
}
