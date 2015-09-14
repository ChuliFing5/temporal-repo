package proy.fing.daemon.buffer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proy.fing.daemon.dao.MeasureDAO;
import proy.fing.daemon.model.Measure;
import proy.fing.daemon.service.sender.MessageSender;

@Service
public class BufferManager {
	
	@Autowired
	MeasureDAO measureDAO;
	
	@Autowired
	private MessageSender messageSender;
	
	
	public void sendBufferedMeasure()
	{
		List<Measure> listToSend = measureDAO.findAll();
		
		//Recorre la lista de medidas, si las puede mandar las borra, sino sigue recorriendo
		for (Measure measure : listToSend) 
		{
			try{
				messageSender.send(measure);
			}
			catch(Exception e){
				break; //sigue con el for
			}
			
			measureDAO.delete(measure);
		}		
	}
	
	public void saveMeasure(Measure measure)
	{
		measureDAO.save(measure);
	}
}
