package com.fable.dsp.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * 生成密钥对.
 * 
 * @author  Yi Hong Wei
 */
public final class SysAuthGenKey {

    private SysAuthGenKey() {}

    /**
     * main.
     * 
     * @param args
     *        args
     */
    public static void main(final String[] args) {
        try {
            final BufferedReader in =
                new BufferedReader(new InputStreamReader(System.in));
            //in = 1qazxsw2
            System.out.println("输入密钥种子：");
            final String strPwd = in.readLine();
            if (strPwd == null || strPwd.length() == 0) {
                System.out.println("必须输入一个种子！");
                return;
            }
            System.out.println("输入私钥文件密码：");
            //strFilePwd = 1qazxsw2
            final String strFilePwd = in.readLine();
            if (strFilePwd == null || strFilePwd.length() == 0) {
                System.out.println("必须输入一个私钥文件密码！");
                return;
            }
            SysAuthGenKey.genKey(strPwd, strFilePwd);
        }
        catch (final Exception e) {
            e.printStackTrace();
            System.out.println("生成密钥对失败");
        }
    }

    private static void genKey(final String pwd, final String filePwd)
        throws Exception {
        final KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
        final SecureRandom secrand = new SecureRandom();
        secrand.setSeed(pwd.getBytes());
        keygen.initialize(512, secrand);
        final KeyPair keys = keygen.genKeyPair();
        final PublicKey pubkey = keys.getPublic();
        final PrivateKey prikey = keys.getPrivate();
        OutputStream out =
            new FileOutputStream(new File(SysAuthUtils.SYSAUTH_PATH + SysAuthUtils.PUB_KEY_FILE));
        out.write(pubkey.getEncoded());
        out.close();
        out = new FileOutputStream(new File(SysAuthUtils.SYSAUTH_PATH + SysAuthUtils.PRI_KEY_FILE));
        out.write(SysAuthGenKey.passwordEncrypt(filePwd.toCharArray(),
            prikey.getEncoded()));
        out.close();
        System.out.println("生成密钥对成功");
    }

    private static byte[] passwordEncrypt(final char[] password,
        final byte[] plaintext) throws Exception {
        final byte[] salt = new byte[8];
        final Random random = new Random();
        random.nextBytes(salt);
        final PBEKeySpec keySpec = new PBEKeySpec(password);
        final SecretKeyFactory keyFactory =
            SecretKeyFactory.getInstance(SysAuthUtils.PRI_KEY_ALG);
        final SecretKey key = keyFactory.generateSecret(keySpec);
        final PBEParameterSpec paramSpec =
            new PBEParameterSpec(salt, SysAuthUtils.PBE_ITER_COUNT);
        final Cipher cipher = Cipher.getInstance(SysAuthUtils.PRI_KEY_ALG);
        cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        final byte[] ciphertext = cipher.doFinal(plaintext);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(salt);
        baos.write(ciphertext);
        return baos.toByteArray();
    }
}
