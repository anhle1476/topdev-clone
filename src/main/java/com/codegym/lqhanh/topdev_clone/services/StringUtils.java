package com.codegym.lqhanh.topdev_clone.services;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtils {
    private static final Pattern NORMALIZE_PATTERN = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    private static final SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");

    public static String removeAccent(String input) {
        input = input.toLowerCase();
        String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
        return NORMALIZE_PATTERN.matcher(temp).replaceAll("")
                .replaceAll("đ", "d");
    }

    public static Date parseDateFromDatabase(String date) {
        try {
            return dateParser.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatDate(Date date) {
        return dateParser.format(date);
    }

    public static List<String> parseTagsInput(String input) {
        String[] tags = input.split(",");
        return Stream.of(tags)
                .map(tag -> tag.trim().replaceAll("\\s+", " "))
                .collect(Collectors.toList());
    }

    public static String parsePostContent(String content) {
        return content.replaceAll("'", "&apos;");
    }
}
