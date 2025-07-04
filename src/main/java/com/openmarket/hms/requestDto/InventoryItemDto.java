package com.openmarket.hms.requestDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryItemDto {
  @NotNull()
  private String name;
  
  @NotNull()
  private String categoryId;
  
  private String subCategoryId;
  
  @NotNull()
  private String description;
  
  @NotNull
  private String skuNumber;
  
  @NotNull
  private Double buyingPrice;

  @NotNull
  private Double sellingPrice;

  @NotNull
  private String imageName;
}
