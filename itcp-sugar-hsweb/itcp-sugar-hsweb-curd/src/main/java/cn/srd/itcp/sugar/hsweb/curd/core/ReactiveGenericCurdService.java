package cn.srd.itcp.sugar.hsweb.curd.core;

import cn.srd.itcp.sugar.hsweb.curd.page.PageResult;
import cn.srd.itcp.sugar.hsweb.curd.utils.Converts;
import cn.srd.itcp.sugar.tools.core.Objects;
import org.hswebframework.ezorm.rdb.mapping.defaults.SaveResult;
import org.hswebframework.web.api.crud.entity.PagerResult;
import org.hswebframework.web.api.crud.entity.QueryParamEntity;
import org.hswebframework.web.api.crud.entity.RecordCreationEntity;
import org.hswebframework.web.api.crud.entity.RecordModifierEntity;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.crud.service.ReactiveCrudService;
import org.hswebframework.web.exception.NotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 为了兼容前端代码，暂时使用该类
 *
 * @param <PO>             PO 模型
 * @param <PrimaryKeyType> 主键类型
 * @author wjm
 * @see ReactiveGenericCurd2Service
 * @since 2022/6/18 19:17
 */
public interface ReactiveGenericCurdService<PO, PrimaryKeyType> extends ReactiveCrudService<PO, PrimaryKeyType> {

    // ============= 新增 =============

    /**
     * 新增单个数据，返回新增后的数据
     * <pre>
     *
     * 示例:
     *  POST /api/test
     *  Content-Type: application/json
     *
     *  {
     *   "name":"value"
     *  }
     *
     * </pre>
     *
     * @param payloadDTO DTO 模型
     * @param poClass    PO 模型类
     * @param voClass    VO 模型类
     * @param <DTO>      DTO 模型类型
     * @param <VO>       VO 模型类型
     * @return 新增后的数据
     */
    default <DTO, VO> Mono<VO> insert(Mono<DTO> payloadDTO, Class<PO> poClass, Class<VO> voClass) {
        Mono<PO> payloadPO = payloadDTO.map(dto -> Converts.withGenericMapstruct().toBean(dto, poClass));
        return Authentication
                .currentReactive()
                .flatMap(authentication -> payloadPO.map(po -> applyAuthentication(po, authentication)))
                .switchIfEmpty(payloadPO)
                .flatMap(po -> getRepository().insert(Mono.just(po)).thenReturn(Converts.withGenericMapstruct().toBean(po, voClass)));
    }

    /**
     * 批量新增数据，返回影响行数
     * <pre>
     *
     * 示例:
     *  POST /api/test/_batch
     *  Content-Type: application/json
     *
     *  [
     *   {
     *    "name":"value"
     *   }
     *  ]
     *
     * </pre>
     *
     * @param payloadDTO DTO 模型
     * @param poClass    PO 模型类
     * @param <DTO>      DTO 模型类型
     * @return 影响行数
     */
    default <DTO> Mono<Integer> insertBatch(Flux<DTO> payloadDTO, Class<PO> poClass) {
        Flux<PO> payloadPO = payloadDTO.map(dto -> Converts.withGenericMapstruct().toBean(dto, poClass));
        return Authentication
                .currentReactive()
                .flatMapMany(authentication -> payloadPO.map(po -> applyAuthentication(po, authentication)))
                .switchIfEmpty(payloadPO)
                .collectList()
                .as(getRepository()::insertBatch);
    }

    /**
     * 批量保存数据，如果传入了主键，并且对应数据存在，则尝试覆盖，不存在则新增
     * <pre>
     *
     * 示例:
     *  PATCH /api/test
     *  Content-Type: application/json
     *
     *  [
     *   {
     *    "name":"value"
     *   }
     *  ]
     *
     * </pre>
     *
     * @param payloadDTO DTO 模型
     * @param poClass    PO 模型类
     * @param <DTO>      DTO 模型类型
     * @return 保存结果
     */
    default <DTO> Mono<SaveResult> saveBatch(Flux<DTO> payloadDTO, Class<PO> poClass) {
        Flux<PO> payloadPO = payloadDTO.map(dto -> Converts.withGenericMapstruct().toBean(dto, poClass));
        return Authentication
                .currentReactive()
                .flatMapMany(authentication -> payloadPO.map(po -> applyAuthentication(po, authentication)))
                .switchIfEmpty(payloadPO)
                .as(getRepository()::save);
    }

    // ============= 更新 =============

    /**
     * 根据主键修改数据，返回是否成功
     * <pre>
     *
     * 示例:
     *  PUT /api/test/{id}
     *  Content-Type: application/json
     *
     *  {
     *   "name":"value"
     *  }
     *
     * </pre>
     *
     * @param primaryKey 主键
     * @param payloadDTO DTO 模型
     * @param poClass    PO 模型类
     * @param <DTO>      DTO 模型类型
     * @return 是否成功
     */
    default <DTO> Mono<Boolean> updateByPrimaryKey(PrimaryKeyType primaryKey, Mono<DTO> payloadDTO, Class<PO> poClass) {
        Mono<PO> payloadPO = payloadDTO.map(dto -> Converts.withGenericMapstruct().toBean(dto, poClass));
        return Authentication
                .currentReactive()
                .flatMap(authentication -> payloadPO.map(po -> applyAuthentication(po, authentication)))
                .switchIfEmpty(payloadPO)
                .flatMap(po -> getRepository().updateById(primaryKey, Mono.just(po)))
                .thenReturn(true);

    }

    // ============= 删除 =============

    /**
     * 根据主键删除数据（物理删除），返回删除后的数据
     *
     * @param primaryKey 主键
     * @param voClass    VO 模型类
     * @param <VO>       VO 模型类型
     * @return 删除后的数据
     */
    default <VO> Mono<VO> deleteByPrimaryKey(PrimaryKeyType primaryKey, Class<VO> voClass) {
        return findById(Mono.just(primaryKey))
                .switchIfEmpty(Mono.error(NotFoundException::new))
                .flatMap(po -> deleteById(Mono.just(primaryKey)).thenReturn(Converts.withGenericMapstruct().toBean(po, voClass)));
    }

    // ============= 查询 =============

    /**
     * 根据主键查询数据
     *
     * @param primaryKey 主键
     * @param voClass    VO 模型类
     * @param <VO>       VO 模型类型
     * @return VO 模型
     */
    default <VO> Mono<VO> getByPrimaryKey(PrimaryKeyType primaryKey, Class<VO> voClass) {
        return findById(Mono.just(primaryKey))
                .flatMap(po -> Mono.just(Converts.withGenericMapstruct().toBean(po, voClass)))
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    /**
     * <pre>
     *
     *  GET 方式查询列表数据（不分页），如果需要获取全部数据，请设置参数 paging=false
     *  示例：
     *   GET /_query/no-paging?pageIndex=0&amp;pageSize=20&amp;where=name is 张三&amp;orderBy=id desc
     *
     * </pre>
     *
     * @param payloadQuery 查询模型
     * @param voClass      VO 模型类
     * @param <VO>         VO 模型类型
     * @return VO 模型
     */
    default <VO> Flux<VO> list(QueryParamEntity payloadQuery, Class<VO> voClass) {
        return createQuery()
                .setParam(payloadQuery)
                .fetch()
                .map(po -> Converts.withGenericMapstruct().toBean(po, voClass));
    }

    /**
     * <pre>
     *
     *  GET 方式查询列表数据（分页）
     *  示例:
     *   GET /_query/no-paging?pageIndex=0&amp;pageSize=20&amp;where=name is 张三&amp;orderBy=id desc
     *
     * </pre>
     *
     * @param payloadQuery 查询模型
     * @param voClass      VO 模型类
     * @param <VO>         VO 模型类型
     * @return 分页 VO 模型
     */
    default <VO> Mono<PageResult<VO>> page(QueryParamEntity payloadQuery, Class<VO> voClass) {
        return Objects.isNull(payloadQuery.getTotal())
                ?
                Mono.zip(
                        getRepository().createQuery().setParam(payloadQuery.clone()).count(),
                        query(payloadQuery.clone()).collectList(),
                        (total, data) -> Converts.withHsWebMapstruct().toPageBean(PagerResult.of(total, data, payloadQuery), voClass)
                )
                :
                getRepository()
                        .createQuery()
                        .setParam(payloadQuery.rePaging(payloadQuery.getTotal()))
                        .fetch()
                        .collectList()
                        .map(data -> Converts.withHsWebMapstruct().toPageBean(PagerResult.of(payloadQuery.getTotal(), data, payloadQuery), voClass));
    }

    /**
     * POST 方式查询列表数据（不分页）
     *
     * @param payloadQuery 查询模型
     * @param voClass      VO 模型类
     * @param <VO>         VO 模型类型
     * @return VO 模型
     */
    default <VO> Flux<VO> list(Mono<QueryParamEntity> payloadQuery, Class<VO> voClass) {
        return payloadQuery
                .flatMapMany(this::query)
                .map(po -> Converts.withGenericMapstruct().toBean(po, voClass));
    }

    /**
     * POST 方式查询列表数据（分页）
     *
     * @param payloadQuery 查询模型
     * @param voClass      VO 模型类
     * @param <VO>         VO 模型类型
     * @return 分页 VO 模型
     */
    default <VO> Mono<PageResult<VO>> page(Mono<QueryParamEntity> payloadQuery, Class<VO> voClass) {
        return payloadQuery
                .flatMap(this::queryPager)
                .map(po -> Converts.withHsWebMapstruct().toPageBean(po, voClass));
    }

    // =============  =============

    /**
     * 尝试设置登陆用户信息到实体中
     *
     * @param entity         实体
     * @param authentication 权限信息
     * @return 设置后的实体
     * @see RecordCreationEntity
     * @see RecordModifierEntity
     */
    @Authorize(ignore = true)
    default PO applyAuthentication(PO entity, Authentication authentication) {
        if (entity instanceof RecordCreationEntity) {
            entity = applyCreationEntity(authentication, entity);
        }
        if (entity instanceof RecordModifierEntity) {
            entity = applyModifierEntity(authentication, entity);
        }
        return entity;
    }

    /**
     * 尝试设置登陆用户信息到实体中
     *
     * @param authentication 权限信息
     * @param entity         实体
     * @return 设置后的实体
     */
    @Authorize(ignore = true)
    default PO applyCreationEntity(Authentication authentication, PO entity) {
        RecordCreationEntity creationEntity = ((RecordCreationEntity) entity);
        creationEntity.setCreateTimeNow();
        creationEntity.setCreatorId(authentication.getUser().getId());
        creationEntity.setCreatorName(authentication.getUser().getName());
        return entity;
    }

    /**
     * 尝试设置登陆用户信息到实体中
     *
     * @param authentication 权限信息
     * @param entity         实体
     * @return 设置后的实体
     */
    @Authorize(ignore = true)
    default PO applyModifierEntity(Authentication authentication, PO entity) {
        RecordModifierEntity modifierEntity = ((RecordModifierEntity) entity);
        modifierEntity.setModifyTimeNow();
        modifierEntity.setModifierId(authentication.getUser().getId());
        modifierEntity.setModifierName(authentication.getUser().getName());
        return entity;
    }

}