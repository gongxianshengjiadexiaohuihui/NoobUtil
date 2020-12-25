package com.ggp.noob.util.common.bytes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ByteUtil {
    /**
     * long转bytes
     *
     * @param l
     * @return
     */
    public static byte[] long2Bytes(long l) {
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) (l >>> ((7 - i) * 8));
        }
        return bytes;
    }

    /**
     * bytes转int
     * @param bytes
     * @return
     */
    public static int bytes2Int(byte[] bytes){
        if(bytes.length != 4){
            throw new IllegalArgumentException("the size is error");
        }
        int value = 0;
        for (int i = 0; i <4 ; i++) {
            value += (bytes[i]&0xFF)<<((3-i)*8);
        }
        return value;
    }

    /**
     * int转bytes
     * @param i
     * @return
     */
    public static byte[] int2Bytes(int i){
        byte[] bytes = new byte[4];
        for (int j = 0; j <4 ; j++) {
            bytes[3-j] =(byte)(i&0xFF);
            i=i>>>8;
        }
        return bytes;
    }
    /**
     * 循环左移
     * 移出的高位放到该数的低位
     *
     * @param i
     * @param bit
     * @return
     */
    public static int rotateLeft(int i, int bit) {
        return (i >>> (32-bit)) | (i << bit);
    }

    /**
     * 填充0或者去掉前面多余的0
     * @return
     */
    public static byte[] toUnsigned(BigInteger value){
        byte[] bytes = value.toByteArray();
        int start = bytes[0] == 0?1:0;
        return Arrays.copyOfRange(bytes,start,bytes.length);
    }

    /**
     * int数组转bytes
     * @param ints
     * @return
     */
    public static byte[] intArray2bytes(int[] ints){
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            for (int i = 0; i <ints.length ; i++) {
                os.write(int2Bytes(ints[i]));
            }
            return os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 多个字节数组转一个字节数组
     * @param bytes
     * @return
     */
    public static byte[] bytesArray2bytes( byte[]... bytes){
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            for (byte[] byteArray : bytes) {
                os.write(byteArray);
            }
            return os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判读是否全是0
     * @param bytes
     * @return
     */
    public static boolean isAllZero(byte[] bytes){
        for (byte b:bytes){
            if((b&0xFF)!= 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 打印字节数组的二进制或16进制显示
     *
     * @param bytes
     */
    public static void print(byte[] bytes,int radix) {
        Map<Integer, String> cache = new HashMap<>();
        char[] chars;
        if(radix == 2){
            chars = new char[8];
        }else{
            chars = new char[2];
        }
        int b;
        for (int i = 0; i < bytes.length; i++) {
            if (i % 8 == 0) {
                System.out.println("\n");
            }
            b = bytes[i];
            int k = bytes[i];
            /**
             * 先判断缓存里有没有
             */
            if (null != cache.get(Integer.valueOf(b))) {
                System.out.print(cache.get(Integer.valueOf(b)));
                System.out.print(" ");
                continue;
            }
            /**
             * 缓存里没有就拼装，暂时只支持2进制和16进制
             */
            if(radix == 2){
                for (int j = 0; j <8 ; j++) {
                    // + 优先级大于 &
                    chars[7-j] = (char) ((b&1)+'0');
                    b=b>>>1;
                }
            }else {
                chars[1] = (char)((b&15)+'0');
                chars[1]= chars[1]> '9'?(char)(chars[1]+7):chars[1];
                b=b>>>4;
                chars[0] = (char)((b&15)+'0');
                chars[0]= chars[0]> '9'?(char)(chars[0]+7):chars[0];
            }
            cache.put(Integer.valueOf(k),new String(chars));
            System.out.print(new String(chars));
            System.out.print(" ");
        }
    }
}
