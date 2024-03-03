package hackaton.Viajes.service;

import hackaton.Viajes.model.Sales;
import java.util.List;

public interface ISalesService {

    public List<Sales> sales();

    public Sales findById(Integer numSales);

    public Sales save(Sales sales);

    public void deleteById(Integer idSales);
}
