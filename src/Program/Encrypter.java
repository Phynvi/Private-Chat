package Program;

import java.util.Arrays;

public class Encrypter {
    public static String getEncryptedVersion(String toEncrypt, String key)
    {
        byte[] byteArray = toEncrypt.getBytes();
        long[] bytes = new long[byteArray.length];

        for (int i = 0; i < bytes.length; i++)
        {
            bytes[i] = ~(~(key.hashCode()*2 + 5) + byteArray[i]); //Simple encryption, can be expanded
        }
        return Arrays.toString(bytes);
    }

    public static String getDecryptedVersion(String toDecrypt, String key)
    {
        long[] convertedBytes = fromString(toDecrypt);
        byte[] byteArray = new byte[convertedBytes.length];
        for (int i = 0; i < byteArray.length; i++)
        {
            long a = convertedBytes[i];
            long b = ~a; //b = (~(key.hashCode()*2 + 5) + byteArray[i])
            long c = ~(key.hashCode()*2 + 5);
            long d = b - c; //d = byteArray[i];
            byteArray[i] = (byte) d;
        }

        return new String(byteArray);
    }

    private static long[] fromString(String toParse) {
        String[] parts = toParse.split(" ");
        long[] converted = new long[parts.length];
        for(int n = 0; n < parts.length; n++) {
            converted[n] = Long.parseLong(parts[n].replaceAll("[^\\d.-]", ""));
        }
        return converted;
    }
}
