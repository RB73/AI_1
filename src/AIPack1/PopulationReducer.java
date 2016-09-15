package AIPack1;


import java.util.ArrayList;

/**
 *
 */
public class PopulationReducer {

    private static double PERCENT_FLIPPED = 0.10;



//    private float median;        // this is the middle of the fitness function result // not used whoops
    private ArrayList<Organism> organisms;
    private int length;



    PopulationReducer(ArrayList<Organism> organisms){
        this.organisms = organisms;
        length = this.organisms.size();
        quickSort(this.organisms, 0, length - 1);
//        median = this.organisms[length / 2 - 1].getFitnessFunction();
    }

    // NOTE: there are two reduce functions (both should work, choose whichever)


    // linear probability function (if there's 100 organisms, 100% for best surviving, 99% for second best, etc..)
    public ArrayList<Organism> reduce(){
        int probability;    // percentage talked about ^
        int keepLength = 0; // count for length of how many organisms survive

        ArrayList<Organism> finalList = new ArrayList<Organism>();

        // figures out whether to keep an organism
        for(int i = 0; i < length; i ++){
            probability = 100 * (length - i) / length;
            if(100 * Math.random() < probability)
                finalList.add(organisms.get(i));
        }

        return finalList;
    }


    // split organisms into 2 sections, give probability% chance of survival for lower half, probability% death for upper half
    public ArrayList<Organism> reduce2(){
        int keepProbability = (int)(100 * PERCENT_FLIPPED);
        int keepLength = 0;

        ArrayList<Organism> finalList = new ArrayList<Organism>();

        for(int i = 0; i < length / 2; i++){        // to keep (from worse half)
            if(100 * Math.random() < keepProbability)
                finalList.add(organisms.get(i));
        }
        for(int i = length / 2; i < length; i++){   // to keep (from better half)
            if(100 * Math.random() < 100 - keepProbability)
                finalList.add(organisms.get(i));
        }

        return finalList;
    }


    // https://en.wikipedia.org/wiki/Quicksort
    private void quickSort(ArrayList<Organism> toSort, int high, int low){
        if(low < high){
            int pivot = partition(toSort, low, high);
            quickSort(toSort, low, pivot - 1);
            quickSort(toSort, pivot + 1, high);
        }
    }
    private int partition(ArrayList<Organism> toSort, int high, int low){
        float pivot = toSort.get(high).getFitnessFunction();
        int i = low;
        for(int j = low; j < high - 1; j++){
            if(toSort.get(j).getFitnessFunction() <= pivot){
                swap(toSort, i, j);
                i++;
            }
        }
        swap(toSort, i, high);
        return i;
    }
    private void swap(ArrayList<Organism> toSort, int first, int second){
        Organism temp = toSort.get(first);
        toSort.set(first, toSort.get(second));  // does this work or is it just pointers
        toSort.set(second, temp);
    }



}
