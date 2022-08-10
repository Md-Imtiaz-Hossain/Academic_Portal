package com.sktech.academicportal.helper;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListToStringConverter implements AttributeConverter<List<Long>, String> {
    @Override
    public String convertToDatabaseColumn(List<Long> longs) {
        return longs == null ? null : String.join(",", longs.toString());
    }

    @Override
    public List<Long> convertToEntityAttribute(String s) {
        return s == null ? Collections.emptyList() : Arrays.stream(s.split(",")).map(z -> Long.parseLong(z)).collect(Collectors.toList());
    }
}
