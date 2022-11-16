package com.geektrust.backend.enums;

import lombok.Getter;

public enum Tenants {
    
    TWO_BEDROOM_TENANTS(3), 
    THREE_BEDROOM_TENANTS(5); 

    @Getter
    private int tenants;  

    Tenants(int tenants){
        this.tenants = tenants;
    }
}
