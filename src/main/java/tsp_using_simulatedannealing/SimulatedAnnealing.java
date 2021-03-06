package tsp_using_simulatedannealing;

public class SimulatedAnnealing {

    private SingleTour bestSolution;

    public void simulation() {
        double temperature = 10000;
        double coolingRate = 0.003;

        SingleTour initialSolution = new SingleTour();
        System.out.println("initial solution is " + initialSolution.getDistance());

        bestSolution = new SingleTour(initialSolution);

        while (temperature > 1) {
            temperature = temperature - coolingRate;

            SingleTour newSolution = new SingleTour(initialSolution).randomlySwapCities();

            double currentEnergy = initialSolution.getDistance();
            double newSolutionEnergy = newSolution.getDistance();

            if (newSolutionEnergyProbabilityIsHigherThanCurrentSolutionEnergy(newSolutionEnergy, currentEnergy, temperature)) {
                initialSolution = new SingleTour(newSolution);
            }
            if (bestSolutionIsLessEffectiveThan(newSolution)) {
                bestSolution = new SingleTour(newSolution);
            }
        }
    }

    private boolean bestSolutionIsLessEffectiveThan(SingleTour newSolution) {
        return newSolution.getDistance() < bestSolution.getDistance();
    }

    private boolean newSolutionEnergyProbabilityIsHigherThanCurrentSolutionEnergy(double newSolutionEnergy, double currentEnergy, double temperature) {
        if (newSolutionEnergy < currentEnergy) {
            return Boolean.TRUE;
        } else {
            return Math.exp((currentEnergy - newSolutionEnergy) / temperature) > Math.random();
        }
    }

    public SingleTour getBestSolution() {
        return bestSolution;
    }
}
