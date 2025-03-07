package org.chdzq.system.controller;

import lombok.AllArgsConstructor;
import org.chdzq.common.file.core.model.FileMeta;
import org.chdzq.common.file.core.service.OssService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件控制器
 *
 * @author chdzq
 * @create 2025/3/7 22:11
 */
@RequestMapping("/api/v1/file")
@AllArgsConstructor
@RestController
public class FileController {

    private final OssService ossService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping
    public FileMeta uploadFile(
            @RequestParam(value = "file") MultipartFile file) {
        return ossService.uploadFile(file);
    }

    /**
     * 文件删除
     * @param filePath
     * @return
     */
    @DeleteMapping
    public void deleteFile(@RequestParam String filePath) {
        ossService.deleteFile(filePath);
    }
}
