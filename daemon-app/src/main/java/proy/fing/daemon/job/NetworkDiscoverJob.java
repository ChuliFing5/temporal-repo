package proy.fing.daemon.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NetworkDiscoverJob {
	
	@Scheduled(cron="${daemon.config.job.cron.networkDiscover}")
	public void discoverNetwork(){
		
		
	}

}
