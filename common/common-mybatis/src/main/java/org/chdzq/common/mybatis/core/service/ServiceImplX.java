package org.chdzq.common.mybatis.core.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.binding.MapperMethod;
import org.chdzq.common.core.entity.Page;
import org.chdzq.common.core.ddd.PageQuery;
import org.chdzq.common.mybatis.core.mapper.BaseMapperX;
import org.chdzq.common.mybatis.domain.BaseDO;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * ServiceImplX 基类
 * @author chdzq
 * @create 2024/11/20
 */
public abstract class ServiceImplX<M extends BaseMapperX<T>, T extends BaseDO> extends ServiceImpl<M, T> {

    public Page<T> page(PageQuery pageParam, Wrapper<T> queryWrapper) {
        return this.customPage(pageParam, (page, aPageParam) -> getBaseMapper().selectPage(page, queryWrapper));
    }

    public Page<T> page(PageQuery pageParam) {
        return this.page(pageParam, Wrappers.emptyWrapper());
    }

    /**
     * 自定义查询
     * @param pageParam
     * @param consumer
     * @return
     * @param <R>
     * @param <P>
     */
    public <R, P extends PageQuery> Page<R> customPage(P pageParam, BaseMapperX.CustomQueryMethod<P, R> consumer) {
        return getBaseMapper().selectCustomPage(pageParam, consumer);
    }

    public Page<T> customPage(PageQuery pageParam) {
        return this.customPage(pageParam, (page, aPageParam) -> page(page));
    }

    public T getOne(String field, Object value) {
        return getOne(field, value, null, null);
    }

    public T getOne(SFunction<T, ?> field, Object value) {
        return getOne(field, value, null, null);
    }

    public T getOne(String field1, Object value1, String field2, Object value2) {
        QueryWrapper<T> queryWrapper = generateQueryWrapperWithLogicDelete()
                .eq(field1, value1);

        if (!Objects.isNull(value2) && !Objects.isNull(field2) ) {
            queryWrapper.eq(field2, value2);
        }
        return getOne(queryWrapper);
    }

    public T getOne(SFunction<T, ?> field1, Object value1, SFunction<T, ?> field2, Object value2) {
        LambdaQueryWrapper<T> queryWrapper = generateLambdaQueryWrapperWithLogicDelete()
                .eq(field1, value1);
        if (!Objects.isNull(value2) && !Objects.isNull(field2) ) {
            queryWrapper.eq(field2, value2);
        }
        return getOne(queryWrapper);
    }

    @Override
    public long count() {
        return count((String) null, null);
    }

    public long count(String field, Object value) {
        QueryWrapper<T> queryWrapper = generateQueryWrapperWithLogicDelete();
        if (!Objects.isNull(value) && !Objects.isNull(field) ) {
            queryWrapper.eq(field, value);
        }
        return count(queryWrapper);
    }

    public long count(SFunction<T, ?> field, Object value) {
        LambdaQueryWrapper<T> queryWrapper = generateLambdaQueryWrapperWithLogicDelete();
        if (!Objects.isNull(value) && !Objects.isNull(field) ) {
            queryWrapper.eq(field, value);
        }
        return count(queryWrapper);
    }

    @Override
    public List<T> list() {
        return list((String)null, (Object) null);
    }

    private QueryWrapper<T> generateQueryWrapperWithLogicDelete() {
        QueryWrapper<T> queryWrapper;
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.getEntityClass());
        if (tableInfo.isWithLogicDelete()) {
            TableFieldInfo deleteFieldInfo = tableInfo.getLogicDeleteFieldInfo();
            queryWrapper = new QueryWrapper<T>().eq(deleteFieldInfo.getColumn(), deleteFieldInfo.getLogicNotDeleteValue());
        } else {
            queryWrapper = new QueryWrapper<T>();
        }
        return queryWrapper;
    }

    private LambdaQueryWrapper<T> generateLambdaQueryWrapperWithLogicDelete() {
        LambdaQueryWrapper<T> queryWrapper;
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.getEntityClass());
        if (tableInfo.isWithLogicDelete()) {
            TableFieldInfo deleteFieldInfo = tableInfo.getLogicDeleteFieldInfo();
            queryWrapper = new QueryWrapper<T>().eq(deleteFieldInfo.getColumn(), deleteFieldInfo.getLogicNotDeleteValue()).lambda();
        } else {
            queryWrapper = new LambdaQueryWrapper<T>();
        }
        return queryWrapper;
    }

    public List<T> list(String field, Object value) {
       QueryWrapper<T> queryWrapper = generateQueryWrapperWithLogicDelete();
        if (!Objects.isNull(value) && !Objects.isNull(field) ) {
            queryWrapper.eq(field, value);
        }
        return list(queryWrapper);
    }

    public List<T> list(SFunction<T, ?> field, Object value) {
        LambdaQueryWrapper<T> queryWrapper = generateLambdaQueryWrapperWithLogicDelete();
        if (!Objects.isNull(value) && !Objects.isNull(field) ) {
            queryWrapper.eq(field, value);
        }
        return list(queryWrapper);
    }

    public List<T> list(String field, Collection<?> values) {
        if (CollectionUtils.isEmpty(values)) {
            return new ArrayList<>();
        }
        QueryWrapper<T> queryWrapper = generateQueryWrapperWithLogicDelete();
        return list(queryWrapper.in(field, values));
    }

    public List<T> list(SFunction<T, ?> field, Collection<?> values) {
        if (CollectionUtils.isEmpty(values)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<T> queryWrapper = generateLambdaQueryWrapperWithLogicDelete();
        return list(queryWrapper.in(field, values));
    }

    @Override
    public T getById(Serializable id) {
        if (null == id) {
            return null;
        }
        if (id instanceof CharSequence) {
            if (StringUtils.isBlank((CharSequence)id)) {
                return null;
            }
        }
        return super.getById(id);
    }

    @Override
    public List<T> listByIds(Collection<? extends Serializable> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return new ArrayList<>(0);
        }
        List<T> list = super.listByIds(idList);
        if (null == list) {
            return new ArrayList<>(0);
        }
        return list;
    }


    /**
     * 根据IDS 检验数据真实性。
     * 查询到的数量都必须完全存在且一致
     * @param ids
     * @return
     */
    public Boolean checkAuthenticityMatchableByIds(Collection<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Boolean.FALSE;
        }
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.getEntityClass());
        QueryWrapper<T> queryWrapper = generateQueryWrapperWithLogicDelete()
                .in(tableInfo.getKeyColumn(), ids);
        long count = count(queryWrapper);
        return count == (long)ids.size();
    }

    @Override
    public boolean saveBatch(Collection<T> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return false;
        }
        return super.saveBatch(entityList);
    }

    /**
     * 根据field属性批量更新或是新增
     * @param entityList
     * @param field
     * @return
     */
    public boolean saveOrUpdateBatch(Collection<T> entityList, SFunction<T, ?> field) {
        return saveOrUpdateBatch(entityList, field, DEFAULT_BATCH_SIZE);
    }

    /**
     * 根据field属性批量更新或是新增
     * @param entityList
     * @param field
     * @param batchSize
     * @return
     */
    public boolean saveOrUpdateBatch(Collection<T> entityList, SFunction<T, ?> field, int batchSize) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(getEntityClass());
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        return SqlHelper.saveOrUpdateBatch(this.getEntityClass(), this.getMapperClass(), this.log, entityList, batchSize, (sqlSession, entity) -> {
            Object val = field.apply(entity);
            if (StringUtils.checkValNull(val)) {
                return true;
            }

            LambdaQueryWrapper<T> fieldQuery = Wrappers.<T>lambdaQuery().eq(field, val);
            T one = getOne(fieldQuery);

            if (null == one) {
                return true;
            }
            Object id = tableInfo.getPropertyValue(one, keyProperty);
            tableInfo.setPropertyValue(entity, keyProperty, id);
            return false;
        }, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(getSqlStatement(SqlMethod.UPDATE_BY_ID), param);
        });
    }

    /**
     * 根据field属性批量更新或是新增
     * @param entityList
     * @param field
     * @param batchSize
     * @return
     */
    public boolean saveOrUpdateBatch(Collection<T> entityList,
                                     SFunction<T, ?> field,
                                     Consumer<? super LambdaQueryWrapper<T>> queryAction,
                                     int batchSize
    ) {
        if (CollectionUtils.isEmpty(entityList)) {
            return false;
        }
        TableInfo tableInfo = TableInfoHelper.getTableInfo(getEntityClass());
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        Map<?, T> entityMap = entityList.stream().collect(Collectors.toMap(field, (var1) -> var1));
        List<T> adds = new ArrayList<>(batchSize);
        List<T> updates = new ArrayList<>(batchSize);
        List<Object> keys = new ArrayList<>(batchSize);
        int i = 0;
        for (Iterator<T> iterator = entityList.iterator(); true ; i++) {
            if (!iterator.hasNext() || i % batchSize == 0) {
                if (CollectionUtils.isNotEmpty(keys)) {
                    LambdaQueryWrapper<T> fieldsQuery = Wrappers.<T>lambdaQuery().in(field, keys);
                    if (Objects.nonNull(queryAction)) {
                        queryAction.accept(fieldsQuery);
                    }
                    List<T> temps = list(fieldsQuery);
                    Map<?, T> tempMap = temps.stream().collect(Collectors.toMap(field, (var1) -> var1));

                    keys.forEach((o)-> {
                        T t = tempMap.get(o);
                        T entity = entityMap.get(o);
                        if (!Objects.isNull(t)) {
                            Object id = tableInfo.getPropertyValue(t, keyProperty);
                            tableInfo.setPropertyValue(entity, keyProperty, id);
                            updates.add(entity);
                        } else {
                            adds.add(entity);
                        }
                    });
                }

                if (CollectionUtils.isNotEmpty(adds)) {
                    saveBatch(adds, batchSize);
                }
                if (CollectionUtils.isNotEmpty(updates)) {
                    updateBatchById(updates, batchSize);
                }
                adds.clear();
                updates.clear();
                keys.clear();
            }
            if (!iterator.hasNext()) {
                break;
            }

            T entity = iterator.next();
            Object val = field.apply(entity);
            if (!StringUtils.checkValNull(val)) {
                keys.add(val);
            } else {
                adds.add(entity);
            }
        }

        return true;
    }


    /**
     * 根据属性更新或是新增数据
     * @param entity
     * @param field
     */
    public void saveOrUpdate(T entity, SFunction<T, ?> field) {
        Object val = field.apply(entity);
        if (StringUtils.checkValNull(val)) {
            return;
        }

        LambdaQueryWrapper<T> fieldQuery = Wrappers.<T>lambdaQuery().eq(field, val);
        T one = getOne(fieldQuery);

        if (Objects.isNull(one)) {
            save(entity);
        } else {
            TableInfo tableInfo = TableInfoHelper.getTableInfo(getEntityClass());
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
            Object id = tableInfo.getPropertyValue(one, keyProperty);
            tableInfo.setPropertyValue(entity, keyProperty, id);
            updateById(entity);
        }
    }

}
