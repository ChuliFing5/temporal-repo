package proy.fing.core.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import proy.fing.core.dao.mongo.MeasureDAO;
import proy.fing.core.dto.measures.MeasureDTO;
import proy.fing.core.model.mongo.Measure;

@Service
public class MeasuresListener {
	
	@Autowired
	private MeasureDAO measureDAO;
	
	@JmsListener(containerFactory="containerFactory",destination="testQueue")
	public void onMeasure(String message){
		
		Gson gson = new Gson();
		MeasureDTO measureDTO = gson.fromJson(message, MeasureDTO.class);
		
		Measure measure = new Measure();
		measure.setNetworkMote(measureDTO.getNetworkMote());
		measure.setNetworkName(measureDTO.getNetworkName());
		measure.setValue(measureDTO.getValue());
		measure.generateId();
		
		this.measureDAO.save(measure);

	}

}
