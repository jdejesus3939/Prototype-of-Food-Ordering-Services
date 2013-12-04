package com.jproject.foodorderingapps.library;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashAlgorithm {

	private String SHA1 = "SHA-1";

	private String convertToHex(byte[] data) { 
	    StringBuffer buf = new StringBuffer();
	    int length = data.length;
	    for(int i = 0; i < length; ++i) { 
	        int halfbyte = (data[i] >>> 4) & 0x0F;
	        int two_halfs = 0;
	        do { 
	            if((0 <= halfbyte) && (halfbyte <= 9)) 
	                buf.append((char) ('0' + halfbyte));
	            else 
	                buf.append((char) ('a' + (halfbyte - 10)));
	            halfbyte = data[i] & 0x0F;
	        }
	        while(two_halfs++ < 1);
	    } 
	    return buf.toString();
	}

	public String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException  { 
	    MessageDigest md = MessageDigest.getInstance(SHA1);
	    md.update(text.getBytes("iso-8859-1"), 0, text.length());
	    byte[] sha1hash = md.digest();
	    return convertToHex(sha1hash);
	} 

/*
 * The same algorithm but different format conversion
	public String sha1Hash(String toHash)
	{
	    String hash = null;
	    try
	    {
	        MessageDigest digest = MessageDigest.getInstance(SHA1);
	        byte[] bytes = toHash.getBytes("UTF-8");
	        digest.update(bytes, 0, bytes.length);
	        bytes = digest.digest();
	        StringBuilder sb = new StringBuilder();
	        for( byte b : bytes )
	        {
	            sb.append( String.format("%02X", b) );
	        }
	        hash = sb.toString();
	    }
	    catch( NoSuchAlgorithmException e )
	    {
	        e.printStackTrace();
	    }
	    catch( UnsupportedEncodingException e )
	    {
	        e.printStackTrace();
	    }
	    return hash;
	}
*/

}
