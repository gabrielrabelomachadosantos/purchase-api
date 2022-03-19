package com.gabrielsantos.purchaseapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDTO {

    private Long number;

    @JsonFormat(pattern = "yyyy/MM")
    @ApiModelProperty(example = "yyyy/MM")
    private Date expiringDate;

}
