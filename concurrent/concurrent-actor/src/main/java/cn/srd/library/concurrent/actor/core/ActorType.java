package cn.srd.library.concurrent.actor.core;

import cn.srd.library.tool.lang.core.standard.CodeAndDescriptionStandard;

/**
 * actor type
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
public interface ActorType extends CodeAndDescriptionStandard {

    /**
     * 获取不同 actor 分支对应的策略
     *
     * @return {@link ActorTypeStrategy}
     */
    ActorTypeStrategy getStrategy();

}
