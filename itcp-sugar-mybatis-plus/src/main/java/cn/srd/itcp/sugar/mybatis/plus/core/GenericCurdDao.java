package cn.srd.itcp.sugar.mybatis.plus.core;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 通用的增删查改 dao
 *
 * @author wjm
 * @date 2020/12/25 11:44
 */
public interface GenericCurdDao<PO> extends BaseMapper<PO> {

    // /**
    //  * 批量保存时一次最多能保存的记录数
    //  */
    // int SAVE_BATCH_MAX_COUNT = 1000;
    //
    // /**
    //  * 批量更新时一次最多能保存的记录数
    //  */
    // int UPDATE_BATCH_MAX_COUNT = 1000;
    //
    // /**
    //  * 插入一条记录，返回带有 id 的实体
    //  *
    //  * @param entity
    //  * @return
    //  */
    // default PO save(PO entity) {
    //     insert(entity);
    //     return entity;
    // }
    //
    // /**
    //  * 批量插入，支持分段插入，避免一次插入量过大报错，批量操作建议使用该方法
    //  *
    //  * @param batchList
    //  * @return
    //  */
    // @Transactional(rollbackFor = Exception.class)
    // default boolean saveBatch(Collection<PO> batchList) {
    //     if (batchList.size() <= SAVE_BATCH_MAX_COUNT) {
    //         return insertBatch(batchList);
    //     }
    //
    //     // 超额分段保存
    //     int size = batchList.size() / SAVE_BATCH_MAX_COUNT;
    //     if (batchList.size() % SAVE_BATCH_MAX_COUNT > 0) {
    //         size += 1;
    //     }
    //     for (int i = 0; i < size; i++) {
    //         if (i == size - 1) {
    //             insertBatch(CollectionsUtil.sub(batchList, i * SAVE_BATCH_MAX_COUNT, batchList.size()));
    //         } else {
    //             insertBatch(CollectionsUtil.sub(batchList, i * SAVE_BATCH_MAX_COUNT, (i + 1) * SAVE_BATCH_MAX_COUNT));
    //         }
    //     }
    //     return true;
    // }
    //
    // /**
    //  * 批量根据主键更新，支持分段更新，避免一次更新量过大报错，批量操作建议使用该方法
    //  *
    //  * @param batchList
    //  */
    // @Transactional(rollbackFor = Exception.class)
    // default void updateBatchByPrimaryKey(Collection<PO> batchList) {
    //     if (batchList.size() <= UPDATE_BATCH_MAX_COUNT) {
    //         updateBatchById(batchList);
    //     }
    //
    //     // 超额分段更新
    //     int size = batchList.size() / UPDATE_BATCH_MAX_COUNT;
    //     if (batchList.size() % UPDATE_BATCH_MAX_COUNT > 0) {
    //         size += 1;
    //     }
    //     for (int i = 0; i < size; i++) {
    //         if (i == size - 1) {
    //             updateBatchById(CollectionsUtil.sub(batchList, i * UPDATE_BATCH_MAX_COUNT, batchList.size()));
    //         } else {
    //             updateBatchById(CollectionsUtil.sub(batchList, i * UPDATE_BATCH_MAX_COUNT, (i + 1) * UPDATE_BATCH_MAX_COUNT));
    //         }
    //     }
    // }
    //
    // /**
    //  * 优化批量插入，因为 mybatis-plus {@link com.baomidou.mybatisplus.extension.service.impl.ServiceImpl#saveBatch(Collection)} 是循环的批量插入
    //  * <pre>
    //  *  {@code @Param(xx)} xx参数名必须是 list/collection/array 3个的其中之一
    //  *  不推荐外部使用该方法，建议使用 {@link #saveBatch(Collection)}
    //  * </pre>
    //  *
    //  * @param batchList
    //  * @return
    //  */
    // @Deprecated
    // @Transactional(rollbackFor = Exception.class)
    // boolean insertBatch(@Param("list") Collection<PO> batchList);
    //
    // /**
    //  * 自定义批量更新，条件为主键
    //  * <pre>
    //  *  {@code @Param(xx)} xx参数名必须是 list/collection/array 3个的其中之一
    //  * </pre>
    //  *
    //  * @param batchList
    //  * @return
    //  */
    // @Transactional(rollbackFor = Exception.class)
    // int updateBatchById(@Param("list") Collection<PO> batchList);
    //
    // /**
    //  * 相当于 {@link BaseMapper#selectById(Serializable)}，增加非空校验
    //  *
    //  * @param primaryKey
    //  * @return
    //  */
    // @NonNull
    // default PO getByPrimaryKey(Serializable primaryKey) {
    //     Assert.INSTANCE.set(HttpStatusEnum.BAD_REQUEST.getCode(), "id为空，请检查").throwsIfNull(primaryKey);
    //     PO result = selectById(primaryKey);
    //     if (Objects.isEmpty(result)) {
    //         WarnAssert.INSTANCE.set(501, StringsUtil.format("数据不存在，primaryKey: {}{}", primaryKey, getStackTraceMsg())).throwsNow();
    //     }
    //     return result;
    // }
    //
    // /**
    //  * 相当于 {@link BaseMapper#selectById(Serializable)}，若数据不存在返回 null，不会抛出异常
    //  *
    //  * @param id
    //  * @return
    //  */
    // @Nullable
    // default PO getByIdSafe(Serializable id) {
    //     if (Objects.isNull(id)) {
    //         return null;
    //     }
    //     return selectById(id);
    // }
    //
    // /**
    //  * 相当于 {@link BaseMapper#selectBatchIds(Collection)} ，若数据不存在或入参为空，返回空的集合
    //  *
    //  * @param primaryKeys
    //  * @return
    //  */
    // @NonNull
    // default List<PO> listByPrimaryKeys(Collection<? extends Serializable> primaryKeys) {
    //     if (Objects.isEmpty(primaryKeys)) {
    //         return new ArrayList<>();
    //     }
    //     return selectBatchIds(primaryKeys);
    // }
    //
    // /**
    //  * 通过数据表的 UUID 字段获取记录
    //  *
    //  * @param uuid
    //  * @return
    //  */
    // @NonNull
    // default PO getByUuid(String uuid) {
    //     WarnAssert.INSTANCE.set(HttpStatusEnum.BAD_REQUEST.getCode(), "uuid为空，请检查").throwsIfNull(uuid);
    //     PO result = selectOne(MpQueryWrappers.apply("uuid = '" + uuid + "'::uuid"));
    //     if (Objects.isEmpty(result)) {
    //         WarnAssert.INSTANCE.set(501, StringsUtil.format("数据不存在，uuid: {}{}", uuid, getStackTraceMsg())).throwsNow();
    //     }
    //     return result;
    // }
    //
    // /**
    //  * 通过数据表的 UUID 字段获取记录，若数据不存在返回 null，不会抛出异常
    //  *
    //  * @param uuid
    //  * @return
    //  */
    // @Nullable
    // default PO getByUuidSafe(String uuid) {
    //     if (Objects.isEmpty(uuid)) {
    //         return null;
    //     }
    //     return selectOne(MpQueryWrappers.apply("uuid = '" + uuid + "'::uuid"));
    // }
    //
    // /**
    //  * 相当于 {@link BaseMapper#updateById(Object)}，增加更新失败校验
    //  *
    //  * @param entity
    //  * @return
    //  */
    // default PO updateByPrimaryKey(PO entity) {
    //     boolean success = SqlHelper.retBool(updateById(entity));
    //     if (Objects.isFalse(success)) {
    //         WarnAssert.INSTANCE.set(501, StringsUtil.format("更新失败，请确认数据是否存在，entity: {}{}", entity.toString(), getStackTraceMsg())).throwsNow();
    //     }
    //     return entity;
    // }
    //
    // /**
    //  * 通过数据表的 UUID 字段更新记录
    //  *
    //  * @param entity
    //  * @param uuid
    //  * @return
    //  */
    // default boolean updateByUuid(PO entity, String uuid) {
    //     boolean success = SqlHelper.retBool(update(entity, MpQueryWrappers.apply("uuid = '" + uuid + "'::uuid")));
    //     if (Objects.isFalse(success)) {
    //         WarnAssert.INSTANCE.set(501, StringsUtil.format("更新失败，请确认数据是否存在，uuid: {}{}", uuid, getStackTraceMsg())).throwsNow();
    //     }
    //     return success;
    // }
    //
    // /**
    //  * 相当于 {@link BaseMapper#deleteById(Serializable)}，增加删除失败校验
    //  *
    //  * @param id
    //  * @return
    //  */
    // default boolean removeByPrimaryKey(Serializable id) {
    //     boolean success = SqlHelper.retBool(deleteById(id));
    //     if (Objects.isFalse(success)) {
    //         WarnAssert.INSTANCE.set(501, StringsUtil.format("删除失败，请确认数据是否存在，id: {}{}", id, getStackTraceMsg())).throwsNow();
    //     }
    //     return success;
    // }
    //
    // /**
    //  * 相当于 {@link BaseMapper#deleteByMap(Map)} ，增加删除失败校验
    //  *
    //  * @param params
    //  * @return
    //  */
    // default boolean removeByMap(Map<String, Object> params) {
    //     boolean success = SqlHelper.retBool(deleteByMap(params));
    //     if (Objects.isFalse(success)) {
    //         WarnAssert.INSTANCE.set(501, StringsUtil.format("删除失败，请确认数据是否存在，params: {}{}", params, getStackTraceMsg())).throwsNow();
    //     }
    //     return success;
    // }
    //
    // /**
    //  * 相当于 {@link BaseMapper#delete(Wrapper)} ，增加删除失败校验
    //  *
    //  * @param queryWrapper
    //  * @return
    //  */
    // default boolean remove(Wrapper<PO> queryWrapper) {
    //     boolean success = SqlHelper.retBool(delete(queryWrapper));
    //     if (Objects.isFalse(success)) {
    //         WarnAssert.INSTANCE.set(501, StringsUtil.format("删除失败，请确认数据是否存在，entity: {}{}", queryWrapper.getEntity(), getStackTraceMsg())).throwsNow();
    //     }
    //     return success;
    // }
    //
    // /**
    //  * 通过数据表的 UUID 字段删除记录
    //  *
    //  * @param uuid
    //  * @return
    //  */
    // default int removeByUuid(String uuid) {
    //     if (Objects.isEmpty(uuid)) {
    //         return 0;
    //     }
    //     return delete(MpQueryWrappers.apply("uuid = '" + uuid + "'::uuid"));
    // }

}
