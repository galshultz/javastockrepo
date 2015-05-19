package com.shultz.service;

import java.util.Calendar;
import java.util.Date;

import org.algo.dto.PortfolioDto;
import org.algo.dto.PortfolioTotalStatus;
import org.algo.exception.PortfolioException;
import org.algo.model.PortfolioInterface;
import org.algo.service.PortfolioManagerInterface;
import org.algo.service.ServiceManager;
import com.google.appengine.api.datastore.DatastoreService;
import com.shultz.model.*;

/**
 * This class represents a Portfolio Manager this class will execute different methods of Portfolio.
 * 
 * @author GalShultz
 * @since 22/4/2015
 */
public class PortfolioManager implements PortfolioManagerInterface{
		
	private DatastoreService datastoreService = ServiceManager.datastoreService();

	@Override
	public PortfolioInterface getPortfolio() {
		PortfolioDto portfolioDto = datastoreService.getPortfolilo();
		return fromDto(portfolioDto);
	}

	/**
	 * Update portfolio with stocks
	 */
	@Override
	void update(){
		StockInterface[] stocks = getPortfolio().getStocks();
		List<String> symbols = new ArrayList<>(Portfolio.getMaxSize());
		for (StockInterface si : stocks) {
			symbols.add(si.getSymbol());
		}

		List<Stock> update = new ArrayList<>(Portfolio.getMaxSize());
		List<Stock> currentStocksList = new ArrayList<Stock>();
		try {
			List<StockDto> stocksList = MarketService.getInstance().getStocks(symbols);
			for (StockDto stockDto : stocksList) {
				Stock stock = fromDto(stockDto);
				currentStocksList.add(stock);
			}

			for (Stock stock : currentStocksList) {
				update.add(new Stock(stock));
			}

			datastoreService.saveToDataStore(toDtoList(update));

		} catch (SymbolNotFoundInNasdaq e) {
			System.out.println(e.getMessage());
		}
	}
		
	}

	PortfolioInterface getPortfolio(){
		
	}

	void setTitle(String title){
		
	}

	void updateBalance(float value) throws PortfolioException{
		
	}

	PortfolioTotalStatus[] getPortfolioTotalStatus(){
		
	}

	void addStock(String symbol) throws PortfolioException{
		
	}

	void buyStock(String symbol, int quantity) throws PortfolioException{
		
	}

	void sellStock(String symbol, int quantity) throws PortfolioException{
		
	}

	void removeStock(String symbol) throws PortfolioException{
		
	}
}
		
		
		
