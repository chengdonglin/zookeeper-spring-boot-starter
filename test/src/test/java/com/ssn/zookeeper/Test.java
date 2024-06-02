package com.ssn.zookeeper;

import com.ssn.zookeeper.core.ZookeeperTemplate;
import com.ssn.zookeeper.core.response.StatePayload;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author linchengdong
 * @Date 2024-05-29 10:18
 * @PackageName:com.ssn.zookeeper
 * @ClassName: Test
 * @Description: TODO
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {


    @Autowired
    private ZookeeperTemplate zookeeperTemplate;


    @org.junit.Test
    public void sequenceTest() {
        try {
            LoadRunnerUtils.run(1000,3,() -> {
                try {
                    Long aLong = zookeeperTemplate.opsForSeq().nextId("/order");
                    System.out.println(Thread.currentThread().getName() + "----" + aLong);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void nodeCreate() {
        String data1 = zookeeperTemplate.opsForNode().create("/data/1", "data1", CreateMode.PERSISTENT);
        String data2 = zookeeperTemplate.opsForNode().create("/data/2", "data2", CreateMode.PERSISTENT);
        String data3 = zookeeperTemplate.opsForNode().create("/data/3", "data3", CreateMode.PERSISTENT);
        String data31 = zookeeperTemplate.opsForNode().create("/data/3/1", "data3-1", CreateMode.PERSISTENT);
        System.out.println(data1);
        System.out.println(data2);
        System.out.println(data3);
        System.out.println(data31);
    }

    @org.junit.Test
    public void nodeRead() {
        String s = zookeeperTemplate.opsForNode().get("/data");
        System.out.println(s);
        String s1 = zookeeperTemplate.opsForNode().get("/data/1");
        System.out.println(s1);
    }

    @org.junit.Test
    public void nodeReadAndChildren() {
        List<String> children = zookeeperTemplate.opsForNode().getChildren("/data");
        System.out.println(children);
    }


    @org.junit.Test
    public void nodeState() {
        StatePayload state = zookeeperTemplate.opsForNode().state("/data/1");
        System.out.println(state.getData());
        System.out.println(state.getStat().toString());
    }


    @org.junit.Test
    public void update() {
        Stat test = zookeeperTemplate.opsForNode().update("/test", "test");
        if (test == null) {
            System.out.println("更新失败");
        }
        Stat updateData1 = zookeeperTemplate.opsForNode().update("/data/1", "updateData1");
        if (updateData1 != null) {
            System.out.println("更新成功");
            String updataData = zookeeperTemplate.opsForNode().get("/data/1");
            System.out.println("更新之后的数据" + updataData);
        }
    }

    @org.junit.Test
    public void deleteNode() {
        boolean delete = zookeeperTemplate.opsForNode().delete("/data/1", false);
        System.out.println(delete);
    }

    @org.junit.Test
    public void exist() {
        boolean exist = zookeeperTemplate.opsForNode().exist("/data/1");
        System.out.println(exist);
    }
}
