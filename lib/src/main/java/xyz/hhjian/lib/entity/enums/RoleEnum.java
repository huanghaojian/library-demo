package xyz.hhjian.lib.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>角色枚举类</p>
 *
 * @author <a href="mailto:hhjian.top@qq.com">hhjian</a>
 * @since 2017.10.19
 */
public enum RoleEnum implements IEnum {
    /**
     * 超级管理员
     */
    ROOT("ROLE_ROOT", "超级管理员"),
    /**
     * 管理员
     */
    ADMIN("ROLE_ADMIN", "管理员"),
    /**
     * 普通用户
     */
    USER("ROLE_USER", "普通用户");

    private String roleName;
    private String desc;

    RoleEnum(final String roleName, final String desc) {
        this.roleName = roleName;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return roleName;
    }

    @JsonValue
    public String getDesc() {
        return desc;
    }
}
