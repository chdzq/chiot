package org.chdzq.system.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.system.entity.Dictionary;
import org.chdzq.system.entity.DictionaryItem;
import org.chdzq.system.repository.DictionaryRepository;

import java.util.List;
import java.util.Objects;

/**
 * 字典项删除CMD
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 18:53
 */
@AllArgsConstructor
public class DictionaryItemDeleteCommand implements ICommand<Dictionary, Long> {
    /**
     * 主键
     */
    @NotNull(message = "主键不能为空")
    private Long id;

    @Override
    public Dictionary buildEntity() {
        Dictionary dictionary = new Dictionary();
        dictionary.setDirty(false);
        DictionaryItem item = new DictionaryItem();
        item.setDictionaryId(id);
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
    }
}
