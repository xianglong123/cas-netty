package com.cas.IO.channel;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/9/25 11:48 上午
 * @desc 遍历目录
 */
public class FilesWalkFileTreeTest {


    public static void main(String[] args) throws IOException {
        AtomicInteger javaCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("/Users/xianglong/IdeaProjects/cas-netty"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".java")) {
                    System.out.println(file);
                    javaCount.incrementAndGet();
                }
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("dir === " + dir);
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("???? == " + dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
        System.out.println("java count: " + javaCount);
    }


}
