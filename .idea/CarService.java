package ru.tbank.hse.fd.streamPractise.service;

import ru.tbank.hse.fd.streamPractise.model.Car;
import ru.tbank.hse.fd.streamPractise.model.CarInfo;
import ru.tbank.hse.fd.streamPractise.model.Owner;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarService {

    public List<String> getConditions(List<Car> cars) {
        return cars.stream()
                .map(car -> car.getCondition().getText())
                .toList();
    }

    public List<Car> getNewCars(List<Car> cars) {
        return cars.stream()
                .filter(car -> "NEW".equals(car.getCondition()))
                .toList();
    }
    public long countCarsOwners(List<Car> cars) {
        return cars.stream()
                .filter(car -> car.getOwners().size() > 2)
                .count();
    }
    public List<Car> incrementCarAge(List<Car> cars) {
        cars.forEach(car -> car.setAge(car.getAge() + 1));
        return cars;
    }

    public Car getOldestCar(List<Car> cars) {
        return cars.stream()
                .max(Comparator.comparing(Car::getAge))
                .orElseThrow(RuntimeException::new);
    }

    public List<String> getOwnersCarsNames(List<Car> cars) {
        return cars.stream()
                .flatMap(car -> car.getOwners().stream().map(Owner::getName))
                .distinct()
                .toList();
    }

    public List<CarInfo> mapToCarInfo(List<Car> cars) {
        return cars.stream()
                .map(car -> new CarInfo(car.getName(), car.getAge(), car.getOwners().size()))
                .toList();
    }

    public List<Car> getTwoBrokenCar(List<Car> cars) {
        return cars.stream()
                .filter(car -> "BROKEN".equals(car.getCondition()))
                .limit(2)
                .toList();
    }

    public List<Car> getSortedCarsByAge(List<Car> cars) {
        return cars.stream()
                .sorted(Comparator.comparing(Car::getAge))
                .toList();
    }

    public double getAvgCarsAge(List<Car> cars) {
        return cars.stream()
                .mapToInt(Car::getAge)
                .average()
                .getAsDouble();
    }

    public Boolean checkBrokenCarsAge(List<Car> cars) {
        return cars.stream()
                .filter(car -> "BROKEN".equals(car.getCondition()))
                .allMatch(car -> car.getAge() > 10);
    }

    public Boolean checkCarOwnerName(List<Car> cars){
        return cars.stream()
                .filter(car -> "USED".equals(car.getCondition()))
                .flatMap(car -> car.getOwners().stream())
                .anyMatch(owner -> "Adam".equals(owner.getName()));
    }
    public Owner getAnyOwner(List<Car> cars) {
        return cars.stream()
                .flatMap(car -> car.getOwners().stream())
                .filter(owner -> owner.getAge() > 36)
                .findAny()
                .orElse(null);
    }
}