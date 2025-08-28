import { Link } from "react-router-dom";
import styles from './Registro.module.css'

export default function Registration() {
    return (
        <div className={styles.contenedor} >
            <img className={styles.vanished} src="/fondo_registro_recortada.png" alt="Fondo" />

            <Link to="/">
                <button className={styles.back}>Regresar</button>
            </Link>

            <section className={styles.registro}>
                <div className={styles.formulario}>
                    <h2>Registrate</h2>
                    <form>
                        <label htmlFor="nombre">Nombre: </label>
                        <input type="text" placeholder="Nombres Apellidos" />

                        <label htmlFor="email">Email: </label>
                        <input type="text" placeholder="correo@gmail.com" />

                        <label htmlFor="contraseña">Contraseña: </label>
                        <input type="password" placeholder="***********" />

                        <label htmlFor="celular">Celular: </label>
                        <input type="text" placeholder="123 456 7890" />

                        <label htmlFor="direccion">Direccion: </label>
                        <input type="text" placeholder="##########" />

                        <div className={styles.contenedor_terminos}>
                            <input className={styles.check_terminos} type="checkbox" name="acepto" value="acepto" />
                            <label className={styles.texto_terminos} for="checkbox">Aceptar términos y condiciones.</label>
                        </div>

                        <input type="submit" value="REGISTRARSE" />
                    </form>
                </div>
            </section>
        </div>
    )
}
