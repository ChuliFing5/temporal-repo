package proy.fing.web.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import proy.fing.web.dto.measures.MeasureDTO;

@Service
public class MeasuresListener {
	
	@JmsListener(containerFactory="containerFactory",destination="testQueue")
	public void onMeasure(MeasureDTO measureDTO){
		
		
		System.out.println("Measure Recived: " + "value = " + measureDTO.getValue());
	}

}
