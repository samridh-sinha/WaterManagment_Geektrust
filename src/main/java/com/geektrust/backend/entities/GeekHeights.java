package com.geektrust.backend.entities;

import com.geektrust.backend.enums.Tenants;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class GeekHeights {
  @NonNull 
  @Getter
  private String Id;   
  @NonNull 
  @Getter
  private Integer apartmentType;  
  @NonNull 
  @Getter
  private Integer corporationRatio;  
  @NonNull
  @Getter
  private Integer boreWellratio;   

  private Integer tenants; 
  
  public int getTenants(){

    if(apartmentType == 2) 
        tenants =  Tenants.TWO_BEDROOM_TENANTS.getTenants(); 
    else 
        tenants =  Tenants.THREE_BEDROOM_TENANTS.getTenants(); 
    return tenants;

  }
}
