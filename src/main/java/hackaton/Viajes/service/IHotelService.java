package hackaton.Viajes.service;

import hackaton.Viajes.model.Hotel;
import java.util.List;

public interface IHotelService {
    public List<Hotel>hotels();

    public Hotel findById(Integer idHotel);

    public Hotel save(Hotel hotel);

    public void deleteById(Integer idHotel);
}
