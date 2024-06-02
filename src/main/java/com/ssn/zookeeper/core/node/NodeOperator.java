package com.ssn.zookeeper.core.node;

import com.ssn.zookeeper.core.response.StatePayload;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @Author linchengdong
 * @Date 2024-05-29 11:36
 * @PackageName:com.ssn.zookeeper.core.node
 * @ClassName: NodeOperator
 * @Description: TODO
 * @Version 1.0
 */
public interface NodeOperator {

    String create(String path, String data, CreateMode mode);


    String get(String path);

    List<String> getChildren(String path);

    StatePayload state(String path);

    boolean delete(String path, Boolean deleteChildren);

    Stat update(String path, String data);

    boolean exist(String path);
}
