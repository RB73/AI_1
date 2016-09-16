package AIPack1;

import java.util.ArrayList;

/**
 *
 */
public class PopulationReducer {

    private static double PERCENT_FLIPPED = 0.10;



//    private float median;        // this is the middle of the fitness function result // not used whoops
    public ArrayList<Organism> organisms;
    private int length;



    PopulationReducer(ArrayList<Organism> organisms){
        length = organisms.size();
        this.organisms = new ArrayList<Organism>();
        for(Organism o : organisms)
            (this.organisms).add(o);
//
//        System.out.println("These are the generations before sorting");
//        for(int i = 0; i < length; i++){
//            System.out.print(i + ": " );
//            for(int j = 0; j < organisms.get(i).getSize(); j++){
//                System.out.print("| " + organisms.get(i).getOperations().get(j));
//            }
//            System.out.println("");
//        }
//
        quickSort(this.organisms, 0, length - 1);
//        median = this.organisms[length / 2 - 1].getFitnessFunction();
    }

    // NOTE: there are two reduce functions (both should work, choose whichever)


    // linear probability function (if there's 100 organisms, 100% for best surviving, 99% for second best, etc..)
    public ArrayList<Organism> reduce(){
        int probability;    // percentage talked about ^
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
        ArrayList<Organism> finalList = new ArrayList<Organism>();

        for(int i = 5; i < length / 2; i++){        // to keep (from better half)
            if(100 * Math.random() < 100 - keepProbability)
                finalList.add(organisms.get(i));
        }

        for(int i = length / 2; i < length - 5; i++){   // to keep (from worse half)
            if(100 * Math.random() < keepProbability)
                finalList.add(organisms.get(i));
        }

        for(int i = 0; i < 5; i++)          // make sure the best 5 are kept
            finalList.add(organisms.get(i));

        return finalList;
    }

    // heavily referenced http://www.algolist.net/Algorithms/Sorting/Quicksort
    private void quickSort(ArrayList<Organism> toSort, int low, int high){
        if(low < high){
            int pivot = partition(toSort, low, high);
            if(low < pivot - 1)
                quickSort(toSort, low, pivot - 1);
            if(high > pivot)
                quickSort(toSort, pivot, high);
        }
    }
    private int partition(ArrayList<Organism> toSort, int low, int high){
        int i = low, j = high;
        Organism temp;
        float pivot = toSort.get((low + high) / 2).getFitnessFunction();
        while(i <= j){
            while(toSort.get(i).getFitnessFunction() < pivot)
                i++;
            while(toSort.get(j).getFitnessFunction() > pivot)
                j--;
            if(i <= j){
                temp = toSort.get(i);
                toSort.set(i, toSort.get(j));
                toSort.set(j, temp);
                i++;
                j--;
            }
        }
        return i;
    }


}
