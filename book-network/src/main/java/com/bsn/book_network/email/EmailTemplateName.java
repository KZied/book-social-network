package com.bsn.book_network.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {
    
    ACTIVATE_ACCOUNT("activate-account")
    ;
    
    private String name;

    EmailTemplateName(String name) {
        this.name = name;
    }
}
