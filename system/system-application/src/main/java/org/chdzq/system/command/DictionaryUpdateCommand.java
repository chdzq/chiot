package org.chdzq.system.command;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.common.core.validation.InEnum;
import org.chdzq.system.entity.Dictionary;
import org.chdzq.system.repository.DictionaryRepository;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 字典更新CMD
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 18:06
 */
@Data
public class DictionaryUpdateCommand implements ICommand<Dictionary, Long> {
    /**
     * 主键
     */
    @NotNull(message = "主键不能为空")
    private Long id;

    /**
     * 字典类型编码
     */
    private String code;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 状态(1:正常;0:禁用)
     */
    @InEnum(value = StatusEnum.class,  message = "名称不能为空")
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    @Override
    public Dictionary buildEntity() {
        Dictionary dictionary = new Dictionary();
        dictionary.setId(id);
        dictionary.setCode(code);
        dictionary.setName(name);
        dictionary.setStatus(StatusEnum.getByCode(status));
        dictionary.setRemark(remark);
        dictionary.setDirty(true);
        return dictionary;
    }

    /**
     * 校验
     * @param dictionaryRepository 仓库
     */
    public void validate(DictionaryRepository dictionaryRepository) {
        ValidationUtil.validate(this);

        //说明有code
        if (StringUtils.hasText(code)) {
            Long idByCode = dictionaryRepository.getIdByCode(code);
            Assert.isTrue(Objects.isNull(idByCode) || Objects.equals(idByCode, id), "字典类型编码重复");
        }
        if (StringUtils.hasText(name)) {
            Long idByName = dictionaryRepository.getIdByCode(name);
            Assert.isTrue(Objects.isNull(idByName) || Objects.equals(idByName, id), "字典名称编码重复");
        }
    }
}
