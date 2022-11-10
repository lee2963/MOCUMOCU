package MOCUMOCU.project.customize.service;

import MOCUMOCU.project.customize.dto.CustomizeDTO;
import MOCUMOCU.project.customize.dto.SendCustomizeDTO;
import MOCUMOCU.project.customize.entity.Customize;
import MOCUMOCU.project.customize.entity.Type;
import MOCUMOCU.project.customize.repository.CustomizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CustomizeServiceImpl implements CustomizeService{

    private final CustomizeRepository customizeRepository;

    @Override
    public void saveCustomize(Customize customize) {
        customizeRepository.save(customize);
    }

    @Override
    public void removeCustomize(Customize customize) {
        customizeRepository.remove(customize);
    }

    @Override
    public Customize findCustomize(Long id) {
        return customizeRepository.findOne(id);
    }

    @Override
    public List<SendCustomizeDTO> findAllComponent(String type) {//타입에 따라 모든 쿠폰판, 도장 불러오기
        List<Customize> customizes = new ArrayList<>();
        if(type.equals("board")){
            customizes = customizeRepository.findByType(Type.BOARD);
        } else{
            customizes = customizeRepository.findByType(Type.STAMP);
        }

        List<SendCustomizeDTO> sendCustomizeDTOS = new ArrayList<>();

        for (Customize customize : customizes) {

            SendCustomizeDTO sendCustomizeDTO = new SendCustomizeDTO();
            sendCustomizeDTO.setId(customize.getId());
            sendCustomizeDTO.setBigImageUrl(customize.getBigImageUrl());
            sendCustomizeDTO.setSmallImageUrl(customize.getSmallImageUrl());
            sendCustomizeDTO.setPoint(customize.getCustomizePoint());

            sendCustomizeDTOS.add(sendCustomizeDTO);
        }
        return sendCustomizeDTOS;
    }


}
