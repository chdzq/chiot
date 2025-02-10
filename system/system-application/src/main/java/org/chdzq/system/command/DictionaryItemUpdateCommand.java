package org.chdzq.system.command;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.common.core.validation.InEnum;
import org.chdzq.system.entity.Dictionary;
import org.chdzq.system.entity.DictionaryItem;
import org.chdzq.system.repository.DictionaryRepository;

import java.util.List;
import java.util.Objects;

/**
 * 字典项创建CMD
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 18:57
 */
@Data
public class DictionaryItemUpdateCommand implements ICommand<Dictionary, Long> {

    /**
     * 字典项主键
     */
    @NotNull(message = "字典项ID不能为空")
    private Long id;

    /**
     * 字典主键
     */
    @NotNull(message = "字典ID不能为空")
    private Long dictionaryId;

    /**
     * 字典项名称
     */
    @NotNull(message = "字典项Key不能为空")
    private String key;

    /**
     * 字典项值
     */
    @NotNull(message = "字典项值不能为空")
    private String value;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态(1:正常;0:禁用)
     */
    @InEnum(StatusEnum.class)
    private Integer status;

    /**
     * 是否默认(1:是;0:否)
     */
    private boolean defaulted;

    @Override
    public Dictionary buildEntity() {
        Dictionary dictionary = new Dictionary();
        dictionary.setId(dictionary.getId());
        dictionary.setDirty(false);

        DictionaryItem item = new DictionaryItem();
        item.setDictionaryId(dictionaryId);
        item.setKey(key);
        item.setValue(value);
        item.setSort(Objects.isNull(sort)? 0: sort);
        item.setValue(value);
        item.setDefaulted(defaulted ? 1: 0);
        item.setDirty(true);
        dictionary.setItems(List.of(item));
        return dictionary;
    }

    /**
     * 校验
     * @param dictionaryRepository 仓库
     */
    public void validate(DictionaryRepository dictionaryRepository) {
        ValidationUtil.validate(this);

        //说明有code
        Assert.isTrue(dictionaryRepository.isExistById(dictionaryId), "字典不存在");
        Assert.isTrue(!dictionaryRepository.isExistByItemKey(dictionaryId, key), "字典Key重复");
    }
}
