package com.foodlab.foodlab.repositories;

import com.foodlab.foodlab.models.Mensaje;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MensajeRepository {

    private final List<Mensaje> baseDeDatos = new ArrayList<>();

    public Mensaje save(Mensaje producto) {
        baseDeDatos.add(producto);
        return producto;
    }

    public Mensaje findByEmail(String email) {
        for (Mensaje producto : baseDeDatos) {
            if (producto.getEmail().equals(email)) {
                return producto;
            }
        }
        return null;
    }

    public List<Mensaje> findAll() {
        return new ArrayList<>(baseDeDatos);
    }

    public void deleteByEmail(String email) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getEmail().equals(email)) {
                baseDeDatos.remove(i);
                return;
            }
        }
    }

    public Mensaje update(Mensaje producto) {
        for (int i = 0; i < baseDeDatos.size(); i++) {
            if (baseDeDatos.get(i).getEmail().equals(producto.getEmail())) {
                baseDeDatos.set(i, producto);
                return producto;
            }
        }
        return null;
    }

    public List<Mensaje> buscarPorFiltros(String email, String message, String respuesta) {
        List<Mensaje> resultado = new ArrayList<>();
        for (Mensaje mensaje : baseDeDatos) {
            boolean coincemaileEmail = (email == null || mensaje.getEmail().contains(email));
            boolean coincemaileMessage = (message == null || mensaje.getMensaje().contains(message));
            boolean coincemaileRespuesta = (respuesta == null || mensaje.getRespuesta().contains(respuesta));
            if (coincemaileEmail && coincemaileMessage && coincemaileRespuesta) {
                resultado.add(mensaje);
            }
        }
        return resultado;
    }
}
