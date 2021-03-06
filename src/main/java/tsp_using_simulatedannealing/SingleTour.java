package tsp_using_simulatedannealing;

import java.util.ArrayList;
import java.util.List;

public class SingleTour {
    private List<City> tour = new ArrayList<>();
    private double distance = 0;

    public SingleTour() {
        generateTour();
    }

    public SingleTour(SingleTour singleTour) {
        this.tour.addAll(singleTour.tour);
    }

    private void generateTour() {
        tour.addAll(Repository.getShuffledCityList());
    }

    private City getCity(int index) {
        return tour.get(index);
    }

    private City getNextCity(int index) {
        return tour.get(index + 1);
    }

    private City firstCity() {
        return tour.get(0);
    }

    private City lastCity() {
        return tour.get(tourSize() - 1);
    }

    public double getDistance() {
        if (distance == 0) {
            return distance = calculateGeneratedTourDistance();
        } else {
            return distance;
        }
    }

    private double calculateGeneratedTourDistance() {
        double tourDistance = 0;
        for (int cityIndex = 0; cityIndex < tourSize(); cityIndex++) {
            if (isLastCity(cityIndex)) {
                tourDistance += lastCity().distanceTo(firstCity());
            } else {
                tourDistance += getCity(cityIndex).distanceTo(getNextCity(cityIndex));
            }
        }
        return tourDistance;
    }

    private boolean isLastCity(int cityIndex) {
        return cityIndex + 1 == tourSize();
    }

    private int tourSize() {
        return tour.size();
    }

    @Override
    public String toString() {
        return "SingleTour{" +
                "tour=" + tour +
                ", distance=" + distance +
                '}';
    }

    private void setCity(int randomIndex, City city) {
        this.tour.set(randomIndex, city);
    }

    public SingleTour randomlySwapCities() {
        int randomIndex1 = (int) (tourSize() * Math.random());
        City city1 = getCity(randomIndex1);

        int randomIndex2 = (int) (tourSize() * Math.random());
        City city2 = getCity(randomIndex2);

        setCity(randomIndex2, city1);
        setCity(randomIndex1, city2);

        return this;
    }
}
