package MOCUMOCU.project.customize;

import java.util.List;

public interface CustomizeService {

    void saveCustomize(Customize customize);

    void removeCustomize(Customize customize);

    Customize findCustomize(Long id);

    List<Customize> findAllComponent(String type);



}
