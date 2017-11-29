package com.zt.service.impl;

import com.zt.entity.ImageInfo;
import com.zt.repository.ImageInfoRepository;
import com.zt.service.ImageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangtong
 * Created by on 2017/11/29
 */
@Service
public class ImageInfoServiceImpl implements ImageInfoService {

    @Autowired
    private ImageInfoRepository imageInfoRepository;

    @Override
    public ImageInfo save(ImageInfo imageInfo) {
        return imageInfoRepository.save(imageInfo);
    }

    @Override
    public ImageInfo findById(Long id) {
        return imageInfoRepository.findOne(id);
    }
}
