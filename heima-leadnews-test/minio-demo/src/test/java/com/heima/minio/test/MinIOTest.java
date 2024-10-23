package com.heima.minio.test;

import com.heima.file.service.FileStorageService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MinIOTest {


    public static void main(String[] args) {

        // 尝试创建一个FileInputStream对象来读取指定路径的文件
        FileInputStream fileInputStream = null;
        try {
            // 初始化FileInputStream对象，指向需要上传的HTML文件
            fileInputStream = new FileInputStream("D:\\list.html");

            // 1. 创建Minio客户端连接
            // 创建一个MinioClient对象，使用builder模式配置访问凭证和端点
            MinioClient minioClient = MinioClient.builder().credentials("minio", "minio123").endpoint("http://192.168.200.130:9000").build();

            // 2. 上传文件
            // 构建上传对象的参数，包括文件名、内容类型、桶名称和文件流
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object("list.html") // 文件名
                    .contentType("text/html") // 文件类型
                    .bucket("leadnews") // 桶名称，需与Minio中创建的桶名称一致
                    .stream(fileInputStream, fileInputStream.available(), -1) // 文件流
                    .build();

            // 使用Minio客户端上传文件
            minioClient.putObject(putObjectArgs);

            // 打印文件上传后的访问路径
            System.out.println("http://192.168.200.130:9000/leadnews/ak47.jpg");

        } catch (Exception ex) {
            // 捕获并打印异常信息
            ex.printStackTrace();
        }
    }
}