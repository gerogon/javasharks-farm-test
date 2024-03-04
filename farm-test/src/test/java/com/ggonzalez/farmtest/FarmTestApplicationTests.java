package com.ggonzalez.farmtest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ggonzalez.farmtest.entity.Farm;
import com.ggonzalez.farmtest.exception.FarmException;
import com.ggonzalez.farmtest.repository.FarmRepository;
import com.ggonzalez.farmtest.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class FarmTestApplicationTests {
	@MockBean
	private ChickenServiceImpl chickenService;
	@MockBean
	private EggServiceImpl eggService;
	@MockBean
	private FarmRepository farmRepository;
	@Autowired
	private FarmServiceImpl farmService;

	@Test
	public void createFarmSuccessTest(){
		Farm expectedFarm = new Farm(100,20,10,5,15);
		expectedFarm.setId(1);
		when(farmRepository.save(any())).thenReturn(expectedFarm);
		Farm actualFarm = farmService.createFarm(100,20,10,5,15);

		verify(farmRepository).save(any());
		assertEquals(expectedFarm.getId(), actualFarm.getId());
		assertEquals(expectedFarm.getMoney(), actualFarm.getMoney());
		assertEquals(expectedFarm.getEggCapacity(), actualFarm.getEggCapacity());
		assertEquals(expectedFarm.getChickenCapacity(), actualFarm.getChickenCapacity());
		assertEquals(expectedFarm.getEggPrice(), actualFarm.getEggPrice());
		assertEquals(expectedFarm.getChickenPrice(), actualFarm.getChickenPrice());
	}

	@Test
	public void buyChickenSuccessTest(){
		Farm farm = new Farm(100,20,10,5,15);
		farm.setId(1);
		when(farmRepository.findById(farm.getId())).thenReturn(Optional.of(farm));
		long initialChickenStock = 2;
		when(chickenService.countChickens()).thenReturn(initialChickenStock);
		int expectedMoneyAfterPurchase = 85;
		farmService.buyChicken(farm.getId());

		assertEquals(expectedMoneyAfterPurchase, farm.getMoney());
		verify(chickenService).countChickens();
		verify(chickenService).save(any());
	}

	@Test
	public void buyChickenWithNotEnoughMoneyErrorTest(){
		Farm farm = new Farm(10,20,10,5,15);
		farm.setId(1);
		when(farmRepository.findById(farm.getId())).thenReturn(Optional.of(farm));

		FarmException exception = assertThrows(FarmException.class, () -> farmService.buyChicken(farm.getId()));
		assertEquals("You don't have enough money to buy a chicken! You need $15 but only have $10", exception.getMessage());
		verify(farmRepository, times(2)).findById(farm.getId());
	}

	@Test
	public void buyChickenWithCapacityReachedErrorTest(){
		Farm farm = new Farm(100,20,10,5,15);
		farm.setId(1);
		long fullChickenCapacity = 10;
		when(chickenService.countChickens()).thenReturn(fullChickenCapacity);
		when(farmRepository.findById(farm.getId())).thenReturn(Optional.of(farm));

		FarmException exception = assertThrows(FarmException.class, () -> farmService.buyChicken(farm.getId()));
		assertEquals("You can't buy any more chickens, the farm has reached the limit!", exception.getMessage());
		verify(farmRepository, times(3)).findById(farm.getId());
		verify(chickenService).countChickens();
	}

	@Test
	public void buyEggSuccessTest(){
		Farm farm = new Farm(100,20,10,5,15);
		farm.setId(1);
		when(farmRepository.findById(farm.getId())).thenReturn(Optional.of(farm));
		long initialEggStock = 0;
		when(eggService.countEggs()).thenReturn(initialEggStock);
		int expectedMoneyAfterPurchase = 95;
		farmService.buyEgg(farm.getId());

		assertEquals(expectedMoneyAfterPurchase, farm.getMoney());
		verify(eggService).countEggs();
		verify(eggService).save(any());
	}

	@Test
	public void buyEggWithNotEnoughMoneyErrorTest(){
		Farm farm = new Farm(4,20,10,5,15);
		farm.setId(1);
		when(farmRepository.findById(farm.getId())).thenReturn(Optional.of(farm));

		FarmException exception = assertThrows(FarmException.class, () -> farmService.buyEgg(farm.getId()));
		assertEquals("You don't have enough money to buy an egg! You need $5 but only have $4", exception.getMessage());
		verify(farmRepository, times(2)).findById(farm.getId());
	}

	@Test
	public void buyEggWithCapacityReachedErrorTest(){
		Farm farm = new Farm(100,20,10,5,15);
		farm.setId(1);
		long fullEggCapacity = 20;
		when(eggService.countEggs()).thenReturn(fullEggCapacity);
		when(farmRepository.findById(farm.getId())).thenReturn(Optional.of(farm));

		FarmException exception = assertThrows(FarmException.class, () -> farmService.buyEgg(farm.getId()));
		assertEquals("You can't buy any more eggs, the farm has reached the limit!", exception.getMessage());
		verify(farmRepository, times(3)).findById(farm.getId());
		verify(eggService).countEggs();
	}

	@Test
	public void sellChickenSuccessTest(){
		Farm farm = new Farm(100,20,10,5,15);
		farm.setId(1);
		when(farmRepository.findById(farm.getId())).thenReturn(Optional.of(farm));
		long chickenStock = 1;
		when(chickenService.countChickens()).thenReturn(chickenStock);
		int expectedMoneyAfterSell = 115;
		farmService.sellChicken(farm.getId());

		assertEquals(expectedMoneyAfterSell, farm.getMoney());
		verify(chickenService).countChickens();
		verify(chickenService).removeChicken();
	}

	@Test
	public void sellChickenWhenThereIsNoStockErrorTest() {
		Farm farm = new Farm(100, 20, 10, 5, 15);
		farm.setId(1);
		long chickenStock = 0;
		when(chickenService.countChickens()).thenReturn(chickenStock);

		FarmException exception = assertThrows(FarmException.class, () -> farmService.sellChicken(farm.getId()));
		assertEquals("You don't have any chickens to sell!", exception.getMessage());
		verify(chickenService).countChickens();
	}

	@Test
	public void sellEggSuccessTest(){
		Farm farm = new Farm(100,20,10,5,15);
		farm.setId(1);
		when(farmRepository.findById(farm.getId())).thenReturn(Optional.of(farm));
		long eggStock = 5;
		when(eggService.countEggs()).thenReturn(eggStock);
		int expectedMoneyAfterSell = 105;
		farmService.sellEgg(farm.getId());

		assertEquals(expectedMoneyAfterSell, farm.getMoney());
		verify(eggService).countEggs();
		verify(eggService).removeEgg();
	}

	@Test
	public void sellEggWhenThereIsNoStockErrorTest() {
		Farm farm = new Farm(100, 20, 10, 5, 15);
		farm.setId(1);
		long eggStock = 0;
		when(eggService.countEggs()).thenReturn(eggStock);

		FarmException exception = assertThrows(FarmException.class, () -> farmService.sellEgg(farm.getId()));
		assertEquals("You don't have any eggs to sell!", exception.getMessage());
		verify(eggService).countEggs();
	}
}