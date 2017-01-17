package org.cats.accounting.domain;

import javax.persistence.AttributeConverter;

/**
 * Created by alexander on 1/16/17.
 */
public enum PostingType {

    NORMAL, REVERSED, REVERSAL;

    @javax.persistence.Converter(autoApply = true)
    public static class Converter implements AttributeConverter<PostingType, String> {

        @Override
        public String convertToDatabaseColumn(PostingType attribute) {
            return attribute.name();
        }

        @Override
        public PostingType convertToEntityAttribute(String dbData) {
            return PostingType.valueOf(dbData);
        }
    }
}
