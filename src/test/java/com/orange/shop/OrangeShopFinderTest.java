package com.orange.shop;

import org.junit.*;
import static org.junit.Assert.assertEquals;


public class OrangeShopFinderTest {

    private OrangeShopFinder orangeShopFinder;

    @Before
    public void setup() {
        FileShopReader fileReader = new FileShopReader();
        orangeShopFinder = new OrangeShopFinderImpl(fileReader);
    }

    /**
     * use Case pour un type de Mobile ipom
     */
    @Test
    public void findOrangeShopWithMobileAvailable() {
        String foundShop;
        foundShop = orangeShopFinder.findOrangeShopWithMobileAvailable(0.36799, 43.29456, "ipom");

        assertEquals(foundShop, "[Orange] 64 Pau (Rue Louis Barthou)");
    }

    /**
     * use Case pour un type de Mobile sunusng
     */
    @Test
    public void findOrangeShopWithMobileAvailableTypeSunusng() {
        String foundShop;
        foundShop = orangeShopFinder.findOrangeShopWithMobileAvailable(5.05375, 44.05591, "sunusng");

        assertEquals(foundShop, "[Orange] 84 Carpentras (67 Avenue du Mont Ventoux)");
    }

    /**
     * use Case pour un type de Mobile weiwei
     */
    @Test
    public void findOrangeShopWithMobileAvailableTypeWeiwei() {
        String foundShop;
        foundShop = orangeShopFinder.findOrangeShopWithMobileAvailable(-1.51816, 43.49508, "weiwei");

        assertEquals(foundShop, "[Orange] 64 Anglet (Avenue Belle Marion)");
    }

    /**
     * use Case pour un type de Mobile qui n'existe pas dans la Data
     */
    @Test
    public void findOrangeShopWithMobileUnsupportedBrand() {
        String foundShop;
        foundShop = orangeShopFinder.findOrangeShopWithMobileAvailable(-1.46916, 47.18722, "xyz");

        assertEquals(foundShop, "");
    }
}
