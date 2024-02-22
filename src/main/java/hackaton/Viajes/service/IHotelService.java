package hackaton.Viajes.service;

import hackaton.Viajes.model.Hotel;

import java.util.List;

public interface IHotelService {

    public List<Hotel>hotels();

    public Hotel findById(Integer idHotel);

    public void save(Hotel hotel);

    public void delete(Hotel hotel);
}
