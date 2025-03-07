package org.chdzq.common.file.core.service.minio;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.SneakyThrows;
import org.chdzq.common.core.result.ResultError;
import org.chdzq.common.core.utils.*;
import org.chdzq.common.file.core.model.FileMeta;
import org.chdzq.common.file.core.service.OssService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

/**
 * MinioOssService 服务类
 *
 * @author chdzq
 * @create 2025/3/7 09:56
 */
public class MinioOssServiceImpl implements OssService, InitializingBean {

    private final MinoConfig config;
    private final MinioClient client;

    public MinioOssServiceImpl(MinoConfig config) {
        this.config = config;
        this.client = MinioClient.builder()
                .endpoint(config.getEndpoint())
                .credentials(config.getAccessKey(), config.getSecretKey())
                .build();
    }

    /**
     * 上传文件
     *
     * @param file 表单文件对象
     * @return
     */
    @Override
    public FileMeta uploadFile(MultipartFile file) {
        // 生成文件名(日期文件夹)
        String suffix = FileUtil.getSuffix(file.getOriginalFilename());
        String uuid = IdUtil.simpleUUID();
        String fileName = DateTimeUtil.format(LocalDateTime.now(), "yyyyMMdd") + "/" + uuid + "." + suffix;
        //  try-with-resource 语法糖自动释放流
        try (InputStream inputStream = file.getInputStream()) {
            // 文件上传
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(config.getBucketName())
                    .object(fileName)
                    .contentType(file.getContentType())
                    .stream(inputStream, inputStream.available(), -1)
                    .build();
            client.putObject(putObjectArgs);

            // 返回文件路径
            String fileUrl;
            if (StringUtil.isBlank(config.getCustomDomain())) { // 未配置自定义域名
                GetPresignedObjectUrlArgs getPresignedObjectUrlArgs = GetPresignedObjectUrlArgs.builder()
                        .bucket(config.getBucketName()).object(fileName)
                        .method(Method.GET)
                        .build();

                fileUrl = client.getPresignedObjectUrl(getPresignedObjectUrlArgs);
                fileUrl = fileUrl.substring(0, fileUrl.indexOf("?"));
            } else { // 配置自定义文件路径域名
                fileUrl = config.getCustomDomain() + '/' + config.getBucketName() + "/" + fileName;
            }

            FileMeta fileInfo = new FileMeta();
            fileInfo.setName(fileName);
            fileInfo.setUrl(fileUrl);
            return fileInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw ResultError.SERVER_ERROR.makeException("文件上传失败");
        }
    }


    /**
     * 删除文件
     * @param filePath 文件路径
     */
    @Override
    public void deleteFile(String filePath) {
        Assert.hasText(filePath, "删除文件路径不能为空");
        try {
            String fileName;
            String customDomain = config.getCustomDomain();
            String bucketName = config.getBucketName();
            if (StringUtil.isNotBlank(customDomain)) {
                // https://oss.chdzq.tech/default/20221120/test.jpg → 20221120/test.jpg
                fileName = filePath.substring(customDomain.length() + 1 + bucketName.length() + 1); // 两个/占了2个字符长度
            } else {
                // http://localhost:9000/default/20221120/test.jpg → 20221120/test.jpg
                fileName = filePath.substring(config.getEndpoint().length() + 1 + bucketName.length() + 1);
            }
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .build();

            client.removeObject(removeObjectArgs);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            throw ResultError.SERVER_ERROR.makeException("文件删除失败");
        }
    }


    /**
     * PUBLIC桶策略
     * 如果不配置，则新建的存储桶默认是PRIVATE，则存储桶文件会拒绝访问 Access Denied
     *
     * @param bucketName
     * @return
     */
    private static String publicBucketPolicy(String bucketName) {
        /**
         * AWS的S3存储桶策略
         * Principal: 生效用户对象
         * Resource:  指定存储桶
         * Action: 操作行为
         */

        return "{\"Version\":\"2012-10-17\","
                + "\"Statement\":[{\"Effect\":\"Allow\","
                + "\"Principal\":{\"AWS\":[\"*\"]},"
                + "\"Action\":[\"s3:ListBucketMultipartUploads\",\"s3:GetBucketLocation\",\"s3:ListBucket\"],"
                + "\"Resource\":[\"arn:aws:s3:::" + bucketName + "\"]},"
                + "{\"Effect\":\"Allow\"," + "\"Principal\":{\"AWS\":[\"*\"]},"
                + "\"Action\":[\"s3:ListMultipartUploadParts\",\"s3:PutObject\",\"s3:AbortMultipartUpload\",\"s3:DeleteObject\",\"s3:GetObject\"],"
                + "\"Resource\":[\"arn:aws:s3:::" + bucketName + "/*\"]}]}";
    }

    /**
     * 创建存储桶(存储桶不存在)
     *
     * @param bucketName
     */
    @SneakyThrows
    private void createBucketIfAbsent(String bucketName) {
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(bucketName).build();
        if (!client.bucketExists(bucketExistsArgs)) {
            MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(bucketName).build();

            client.makeBucket(makeBucketArgs);

            // 设置存储桶访问权限为PUBLIC， 如果不配置，则新建的存储桶默认是PRIVATE，则存储桶文件会拒绝访问 Access Denied
            SetBucketPolicyArgs setBucketPolicyArgs = SetBucketPolicyArgs
                    .builder()
                    .bucket(bucketName)
                    .config(publicBucketPolicy(bucketName))
                    .build();
            client.setBucketPolicy(setBucketPolicyArgs);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        createBucketIfAbsent(config.getBucketName());
    }
}
