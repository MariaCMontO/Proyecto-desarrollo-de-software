package service;

import java.util.List;
import modelo.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.MensajeRepository;

@Service
public class MensajeService {

    private final MensajeRepository mensajeRepository;

    @Autowired
    public MensajeService(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
        initSampleData();
    }

    private void initSampleData() {
        Mensaje mensaje1 = new Mensaje("juan43@gmail.com", "Como me registro");
        Mensaje mensaje2 = new Mensaje("ali98@gmail.com", "Como personalizo mis comidas favoritas");
        Mensaje mensaje3 = new Mensaje("karina67@gmail.com", "Muy lindas la instalaciones");
        save(mensaje1);
        save(mensaje2);
        save(mensaje3);
    }

    public Mensaje save(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    public Mensaje findByEmail(String email) {
        return mensajeRepository.findByEmail(email);
    }

    public List<Mensaje> findAll() {
        return mensajeRepository.findAll();
    }

    public Mensaje update(Mensaje mensaje) {
        return mensajeRepository.update(mensaje);
    }

    public void deleteByEmail(String email) {
        mensajeRepository.deleteByEmail(email);
    }

    public List<Mensaje> buscarPorFiltros(String email, String mensaje, String respuesta) {
        return mensajeRepository.buscarPorFiltros(email, mensaje, respuesta);
    }
}
