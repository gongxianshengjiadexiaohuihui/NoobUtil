package com.ggp.pki.pem;

import com.ggp.common.base.AbstractProvider;
import org.bouncycastle.openssl.PEMWriter;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author: ggp
 * @Date: 2020/1/16 13:51
 * @Description:
 * 支持pem 相关操作的对象
 * X509Certificate
 * X509CRL
 * KeyPair
 * PrivateKey
 * PublicKey
 */
public class PemUtil extends AbstractProvider {
    /**
     * 将对象写入文件
     * @param object  见pemUtil-@Description
     * @param path    写入路径
     */
    public static void writeObjectToFile(Object object,String path){
        CharArrayWriter writer = new CharArrayWriter();
        PEMWriter pemWriter = new PEMWriter(writer);
        File file = new File(path);
        file.getParentFile().mkdirs();
        FileOutputStream fileOutputStream = null;
        try {
            pemWriter.writeObject(object);
            pemWriter.close();
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(writer.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("写入文件失败",e);
        }
    }
}