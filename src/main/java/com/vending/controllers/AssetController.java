package com.vending.controllers;

import com.vending.models.Asset;
import com.vending.models.Location;
import com.vending.models.Model;
import com.vending.repository.AssetRepository;
import com.vending.repository.LocationRepository;
import com.vending.repository.ModelRepository;
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
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private LocationRepository locationRepository;


    @GetMapping("/assets")
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @DeleteMapping("/assets/{id}")
    @Transactional
    void deleteAsset(@PathVariable Long id) {
        assetRepository.deleteById(id);
    }


    @PostMapping("/assets")
    @Transactional
    public Asset  createAsset(@RequestBody Asset asset) {
        if(assetRepository.findAssetByCode(asset.getCode()).isPresent())
            throw  new ResponseStatusException(HttpStatus.CONFLICT, "Code already exists");

        else
            return assetRepository.save(asset);


    }

    @PutMapping("/assets")
    @Transactional
    public Asset updateAsset(@RequestBody Asset asset) {

        Optional<Model> model = modelRepository.findModelByCode(asset.getModel().getCode());

        Optional<Location> location = locationRepository.findLocationByCode(asset.getLocation().getCode());

        if(model.isPresent())
        {
            return assetRepository.findAssetByCode(asset.getCode())
                    .map(newModel -> {
                        newModel.setCode(asset.getCode());
                        newModel.setName(asset.getName());
                        newModel.setDescr(asset.getDescr());
                        newModel.setModel(model.get());
                        newModel.setLocation(location.get());
                        return assetRepository.save(newModel);
                    })
                    .orElseGet(() -> {
                        asset.setModel(model.get());
                        return assetRepository.save(asset);
                    });
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Asset doesn't exist");

    }

}
