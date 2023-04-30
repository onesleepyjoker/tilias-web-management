package com.example.tiliaswebmanagement.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
@Component
public class AliOSSUtils {
//一个工具类，作用：将文件上传到阿里云服务器当中，
// 然后拼接返回其存放的地址（阿里云的图片地址自动生成是按照一定规律的）
    private String endpoint ="https://oss-cn-beijing.aliyuncs.com";
    private String accessKeyId = "LTAI5t7s9FPK6BHFUbwasVGf";
    private String accessKeySecret = "IIXBOxpuUzligOn0rC942iMkfO5WxM";
    private String bucketName = "web-tlias-fan";

    /**
     * 实现上传图片到OSS
     */
    public String upload(MultipartFile file) throws IOException {
        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传文件到 OSS   oss的核心代码直接从网上拿过来照抄
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);//创建ossclient实例
        ossClient.putObject(bucketName, fileName, inputStream);

        //文件访问路径  返回值路径，在为返回值就行拼接
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }

}
