package com.dlh;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2018/12/11 21:48
 */
@ContextConfiguration(locations = {"classpath:conf/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTestCase {
}
