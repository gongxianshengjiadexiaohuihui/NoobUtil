package com.ggp.io.zip;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author: ggp
 * @Date: 2020/3/9 15:53
 * @Description:
 */
public class ZipUtil {
    /**
     * 构造ZIP压缩文件
     *
     * @param files 待压缩文件
     * @return 文件二进制
     * @throws Exception
     */
    public static byte[] buildZip(Map<String, byte[]> files) throws Exception {
        try {
            ByteArrayOutputStream byteArry = new ByteArrayOutputStream();
            ZipOutputStream out = new ZipOutputStream(byteArry);
            ZipEntry entry = null;

            for (Map.Entry<String, byte[]> file : files.entrySet()) {
                entry = new ZipEntry(file.getKey());
                out.putNextEntry(entry);
                out.write(file.getValue());

                out.closeEntry();
            }

            out.close();

            return byteArry.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }
}
