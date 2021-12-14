package main.java.com.blockchain.beginner;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 12/14/21 1:10 PM
 */

public class BlockChainIntro {

    public static void main(String[] args) {

//        demoHashcodeMethod();
//        hashAnArray();
        demoSeriesOfBlocks();

    }

    /**
     * Demonstrate a Series of Blocks
     */
    private static void demoSeriesOfBlocks() {

        ArrayList<Block> blockchain = new ArrayList<Block>();

        /**
         * block one
         * */
        String[] initValues = {"Introduction to Blockchain", "Series of Blockchain"};
        Block block1 = new Block(initValues, 0);
        blockchain.add(block1);
        System.out.println("Block 1: " + block1);
//        System.out.println("Blockchain : " + blockchain);

        /**
         * block two
         * */
        initValues = new String[]{"Introduction to Blockchain", "Series of Blockchain 2"};
        Block block2 = new Block(initValues, block1.getBlockHash());
        blockchain.add(block2);
        System.out.println("Block 2: " + block2);
//        System.out.println("Blockchain : " + blockchain);

        /**
         * block two
         * */
        initValues = new String[]{"Introduction to Blockchain", "Series of Blockchain 3"};
        Block block3 = new Block(initValues, block2.getBlockHash());
        blockchain.add(block3);
        System.out.println("Block 3: " + block3);
        System.out.println("Blockchain : " + blockchain);

    }

    /**
     * Hash an array
     */
    private static void hashAnArray() {

        String[] stringListOne = {"one", "two", "three"};
        String[] stringListTwo = {"one", "two", "three"};
        String[] stringListThree = {"one", "two", "Three"};

        int hashcodeOne = Arrays.hashCode(stringListOne);
        int hashcodeTwo = Arrays.hashCode(stringListTwo);
        int hashcodeThree = Arrays.hashCode(stringListThree);
        System.out.println("List 1: " + Arrays.toString(stringListOne) + " hashcode 1: " + hashcodeOne);
        System.out.println("List 2: " + Arrays.toString(stringListTwo) + " hashcode 2: " + hashcodeTwo);
        System.out.println("List 3: " + Arrays.toString(stringListThree) + " hashcode 3: " + hashcodeThree);

    }

    /**
     * Demonstrate a Hashcode method
     */
    private static void demoHashcodeMethod() {

        String treasure = "Introduction to BlockChain";
        int hashcode = treasure.hashCode();
        System.out.println("Treasure 1: " + treasure + " Hash 1: " + hashcode);
    }
}
