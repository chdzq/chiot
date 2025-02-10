package org.chdzq.system.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.common.core.validation.InEnum;
import org.chdzq.system.entity.Dictionary;
import org.chdzq.system.repository.DictionaryRepository;

/**
 * 字典创建CMD
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 18:06
 */
@Data
public class DictionaryCreateCommand implements ICommand<Dictionary, Long> {

    /**
     * 字典类型编码
     */
    @NotBlank(message = "编码不能为空")
    private String code;

    /**
     * 字典名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 状态(1:正常;0:禁用)
     */
    @InEnum(value = StatusEnum.class,  message = "名称不能为空")
    private Integer status = StatusEnum.ENABLE.getCode();

    /**
     * 备注
     */
    private String remark;

    @Override
    public Dictionary buildEntity() {
        Dictionary dictionary = new Dictionary();
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
        Assert.isNull(dictionaryRepository.getIdByCode(code), "字典类型编码重复");
        Assert.isNull(dictionaryRepository.getIdByCode(name), "字典名称编码重复");
    }
}
