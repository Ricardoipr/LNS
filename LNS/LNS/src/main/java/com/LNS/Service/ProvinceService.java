package com.LNS.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.LNS.Templates;
import com.LNS.Models.ModelsETApi.Provinces;
import com.LNS.Models.ModelsOurApi.Community;
import com.LNS.Models.ModelsOurApi.Municipality;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import com.LNS.Models.ModelsETApi.ProvincesData;
import com.LNS.Models.ModelsOurApi.Province;

@Service
public class ProvinceService {

    @Autowired
    RestTemplate restTemplate;

    public List<ProvincesData> GetData(RestTemplate restTemplate) {
        String ProvinceCommunityURL = "https://www.el-tiempo.net/api/json/v2/provincias";
        Provinces province = restTemplate.getForObject(ProvinceCommunityURL, Provinces.class);
        List<ProvincesData> listProvinces = province.getProvinces();

        return listProvinces;
    }

    public Provinces FilterCommunityDupes(RestTemplate restTemplate) {
        String ProvinceCommunityURL = "https://www.el-tiempo.net/api/json/v2/provincias";
        Provinces province = restTemplate.getForObject(ProvinceCommunityURL, Provinces.class);
        List<String> included = new ArrayList<>();
        List<ProvincesData> filtered = province.getProvinces()
                .stream().filter(p -> {
                    if (included.contains(p.getCodAuton())) {
                        return false;
                    } else {
                        included.add(p.getCodAuton());
                        return true;
                    }
                })
                .collect(Collectors.toList());
        province.setProvinces(filtered);

        return province;
    }

    public List<Community> printcommunities() {

        Provinces prov = FilterCommunityDupes(restTemplate);
        List<Community> result = new ArrayList<Community>();
        List<ProvincesData> before = prov.getProvinces();
        before.sort(Comparator.comparing(ProvincesData::getComunidadeCidadeAutonoma));
        for (int i = 0; i < prov.getProvinces().size(); i++) {

            ProvincesData test = before.get(i);
            Community communities = new Community(test.getCodAuton(), test.getComunidadeCidadeAutonoma());
            result.add(i, communities);
        }
        return result;
    }

    public List<Province> getProvincesInCommunity(String codprov) {
        List<ProvincesData> listProvinces = GetData(restTemplate);
        listProvinces.sort(Comparator.comparing(ProvincesData::getNomeProvincia));
        List<Province> listFilteredProvinces = new ArrayList<Province>();

        for (int i = 0; i < listProvinces.size(); i++) {
            ProvincesData test = listProvinces.get(i);
            if ((test.getCodAuton()).equals(codprov)) {
                Province prov = new Province(test.getCodProv(), test.getNomeProvincia());
                listFilteredProvinces.add(prov);
            }
        }

        return listFilteredProvinces;

    }

    // Obtem todas os municipios
    public List<Municipality> getAllMunicipalities(String code) {

        if (code.equals("01")) {
            List<Municipality> listOfMunicipalities = Templates.restTemplateMunicipalityListCase(restTemplate);
            return listOfMunicipalities;
        }
        List<Municipality> listOfMunicipalities = Templates.restTemplateMunicipality(restTemplate,code);
        return listOfMunicipalities;
    }
}
