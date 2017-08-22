/**
 * Project Name:dt36_springmvc2
 * File Name:FirstTask.java
 * Package Name:cn.java.tasks
 * Date:2017年7月26日下午2:25:23
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.java.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Description: <br/>
 * Date: 2017年7月26日 下午2:25:23 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
@Component
public class FirstTask {

    // @Scheduled(fixedRate = 5000)
    // @Scheduled(cron = "0/2 39 1 * * ?")
    public void oneTask() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间为：" + sdf.format(d));
    }
}
