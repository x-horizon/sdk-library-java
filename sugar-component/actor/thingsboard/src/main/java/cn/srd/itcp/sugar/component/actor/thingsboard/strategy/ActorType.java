package cn.srd.itcp.sugar.component.actor.thingsboard.strategy;

/**
 * actor type
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
public interface ActorType {

    /**
     * 获取 code
     *
     * @return code
     */
    int getCode();

    /**
     * 获取描述
     *
     * @return 描述
     */
    String getDescription();

    /**
     * 获取不同 actor 分支对应的策略
     *
     * @return {@link ActorTypeStrategy}
     */
    ActorTypeStrategy getStrategy();

}
