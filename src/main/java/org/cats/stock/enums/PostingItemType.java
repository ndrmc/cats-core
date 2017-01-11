package org.cats.stock.enums;

import javax.persistence.AttributeConverter;

/**
 * Created by alexander on 1/11/17.
 */
public enum PostingItemType {
    
    P; 

    @javax.persistence.Converter(autoApply = true)
    public class Converter implements AttributeConverter<PostingItemType, String> {

        @Override
        public String convertToDatabaseColumn(PostingItemType attribute) {
            return attribute.name();
        }

        @Override
        public PostingItemType convertToEntityAttribute(String dbData) {
            return PostingItemType.valueOf(dbData);
        }
    }
}
