package com.cev.sircapcev.entity;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "configuracion")
public class ConfiguracionEntity {
    @Id
    private String id;
    private String configKey;
    private Date initializationDate;
    private Boolean configValue;
}
