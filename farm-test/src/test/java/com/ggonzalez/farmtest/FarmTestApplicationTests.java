package com.ggonzalez.farmtest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ggonzalez.farmtest.entity.Egg;
import com.ggonzalez.farmtest.repository.ChickenRepository;
import com.ggonzalez.farmtest.repository.EggRepository;
import com.ggonzalez.farmtest.repository.FarmRepository;
import com.ggonzalez.farmtest.service.ChickenService;
import com.ggonzalez.farmtest.service.EggService;
import com.ggonzalez.farmtest.service.FarmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class FarmTestApplicationTests {

	@Test
	void contextLoads(){
	}

}
