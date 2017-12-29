package xyz.hhjian.lib;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * <p>运行多个测试类</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.20
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LibApplicationTests.class,
        MapperTest.class}
)
public class SuiteTest {
}
