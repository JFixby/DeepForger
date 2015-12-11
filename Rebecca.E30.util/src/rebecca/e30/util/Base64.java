package rebecca.e30.util;

//
////Copyright 2003-2009 Christian d'Heureuse, Inventec Informatik AG, Zurich, Switzerland
////www.source-code.biz, www.inventec.ch/chdh
////
////This module is multi-licensed and may be used under the terms
////of any of the following licenses:
////
////EPL, Eclipse Public License, http://www.eclipse.org/legal
////LGPL, GNU Lesser General Public License, http://www.gnu.org/licenses/lgpl.html
////AL, Apache License, http://www.apache.org/licenses
////
////Please contact the author if you need another license.
////This module is provided "as is", without warranties of any kind.
//
///**
// * A Base64 Encoder/Decoder.
// * 
// * <p>
// * This class is used to encode and decode data in Base64 format as described in
// * RFC 1521.
// * 
// * <p>
// * Home page: <a href="http://www.source-code.biz">www.source-code.biz</a><br>
// * Author: Christian d'Heureuse, Inventec Informatik AG, Zurich, Switzerland<br>
// * Multi-licensed: EPL/LGPL/AL.
// * 
// * <p>
// * Version history:<br>
// * 2003-07-22 Christian d'Heureuse (chdh): Module created.<br>
// * 2005-08-11 chdh: Lincense changed from GPL to LGPL.<br>
// * 2006-11-21 chdh:<br>
// * &nbsp; Method encode(String) renamed to encodeString(String).<br>
// * &nbsp; Method decode(String) renamed to decodeString(String).<br>
// * &nbsp; New method encode(byte[],int) added.<br>
// * &nbsp; New method decode(String) added.<br>
// * 2009-07-16: Additional licenses (EPL/AL) added.<br>
// */
//
//public class Base64 {
//
//	// Mapping table from 6-bit nibbles to Base64 characters.
//	private static char[] map1 = new char[64];
//	static {
//		int i = 0;
//		for (char c = 'A'; c <= 'Z'; c++)
//			map1[i++] = c;
//		for (char c = 'a'; c <= 'z'; c++)
//			map1[i++] = c;
//		for (char c = '0'; c <= '9'; c++)
//			map1[i++] = c;
//		map1[i++] = '+';
//		map1[i++] = '/';
//	}
//
//	// Mapping table from Base64 characters to 6-bit nibbles.
//	private static byte[] map2 = new byte[128];
//	static {
//		for (int i = 0; i < map2.length; i++)
//			map2[i] = -1;
//		for (int i = 0; i < 64; i++)
//			map2[map1[i]] = (byte) i;
//	}
//
//	/**
//	 * Encodes a string into Base64 format. No blanks or line breaks are
//	 * inserted.
//	 * 
//	 * @param s
//	 *            a String to be encoded.
//	 * @return A String with the Base64 encoded data.
//	 */
//	public static String encodeString(String s) {
//		return new String(encode(s.getBytes()));
//	}
//
//	/**
//	 * Encodes a byte array into Base64 format. No blanks or line breaks are
//	 * inserted.
//	 * 
//	 * @param in
//	 *            an array containing the data bytes to be encoded.
//	 * @return A character array with the Base64 encoded data.
//	 */
//	public static char[] encode(byte[] in) {
//		return encode(in, in.length);
//	}
//
//	/**
//	 * Encodes a byte array into Base64 format. No blanks or line breaks are
//	 * inserted.
//	 * 
//	 * @param in
//	 *            an array containing the data bytes to be encoded.
//	 * @param iLen
//	 *            number of bytes to process in <code>in</code>.
//	 * @return A character array with the Base64 encoded data.
//	 */
//	public static char[] encode(byte[] in, int iLen) {
//		int oDataLen = (iLen * 4 + 2) / 3; // output length without padding
//		int oLen = ((iLen + 2) / 3) * 4; // output length including padding
//		char[] out = new char[oLen];
//		int ip = 0;
//		int op = 0;
//		while (ip < iLen) {
//			int i0 = in[ip++] & 0xff;
//			int i1 = ip < iLen ? in[ip++] & 0xff : 0;
//			int i2 = ip < iLen ? in[ip++] & 0xff : 0;
//			int o0 = i0 >>> 2;
//			int o1 = ((i0 & 3) << 4) | (i1 >>> 4);
//			int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
//			int o3 = i2 & 0x3F;
//			out[op++] = map1[o0];
//			out[op++] = map1[o1];
//			out[op] = op < oDataLen ? map1[o2] : '=';
//			op++;
//			out[op] = op < oDataLen ? map1[o3] : '=';
//			op++;
//		}
//		return out;
//	}
//
//	/**
//	 * Decodes a string from Base64 format.
//	 * 
//	 * @param s
//	 *            a Base64 String to be decoded.
//	 * @return A String containing the decoded data.
//	 * @throws IllegalArgumentException
//	 *             if the input is not valid Base64 encoded data.
//	 */
//	public static String decodeString(String s) {
//		return new String(decode(s));
//	}
//
//	/**
//	 * Decodes a byte array from Base64 format.
//	 * 
//	 * @param s
//	 *            a Base64 String to be decoded.
//	 * @return An array containing the decoded data bytes.
//	 * @throws IllegalArgumentException
//	 *             if the input is not valid Base64 encoded data.
//	 */
//	public static byte[] decode(String s) {
//		return decode(s.toCharArray());
//	}
//
//	/**
//	 * Decodes a byte array from Base64 format. No blanks or line breaks are
//	 * allowed within the Base64 encoded data.
//	 * 
//	 * @param in
//	 *            a character array containing the Base64 encoded data.
//	 * @return An array containing the decoded data bytes.
//	 * @throws IllegalArgumentException
//	 *             if the input is not valid Base64 encoded data.
//	 */
//	public static byte[] decode(char[] in) {
//		int iLen = in.length;
//		if (iLen % 4 != 0)
//			throw new IllegalArgumentException(
//					"Length of Base64 encoded input string is not a multiple of 4.");
//		while (iLen > 0 && in[iLen - 1] == '=')
//			iLen--;
//		int oLen = (iLen * 3) / 4;
//		byte[] out = new byte[oLen];
//		int ip = 0;
//		int op = 0;
//		while (ip < iLen) {
//			int i0 = in[ip++];
//			int i1 = in[ip++];
//			int i2 = ip < iLen ? in[ip++] : 'A';
//			int i3 = ip < iLen ? in[ip++] : 'A';
//			if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127)
//				throw new IllegalArgumentException(
//						"Illegal character in Base64 encoded data.");
//			int b0 = map2[i0];
//			int b1 = map2[i1];
//			int b2 = map2[i2];
//			int b3 = map2[i3];
//			if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0)
//				throw new IllegalArgumentException(
//						"Illegal character in Base64 encoded data.");
//			int o0 = (b0 << 2) | (b1 >>> 4);
//			int o1 = ((b1 & 0xf) << 4) | (b2 >>> 2);
//			int o2 = ((b2 & 3) << 6) | b3;
//			out[op++] = (byte) o0;
//			if (op < oLen)
//				out[op++] = (byte) o1;
//			if (op < oLen)
//				out[op++] = (byte) o2;
//		}
//		return out;
//	}
//
//	// Dummy constructor.
//	private Base64() {
//		
//	}
//
//} // end class Base64Coder

import java.util.Arrays;

/**
 * A very fast and memory efficient class to encode and decode to and from
 * BASE64 in full accordance with RFC 2045.<br>
 * <br>
 * On Windows XP sp1 with 1.4.2_04 and later ;), this encoder and decoder is
 * about 10 times faster on small arrays (10 - 1000 bytes) and 2-3 times as fast
 * on larger arrays (10000 - 1000000 bytes) compared to
 * <code>sun.misc.Encoder()/Decoder()</code>.<br>
 * <br>
 * 
 * On byte arrays the encoder is about 20% faster than Jakarta Commons Base64
 * Codec for encode and about 50% faster for decoding large arrays. This
 * implementation is about twice as fast on very small arrays (&lt 30 bytes). If
 * source/destination is a <code>String</code> this version is about three times
 * as fast due to the fact that the Commons Codec result has to be recoded to a
 * <code>String</code> from <code>byte[]</code>, which is very expensive.<br>
 * <br>
 * 
 * This encode/decode algorithm doesn't create any temporary arrays as many
 * other codecs do, it only allocates the resulting array. This produces less
 * garbage and it is possible to handle arrays twice as large as algorithms that
 * create a temporary array. (E.g. Jakarta Commons Codec). It is unknown whether
 * Sun's <code>sun.misc.Encoder()/Decoder()</code> produce temporary arrays but
 * since performance is quite low it probably does.<br>
 * <br>
 * 
 * The encoder produces the same output as the Sun one except that the Sun's
 * encoder appends a trailing line separator if the last character isn't a pad.
 * Unclear why but it only adds to the length and is probably a side effect.
 * Both are in conformance with RFC 2045 though.<br>
 * Commons codec seem to always att a trailing line separator.<br>
 * <br>
 * 
 * <b>Note!</b> The encode/decode method pairs (types) come in three versions
 * with the <b>exact</b> same algorithm and thus a lot of code redundancy. This
 * is to not create any temporary arrays for transcoding to/from different
 * format types. The methods not used can simply be commented out.<br>
 * <br>
 * 
 * There is also a "fast" version of all decode methods that works the same way
 * as the normal ones, but har a few demands on the decoded input. Normally
 * though, these fast verions should be used if the source if the input is known
 * and it hasn't bee tampered with.<br>
 * <br>
 * 
 * If you find the code useful or you find a bug, please send me a note at
 * base64 @ miginfocom . com.
 * 
 * Licence (BSD): ==============
 * 
 * Copyright (c) 2004, Mikael Grev, MiG InfoCom AB. (base64 @ miginfocom . com)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. Neither the name of the MiG InfoCom AB nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * @version 2.2
 * @author Mikael Grev Date: 2004-aug-02 Time: 11:31:11
 */

public class Base64 {
	private static final char[] CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
			.toCharArray();
	private static final int[] IA = new int[256];
	static {
		Arrays.fill(IA, -1);
		for (int i = 0, iS = CA.length; i < iS; i++)
			IA[CA[i]] = i;
		IA['='] = 0;
	}

	//

	// * char[] version
	//

	/**
	 * Encodes a raw byte array into a BASE64 <code>char[]</code> representation
	 * i accordance with RFC 2045.
	 * 
	 * @param sArr
	 *            The bytes to convert. If <code>null</code> or length 0 an
	 *            empty array will be returned.
	 * @param lineSep
	 *            Optional "\r\n" after 76 characters, unless end of file.<br>
	 *            No line separator will be in breach of RFC 2045 which
	 *            specifies max 76 per line but will be a little faster.
	 * @return A BASE64 encoded array. Never <code>null</code>.
	 */
	public final static char[] encodeToChar(byte[] sArr, boolean lineSep) {
		// Check special case
		int sLen = sArr != null ? sArr.length : 0;
		if (sLen == 0)
			return new char[0];

		int eLen = (sLen / 3) * 3; // Length of even 24-bits.
		int cCnt = ((sLen - 1) / 3 + 1) << 2; // Returned character count
		int dLen = cCnt + (lineSep ? (cCnt - 1) / 76 << 1 : 0); // Length of
																// returned

		char[] dArr = new char[dLen];

		// Encode even 24-bits
		for (int s = 0, d = 0, cc = 0; s < eLen;) {
			// Copy next three bytes into lower 24 bits of int, paying attension
			// to sign.
			int i = (sArr[s++] & 0xff) << 16 | (sArr[s++] & 0xff) << 8
					| (sArr[s++] & 0xff);

			// Encode the int into four chars
			dArr[d++] = CA[(i >>> 18) & 0x3f];
			dArr[d++] = CA[(i >>> 12) & 0x3f];
			dArr[d++] = CA[(i >>> 6) & 0x3f];
			dArr[d++] = CA[i & 0x3f];

			// Add optional line separator
			if (lineSep && ++cc == 19 && d < dLen - 2) {
				dArr[d++] = '\r';
				dArr[d++] = '\n';
				cc = 0;
			}
		}

		// Pad and encode last bits if source isn't even 24 bits.
		int left = sLen - eLen; // 0 - 2.
		if (left > 0) {
			// Prepare the int
			int i = ((sArr[eLen] & 0xff) << 10)
					| (left == 2 ? ((sArr[sLen - 1] & 0xff) << 2) : 0);

			// Set last four chars
			dArr[dLen - 4] = CA[i >> 12];
			dArr[dLen - 3] = CA[(i >>> 6) & 0x3f];
			dArr[dLen - 2] = left == 2 ? CA[i & 0x3f] : '=';
			dArr[dLen - 1] = '=';
		}
		return dArr;
	}

	/**
	 * Decodes a BASE64 encoded char array. All illegal characters will be
	 * ignored and can handle both arrays with and without line separators.
	 * 
	 * @param sArr
	 *            The source array. <code>null</code> or length 0 will return an
	 *            empty array.
	 * @return The decoded array of bytes. May be of length 0. Will be
	 *         <code>null</code> if the legal characters (including '=') isn't
	 *         divideable by 4. (I.e. definitely corrupted).
	 */
	public final static byte[] decode(char[] sArr) {
		// Check special case
		int sLen = sArr != null ? sArr.length : 0;
		if (sLen == 0)
			return new byte[0];

		// Count illegal characters (including '\r', '\n') to know what size the

		// so we don't have to reallocate & copy it later.
		int sepCnt = 0; // Number of separator characters. (Actually illegal

		for (int i = 0; i < sLen; i++)
			// If input is "pure" (I.e. no line separators

			if (IA[sArr[i]] < 0)
				sepCnt++;

		// Check so that legal chars (including '=') are evenly divideable by 4
		// as

		if ((sLen - sepCnt) % 4 != 0)
			return null;

		int pad = 0;
		for (int i = sLen; i > 1 && IA[sArr[--i]] <= 0;)
			if (sArr[i] == '=')
				pad++;

		int len = ((sLen - sepCnt) * 6 >> 3) - pad;

		byte[] dArr = new byte[len]; // Preallocate byte[] of exact length

		for (int s = 0, d = 0; d < len;) {
			// Assemble three bytes into an int from four "valid" characters.
			int i = 0;
			for (int j = 0; j < 4; j++) { // j only increased if a valid char
											// was found.
				int c = IA[sArr[s++]];
				if (c >= 0)
					i |= c << (18 - j * 6);
				else
					j--;
			}
			// Add the bytes
			dArr[d++] = (byte) (i >> 16);
			if (d < len) {
				dArr[d++] = (byte) (i >> 8);
				if (d < len)
					dArr[d++] = (byte) i;
			}
		}
		return dArr;
	}

	/**
	 * Decodes a BASE64 encoded char array that is known to be resonably well
	 * formatted. The method is about twice as fast as {@link #decode(char[])}.
	 * The preconditions are:<br>
	 * + The array must have a line length of 76 chars OR no line separators at
	 * all (one line).<br>
	 * + Line separator must be "\r\n", as specified in RFC 2045 + The array
	 * must not contain illegal characters within the encoded string<br>
	 * + The array CAN have illegal characters at the beginning and end, those
	 * will be dealt with appropriately.<br>
	 * 
	 * @param sArr
	 *            The source array. Length 0 will return an empty array.
	 *            <code>null</code> will throw an exception.
	 * @return The decoded array of bytes. May be of length 0.
	 */
	public final static byte[] decodeFast(char[] sArr) {
		// Check special case
		int sLen = sArr.length;
		if (sLen == 0)
			return new byte[0];

		int sIx = 0, eIx = sLen - 1; // Start and end index after trimming.

		// Trim illegal chars from start
		while (sIx < eIx && IA[sArr[sIx]] < 0)
			sIx++;

		// Trim illegal chars from end
		while (eIx > 0 && IA[sArr[eIx]] < 0)
			eIx--;

		// get the padding count (=) (0, 1 or 2)
		int pad = sArr[eIx] == '=' ? (sArr[eIx - 1] == '=' ? 2 : 1) : 0; // Count
																			// '='

		int cCnt = eIx - sIx + 1; // Content count including possible separators
		int sepCnt = sLen > 76 ? (sArr[76] == '\r' ? cCnt / 78 : 0) << 1 : 0;

		int len = ((cCnt - sepCnt) * 6 >> 3) - pad; // The number of decoded
													// bytes
		byte[] dArr = new byte[len]; // Preallocate byte[] of exact length

		// Decode all but the last 0 - 2 bytes.
		int d = 0;
		for (int cc = 0, eLen = (len / 3) * 3; d < eLen;) {
			// Assemble three bytes into an int from four "valid" characters.
			int i = IA[sArr[sIx++]] << 18 | IA[sArr[sIx++]] << 12
					| IA[sArr[sIx++]] << 6 | IA[sArr[sIx++]];

			// Add the bytes
			dArr[d++] = (byte) (i >> 16);
			dArr[d++] = (byte) (i >> 8);
			dArr[d++] = (byte) i;

			// If line separator, jump over it.
			if (sepCnt > 0 && ++cc == 19) {
				sIx += 2;
				cc = 0;
			}
		}

		if (d < len) {
			// Decode last 1-3 bytes (incl '=') into 1-3 bytes
			int i = 0;
			for (int j = 0; sIx <= eIx - pad; j++)
				i |= IA[sArr[sIx++]] << (18 - j * 6);

			for (int r = 16; d < len; r -= 8)
				dArr[d++] = (byte) (i >> r);
		}

		return dArr;
	}

	//

	// * byte[] version
	//

	/**
	 * Encodes a raw byte array into a BASE64 <code>byte[]</code> representation
	 * i accordance with RFC 2045.
	 * 
	 * @param sArr
	 *            The bytes to convert. If <code>null</code> or length 0 an
	 *            empty array will be returned.
	 * @param lineSep
	 *            Optional "\r\n" after 76 characters, unless end of file.<br>
	 *            No line separator will be in breach of RFC 2045 which
	 *            specifies max 76 per line but will be a little faster.
	 * @return A BASE64 encoded array. Never <code>null</code>.
	 */
	public final static byte[] encodeToByte(byte[] sArr, boolean lineSep) {
		// Check special case
		int sLen = sArr != null ? sArr.length : 0;
		if (sLen == 0)
			return new byte[0];

		int eLen = (sLen / 3) * 3; // Length of even 24-bits.
		int cCnt = ((sLen - 1) / 3 + 1) << 2; // Returned character count
		int dLen = cCnt + (lineSep ? (cCnt - 1) / 76 << 1 : 0); // Length of
																// returned

		byte[] dArr = new byte[dLen];

		// Encode even 24-bits
		for (int s = 0, d = 0, cc = 0; s < eLen;) {
			// Copy next three bytes into lower 24 bits of int, paying attension
			// to sign.
			int i = (sArr[s++] & 0xff) << 16 | (sArr[s++] & 0xff) << 8
					| (sArr[s++] & 0xff);

			// Encode the int into four chars
			dArr[d++] = (byte) CA[(i >>> 18) & 0x3f];
			dArr[d++] = (byte) CA[(i >>> 12) & 0x3f];
			dArr[d++] = (byte) CA[(i >>> 6) & 0x3f];
			dArr[d++] = (byte) CA[i & 0x3f];

			// Add optional line separator
			if (lineSep && ++cc == 19 && d < dLen - 2) {
				dArr[d++] = '\r';
				dArr[d++] = '\n';
				cc = 0;
			}
		}

		// Pad and encode last bits if source isn't an even 24 bits.
		int left = sLen - eLen; // 0 - 2.
		if (left > 0) {
			// Prepare the int
			int i = ((sArr[eLen] & 0xff) << 10)
					| (left == 2 ? ((sArr[sLen - 1] & 0xff) << 2) : 0);

			// Set last four chars
			dArr[dLen - 4] = (byte) CA[i >> 12];
			dArr[dLen - 3] = (byte) CA[(i >>> 6) & 0x3f];
			dArr[dLen - 2] = left == 2 ? (byte) CA[i & 0x3f] : (byte) '=';
			dArr[dLen - 1] = '=';
		}
		return dArr;
	}

	/**
	 * Decodes a BASE64 encoded byte array. All illegal characters will be
	 * ignored and can handle both arrays with and without line separators.
	 * 
	 * @param sArr
	 *            The source array. Length 0 will return an empty array.
	 *            <code>null</code> will throw an exception.
	 * @return The decoded array of bytes. May be of length 0. Will be
	 *         <code>null</code> if the legal characters (including '=') isn't
	 *         divideable by 4. (I.e. definitely corrupted).
	 */
	public final static byte[] decode(byte[] sArr) {
		// Check special case
		int sLen = sArr.length;

		// Count illegal characters (including '\r', '\n') to know what size the

		// so we don't have to reallocate & copy it later.
		int sepCnt = 0; // Number of separator characters. (Actually illegal

		for (int i = 0; i < sLen; i++)
			// If input is "pure" (I.e. no line separators

			if (IA[sArr[i] & 0xff] < 0)
				sepCnt++;

		// Check so that legal chars (including '=') are evenly divideable by 4
		// as

		if ((sLen - sepCnt) % 4 != 0)
			return null;

		int pad = 0;
		for (int i = sLen; i > 1 && IA[sArr[--i] & 0xff] <= 0;)
			if (sArr[i] == '=')
				pad++;

		int len = ((sLen - sepCnt) * 6 >> 3) - pad;

		byte[] dArr = new byte[len]; // Preallocate byte[] of exact length

		for (int s = 0, d = 0; d < len;) {
			// Assemble three bytes into an int from four "valid" characters.
			int i = 0;
			for (int j = 0; j < 4; j++) { // j only increased if a valid char
											// was found.
				int c = IA[sArr[s++] & 0xff];
				if (c >= 0)
					i |= c << (18 - j * 6);
				else
					j--;
			}

			// Add the bytes
			dArr[d++] = (byte) (i >> 16);
			if (d < len) {
				dArr[d++] = (byte) (i >> 8);
				if (d < len)
					dArr[d++] = (byte) i;
			}
		}

		return dArr;
	}

	/**
	 * Decodes a BASE64 encoded byte array that is known to be resonably well
	 * formatted. The method is about twice as fast as {@link #decode(byte[])}.
	 * The preconditions are:<br>
	 * + The array must have a line length of 76 chars OR no line separators at
	 * all (one line).<br>
	 * + Line separator must be "\r\n", as specified in RFC 2045 + The array
	 * must not contain illegal characters within the encoded string<br>
	 * + The array CAN have illegal characters at the beginning and end, those
	 * will be dealt with appropriately.<br>
	 * 
	 * @param sArr
	 *            The source array. Length 0 will return an empty array.
	 *            <code>null</code> will throw an exception.
	 * @return The decoded array of bytes. May be of length 0.
	 */
	public final static byte[] decodeFast(byte[] sArr) {
		// Check special case
		int sLen = sArr.length;
		if (sLen == 0)
			return new byte[0];

		int sIx = 0, eIx = sLen - 1; // Start and end index after trimming.

		// Trim illegal chars from start
		while (sIx < eIx && IA[sArr[sIx] & 0xff] < 0)
			sIx++;

		// Trim illegal chars from end
		while (eIx > 0 && IA[sArr[eIx] & 0xff] < 0)
			eIx--;

		// get the padding count (=) (0, 1 or 2)
		int pad = sArr[eIx] == '=' ? (sArr[eIx - 1] == '=' ? 2 : 1) : 0; // Count
																			// '='

		int cCnt = eIx - sIx + 1; // Content count including possible separators
		int sepCnt = sLen > 76 ? (sArr[76] == '\r' ? cCnt / 78 : 0) << 1 : 0;

		int len = ((cCnt - sepCnt) * 6 >> 3) - pad; // The number of decoded
													// bytes
		byte[] dArr = new byte[len]; // Preallocate byte[] of exact length

		// Decode all but the last 0 - 2 bytes.
		int d = 0;
		for (int cc = 0, eLen = (len / 3) * 3; d < eLen;) {
			// Assemble three bytes into an int from four "valid" characters.
			int i = IA[sArr[sIx++]] << 18 | IA[sArr[sIx++]] << 12
					| IA[sArr[sIx++]] << 6 | IA[sArr[sIx++]];

			// Add the bytes
			dArr[d++] = (byte) (i >> 16);
			dArr[d++] = (byte) (i >> 8);
			dArr[d++] = (byte) i;

			// If line separator, jump over it.
			if (sepCnt > 0 && ++cc == 19) {
				sIx += 2;
				cc = 0;
			}
		}

		if (d < len) {
			// Decode last 1-3 bytes (incl '=') into 1-3 bytes
			int i = 0;
			for (int j = 0; sIx <= eIx - pad; j++)
				i |= IA[sArr[sIx++]] << (18 - j * 6);

			for (int r = 16; d < len; r -= 8)
				dArr[d++] = (byte) (i >> r);
		}

		return dArr;
	}

	//

	// * String version
	//

	/**
	 * Encodes a raw byte array into a BASE64 <code>String</code> representation
	 * i accordance with RFC 2045.
	 * 
	 * @param sArr
	 *            The bytes to convert. If <code>null</code> or length 0 an
	 *            empty array will be returned.
	 * @param lineSep
	 *            Optional "\r\n" after 76 characters, unless end of file.<br>
	 *            No line separator will be in breach of RFC 2045 which
	 *            specifies max 76 per line but will be a little faster.
	 * @return A BASE64 encoded array. Never <code>null</code>.
	 */
	public final static String encodeToString(byte[] sArr, boolean lineSep) {
		// Reuse char[] since we can't create a String incrementally anyway and

		return new String(encodeToChar(sArr, lineSep));

	}

	/**
	 * Decodes a BASE64 encoded <code>String</code>. All illegal characters will
	 * be ignored and can handle both strings with and without line separators.<br>
	 * <b>Note!</b> It can be up to about 2x the speed to call
	 * <code>decode(str.toCharArray())</code> instead. That will create a
	 * temporary array though. This version will use <code>str.charAt(i)</code>
	 * to iterate the string.
	 * 
	 * @param str
	 *            The source string. <code>null</code> or length 0 will return
	 *            an empty array.
	 * @return The decoded array of bytes. May be of length 0. Will be
	 *         <code>null</code> if the legal characters (including '=') isn't
	 *         divideable by 4. (I.e. definitely corrupted).
	 */
	public final static byte[] decode(String str) {
		// Check special case
		int sLen = str != null ? str.length() : 0;
		if (sLen == 0)
			return new byte[0];

		// Count illegal characters (including '\r', '\n') to know what size the

		// so we don't have to reallocate & copy it later.
		int sepCnt = 0; // Number of separator characters. (Actually illegal

		for (int i = 0; i < sLen; i++)
			// If input is "pure" (I.e. no line separators

			if (IA[str.charAt(i)] < 0)
				sepCnt++;

		// Check so that legal chars (including '=') are evenly divideable by 4
		// as

		if ((sLen - sepCnt) % 4 != 0)
			return null;

		// Count '=' at end
		int pad = 0;
		for (int i = sLen; i > 1 && IA[str.charAt(--i)] <= 0;)
			if (str.charAt(i) == '=')
				pad++;

		int len = ((sLen - sepCnt) * 6 >> 3) - pad;

		byte[] dArr = new byte[len]; // Preallocate byte[] of exact length

		for (int s = 0, d = 0; d < len;) {
			// Assemble three bytes into an int from four "valid" characters.
			int i = 0;
			for (int j = 0; j < 4; j++) { // j only increased if a valid char
											// was found.
				int c = IA[str.charAt(s++)];
				if (c >= 0)
					i |= c << (18 - j * 6);
				else
					j--;
			}
			// Add the bytes
			dArr[d++] = (byte) (i >> 16);
			if (d < len) {
				dArr[d++] = (byte) (i >> 8);
				if (d < len)
					dArr[d++] = (byte) i;
			}
		}
		return dArr;
	}

	/**
	 * Decodes a BASE64 encoded string that is known to be resonably well
	 * formatted. The method is about twice as fast as {@link #decode(String)}.
	 * The preconditions are:<br>
	 * + The array must have a line length of 76 chars OR no line separators at
	 * all (one line).<br>
	 * + Line separator must be "\r\n", as specified in RFC 2045 + The array
	 * must not contain illegal characters within the encoded string<br>
	 * + The array CAN have illegal characters at the beginning and end, those
	 * will be dealt with appropriately.<br>
	 * 
	 * @param s
	 *            The source string. Length 0 will return an empty array.
	 *            <code>null</code> will throw an exception.
	 * @return The decoded array of bytes. May be of length 0.
	 */
	public final static byte[] decodeFast(String s) {
		// Check special case
		int sLen = s.length();
		if (sLen == 0)
			return new byte[0];

		int sIx = 0, eIx = sLen - 1; // Start and end index after trimming.

		// Trim illegal chars from start
		while (sIx < eIx && IA[s.charAt(sIx) & 0xff] < 0)
			sIx++;

		// Trim illegal chars from end
		while (eIx > 0 && IA[s.charAt(eIx) & 0xff] < 0)
			eIx--;

		// get the padding count (=) (0, 1 or 2)
		int pad = s.charAt(eIx) == '=' ? (s.charAt(eIx - 1) == '=' ? 2 : 1) : 0; //

		int cCnt = eIx - sIx + 1; // Content count including possible separators
		int sepCnt = sLen > 76 ? (s.charAt(76) == '\r' ? cCnt / 78 : 0) << 1
				: 0;

		int len = ((cCnt - sepCnt) * 6 >> 3) - pad; // The number of decoded
													// bytes
		byte[] dArr = new byte[len]; // Preallocate byte[] of exact length

		// Decode all but the last 0 - 2 bytes.
		int d = 0;
		for (int cc = 0, eLen = (len / 3) * 3; d < eLen;) {
			// Assemble three bytes into an int from four "valid" characters.
			int i = IA[s.charAt(sIx++)] << 18 | IA[s.charAt(sIx++)] << 12
					| IA[s.charAt(sIx++)] << 6 | IA[s.charAt(sIx++)];

			// Add the bytes
			dArr[d++] = (byte) (i >> 16);
			dArr[d++] = (byte) (i >> 8);
			dArr[d++] = (byte) i;

			// If line separator, jump over it.
			if (sepCnt > 0 && ++cc == 19) {
				sIx += 2;
				cc = 0;
			}
		}

		if (d < len) {
			// Decode last 1-3 bytes (incl '=') into 1-3 bytes
			int i = 0;
			for (int j = 0; sIx <= eIx - pad; j++)
				i |= IA[s.charAt(sIx++)] << (18 - j * 6);

			for (int r = 16; d < len; r -= 8)
				dArr[d++] = (byte) (i >> r);
		}

		return dArr;
	}
}