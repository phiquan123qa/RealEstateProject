package com.vn.service;

import com.vn.model.RealEstate;

import java.util.List;

public interface RealEstateService {
    RealEstate createRealEstate(RealEstate realEstate);
    List<RealEstate> getAllRealEstates();
    RealEstate getRealEstateById(Long id);
    RealEstate updateRealEstate(Long id, RealEstate realEstate);

    List<RealEstate> getAllByTitle(String title);


}
