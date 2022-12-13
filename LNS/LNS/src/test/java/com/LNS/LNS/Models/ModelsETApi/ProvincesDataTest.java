package com.LNS.LNS.Models.ModelsETApi;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.LNS.Templates;
import com.LNS.Models.ModelsETApi.Provinces;
import com.LNS.Models.ModelsETApi.ProvincesData;
import com.LNS.Models.ModelsOurApi.Community;
import com.LNS.Models.ModelsOurApi.Municipality;
import com.LNS.Service.ProvinceService;

@SpringBootTest
public class ProvincesDataTest {
    
    @Autowired
	RestTemplate restTemplate;

	@Autowired
	ProvinceService provinceService;

    @Test
	@DisplayName("Teste para verificar se os dados das provincias e comunidades foram corretamente extraidos da API para a aplicação")
	public void ProvincesAndCommunitiesToDataTest() {
		ProvincesData provincesAndCommunitiesTest1 = new ProvincesData("01", "Araba/Álava",
				"16", "País Vasco/Euskadi");
		ProvincesData provincesAndCommunitiesTest2 = new ProvincesData("08", "Barcelona",
				"09", "Cataluña/Catalunya");
		ProvincesData provincesAndCommunitiesTest3 = new ProvincesData("07", "Illes Balears",
				"04", "Illes Balears");
		ProvincesData provincesAndCommunitiesTest4 = new ProvincesData(" ", " ",
				" ", " ");

		Provinces provinces = Templates.restTemplateProvince(restTemplate);

		List<ProvincesData> testingList = provinces.getProvinces();

		assertNotNull(testingList);
		assertTrue(testingList.contains(provincesAndCommunitiesTest1));
		assertTrue(testingList.contains(provincesAndCommunitiesTest2));
		assertTrue(testingList.contains(provincesAndCommunitiesTest3));
		assertFalse(testingList.contains(provincesAndCommunitiesTest4));
	}
}
