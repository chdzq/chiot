package org.chdzq.system.controller;

import lombok.AllArgsConstructor;
import org.chdzq.system.command.DepartmentCreateCommand;
import org.chdzq.system.command.DepartmentDeleteCommand;
import org.chdzq.system.command.DepartmentUpdateCommand;
import org.chdzq.system.query.DepartmentQuery;
import org.chdzq.system.query.model.DepartmentTreeVO;
import org.chdzq.system.query.model.DepartmentVO;
import org.chdzq.system.service.DepartmentQueryService;
import org.chdzq.system.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门控制器
 *
 * @author chdzq
 * @create 2025/2/18 18:50
 */
@AllArgsConstructor
@RequestMapping("/api/v1/department")
@RestController
public class DepartmentController {

    private DepartmentService commandService;

    private DepartmentQueryService queryService;

    /**
     * 新增
     * @param command 命令
     */
    @PostMapping()
    public void create(@RequestBody DepartmentCreateCommand command) {
        commandService.create(command);
    }

    /**
     * 更新
     * @param departmentId 部门id
     * @param command 命令
     */
    @PutMapping(value = "/{departmentId}")
    public void update(
            @PathVariable("departmentId") Long departmentId,
            @RequestBody DepartmentUpdateCommand command) {
        command.setId(departmentId);
        commandService.update(command);
    }

    /**
     * 删除
     * @param departmentId 部门Id
     */
    @DeleteMapping(value = "/{departmentId}")
    public void delete(@PathVariable("departmentId") Long departmentId) {
        commandService.delete(new DepartmentDeleteCommand(departmentId));
    }

    /**
     * 查询
     * @param departmentId 部门Id
     */
    @GetMapping(value = "/{departmentId}")
    public DepartmentVO detail(@PathVariable("departmentId") Long departmentId) {
        return queryService.view(departmentId);
    }

    @GetMapping(value = "/tree")
    public List<DepartmentTreeVO> tree(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "status", required = false) Integer status
    ) {
        DepartmentQuery query = DepartmentQuery.builder()
                .keyword(keyword)
                .status(status)
                .build();

        return queryService.tree(query);
    }
}
