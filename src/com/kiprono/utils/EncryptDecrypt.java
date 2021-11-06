/*Gabriel Kiprono
 * 10/24/2021 
 * 
 * 
 * */

package com.kiprono.utils;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

// helps with decrypting and encrypting password
// using the AES (Advanced Encryption System)
public class EncryptDecrypt {
	static Cipher cipher;
	
	static String result = null;
	static KeyGenerator keyGenerator;
	static SecretKey secretKey;
	
	private static EncryptDecrypt encDnc = new  EncryptDecrypt();;
	
	
	EncryptDecrypt(){
		try {
			cipher = Cipher.getInstance("AES");
			result = null;
			keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			secretKey = keyGenerator.generateKey();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static EncryptDecrypt getEncryptor() {
		return encDnc;
	}
	
	// encrypts the personal details for storing in the database
	public String encrypt(String text) throws Exception {
		
		try {
			byte[] txtByte = text.getBytes();
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] encryptedByte = cipher.doFinal(txtByte);
			Base64.Encoder encoder = Base64.getEncoder();
			result = encoder.encodeToString(encryptedByte);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	// Decrypts personal details for storing in database
	
	public String decrypt(String encryptedText) throws Exception{
		
		try {
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] encryptedByte = decoder.decode(encryptedText);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] decryptedByte = cipher.doFinal(encryptedByte);
			result = new String(decryptedByte);
			
		}catch (Exception e){
			//e.printStackTrace();
		}
		
		return result;
	}
}
