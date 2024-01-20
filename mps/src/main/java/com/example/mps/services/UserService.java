package com.example.mps.services;

import com.example.mps.model.*;
import com.example.mps.repository.contracts.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    private RepairRepository repairRepository;

    private AppointmentRepository appointmentRepository;

    private VehicleRepository vehicleRepository;

    private WorkshopRepository workshopRepository;

    public UserService(UserRepository userRepository, RepairRepository repairRepository, AppointmentRepository appointmentRepository, VehicleRepository vehicleRepository, WorkshopRepository workshopRepository) {
        this.userRepository = userRepository;
        this.repairRepository = repairRepository;
        this.appointmentRepository = appointmentRepository;
        this.vehicleRepository = vehicleRepository;
        this.workshopRepository = workshopRepository;
    }

    public void updateUser(User user) {

        User user1 = userRepository.findByUsername(user.getUsername());
        if (user.getServices() != null) {
            user1.setServices(user.getServices());
        }
        if (user.getWorkshop() != null) {
            user1.setWorkshop(user.getWorkshop());
        }

        userRepository.save(user1);
    }

    public void createBooking(Booking booking) {
        appointmentRepository.save(booking);
    }

    public void deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
    }

    public List<Booking> getAllUserAppointments(String username){
        User user = getUser(username);
        return appointmentRepository.findAllByClient(user);
    }

    public void addCar(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public void removeCar(int id) {
        vehicleRepository.deleteById(id);
    }

    public void repair(Booking booking, double price, String worker) {
        Repair repair = new Repair();
        repair.setPrice(price);
        repair.setVehicle(booking.getVehicle());
        repair.setWorker(getUser(worker));
        repair.setWorkshop(booking.getWorkshop());

        repairRepository.save(repair);
    }

    public List<Vehicle> getAllUserCars(String username){
        User user = getUser(username);

        return vehicleRepository.findAllByOwner(user);
    }



    public List<Repair> getRepairsByWorkshop(String username) {
        Workshop workshop = getUser(username).getWorkshop();
        return repairRepository.findAllByWorkshop(workshop);
    }

    public List<Repair> getRepairsByClient(String username) {
        User user = getUser(username);
        return repairRepository.findAllByClient(user);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public List<Workshop> getAllWorkshops(){
        return workshopRepository.findAll();
    }

    public Vehicle getVehicleById(int id){
        return vehicleRepository.getById(id);
    }


    public Workshop getWorkshopByName(String name){
        return workshopRepository.findByName(name);
    }
}
