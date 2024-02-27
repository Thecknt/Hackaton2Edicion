package hackaton.Viajes.service;

import hackaton.Viajes.model.Hotel;
import hackaton.Viajes.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService{

    @Autowired
    HotelRepository hotelRepository;
    @Override
    public List<Hotel> hotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel findById(Integer idHotel) {
        Hotel hotel = hotelRepository.findById(idHotel).orElse(null);
        return hotel;
    }

    @Override
    public void save(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        hotelRepository.delete(hotel);
    }
}
