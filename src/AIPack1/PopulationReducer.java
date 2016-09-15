package AIPack1;

/**
 *
 */
public class PopulationReducer {

    private static double PERCENT_FLIPPED = 0.10;



//    private float median;        // this is the middle of the fitness function result // not used whoops
    private Organism[] organisms;
    private boolean[] toKeep; // used in reference of the original organisms array
    private int length;



    PopulationReducer(Organism[] organisms){
        this.organisms = organisms;
        length = this.organisms.length;
        quickSort(this.organisms, 0, length - 1);
//        median = this.organisms[length / 2 - 1].getFitnessFunction();
        toKeep = new boolean[length];
    }

    // NOTE: there are two reduce functions (both should work, choose whichever)


    // linear probability function (if there's 100 organisms, 100% for best surviving, 99% for second best, etc..)
    private Organism[] reduce(){
        int probability;    // percentage talked about ^
        int keepLength = 0; // count for length of how many organisms survive

        // figures out whether to keep an organism
        for(int i = 0; i < length; i ++){
            probability = 100 * (length - i) / length;
            if(100 * Math.random() < probability) {
                toKeep[i] = true;
                keepLength++;
            }
            else toKeep[i] = false;
        }

        // moves the list of organisms to keep over to a final array
        return makeFinalList(keepLength);
    }


    // split organisms into 2 sections, give probability% chance of survival for lower half, probability% death for upper half
    private Organism[] reduce2(){
        int keepProbability = (int)(100 * PERCENT_FLIPPED);
        int keepLength = 0;


        for(int i = 0; i < length / 2; i++){        // to keep (from worse half)
            if(100 * Math.random() < keepProbability){
                toKeep[i] = true;
                keepLength++;
            } else toKeep[i] = false;
        }
        for(int i = length / 2; i < length; i++){   // to keep (from better half)
            if(100 * Math.random() < 100 - keepProbability){
                toKeep[i] = true;
                keepLength++;
            } else toKeep[i] = false;
        }

        // moves the list of organisms to keep over to a final array
        return makeFinalList(keepLength);
    }


    private Organism[] makeFinalList(int finalLength){
        Organism[] keptOrganisms = new Organism[finalLength];
        int count = 0;
        for(int i = 0; i < toKeep.length; i++){
            if(toKeep[i]){
                keptOrganisms[count] = organisms[i];
                count++;
            }
        }
        return keptOrganisms;
    }


    // https://en.wikipedia.org/wiki/Quicksort
    private void quickSort(Organism[] toSort, int high, int low){
        if(low < high){
            int pivot = partition(toSort, low, high);
            quickSort(toSort, low, pivot - 1);
            quickSort(toSort, pivot + 1, high);
        }
    }
    private int partition(Organism[] toSort, int high, int low){
        float pivot = toSort[high].getFitnessFunction();
        int i = low;
        for(int j = low; j < high - 1; j++){
            if(toSort[j].getFitnessFunction() <= pivot){
                swap(toSort, i, j);
                i++;
            }
        }
        swap(toSort, i, high);
        return i;
    }
    private void swap(Organism[] toSort, int first, int second){
        Organism temp = toSort[first];
        toSort[first] = toSort[second];
        toSort[second] = temp;
    }



}
