package cn.srd.library.java.concurrent.actor.core;

/**
 * actor type
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
public interface ActorType {

    int getCode();

    String getDescription();

    /**
     * 获取不同 actor 分支对应的策略
     *
     * @return {@link ActorTypeStrategy}
     */
    ActorTypeStrategy getStrategy();

}