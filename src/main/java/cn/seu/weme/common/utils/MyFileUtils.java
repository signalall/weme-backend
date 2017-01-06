package cn.seu.weme.common.utils;

import org.aspectj.util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by LCN on 2017-1-6.
 */
public class MyFileUtils {

    public static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }


    private static void copyFileUsingApacheCommonsIO(File source, File dest)
            throws IOException {
        FileUtil.copyFile(source, dest);
    }


    public static void copyFileUsingApacheCommonsIO(String source, String dest)
            throws IOException {

        File sourceFile = null;
        File destFile = null;
        sourceFile = new File(source);
        destFile = new File(dest);
        FileUtil.copyFile(sourceFile, destFile);

    }
}
