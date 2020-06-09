package com.vending.controllers;

import com.vending.models.Asset;
import com.vending.models.Model;
import com.vending.repository.AssetRepository;
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


    @GetMapping("/assets")
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    @PostMapping("/assets")
    @Transactional
    public Asset createAsset(@RequestBody Asset asset) {

        Optional<Model> model = modelRepository.findModelByCode(asset.getModel().getCode());

//        return asset;

        if(model.isPresent())
            {
            return assetRepository.findAssetByCode(asset.getCode())
                    .map(newModel -> {
                        newModel.setCode(asset.getCode());
                        newModel.setName(asset.getName());
                        newModel.setDescr(asset.getDescr());
                        newModel.setModel(model.get());
                        return assetRepository.save(newModel);
                    })
                    .orElseGet(() -> {
                        asset.setModel(model.get());
                        return assetRepository.save(asset);
                    });
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Model doesn't exist");

    }

}
