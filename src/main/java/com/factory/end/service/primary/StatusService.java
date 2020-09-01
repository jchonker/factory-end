package com.factory.end.service.primary;

import com.factory.end.dto.primary.StatusDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author jchonker
 * @Date 2020/8/26 17:44
 * @Version 1.0
 */
@Service
public interface StatusService {
    List<StatusDto> findAll();
}
