/*
package RSA;

import javax.crypto.Cipher;
import javax.servlet.http.HttpSession;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

public class RSAJS {
    // 密钥大小
    public static final int KEYSIZE = 1024;
    // 算法名称
    public static final String RSA_ALGORITHM = "RSA";
    //公钥名称
    public static final String PUBLIC_KEY = "PUBLIC_KEY";
    //私钥名称，session中存储的私钥key
    public static final String PRIVATE_KEY = "PRIVATE_KEY";

    public static Map<String, Object> createKey() {
        try {
            //算法名称
            KeyPairGenerator keypairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM, new org.bouncycastle.jce.provider.BouncyCastleProvider());
            SecureRandom random = new SecureRandom();
            //秘钥大小
            keypairGenerator.initialize(KEYSIZE, random);
            //生成密钥对
            KeyPair keyPair = keypairGenerator.generateKeyPair();
            //得到公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            //得到秘钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            //将公钥私钥存入map中并返回
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(PUBLIC_KEY, publicKey);
            map.put(PRIVATE_KEY, privateKey);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    private static byte[] privateDecrypt(Key privateKey, byte[] encoData)
            throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM,
                new org.bouncycastle.jce.provider.BouncyCastleProvider());
// 设置为解密模式，用私钥解密
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
// 解密
        byte[] data = cipher.doFinal(encoData);
        return data;
    }


    public static String sessionDecryptHexStr(HttpSession session, String param)
            throws Exception {
// 根据模和私钥指数获取私钥
        RSAPrivateKey prkey = (RSAPrivateKey) session.getAttribute(PRIVATE_KEY);
        return privateDecryptHexStr(prkey, param);
    }


    private static String privateDecryptHexStr(Key prkey, String param)
            throws Exception {
        byte[] endata = parseHexStr2Byte(param);//将16进制转2进制
        byte[] data = privateDecrypt(prkey, endata);//使用私钥解密
        return new String(data);
    }

}
*/
