package MOCUMOCU.project.customize.service;

import MOCUMOCU.project.customize.entity.Customize;
import MOCUMOCU.project.customize.repository.CustomizeRepository;
import com.sun.codemodel.internal.JType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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
    public List<Customize> findAllComponent(String type) { //타입에 따라 모든 쿠폰판, 도장 불러오기
        return customizeRepository.findByType(type);
    }


}
