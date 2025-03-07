package org.chdzq.common.file.core.service.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.VoidResult;
import lombok.SneakyThrows;
import org.chdzq.common.core.exceptions.BusinessException;
import org.chdzq.common.core.result.ResultError;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.DateTimeUtil;
import org.chdzq.common.core.utils.FileUtil;
import org.chdzq.common.core.utils.IdUtil;
import org.chdzq.common.file.core.model.FileMeta;
import org.chdzq.common.file.core.service.OssService;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;

/**
 * AliyunOss服务实现类
 *
 * @author chdzq
 * @create 2025/3/7 21:49
 */
public class AliyunOssServiceImpl implements OssService {

    private final AliyunOssConfig config;
    private final OSS client;

    public AliyunOssServiceImpl(AliyunOssConfig config) {
        this.config = config;
        this.client = new OSSClientBuilder().build(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret());

    }

    @Override
    public FileMeta uploadFile(MultipartFile file) {
        // 生成文件名(日期文件夹)
        String suffix = FileUtil.getSuffix(file.getOriginalFilename());
        String uuid = IdUtil.simpleUUID();
        String fileName = DateTimeUtil.format(LocalDateTime.now(), "yyyyMMdd") + "/" + uuid + "." + suffix;
        //  try-with-resource 语法糖自动释放流
        try (InputStream inputStream = file.getInputStream()) {

            // 设置上传文件的元信息，例如Content-Type
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            // 创建PutObjectRequest对象，指定Bucket名称、对象名称和输入流
            PutObjectRequest putObjectRequest = new PutObjectRequest(config.getBucketName(), fileName, inputStream, metadata);
            // 上传文件
            client.putObject(putObjectRequest);
        } catch (Exception e) {
            throw ResultError.SERVER_ERROR.makeException("文件上传失败");
        }
        // 获取文件访问路径
        String fileUrl = "https://" + config.getBucketName() + "." + config.getEndpoint() + "/" + fileName;
        FileMeta fileInfo = new FileMeta();
        fileInfo.setName(fileName);
        fileInfo.setUrl(fileUrl);
        return fileInfo;
    }

    @Override
    public void deleteFile(String filePath) {
        Assert.hasText(filePath, "删除文件路径不能为空");
        String fileHost = "https://" + config.getBucketName() + "." + config.getEndpoint(); // 文件主机域名
        String fileName = filePath.substring(fileHost.length() + 1); // +1 是/占一个字符，截断左闭右开
        VoidResult result = client.deleteObject(config.getBucketName(), fileName);
        if (!result.getResponse().isSuccessful()) {
            throw ResultError.SERVER_ERROR.makeException("文件删除失败");
        }
    }
}
