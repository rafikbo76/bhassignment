package com.cpgm.bh.bhassignment.ws.rest.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.macs.CBCBlockCipherMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.springframework.security.crypto.codec.Base64;


public class CryptoUtil {
	
	public static Random random = new SecureRandom();
	
	public static String SEPARATOR = "$£$";
	
	public static String ALGORITHM = "PBKDF2WithHmacSHA1";
	
	public static int SALT_SIZE = 64;
	public static int TOKEN_SIZE = 64;
	
	public static int PBKDF2_ITERATIONS = 65536;
	public static int HASH_BYTE_SIZE = 512;
	
	
	public static String issueToken(){
		byte[] randToken = generateRandom(TOKEN_SIZE);
		String token = new String(Base64.encode(randToken));
		return token;
	}
	
	public static byte[] generateRandom(int size){
		byte[] result = new byte[size];
		random.nextBytes(result);
		
	    return result;
	}
	
	/*	PBKDF2WithHmacSHA256 encoder of clear Text
	 *  return Base64(Salt)||SEPARATOR||Base64(Hash(Text))
	 */
	public static String encodePassword(String clearPassword, byte[] salt){
		try {
			//generate Salt if NULL
			if(salt==null)
				salt = generateRandom(SALT_SIZE);

			//Compute Hash 
			KeySpec spec = new PBEKeySpec(clearPassword.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
			SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
			byte[] hash = f.generateSecret(spec).getEncoded();
			
			//return salt concatenated to hash
			return new String(Base64.encode(salt))+SEPARATOR+new String(Base64.encode(hash));
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*	check password
	 * Extract salt from encodedText, Encode clearText and compare
	 *  
	 */
	public static boolean checkEncodedPassword(String clearPassword, String encodedPassword){
		try{
			if ((clearPassword==null)||(encodedPassword==null))
				return false;
			
			String saltStr = extractSalt(encodedPassword);
			if (saltStr==null)
				return false;
			
			byte [] salt = Base64.decode(saltStr.getBytes());
			
			String newEncodedText = encodePassword(clearPassword, salt);
			
			return newEncodedText.equals(encodedPassword);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public static String extractSalt(String encodedText){
		if(encodedText==null)
			return null;
		
		return encodedText.substring(0, encodedText.indexOf(SEPARATOR));
	}
	
	public static String encodeToken(String clearToken){
		try{
			// use the SHA256(token) bytes as salt
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] salt = digest.digest(clearToken.getBytes(StandardCharsets.UTF_8));
			
			//Calculate PBKDF2 Hash
			KeySpec spec = new PBEKeySpec(clearToken.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
			SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
			byte[] hash = f.generateSecret(spec).getEncoded();
			
			return new String(Base64.encode(hash));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("unused")
	private static byte[] calculateSignature(byte[] macKey, String transactionData) {
		KeyParameter key = new KeyParameter(macKey);
        BlockCipher cipher = new DESedeEngine();
        Mac mac = new CBCBlockCipherMac(cipher, 64, null); // ALG_DES_MAC8_NOPAD

        mac.init(key);
        mac.update(transactionData.getBytes(), 0, transactionData.getBytes().length);
        byte[]  out = new byte[8];
        mac.doFinal(out, 0);
        
        byte [] signature = new byte[4];
        for (int i=0; i < signature.length; i++)
            signature[i] =  (byte) (out[i+4] ^ out[i]);
        
        return signature;
	}

	@SuppressWarnings("unused")
	private static byte[] calculateMacKey(String fullData) {
		if (fullData==null)
			return null;
		
		//calculate hash256
		MessageDigest digest;
		int keySize = 16;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(fullData.getBytes());
			
			//copy first 16 byte
			byte[] macKey = new byte[keySize];
			System.arraycopy(hash, 0, macKey, 0, keySize);
			//xor with last keySize byte
			for (int i = keySize; i < hash.length; i++) {
				macKey[i-keySize] = (byte) (macKey[i-keySize]^hash[i]);
			}
			
			return macKey;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
