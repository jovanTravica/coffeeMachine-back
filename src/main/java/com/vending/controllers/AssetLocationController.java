package com.vending.controllers;

import com.vending.models.Asset;
import com.vending.models.AssetLocation;
import com.vending.models.Location;
import com.vending.repository.AssetLocationRepository;
import com.vending.repository.AssetRepository;
import com.vending.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class AssetLocationController {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AssetLocationRepository assetLocationRepository;



    @GetMapping("/assetloc")
    public List<AssetLocation> getAllAssetLocations() {
        return assetLocationRepository.findAll();
    }


    @PostMapping("/assetloc")
    @Transactional
    public AssetLocation createAssetLocation(@RequestBody AssetLocation assetLocation) {

        Optional<Asset> asset = assetRepository.findAssetByCode(assetLocation.getAsset().getCode());
        Optional<Location> location = locationRepository.findLocationByCode(assetLocation.getLocation().getCode());

//        return asset;

        if(asset.isPresent() && location.isPresent())
        {
            return assetLocationRepository.findAssetLocationByCode(assetLocation.getCode())
                    .map(newAssetLocation -> {
                        newAssetLocation.setCode(assetLocation.getCode());
                        newAssetLocation.setName(assetLocation.getName());
                        newAssetLocation.setDescr(assetLocation.getDescr());
                        newAssetLocation.setDateFrom(assetLocation.getDateFrom());
                        newAssetLocation.setDateTo(assetLocation.getDateTo());
                        newAssetLocation.setAsset(asset.get());
                        newAssetLocation.setLocation(location.get());
                        return assetLocationRepository.save(newAssetLocation);
                    })
                    .orElseGet(() -> {
                        assetLocation.setAsset(asset.get());
                        assetLocation.setLocation(location.get());
                        return assetLocationRepository.save(assetLocation);
                    });
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Model doesn't exist");

    }

}
