package com.factory.end.util.validate;

/**
 * @Author jchonker
 * @Date 2020/9/1 14:51
 * @Version 1.0
 */
public class SchedulingValidationGroups {
    /**
     * 公共接口
     */
    public interface Common{

    }

    /**
     * 修改操作时所属
     */
    public interface Update extends Common{

    }

    /**
     * 新增惭怍时所属
     */
    public interface Insert extends Common{

    }

    /**
     * 删除操作时所属
     */
    public interface Delete extends Common{

    }

    /**
     * 查询操作时所属
     */
    public interface Select extends Common{

    }
}
