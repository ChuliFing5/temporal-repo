package proy.fing.daemon.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import proy.fing.daemon.buffer.BufferManager;

@Service
public class TestConnectionJob {

	@Autowired
	BufferManager bufferManager;
	
	@Scheduled(cron="${daemon.config.job.cron.testConnection}")
	public void testConnection(){
		//Se manda lo buffereado
		//Si hay conexion se manda, sino no
		bufferManager.sendBufferedMeasure();
	}
}
