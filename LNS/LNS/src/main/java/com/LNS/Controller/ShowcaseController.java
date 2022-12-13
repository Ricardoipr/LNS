package com.LNS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.LNS.Models.ModelsOurApi.Community;
import com.LNS.Models.ModelsOurApi.Municipality;
import com.LNS.Models.ModelsOurApi.Province;
import com.LNS.Service.ProvinceService;

@RestController
public class ShowcaseController {

	@Autowired
	private ProvinceService provinceService;

	@GetMapping("/communities")
	public List<Community> printcommunities() {

		return provinceService.printcommunities();
	}

	@GetMapping("/communities/provinces/{codCom}")
	public List<Province> FilterProv(@PathVariable("codCom") String code) {

		return provinceService.getProvincesInCommunity(code);
	}

	@GetMapping("/communities/provinces/{codCom}/municipalities/{codeProv}")
	public List<Municipality> getAllMunicipalities(@PathVariable("codeProv") String code) {

		return provinceService.getAllMunicipalities(code);
	}
}