package org.chdzq.system.controller;

import lombok.AllArgsConstructor;
import org.chdzq.system.command.*;
import org.chdzq.system.query.model.ResourceVO;
import org.chdzq.system.service.ResourceQueryService;
import org.chdzq.system.service.ResourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源控制器
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 11:30
 */
@AllArgsConstructor
@RequestMapping("/api/v1/resource")
@RestController
public class ResourceController {

    private ResourceService resourceService;

    private ResourceQueryService queryService;
    /**
     * 新增
     * @param command 用户信息
     */
    @PostMapping()
    public void create(@RequestBody CreateResourceCommand command) {
        resourceService.create(command);
    }

    /**
     * 更新
     * @param resourceId 资源id
     * @param command 更新内容
     */
    @PutMapping(value = "/{resourceId}")
    public void update(
            @PathVariable("resourceId") Long resourceId,
            @RequestBody UpdateResourceCommand command) {
        command.setId(resourceId);
        resourceService.update(command);
    }

    /**
     * 删除用户
     * @param resourceId 删除的用户id
     */
    @DeleteMapping(value = "/{resourceId}")
    public void delete(@PathVariable("resourceId") Long resourceId) {
        resourceService.delete(new DeleteResourceCommand(resourceId));
    }

    /**
     * 查询列表
     */
    @GetMapping(value = "/tree")
    public List<ResourceVO> tree() {
        return queryService.tree();
    }

}
