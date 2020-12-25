package com.ggp.noob.util.pki.pem;

import com.ggp.noob.util.pki.common.base.AbstractProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.PEMWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

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
  public static   Logger logger  = LoggerFactory.getLogger(PemUtil.class);
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

    /**
     * 解析PEM文件
     * @param path
     * @return
     */
    public static Object readPEM(String path) throws Exception{
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("文件不存在");
        }
        PEMParser parser = new PEMParser(new FileReader(file));
        Object obj = parser.readObject();
        parser.close();
        return obj;
    }
}
