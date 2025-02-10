package org.chdzq.system.service.impl;

import lombok.AllArgsConstructor;
import org.chdzq.system.command.*;
import org.chdzq.system.entity.Dictionary;
import org.chdzq.system.repository.DictionaryRepository;
import org.chdzq.system.service.DictionaryService;
import org.springframework.stereotype.Service;

/**
 * 字典服务类
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 18:31
 */
@Service
@AllArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private DictionaryRepository dictionaryRepository;

    @Override
    public void create(DictionaryCreateCommand cmd) {
        cmd.validate(dictionaryRepository);

        Dictionary dictionary = cmd.buildEntity();

        dictionaryRepository.create(dictionary);
    }

    @Override
    public void update(DictionaryUpdateCommand cmd) {
        cmd.validate(dictionaryRepository);

        Dictionary dictionary = cmd.buildEntity();

        dictionaryRepository.update(dictionary);
    }

    @Override
    public void delete(DictionaryDeleteCommand cmd) {
        cmd.validate(dictionaryRepository);

        dictionaryRepository.delete(cmd.buildEntity());
    }

    @Override
    public void create(DictionaryItemCreateCommand cmd) {
        cmd.validate(dictionaryRepository);

        Dictionary dictionary = cmd.buildEntity();

        dictionaryRepository.create(dictionary);
    }

    @Override
    public void update(DictionaryItemUpdateCommand cmd) {
        cmd.validate(dictionaryRepository);

        Dictionary dictionary = cmd.buildEntity();

        dictionaryRepository.update(dictionary);
    }


    @Override
    public void delete(DictionaryItemDeleteCommand cmd) {
        cmd.validate(dictionaryRepository);
        dictionaryRepository.delete(cmd.buildEntity());
    }
}
