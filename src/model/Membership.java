package model;
/**
 * Angiver typen af medlemskab for et medlem.
 */
public interface Membership {
    /**
     * Når implementeret skal denne metode fortælle om medlemmernes medlemskab er aktivt(true) eller passivt(false).
     * @return En boolean.
     */
    boolean membership();
}
