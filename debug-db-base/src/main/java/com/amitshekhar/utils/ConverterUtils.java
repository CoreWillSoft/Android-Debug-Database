/*
 *
 *  *    Copyright (C) 2019 Amit Shekhar
 *  *    Copyright (C) 2011 Android Open Source Project
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package com.amitshekhar.utils;

import java.util.Arrays;
import java.util.Locale;

/**
 * Created by amitshekhar on 06/02/17.
 */

public class ConverterUtils {

    private static final int MAX_BLOB_LENGTH = 512;

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    private ConverterUtils() {
        // This class in not publicly instantiable
    }

    public static String blobToString(byte[] blob) {
//        if (blob.length <= MAX_BLOB_LENGTH) {
//            if (fastIsAscii(blob)) {
//                try {
//                    return new String(blob, "US-ASCII");
//                } catch (UnsupportedEncodingException ignored) {
//
//                }
//            }
//        }
        return "hex(" + encodeHex(blob) + ")";
    }

    public static boolean fastIsAscii(byte[] blob) {
        for (byte b : blob) {
            if ((b & ~0x7f) != 0) {
                return false;
            }
        }
        return true;
    }

    public static String encodeHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] decodeHex(String hexString) {
        if ((hexString.length() & 0x01) != 0) {
            throw new IllegalArgumentException("Odd number of characters.");
        }
        char[] hexChars = hexString.toUpperCase(Locale.ROOT).toCharArray();
        byte[] result = new byte[hexChars.length / 2];
        for (int i = 0; i < hexChars.length; i += 2) {
            result[i / 2] = (byte) (Arrays.binarySearch(HEX_ARRAY, hexChars[i]) * 16 + Arrays.binarySearch(HEX_ARRAY, hexChars[i + 1]));
        }
        return result;
    }

}
