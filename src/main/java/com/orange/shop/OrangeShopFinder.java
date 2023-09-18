package com.orange.shop;

/**
 * Recherche de boutiques Orange.
 * 
 * Le point d'entrée doit implémenter cette interface.
 * 
 * 
 */
public interface OrangeShopFinder {

	String findOrangeShopWithMobileAvailable(double longitude, double latitude, String nameMobile);
}
