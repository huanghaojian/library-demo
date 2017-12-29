package xyz.hhjian.lib.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.hhjian.lib.entity.domain.User;

/**
 * <p>用户仓库</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据username获取User
     *
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    User selectByUsername(@Param("username") String username);
}
