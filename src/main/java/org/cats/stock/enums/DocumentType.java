package org.cats.stock.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by alexander on 1/11/17.
 */
public enum DocumentType {
    D("D");

    DocumentType(String code ){
        this.code = code;
    }


    private String code;

    public String getCode() {
        return this.code;
    }

    @javax.persistence.Converter(autoApply = true)
    public class Converter implements AttributeConverter<DocumentType, String> {

        @Override
        public String convertToDatabaseColumn(DocumentType attribute) {
            return attribute.name();
        }

        @Override
        public DocumentType convertToEntityAttribute(String dbData) {
            return DocumentType.valueOf(dbData);
        }
    }
}

