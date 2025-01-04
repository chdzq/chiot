package org.chdzq.system.controller;

import lombok.AllArgsConstructor;
import org.chdzq.common.core.entity.Page;
import org.chdzq.system.command.CreateRoleCommand;
import org.chdzq.system.command.DeleteRoleCommand;
import org.chdzq.system.command.RoleAuthorizeCommand;
import org.chdzq.system.command.UpdateRoleCommand;
import org.chdzq.system.query.RolePageQuery;
import org.chdzq.system.query.model.RoleVO;
import org.chdzq.system.service.RoleQueryService;
import org.chdzq.system.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制器
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 17:50
 */
@AllArgsConstructor
@RequestMapping("/api/v1/role")
@RestController
public class RoleController {

    private RoleService roleService;

    private RoleQueryService queryService;

    /**
     * 新增
     * @param command 命令
     */
    @PostMapping()
    public void create(@RequestBody CreateRoleCommand command) {
        roleService.create(command);
    }

    /**
     * 更新
     * @param roleId 角色Id
     * @param command 命令
     */
    @PutMapping(value = "/{roleId}")
    public void update(
            @PathVariable("roleId") Long roleId,
            @RequestBody UpdateRoleCommand command) {
        command.setId(roleId);
        roleService.update(command);
    }

    /**
     * 删除
     * @param roleId 角色id
     */
    @DeleteMapping(value = "/{roleId}")
    public void delete(@PathVariable("roleId") Long roleId) {
        roleService.delete(new DeleteRoleCommand(roleId));
    }

    /**
     * 查询列表
     */
    @GetMapping(value = "/page")
    public Page<? extends RoleVO> page(
                             @RequestParam(name = "pageNo", required = false) Integer pageNo,
                             @RequestParam(name = "pageSize", required = false) Integer pageSize,
                             @RequestParam(name = "code", required = false) String code,
                             @RequestParam(name = "status", required = false) Integer status,
                             @RequestParam(name = "name", required = false) String name) {
        return queryService.page(
                RolePageQuery.builder()
                        .code(code)
                        .status(status)
                        .name(name)
                        .pageNo(pageNo)
                        .pageSize(pageSize)
                        .build()
        );
    }

    /**
     * 更新
     * @param roleId 角色Id
     * @param resourceIdList 资源列表
     */
    @PutMapping(value = "/{roleId}/resources")
    public void authorization(
            @PathVariable("roleId") Long roleId,
            @RequestBody List<Long> resourceIdList) {

        RoleAuthorizeCommand cmd = RoleAuthorizeCommand.builder()
                .resourceIds(resourceIdList)
                .roleId(roleId)
                .build();
        roleService.authorize(cmd);
    }
}
