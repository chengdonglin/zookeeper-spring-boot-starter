package com.ssn.zookeeper.core.node;

import com.ssn.zookeeper.core.AbstractOperator;
import com.ssn.zookeeper.core.response.StatePayload;
import org.apache.zookeeper.data.Stat;
import com.ssn.zookeeper.core.constants.ZookeeperErrorCode;
import com.ssn.zookeeper.core.exception.ZookeeperException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author linchengdong
 * @Date 2024-05-29 11:17
 * @PackageName:com.ssn.zookeeper.core.node
 * @ClassName: DataNodeOperator
 * @Description: TODO
 * @Version 1.0
 */
public class DataNodeOperator extends AbstractOperator implements NodeOperator{

    public DataNodeOperator(CuratorFramework curatorFramework) {
        super(curatorFramework);
    }


    /**
     * 创建节点，设置数据， 父节点不存在的话会自动创建父节点
     * @param path 路径
     * @param data 数据
     * @param mode 模式
     * @return
     */
    @Override
    public String create(String path, String data, CreateMode mode) {
        try {
            return getCurator().create().creatingParentsIfNeeded().withMode(mode).forPath(path, data.getBytes());
        } catch (Exception e) {
            throw new ZookeeperException(ZookeeperErrorCode.CREATE_NODE_ERROR,e.getMessage(),e.getCause());
        }
    }


    /**
     * 读取某个节点数据
     * @param path
     * @return
     */
    @Override
    public String get(String path) {
        try {
            byte[] bytes = getCurator().getData().forPath(path);
            return new String(bytes);
        } catch (Exception e) {
            throw new ZookeeperException(ZookeeperErrorCode.READ_NODE_DATA_ERROR,e.getMessage(),e.getCause());
        }
    }


    /**
     * 获取包括子节点
     * @param path
     * @return
     */
    @Override
    public List<String> getChildren(String path) {
        try {
            List<String> paths = getCurator().getChildren().forPath(path);
            return paths.stream().map(p -> String.format("%s/%s", path, p)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ZookeeperException(ZookeeperErrorCode.READ_NODE_CHILDREN_ERROR,e.getMessage(),e.getCause());
        }
    }

    @Override
    public StatePayload state(String path) {
     try {
         Stat stat = new Stat();
         byte[] bytes = getCurator().getData().storingStatIn(stat).forPath(path);
         String dataString = new String(bytes);
         StatePayload payload = new StatePayload();
         payload.setData(dataString);
         payload.setStat(stat);
         return payload;
     } catch (Exception e) {
         throw new ZookeeperException(ZookeeperErrorCode.READ_NODE_STATE_ERROR,e.getMessage(),e.getCause());
     }
    }


    /**
     * 删除某个节点
     *
     * @param path
     * @param deleteChildren
     * @return
     */
    @Override
    public boolean delete(String path, Boolean deleteChildren) {
        try {
            if (deleteChildren) {
                getCurator().delete().deletingChildrenIfNeeded().forPath(path);
            } else {
                getCurator().delete().forPath(path);
            }
            Stat stat = getCurator().checkExists().forPath(path);
            return stat == null;
        } catch (Exception e) {
           throw new ZookeeperException(ZookeeperErrorCode.DELETE_NODE_ERROR,e.getMessage(),e.getCause());
        }
    }


    @Override
    public Stat update(String path, String data) {
        try {
            boolean exist = exist(path);
            if (!exist) {
                return null;
            }
            return getCurator().setData().forPath(path, data.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e) {
            throw new ZookeeperException(ZookeeperErrorCode.UPDATE_NODE_ERROR,e.getMessage(),e.getCause());
        }
    }

    @Override
    public boolean exist(String path) {
        try {
            Stat stat = getCurator().checkExists().forPath(path);
            return stat != null;
        } catch (Exception e) {
            throw new ZookeeperException(ZookeeperErrorCode.CHECK_NODE_ERROR,e.getMessage(),e.getCause());
        }
    }
}
