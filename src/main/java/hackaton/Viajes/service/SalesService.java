package hackaton.Viajes.service;

import hackaton.Viajes.model.Sales;
import hackaton.Viajes.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SalesService implements ISalesService{

    @Autowired
    SalesRepository salesRepository;

    @Override
    public List<Sales> sales() {
        return salesRepository.findAll();
    }

    @Override
    public Sales findById(Integer numSales) {
        Sales sales = salesRepository.findById(numSales).orElse(null);
        return sales;
    }

    @Override
    public void save(Sales sales) {
       salesRepository.save(sales);
    }

    @Override
    public void delete(Sales sales) {
       salesRepository.delete(sales);
    }
}