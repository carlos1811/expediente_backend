package com.carlosrey.springboot.backend.apirest;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import com.carlosrey.springboot.backend.apirest.models.dao.IClienteDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootBackendApirestApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	@Mock
	IClienteDao clienteDao;
	
	@Test
	final void testGetUser(){
		
	when(clienteDao.findAll()).thenReturn(null);			
		
	}
	
		
	//clienteDao
		
	
	
}
