package proy.fing.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import proy.fing.core.dao.MoteDAO;
import proy.fing.core.dao.NetworkDAO;
import proy.fing.core.dto.MoteDTO;
import proy.fing.core.dto.NetworkDTO;
import proy.fing.core.model.Mote;
import proy.fing.core.model.Network;
import proy.fing.core.service.mapper.MoteMapper;
import proy.fing.core.service.mapper.NetworkMapper;

@RestController
@RequestMapping("/network")
public class NetworkServices {
	
	@Autowired
	NetworkDAO networkDAO;
	
	@Autowired
	MoteDAO moteDAO;
	
	
	@Autowired
	NetworkMapper networkMapper;
	
	@Autowired
	MoteMapper moteMapper;
	
	
	
	@RequestMapping(value = "/createNetwork", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void createNetwork(@RequestBody NetworkDTO networkDTO)
	{
		Network network = networkMapper.convert(networkDTO);
		networkDAO.save(network);
	}
	
	@RequestMapping(value = "/addMote", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void addMoteNetwork(@RequestBody MoteDTO moteDTO)
	{
		Mote mote = moteMapper.convert(moteDTO);
		moteDAO.save(mote);
	}
	
	
}
