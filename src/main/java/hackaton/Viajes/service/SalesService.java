package hackaton.Viajes.service;

import hackaton.Viajes.model.Sales;
import hackaton.Viajes.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService implements ISalesService{

    @Autowired
    SalesRepository salesRepository;

    @Override
    public List<Sales> sales() {
        return this.salesRepository.findAll();
    }

    @Override
    public Sales findById(Integer numSales) {
        Sales sales = this.salesRepository.findById(numSales).orElse(null);
        return sales;
    }

    @Override
    public Sales save(Sales sales) {
       return this.salesRepository.save(sales);
    }

    @Override
    public void deleteById(Integer idSales) {
        this.salesRepository.deleteById(idSales);
    }

}