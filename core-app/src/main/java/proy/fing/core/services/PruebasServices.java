package proy.fing.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import proy.fing.core.dao.mongo.MeasureDAO;
import proy.fing.core.dto.MeasureDTO;
import proy.fing.core.dto.NetworkDTO;
import proy.fing.core.model.Measure;
import proy.fing.core.model.Network;

@RestController
@RequestMapping("/pruebas")
public class PruebasServices {

	@Autowired
	private MeasureDAO measureDAO;
	
	@RequestMapping(value = "/addMeasure", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void createNetwork(@RequestBody String measure)
	{
		Gson gson = new Gson();
		MeasureDTO measureDTO = gson.fromJson(measure, MeasureDTO.class);
		
		Measure measureNew = new Measure();
		measureNew.setNetworkMote(measureDTO.getNetworkMote());
		measureNew.setNetworkName(measureDTO.getNetworkName());
		measureNew.setValue(measureDTO.getValue());
		measureNew.generateId();
			
		
		this.measureDAO.save(measureNew);
	}
}
