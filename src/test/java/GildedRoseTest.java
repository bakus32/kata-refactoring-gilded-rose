import org.example.GildedRose;
import org.example.Item;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class GildedRoseTest {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String FOO = "foo";

    @Test
    public void foo() {
        Item[] items = new Item[] { new Item(FOO, 0, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(FOO, app.items[0].name);
    }

    @Test(expected = RuntimeException.class)
    public void testShouldThrowRuntimeExceptionWhenQualityIsNegative() {
        Item[] items = new Item[] { new Item(FOO, 1, -1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
    }

    @Test
    public void testShouldDecreaseQualityByOne() {
        Item[] items = new Item[] { new Item(FOO, 1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void testShouldIncreaseQualityByOneWhenQualityIsLessThan50() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 1, 45) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(46, app.items[0].quality);
    }

    @Test
    public void testShouldIncreaseQualityByOneWhenNameIsBackStageAndLessInIs11() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 10, 45) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(47, app.items[0].quality);
    }

    @Test
    public void testShouldIncreaseQualityByOneWhenNameIsBackStageAndLessInIs6() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, 4, 45) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(47, app.items[0].quality);
    }

    @Test
    public void testShouldIncreaseSellInByOneWhenItemIsNotSulfras() {
        Item[] items = new Item[] { new Item(AGED_BRIE, 2, 45) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].sellIn);
    }

    @Test
    public void testShouldDecreaseQualityByOneAndDecreseSellInWhenNegativeSellInAndPositiveQualityAndItemNotInSpecialList() {
        Item[] items = new Item[] { new Item(FOO, -1, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    public void testShouldSubstractQualityByHimselfAndDecreseSellInWhenNegativeSellInAndPositiveQualityAndNameInSpecialListAndDifferentFromAgeBrie() {
        Item[] items = new Item[] { new Item(BACKSTAGE_PASSES, -1, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    public void testShouldIncreaseQualityAndDecreseSellInWhenNegativeSellInAndPositiveQualityAndAgeBrie() {
        Item[] items = new Item[] { new Item(AGED_BRIE, -1, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }

    @Test
    public void assertShouldDoNothingWhenItemIsSulfras() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }

}