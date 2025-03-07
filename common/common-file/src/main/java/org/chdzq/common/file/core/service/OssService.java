package org.chdzq.common.file.core.service;

import org.chdzq.common.file.core.model.FileMeta;
import org.springframework.web.multipart.MultipartFile;

/**
 * 对象存储服务接口层
 *
 * @author chdzq
 * @create 2025/3/7 09:47
 */
public interface OssService {

    /**
     * 上传文件
     * @param file 表单文件对象
     * @return 文件信息
     */
    FileMeta uploadFile(MultipartFile file);

    /**
     * 删除文件
     *
     * @param filePath 文件完整URL
     */
    void deleteFile(String filePath);


}