package proy.fing.core.service.mapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collection;
import java.util.List;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import proy.fing.core.dao.db.AbstractEntity;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public abstract class AbstractMapper<C extends AbstractEntity,DTO> {
	
	private MapperFacade mapperFacade;
	
	private Class<C> className;
	private Class<DTO> dtoName;
	
	public void map(C source, DTO dto) {
        this.mapperFacade.map(source, dto);
    }

    public void map(DTO dto, C source) {
        this.mapperFacade.map(dto, source);
    }

    public DTO convert(C source) {
        return this.mapperFacade.map(source, this.getDTOClass());
    }

    public List<DTO> convertToDTOs(Collection<C> sources) {
        List<DTO> dtos = new ArrayList<DTO>(sources.size());
        for (C t : sources) {
            dtos.add(this.mapperFacade.map(t, this.getDTOClass()));
        }
        return dtos;
    }

    /**
     * convert a DTO to a model Entity
     * 
     * @param source
     * @return
     */
    public C convert(DTO source) {
        return this.mapperFacade.map(source, this.getEntityClass());
    }

    /**
     * convert a collection of DTO to a model Entity list
     * 
     * @param dtos
     * @return
     */
    public List<C> convertToEntities(Collection<DTO> dtos) {
        List<C> entities = new ArrayList<C>(dtos.size());
        for (DTO dto : dtos) {
            entities.add(this.mapperFacade.map(dto, this.getEntityClass()));
        }
        return entities;
    }

    @SuppressWarnings("unchecked")
    private Class<DTO> getDTOClass() {

        if (this.dtoName == null) {
            Type type = this.getClass().getGenericSuperclass();
            ParameterizedType paramType = (ParameterizedType) type;
            this.dtoName = (Class<DTO>) paramType.getActualTypeArguments()[1];
        }

        return this.dtoName;
    }

    @SuppressWarnings("unchecked")
    private Class<C> getEntityClass() {

        if (this.className == null) {
            Type type = this.getClass().getGenericSuperclass();
            ParameterizedType paramType = (ParameterizedType) type;
            this.className = (Class<C>) paramType.getActualTypeArguments()[0];
        }

        return this.className;
    }
	
	@PostConstruct
	public void initializeMapper(){
		
		// Cargamos las clases
		
		Type type1 = this.getClass().getGenericSuperclass();
        ParameterizedType paramType1 = (ParameterizedType) type1;
        this.className = (Class<C>) paramType1.getActualTypeArguments()[0];
        
        Type type2 = this.getClass().getGenericSuperclass();
        ParameterizedType paramType2 = (ParameterizedType) type2;
        this.dtoName = (Class<DTO>) paramType2.getActualTypeArguments()[1];
        
        // Definimos el mapper
		
		MapperFactory mapper = new DefaultMapperFactory.Builder().build();
		mapper.classMap(dtoName, className).byDefault().register();
		
		this.mapperFacade = mapper.getMapperFacade();
	}

}
