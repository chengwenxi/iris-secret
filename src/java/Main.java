import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import ecdsa.ECCEncrypt;
import rsa.RSAEncrypt;
import utils.FileUtil;

import java.util.Map;

/**
 * Created by vincent on 2017/9/20.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        eccTest();

    }

    public static void rsaTest() throws Exception {
        String privateKey = FileUtil.loadFile("src/java/rsa/privateKey.keystore");
        String publicKey = FileUtil.loadFile("src/java/rsa/publicKey.keystore");
        String message = FileUtil.loadFile("message");
        byte[] cipherData = RSAEncrypt.encrypt(publicKey, message);
        String encode = Base64.encode(cipherData);
        byte[] res = RSAEncrypt.decrypt(privateKey, Base64.decode(encode));
        String restr = new String(res);

        System.out.println("原文：" + message);
        System.out.println("加密：" + encode);
        System.out.println("解密：" + restr);
    }

    public static void eccTest() throws Exception {
//        String privateKey = FileUtil.loadFile("src/java/ecdsa/privateKey.keystore");
//        String publicKey = FileUtil.loadFile("src/java/ecdsa/publicKey.keystore");
//        String message = FileUtil.loadFile("message");
//        byte[] data = message.getBytes();
//
//        byte[] encodedData = ECCEncrypt.encrypt(data, publicKey);
//
//        String encode = new String(encodedData);
//
//        byte[] decodedData = ECCEncrypt.decrypt(encodedData, privateKey);
//        String restr = new String(decodedData);
//
//        System.out.println("原文：" + message);
//        System.out.println("加密：" + encode);
//        System.out.println("解密：" + restr);

        String inputStr = "abc";
        byte[] data = inputStr.getBytes();

        Map<String, Object> keyMap = ECCEncrypt.initKey();

        String publicKey = ECCEncrypt.getPublicKey(keyMap);
        String privateKey = ECCEncrypt.getPrivateKey(keyMap);
        System.err.println("公钥: \n" + publicKey);
        System.err.println("私钥： \n" + privateKey);

        byte[] encodedData = ECCEncrypt.encrypt(data, publicKey);

        byte[] decodedData = ECCEncrypt.decrypt(encodedData, privateKey);

        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
    }
}
