package com.orange.shop;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Recherche de boutiques Orange.
 *
 * Cette classe permet d'implémenter l'interface OrangeShopFinder.
 * Elle contient une methode qui cherche la boutique la plus proche
 * avec un stock supérieur à 0 pour un id mobile donné.
 *
 */


public class OrangeShopFinderImpl implements OrangeShopFinder {
    private FileShopReader fileShopReader;

    /**
     * un constructeur paramétré.
     * @param fileShopReader
     */
    public OrangeShopFinderImpl(FileShopReader fileShopReader) {

        this.fileShopReader = fileShopReader;
    }

    // cette méthode retourne la boutique la plus proche qui a un stock supérieur à 0 pour un id mobile donné.
    @Override
    public String findOrangeShopWithMobileAvailable(double longitude, double latitude, String nameMobile) {

        List<Line> shops = fileShopReader.setAllLine();

        return nearestShop(longitude, latitude, shops, nameMobile);
    }

    // cette methode permet de chercher la boutique la plus proche.
    private String nearestShop(@NotNull double longitude,
                               @NotNull double latitude,
                               @NotNull List<Line> shops,
                               @NotNull String nameMobile) {

        return filterInventory(shops, nameMobile).stream()
                .min(Comparator.comparingDouble(shop -> calculateDistance(
                        longitude,
                        latitude,
                        Double.parseDouble(shop.getField1()),
                        Double.parseDouble(shop.getField2()))))
                .map(Line::getField3) //
                // Il y a toujours au moins une boutique avec stock,
                // y a pas un cas où toutes les boutiques ont un stock 0 pour un tel Mobile.
                .orElse("");
    }


    // cette methode permet de calculer la distance entre le boutique orange et la position du client.
    private List<Line> filterInventory(@NotNull List<Line> shops,
                                       @NotNull String nameMobile) {

        switch (nameMobile) {
            case "sunusng":
                return shops.stream().filter(shop -> Integer.parseInt(shop.getField4()) > 0).collect(Collectors.toList());

            case "ipom":
                return shops.stream().filter(shop -> Integer.parseInt(shop.getField5()) > 0).collect(Collectors.toList());

            case "weiwei":
                return shops.stream().filter(shop -> Integer.parseInt(shop.getField6()) > 0).collect(Collectors.toList());

            default:
                // on peut ajouter Error handling lors d'une valeur inattendue,
                // mais je vais juste return une liste vide.
                return new ArrayList<>();
        }
    }

    // cette methode permet de calculer la distance entre la boutique orange et la position du client.
    private double calculateDistance(@NotNull double longitudeOrange,
                                     @NotNull double latitudeOrange,
                                     @NotNull double longitudeClient,
                                     @NotNull double latitudeClient) {

        double distLat = Math.abs(latitudeClient - latitudeOrange);
        double distLon = Math.abs(longitudeClient - longitudeOrange);

        return Math.sqrt(Math.pow(distLat, 2) + Math.pow(distLon, 2));
    }
}