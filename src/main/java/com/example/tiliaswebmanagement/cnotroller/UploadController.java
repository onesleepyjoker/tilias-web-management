package com.example.tiliaswebmanagement.cnotroller;

import com.example.tiliaswebmanagement.pojo.Result;
import com.example.tiliaswebmanagement.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {


    //本地存储代码
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传：{},{},{}",username,age,image);
//        //1.获取文件的原始名字后缀
//        String originalFilename = image.getOriginalFilename();
//        int lastIndexOf = originalFilename.lastIndexOf(".");
//        String extname = originalFilename.substring(lastIndexOf);
//        //2.拼接uuid和文件名后缀存储
//        String uuid = UUID.randomUUID().toString();
//        String newfilename=uuid+extname;
////        3.文件存储
//        image.transferTo(new File("D:\\java\\" + newfilename));
//
//
//        image.getSize();//获取文件大小  字节
//        image.getBytes();//获取文件内容的字节数组
//        image.transferTo(new File(""));//将文件存储到磁盘当中
//        image.getInputStream(); //获取接收到文件的输入流
//        return Result.success();
//    }


    //阿里云存储代码
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传。文件名：{}",image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("文件上传成功，url：{}",url);
        return Result.success(url);
    }
}
