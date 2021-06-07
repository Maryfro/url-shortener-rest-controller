package de.maryfro.urlshortenerrestcontroller.service;

public class StringShortenerHelper {
    public static final String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final int baseLength = base.length();

    public static String uuidToShortUrl(long uuid) {
        StringBuilder shortUrl = new StringBuilder();
        while (uuid > 0) {
            shortUrl.append(base.charAt((int) (uuid % baseLength)));
            uuid = uuid / baseLength;
        }
        return shortUrl.reverse().toString();
    }

    public static long longUrlToUuid(String longURL) {
        int uuid = 0;
        for (int i = 0; i < longURL.length(); i++) {
            uuid = uuid * baseLength + base.indexOf(longURL.charAt(i));
        }
        return Math.abs(uuid);
    }

}
