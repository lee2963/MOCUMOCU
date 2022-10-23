package MOCUMOCU.project.customize.service;

import MOCUMOCU.project.customize.entity.Customize;

import java.util.List;

public interface CustomizeService {

    void saveCustomize(Customize customize);

    void removeCustomize(Customize customize);

    Customize findCustomize(Long id);

    List<Customize> findAllComponent(String type);



}
