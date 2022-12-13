package com.LNS.LNS.Controller;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.LNS.Models.ModelsOurApi.Community;
import com.LNS.Models.ModelsOurApi.Municipality;
import com.LNS.Models.ModelsOurApi.Province;
import com.LNS.Service.ProvinceService;

@SpringBootTest
public class ShowcaseControllerTest {

    @Autowired
	ProvinceService provinceService;

    @Test
	@DisplayName("Teste para verificar o metodo printcommunities() do controller")
    public void PrintCommunitiesTest() {

        Community communityTest1 = new Community("01", "Andalucía");
        Community communityTest2 = new Community("02", "Aragón");
        Community communityTest3 = new Community("123456789", "Wrong name test");

        List<Community> community = provinceService.printcommunities();

        assertNotNull(community);
        assertTrue(community.contains(communityTest1));
		assertTrue(community.contains(communityTest2));
        assertFalse(community.contains(communityTest3));
    }

    @Test
	@DisplayName("Teste para verificar o metodo FilterProv() do controller")
    public void FilterProvTest() {
        Province provinceTest1 = new Province("02","Albacete");
        Province provinceTest2 = new Province("13","Ciudad Real");
        Province provinceTest3 = new Province("1efsf3","dfgdge");

        List<Province> province = provinceService.getProvincesInCommunity("08");

        assertNotNull(province);
        assertTrue(province.contains(provinceTest1));
		assertTrue(province.contains(provinceTest2));
        assertFalse(province.contains(provinceTest3));


    }


    @Test
	@DisplayName("Teste para verificar o metodo getAllMunicipalities(code:01) do controller")
    public void  getAllMunicipalitiesTest1(){

        Municipality municipalityTest1 = new Municipality("01001000000","Alegría-Dulantzi");
        Municipality municipalityTest2 = new Municipality("01002000000","Amurrio");
        Municipality municipalityTest3 = new Municipality("rrrrrrr","rrrrrrrrrrrrrrrrrrrr");

        List<Municipality> municipality = provinceService.getAllMunicipalities("01");
        assertNotNull(municipality);
        assertTrue(municipality.contains(municipalityTest1));
		assertTrue(municipality.contains(municipalityTest2));
        assertFalse(municipality.contains(municipalityTest3));

    }
    @Test
	@DisplayName("Teste para verificar o metodo getAllMunicipalities(code: other than  01) do controller")
    public void  getAllMunicipalitiesTest2(){

        Municipality municipalityTest21 = new Municipality("02001000000","Abengibre");
        Municipality municipalityTest22 = new Municipality("02002000000","Alatoz");
        Municipality municipalityTest23 = new Municipality("rrrrrrr","rrrrrrrrrrrrrrrrrrrr");

        List<Municipality> municipality2 = provinceService.getAllMunicipalities("02");
        assertNotNull(municipality2);
        assertTrue(municipality2.contains(municipalityTest21));
		assertTrue(municipality2.contains(municipalityTest22));
        assertFalse(municipality2.contains(municipalityTest23));

    }
}