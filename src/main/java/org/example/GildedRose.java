package org.example;


import java.util.Arrays;

public class GildedRose {
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String[] LIST_OF_SPECIAL_ITEMS
            = {AGED_BRIE, BACKSTAGE_PASSES, SULFURAS_HAND_OF_RAGNAROS};
    private static final String QUALITY_SHOULD_NEVER_BE_NEGATIVE = "Quality should never be negative";
    public Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        for (int i = 0; i < items.length; i++) {

            if (items[i].quality < 0) {
                throw new RuntimeException(QUALITY_SHOULD_NEVER_BE_NEGATIVE);
            }
            if (!Arrays.asList(LIST_OF_SPECIAL_ITEMS)
                    .contains(items[i].name)) {
                decreaseByOneWhenItemNameNotInList(i);
            } else {
                computeNormalItems(i);
            }

            if (!items[i].name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                decreaseSellInByOneWhenItemIsNotSulfras(i);
            }

            if (items[i].sellIn < 0) {
                if (items[i].quality < 0) {
                    throw new RuntimeException(QUALITY_SHOULD_NEVER_BE_NEGATIVE);
                }

                if (!items[i].name.equals(AGED_BRIE)) {
                    if (!Arrays.asList(LIST_OF_SPECIAL_ITEMS)
                            .contains(items[i].name)) {
                        decreaseByOneWhenItemNameNotInList(i);
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        increaseByOneWhenItemNameNotInList(i);
                    }
                }
            }
        }
    }

    private void decreaseSellInByOneWhenItemIsNotSulfras(int i) {
        items[i].sellIn = items[i].sellIn - 1;
    }

    private void computeNormalItems(int i) {
        if (items[i].quality < 50) {
            increaseByOneWhenItemNameNotInList(i);
            computeBackStagePasses(i);
        }
    }

    private void computeBackStagePasses(int i) {
        if (items[i].name.equals(BACKSTAGE_PASSES)) {
            if (items[i].sellIn < 11) {
                increaseByOneWhenItemNameNotInList(i);
            }
        }
    }

    private void decreaseByOneWhenItemNameNotInList(int i) {
        items[i].quality = items[i].quality - 1;
    }

    private void increaseByOneWhenItemNameNotInList(int i) {
        items[i].quality = items[i].quality + 1;
    }
}
