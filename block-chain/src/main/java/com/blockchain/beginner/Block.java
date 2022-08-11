package main.java.com.blockchain.beginner;

import java.util.Arrays;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 12/14/21 2:53 PM
 */

public class Block {

    private String[] transactions;
    private int blockHash;
    private int previousBlockHash;

    public Block(String[] transactions, int previousBlockHash) {
        super();
        this.transactions = transactions;
        this.previousBlockHash = previousBlockHash;
        this.blockHash = Arrays.hashCode(new int[]{Arrays.hashCode(transactions), this.previousBlockHash});
    }

    public String[] getTransactions() {
        return transactions;
    }

    public void setTransactions(String[] transactions) {
        this.transactions = transactions;
    }

    public int getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(int blockHash) {
        this.blockHash = blockHash;
    }

    public int getPreviousBlockHash() {
        return previousBlockHash;
    }

    public void setPreviousBlockHash(int previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }

    @Override
    public String toString() {
        return "NewBlock{" +
                "transactions=" + Arrays.toString(transactions) +
                ", blockHash=" + blockHash +
                ", previousBlockHash=" + previousBlockHash +
                '}';
    }
}
