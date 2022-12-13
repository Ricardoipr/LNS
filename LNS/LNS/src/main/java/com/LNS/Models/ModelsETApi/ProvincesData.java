package com.LNS.Models.ModelsETApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvincesData {

	@JsonProperty("CODPROV")
	private String codProv;

	@JsonProperty("NOMBRE_PROVINCIA")
	private String nomeProvincia;

	@JsonProperty("CODAUTON")
	private String codAuton;

	@JsonProperty("COMUNIDAD_CIUDAD_AUTONOMA")
	private String comunidadeCidadeAutonoma;
}
