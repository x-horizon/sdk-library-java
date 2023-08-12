package cn.srd.library.orm.hsweb.core;

import cn.srd.library.orm.hsweb.page.PageResult;
import cn.srd.library.orm.hsweb.utils.Converts;
import org.hswebframework.ezorm.rdb.mapping.ReactiveRepository;
import org.hswebframework.web.api.crud.entity.QueryParamEntity;
import org.hswebframework.web.crud.service.ReactiveCrudService;
import org.hswebframework.web.crud.web.reactive.ReactiveCrudController;
import org.hswebframework.web.crud.web.reactive.ReactiveDeleteController;
import org.hswebframework.web.crud.web.reactive.ReactiveQueryController;
import org.hswebframework.web.crud.web.reactive.ReactiveSaveController;
import org.hswebframework.web.exception.NotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 该类是为了后续修改规范时用，到时将该类的类名中的2去掉，再将 {@link ReactiveGenericCurdService} 删掉即可
 * <pre>
 * 该类是基于 {@link ReactiveCrudController} 实现的响应式通用增删查改 service;
 * 这么做的原因是：
 *  1、无法自定义 URI 资源路径，hsweb 的通用 Controller 对于增删查改的 URI 路径并不符合 RESTful 规范；
 *  2、hsweb 的增删查改统一使用了贫血模型，无法灵活地适应业务变化；
 *  3、直接侵入 Controller 的方式并不友好；
 * </pre>
 *
 * @param <PO>             PO 模型
 * @param <PrimaryKeyType> 主键类型
 * @author wjm
 * @see ReactiveCrudController
 * @see ReactiveSaveController
 * @see ReactiveQueryController
 * @see ReactiveDeleteController
 * @see ReactiveCrudService
 * @see ReactiveRepository
 * @since 2022/6/18 19:17
 */
public interface ReactiveGenericCurd2Service<PO, PrimaryKeyType> extends ReactiveCrudService<PO, PrimaryKeyType> {

    // ============= 新增 =============

    /**
     * 新增单个数据，返回影响行数
     *
     * @param payloadDTO DTO 模型
     * @param poClass    PO 模型类
     * @param <DTO>      DTO 模型类型
     * @return 影响行数
     */
    default <DTO> Mono<Integer> save(Mono<DTO> payloadDTO, Class<PO> poClass) {
        return payloadDTO
                .map(dto -> Converts.withGenericMapstruct().toBean(dto, poClass))
                .as(this::insert);
    }

    /**
     * 批量新增数据，返回影响行数
     *
     * @param payloadDTO DTO 模型
     * @param poClass    PO 模型类
     * @param <DTO>      DTO 模型类型
     * @return 影响行数
     */
    default <DTO> Mono<Integer> saveBatch(Flux<DTO> payloadDTO, Class<PO> poClass) {
        return payloadDTO
                .map(dto -> Converts.withGenericMapstruct().toBean(dto, poClass))
                .collectList()
                .as(this::insertBatch);
    }

    // ============= 更新 =============

    /**
     * 根据主键修改数据，返回影响行数
     *
     * @param primaryKey 主键
     * @param payloadDTO DTO 模型
     * @param poClass    PO 模型类
     * @param <DTO>      DTO 模型类型
     * @return 影响行数
     */
    default <DTO> Mono<Integer> updateByPrimaryKey(PrimaryKeyType primaryKey, Mono<DTO> payloadDTO, Class<PO> poClass) {
        return payloadDTO
                .map(dto -> Converts.withGenericMapstruct().toBean(dto, poClass))
                .as(po -> updateById(primaryKey, po));
    }

    // ============= 删除 =============

    /**
     * 根据主键删除数据（物理删除），返回影响行数
     *
     * @param primaryKey 主键
     * @return 影响行数
     */
    default Mono<Integer> deleteByPrimaryKey(PrimaryKeyType primaryKey) {
        return deleteById(primaryKey);
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
        return findById(primaryKey)
                .flatMap(po -> Mono.just(Converts.withGenericMapstruct().toBean(po, voClass)))
                .switchIfEmpty(Mono.error(NotFoundException::new));
    }

    /**
     * 查询列表数据（不分页），如果需要获取全部数据，请设置参数 paging=false
     *
     * @param payloadQuery 查询模型
     * @param voClass      VO 模型类
     * @param <VO>         VO 模型类型
     * @return VO 模型
     */
    default <VO> Flux<VO> list(Mono<QueryParamEntity> payloadQuery, Class<VO> voClass) {
        return payloadQuery
                .flatMapMany(this::query)
                .flatMap(po -> Flux.just(Converts.withGenericMapstruct().toBean(po, voClass)));
    }

    /**
     * 查询列表数据（分页）
     *
     * @param payloadQuery 查询模型
     * @param voClass      VO 模型类
     * @param <VO>         VO 模型类型
     * @return 分页 VO 模型
     */
    default <VO> Mono<PageResult<VO>> page(Mono<QueryParamEntity> payloadQuery, Class<VO> voClass) {
        return payloadQuery
                .flatMap(this::queryPager)
                .flatMap(pagerResult -> Mono.just(Converts.withHsWebMapstruct().toPageBean(pagerResult, voClass)));
    }

    // =============  =============

}