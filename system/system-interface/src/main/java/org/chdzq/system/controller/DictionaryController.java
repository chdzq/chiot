package org.chdzq.system.controller;

import lombok.AllArgsConstructor;
import org.chdzq.common.core.dictionary.DictionaryConstant;
import org.chdzq.common.core.dictionary.DictionaryConstantService;
import org.chdzq.common.core.entity.Page;
import org.chdzq.system.command.*;
import org.chdzq.system.query.DictionaryItemPageQuery;
import org.chdzq.system.query.DictionaryPageQuery;
import org.chdzq.system.query.model.DictionaryItemVO;
import org.chdzq.system.query.model.DictionaryVO;
import org.chdzq.system.service.DictionaryQueryService;
import org.chdzq.system.service.DictionaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典控制器
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/10 20:00
 */
@AllArgsConstructor
@RequestMapping("/api/v1/dictionary")
@RestController
public class DictionaryController {

    private DictionaryService dictionaryService;

    private DictionaryConstantService dictionaryConstantService;

    /**
     * 新增
     * @param command 命令
     */
    @PostMapping()
    public void create(@RequestBody DictionaryCreateCommand command) {
        dictionaryService.create(command);
    }

    /**
     * 更新
     * @param dictionaryId 字典id
     * @param command 命令
     */
    @PutMapping(value = "/{dictionaryId}")
    public void update(
            @PathVariable("dictionaryId") Long dictionaryId,
            @RequestBody DictionaryUpdateCommand command) {
        command.setId(dictionaryId);
        dictionaryService.update(command);
    }

    /**
     * 删除
     * @param dictionaryId 字典Id
     */
    @DeleteMapping(value = "/{dictionaryId}")
    public void delete(@PathVariable("dictionaryId") Long dictionaryId) {
        dictionaryService.delete(new DictionaryDeleteCommand(dictionaryId));
    }

    /**
     * 新增
     * @param command 命令
     */
    @PostMapping("/{dictionaryId}/item")
    public void createItem(
            @PathVariable("dictionaryId") Long dictionaryId,
            @RequestBody DictionaryItemCreateCommand command) {
        command.setDictionaryId(dictionaryId);
        dictionaryService.create(command);
    }

    /**
     * 更新
     * @param dictionaryId 资源id
     * @param itemId 字典项id
     * @param command 命令
     */
    @PutMapping(value = "/{dictionaryId}/item/{itemId}")
    public void updateItem(
            @PathVariable("dictionaryId") Long dictionaryId,
            @PathVariable("itemId") Long itemId,
            @RequestBody DictionaryItemUpdateCommand command) {
        command.setId(itemId);
        command.setDictionaryId(dictionaryId);
        dictionaryService.update(command);
    }

    /**
     * 删除
     * @param itemId 字典项Id
     */
    @DeleteMapping(value = "/{dictionaryId}/item/{itemId}")
    public void deleteItem(@PathVariable("dictionaryId") Long dictionaryId,
                           @PathVariable("itemId") Long itemId) {
        dictionaryService.delete(new DictionaryItemDeleteCommand(itemId));
    }


    private DictionaryQueryService dictionaryQueryService;

    /**
     * 分页获取字典集列表
     * @param pageNo 页面 默认为1
     * @param pageSize 分页数量 默认为10
     * @param keyword 搜索关键词
     * @param status 状态
     * @return 字典集列表
     */
    @GetMapping(value = "/page")
    public Page<? extends DictionaryVO> page(@RequestParam(name = "pageNo", required = false) Integer pageNo,
                                             @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                             @RequestParam(name = "keyword", required = false) String keyword,
                                             @RequestParam(name = "status", required = false) Integer status
    ) {
        DictionaryPageQuery query = DictionaryPageQuery.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .keyword(keyword)
                .status(status)
                .build();
        return dictionaryQueryService.page(query);
    }

    /**
     * 获取字典常量列表
     * @return 常量字典列表
     */
    @GetMapping(value = "/constants")
    public List<DictionaryConstant> constantList() {
        return dictionaryConstantService.getDictionaryConstantList();
    }

    /**
     * 分页获取字典项列表
     * @param pageNo 页面 默认为1
     * @param pageSize 分页数量 默认为10
     * @param keyword 搜索关键词
     * @param status 状态
     * @return 字典项列表
     */
    @GetMapping(value = "/{dictionaryId}/page")
    public Page<? extends DictionaryItemVO> itemPage(
            @PathVariable("dictionaryId") Long dictionaryId,
            @RequestParam(name = "pageNo", required = false) Integer pageNo,
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "status", required = false) Integer status
    ) {
        DictionaryItemPageQuery query = DictionaryItemPageQuery.builder()
                .dictionaryId(dictionaryId)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .keyword(keyword)
                .status(status)
                .build();
        return dictionaryQueryService.page(query);
    }

}
